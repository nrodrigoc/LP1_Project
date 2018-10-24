import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    public static Tabuleiro tabuleiro;
    private int jogador;
    private boolean movimentoPermitido;
    public static ArrayList<Peca> peoes; 
    
    public Jogo() {
        peoes = new ArrayList<>();
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
                    peoes.add(peca);
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
                    peoes.add(peca);
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
        
        if(destino.possuiPeca()){
            mudarJogador(origemX, origemY, destinoX, destinoY);        
            //Movimento Roque
            if(podeRoque(destino) && destino.getX() > origem.getX()){
                Casa casaRei = tabuleiro.getCasa(destino.getX()+1, destino.getY());
                roque(casaRei, destino);
                
            }else if(podeRoque(destino) && destino.getX() < origem.getX()){
                Casa casaRei = tabuleiro.getCasa(destino.getX()-1,destino.getY());
                roque(casaRei, destino);
                
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
        if(destino.possuiPeca() && (destino.getX() != origem.getX() || destino.getY() != origem.getY())){
            mudarJogador(origemX, origemY, destinoX, destinoY);
        }
        /*else{
            continuarJogador(origemX, origemY, destinoX, destinoY);
        }*/
    }
    
    /**@param x linha
     * @param y coluna
     * @return se há uma peca na posicao solicitada 
     */
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
    
    /**
     * Troca o jogador da vez
     */
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
    
    /*
    /**
     * Mantem a rodada no mesmo jogador
     */
    /*private void continuarJogador(int origemX, int origemY, int destinoX, int destinoY){
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
    }*/
   
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
    
    public boolean podeRoque(Casa casaTorre){
        int x = 0;
        int y = 0;
        if(casaTorre.getPeca() instanceof Torre){
            x = casaTorre.getX();
            y = casaTorre.getY();
            
            if(noLimite(x+1, y)){
                Casa casaRei = tabuleiro.getCasa(x+1,y);
                if(casaRei.possuiPeca() && (casaRei.getPeca() instanceof Rei) && casaRei.getPeca().getMovimento() == true){
                    return true;
                }
            }
        
            if(noLimite(x-1, y)){
                Casa casaRei2 = tabuleiro.getCasa(x-1,y);
                if(casaRei2.possuiPeca() && (casaRei2.getPeca() instanceof Rei) && casaRei2.getPeca().getMovimento() == true){
                    return true;
                }
            }  
                        
        }
            
        return false;
    }
    
    public void promocao(int origemX, int origemY, int destinoX, int destinoY){
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = destino.getPeca();
        Object[] pecas = {"Torre", "Bispo", "Cavalo", "Rainha"};
        if(peca != null && peca.getTipo() == 1 && destino.getY() == 7){
            Object selecionarPecasB = JOptionPane.showOptionDialog(null, "Escolha uma peca", "selectionValues",JOptionPane.DEFAULT_OPTION, 
                                            JOptionPane.INFORMATION_MESSAGE, null,pecas, pecas[0]);
            if(selecionarPecasB.equals(3)){
                destino.removerPeca();
                Peca rainha = new Rainha(destino, Peca.RAINHA_BRANCO);
            }
            else if(selecionarPecasB.equals(2)){
                destino.removerPeca();
                Peca cavalo = new Cavalo(destino, Peca.CAVALO_BRANCO);
            }
            else if(selecionarPecasB.equals(1)){
                destino.removerPeca();
                Peca bispo = new Bispo(destino, Peca.BISPO_BRANCO);
            }
            else if(selecionarPecasB.equals(0)){
                destino.removerPeca();
                Peca torre = new Torre(destino, Peca.TORRE_BRANCO); 
            }
            else if(selecionarPecasB == null){
                JOptionPane.showMessageDialog(null, "Você irá continuar com o peão");
            }
        }
        else if(peca != null && peca.getTipo() == 7 && destino.getY() == 0){
            Object selecionarPecasP = JOptionPane.showOptionDialog(null, "Escolha uma peca", "selectionValues",JOptionPane.DEFAULT_OPTION, 
                                            JOptionPane.INFORMATION_MESSAGE, null,pecas, pecas[0]);
            if(selecionarPecasP.equals(3)){
                destino.removerPeca();
                Peca rainha = new Rainha(destino, Peca.RAINHA_PRETO);
            }
            else if(selecionarPecasP.equals(2)){
                destino.removerPeca();
                Peca cavalo = new Cavalo(destino, Peca.CAVALO_PRETO);
            }
            else if(selecionarPecasP.equals(1)){
                destino.removerPeca();
                Peca bispo = new Bispo(destino, Peca.BISPO_PRETO);
            }
            else if(selecionarPecasP.equals(0)){
                destino.removerPeca();
                Peca torre = new Torre(destino, Peca.TORRE_PRETO); 
            }
            else if(selecionarPecasP == null){
                JOptionPane.showMessageDialog(null, "Você irá continuar com o peão");
            }
        }
    }
    
    public boolean check(int destinoX, int destinoY){
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = destino.getPeca();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Casa casa = tabuleiro.getCasa(i,j);
                if(casa.possuiPeca() && ((casa.getPeca().getTipo() == 5 && jogador == 0) || (casa.getPeca().getTipo() == 11 && jogador == 1))){
                    //verifica o lado direito
                    for(int k = i+1; k < 8; k++){
                        Casa casaAdversaria = tabuleiro.getCasa(k,j);
                        if(casaAdversaria.getPeca() == null){
                            continue;
                        }
                        else if(casaAdversaria.getPeca().getTipoGeral() == casa.getPeca().getTipoGeral()){
                             break;
                        }
                        else{
                                if(casaAdversaria.getPeca() instanceof Torre /*|| (casaAdversaria.getPeca() instanceof Rainha)*/){
                                    peca.possibilidades(casaAdversaria, destino);
                                    if(peca.movimentosPossiveis.contains(casaAdversaria)){
                                        JOptionPane.showMessageDialog(null, "check");
                                        return true;
                                    }
                                }
                                else{
                                    break;
                                }
                        }
                    }
                    //verifica o lado esquerdo
                    for(int k = i-1; k >= 0; k--){
                        Casa casaAdversaria = tabuleiro.getCasa(k,j);
                        if(casaAdversaria.getPeca() == null){
                            continue;
                        }
                        else if(casaAdversaria.getPeca().getTipoGeral() == casa.getPeca().getTipoGeral()){
                             break;
                        }
                        else{
                                if(casaAdversaria.getPeca() instanceof Torre /*|| (casaAdversaria.getPeca() instanceof Rainha)*/){
                                    peca.possibilidades(casaAdversaria, destino);
                                    if(peca.movimentosPossiveis.contains(casa)){
                                        JOptionPane.showMessageDialog(null, "check");
                                        return true;
                                    }
                                }
                                else{
                                    break;
                                }
                        }
                    }
                    //verifica para cima
                    for(int l = j+1; l < 8; l++){
                        Casa casaAdversaria = tabuleiro.getCasa(i,l);
                        if(casaAdversaria.getPeca() == null){
                            continue;
                        }
                        else if(casaAdversaria.getPeca().getTipoGeral() == casa.getPeca().getTipoGeral()){
                             break;
                        }
                        else{
                                if(casaAdversaria.getPeca() instanceof Torre /*|| (casaAdversaria.getPeca() instanceof Rainha)*/){
                                    peca.possibilidades(casaAdversaria, destino);
                                    if(peca.movimentosPossiveis.contains(casa)){
                                        JOptionPane.showMessageDialog(null, "check");
                                        return true;
                                    }
                                }
                                else{
                                    break;
                                }
                        }
                    }
                    //verifica para baixo
                    for(int l = j-1; l >= 0; l--){
                        Casa casaAdversaria = tabuleiro.getCasa(i,l);
                        if(casaAdversaria.getPeca() == null){
                            continue;
                        }
                        else if(casaAdversaria.getPeca().getTipoGeral() == casa.getPeca().getTipoGeral()){
                             break;
                        }
                        else{
                                if(casaAdversaria.getPeca() instanceof Torre /*|| (casaAdversaria.getPeca() instanceof Rainha)*/){
                                    peca.possibilidades(casaAdversaria, destino);
                                    if(peca.movimentosPossiveis.contains(casa)){
                                        JOptionPane.showMessageDialog(null, "check");
                                        return true;
                                    }
                                }
                                else{
                                    break;
                                }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * retira o passant depois de uma rodada
     */
    
    public static void removePassant(){
        for(Peca peao: peoes){
            peao.setPassant(false);
        }
    }    
    
    
    /**
     * @return o Tabuleiro em jogo.
     */
    public static Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
