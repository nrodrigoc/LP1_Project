
/**
 * Representa uma Peca do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Peca {

    public static final int PEDRA_BRANCA = 0;
    public static final int DAMA_BRANCA = 1;
    public static final int PEDRA_VERMELHA = 2;
    public static final int DAMA_VERMELHA = 3;
    
    private Casa casa;
    private int tipo;

    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
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
     * @return time da Peca selecionada
     * 
     */
    public int getCor(Peca peca) {
        
        if (peca.getTipo() == PEDRA_BRANCA || peca.getTipo() == DAMA_BRANCA) {
            return CasaGUI.PECA_BRANCA;
        }
        else if (peca.getTipo() == PEDRA_VERMELHA || peca.getTipo() == DAMA_VERMELHA) {
             return CasaGUI.PECA_VERMELHA;
        }
        else{
            return CasaGUI.SEM_PECA;
        }
    }
    
     /**
     * @return time inimigo da Peca selecionada
     * 
     */
    public int getInimiga(Peca peca) {
        
        if (peca.getTipo() == PEDRA_BRANCA || peca.getTipo() == DAMA_BRANCA) {
            return CasaGUI.PECA_VERMELHA;
        }
        else if (peca.getTipo() == PEDRA_VERMELHA || peca.getTipo() == DAMA_VERMELHA) {
             return CasaGUI.PECA_BRANCA;
        }
        else{
            return CasaGUI.SEM_PECA;
        }
    }
    
    /**
     * Valor    Tipo
     *   0   Branca (Pedra)
     *   1   Branca (Dama)
     *   2   Vermelha (Pedra)
     *   3   Vermelha (Dama)
     * @return o tipo da peca.
     */
    public int getTipo() {
        return tipo;
    }
}
