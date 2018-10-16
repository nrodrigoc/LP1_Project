/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    private static Tabuleiro tabuleiro;
    private int jogador;
    private boolean movimentoPermitido;
    public Jogo() {
        tabuleiro = new Tabuleiro();
        criarPecas();
        jogador = 0;
    }
    
    /**
     * Posiciona pecas no tabuleiro.
     * Utilizado na inicializa�ao do jogo.
     */
    private void criarPecas() {
        //Cria objetos das subclasses com o tipo da classe mãe
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 2; j++) {
                if(j == 0 && (i == 0 || i == 7)) {
                    Casa casa = tabuleiro.getCasa(i,j);
                    Torre peca = new Torre(casa, Peca.TORRE_BRANCO);
                }
                else if(j == 1){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peao peca = new Peao(casa, Peca.PEAO_BRANCO);
                }
                else if(j == 0 && (i == 1 || i == 6)){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Cavalo peca = new Cavalo(casa, Peca.CAVALO_BRANCO);
                }
                else if(j == 0 && (i == 2 || i == 5)){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Bispo peca = new Bispo(casa, Peca.BISPO_BRANCO);
                }
                else if(j == 0 && i == 4){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Rei peca = new Rei(casa, Peca.REI_BRANCO);
                }
                else if(j == 0 && i == 3){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Rainha peca = new Rainha(casa, Peca.RAINHA_BRANCO);
                }
            }
        }
        
        for(int i = 0; i < 8; i++) {
            for(int j = 7; j > 5; j--) {
                if(j == 7 && (i == 0 || i == 7)) {
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Torre(casa, Peca.TORRE_PRETO);
                }
                else if(j == 6){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Peao(casa, Peca.PEAO_PRETO);
                }
                else if(j == 7 && (i == 1 || i == 6)){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Cavalo(casa, Peca.CAVALO_PRETO);
                }
                else if(j == 7 && (i == 2 || i == 5)){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Bispo(casa, Peca.BISPO_PRETO);
                }
                else if(j == 7 && i == 4){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Rei(casa, Peca.REI_PRETO);
                }
                else if(j == 7 && i == 3){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Rainha(casa, Peca.RAINHA_PRETO);
                }
            }
        }
    }
    
    /**
     * Comanda uma Pe�a na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
     * 
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        peca.mover(destino);
        
        if(destino.getPeca() != null){
            mudarJogador(origemX, origemY, destinoX, destinoY);
        }
        
        if(destino.getPeca().getTipo() == Peca.TORRE_BRANCO 
        || destino.getPeca().getTipo() == Peca.TORRE_PRETO){
            if(podeRoque(destino)){
                int x = 0;
                int y = 0;
                
                x = destino.getX();
                y = destino.getY();
                if(noLimite(x+1, y) && destino.getX() > origem.getX()){
                    Casa casa1 = tabuleiro.getCasa(x+1,y);
                    
                    if(casa1.possuiPeca() && 
                    (casa1.getPeca().getTipo() == Peca.REI_BRANCO || casa1.getPeca().getTipo() == Peca.REI_PRETO) && 
                    casa1.getPeca().getMovimento() == true){
                        roque(casa1, destino);
                    }
                                      
                }else if(noLimite(x-1, y) && destino.getX() < origem.getX()){
                    Casa casa2 = tabuleiro.getCasa(x-1,y);
        
                    if(casa2.possuiPeca() &&
                    (casa2.getPeca().getTipo() == Peca.REI_BRANCO || casa2.getPeca().getTipo() == Peca.REI_PRETO) &&
                    casa2.getPeca().getMovimento() == true){
                        roque(casa2, destino);
                    }
                
                }
                
                
            }
            
        }
    }
    
    /**
     * Comanda uma Peca na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
     * 
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void capturarPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        peca.capturar(destino);
        if(destino.getPeca() != null){
            mudarJogador(origemX, origemY, destinoX, destinoY);
        }
        continuarJogador(origemX, origemY, destinoX, destinoY);
    }
    
    public static boolean possuiP(int x, int y){
        Casa c1 = tabuleiro.getCasa(x,y);
        if(c1.possuiPeca()){
            return true;
        }else if(!c1.possuiPeca()){
            return false;
        }
        return false;
    }
    
    public int getJogador(){
        return jogador;
    }
    
     public boolean jogador(int origemX, int origemY){
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Peca peca = origem.getPeca();
        if(peca.getTipoGeral() == 12 && jogador == 0 || peca.getTipoGeral() == 13 && jogador == 1){
            movimentoPermitido = true;
            return movimentoPermitido;
        }
        else if(peca.getTipoGeral() == 12 && jogador == 1 || peca.getTipoGeral() == 13 && jogador == 0){
            movimentoPermitido = false;
            return movimentoPermitido;
        }
        return false;
    }
    
    private void mudarJogador(int origemX, int origemY, int destinoX, int destinoY){
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = destino.getPeca();
        if(peca.getTipoGeral() == 12 && jogador == 0){
            jogador = 1;
        }
        else{
            jogador = 0;
        }
    }
    
    private void continuarJogador(int origemX, int origemY, int destinoX, int destinoY){
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = destino.getPeca();
        if(origem.getX() == destino.getX() && origem.getY() == destino.getY()){
           if(peca.getTipoGeral() == 12 && jogador == 1){
                jogador = 0;
            }
           else  if(peca.getTipoGeral() == 13 && jogador == 0){
               jogador = 1;
           }
        }
    }
   
    /**
     * @return true se as casas estao no limite do tabuleiro
     */
    public boolean noLimite(int x, int y){
        if(x >= 0 && x <= 7 && y >= 0 && y <= 7){
            return true;
        }
        return false;
    }
    
    
    /**
     * Realiza o movimento roque
     */
    public void roque(Casa crei, Casa ctorre){
        
        Peca rei = crei.getPeca();
        Peca torre = ctorre.getPeca();
        crei.removerPeca();
        
        if(ctorre.getX() == 3){
            crei = tabuleiro.getCasa(crei.getX()-2, crei.getY());
        }else if(ctorre.getX() == 5){
            crei = tabuleiro.getCasa(crei.getX()+2, crei.getY());
        }
       
        crei.colocarPeca(rei);
        rei.primeiroMovimento = false;
    }
    
    
    /**
     * @return se o movimento roque pode ser feito
     * 
     */
    
    public boolean podeRoque(Casa casa1){
        int x = 0;
        int y = 0;
        if(casa1.getPeca().getTipo() == Peca.TORRE_BRANCO || 
        casa1.getPeca().getTipo() == Peca.TORRE_PRETO/*&& casa1.getPeca().getMovimento() == true*/){
            x = casa1.getX();
            y = casa1.getY();
            Casa casa2 = tabuleiro.getCasa(x+1,y);
            if(casa2.possuiPeca() && (casa2.getPeca().getTipo() == Peca.REI_BRANCO ||
            casa2.getPeca().getTipo() == Peca.REI_PRETO)){
                return true;
            }
        
            Casa casa3 = tabuleiro.getCasa(x-1,y);
            if(casa3.possuiPeca() && (casa3.getPeca().getTipo() == Peca.REI_BRANCO ||
            casa3.getPeca().getTipo() == Peca.REI_PRETO)){
                return true;
            }
            
            
        }
            
        return false;
    }
    
    
    
    
    
    
    /**
     * @return o Tabuleiro em jogo.
     */
    public static Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
