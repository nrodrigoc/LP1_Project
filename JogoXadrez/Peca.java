import java.util.ArrayList;

/**
 * Representa uma Peca do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public  class Peca {
    
    public static final int BISPO_BRANCO = 0;
    public static final int PEAO_BRANCO = 1;
    public static final int TORRE_BRANCO = 2;
    public static final int CAVALO_BRANCO = 3;
    public static final int RAINHA_BRANCO = 4;
    public static final int REI_BRANCO = 5;
    public static final int BISPO_PRETO = 6;
    public static final int PEAO_PRETO = 7;
    public static final int TORRE_PRETO = 8;
    public static final int CAVALO_PRETO = 9;
    public static final int RAINHA_PRETO = 10;
    public static final int REI_PRETO = 11;
    
    public static final int PECAS_BRANCAS = 12;
    public static final int PECAS_PRETAS = 13;

    protected Casa casa;
    protected int tipo;
    private int tipoGeral;
    
    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
        //pecas = new ArrayList<Peca>();
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
    }
    
    /**
     * Faz a captura de uma peça.
     * @param destino nova casa que irá conter esta peca.
     * @param capturada a casa que conter a peça que será capturada.
     */
    public void capturar(Casa destino) {
        if(destino.possuiPeca() && (getTipoGeral() != destino.getPeca().getTipoGeral())){
            casa.removerPeca();
            destino.removerPeca();
            destino.colocarPeca(this);
            casa = destino;
        }
    }
    
    /**
     * Valor    Tipo
     *   0   Branca (Bispo)
     *   1   Branca (Peao)
     *   2   Branca (Torre)
     *   3   Branca (Cavalo)
     *   4   Branca (Rainha)
     *   5   Branca (Rei)
     *   6   Preto (Bispo)
     *   7   Preto (Peao)
     *   8   Preto (Torre)
     *   9   Preto (Cavalo)
     *   10  Preto (Rainha)
     *   11  Preto (Rei)
     * @return o tipo da peca.
     */
    public int getTipo() {
        return tipo;
    }
    
    /**
     * Valor       Tipo
     *  12     Peças Brancas 
     *  13     Peças Pretas
     */
    public int getTipoGeral() {
        if(getTipo() == 0 || getTipo() == 1 || getTipo() == 2 || getTipo() == 3 || getTipo() == 4 || getTipo() == 5){
            tipoGeral = 12;
        }
        else{
            tipoGeral = 13;
        }
        return tipoGeral;
    }
}