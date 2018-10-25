import java.util.ArrayList;
/**
 * Representa uma Peca do jogo.
 * Possui uma casa e um tipo associado.
 *
 * @author Luciane Falcao
 * @author Nathan Rodrigo
 * 
 */
public abstract class Peca {
    
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
    
    protected Casa origem;
    protected Casa casa;
    protected int tipo;
    protected ArrayList<Casa> movimentosPossiveis;


    private int tipoGeral;
    //enPassant - guarda "true" caso o Peao esteja em "en Passant"
    protected boolean enPassant;
    //primeiroMovimento - guarda "true" a peca ainda nao se movimentou na partida
    public boolean primeiroMovimento;
    
    public Peca(Casa casa, int tipo) {
        primeiroMovimento = true;
        this.origem = casa;
        this.casa = casa;
        this.tipo = tipo;
        movimentosPossiveis = new ArrayList<Casa>();
        casa.colocarPeca(this);
        enPassant = false;
    }
    
    public abstract ArrayList<Casa> possibilidades(Casa casa, Casa verifica);
    
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino) {
        if(!destino.possuiPeca()){
            Jogo.removePassant();
            casa.removerPeca();
            destino.colocarPeca(this);
            //Se a origem nao tiver mais peca, ela se moveu
            if(!casa.possuiPeca()){
               primeiroMovimento = false;
            }
            casa = destino;
        }

    }    
    
    /**
     * Faz a captura de uma peça.
     * @param destino nova casa que irá conter esta peca.
     * @param capturada a casa que conter a peça que será capturada.
     */
    public void capturar(Casa destino) {
        if(destino.possuiPeca() && (getTipoGeral() != destino.getPeca().getTipoGeral()) 
        && !(destino.getPeca() instanceof Rei)){
             if(destino.getPeca() instanceof Peao){
                Jogo.peoes.remove(destino.getPeca());
            }
            casa.removerPeca();
            destino.removerPeca();
            destino.colocarPeca(this);
            if(!casa.possuiPeca()){
                primeiroMovimento = false;
            }            
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
    
    /**
     * @return true se a peca movida possibilita o en passant
     */
    public boolean podePassant(Casa origem, Casa destino){
        if(this.getTipo() == PEAO_BRANCO && origem.getY()+2 == destino.getY()){
            return true;
        }else if(this.getTipo() == PEAO_PRETO && origem.getY()-2 == destino.getY()){
            return true;
        }
        return false;
    }
    
    /**
     * @return se o peao da casa selecionada está en passant
     */
    public boolean getPassant(Casa casa){       
       return casa.getPeca().enPassant;
    }
    
    public void setPassant(boolean b){
        this.enPassant = b;
    }

    /**
     * @return true se for o primeiro movimento da peca
     */
    public boolean getMovimento(){
        return primeiroMovimento;
    }  

}
