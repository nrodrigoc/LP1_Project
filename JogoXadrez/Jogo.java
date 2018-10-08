/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    private static Tabuleiro tabuleiro;
    
    
    public Jogo() {
        tabuleiro = new Tabuleiro();
        criarPecas();
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
                    Peca peca = new Torre(casa, Peca.TORRE_BRANCO);
                }
                else if(j == 1){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Peao(casa, Peca.PEAO_BRANCO);
                }
                else if(j == 0 && (i == 1 || i == 6)){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Cavalo(casa, Peca.CAVALO_BRANCO);
                }
                else if(j == 0 && (i == 2 || i == 5)){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Bispo(casa, Peca.BISPO_BRANCO);
                }
                else if(j == 0 && i == 4){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Rei(casa, Peca.REI_BRANCO);
                }
                else if(j == 0 && i == 3){
                    Casa casa = tabuleiro.getCasa(i,j);
                    Peca peca = new Rainha(casa, Peca.RAINHA_BRANCO);
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
    
    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}