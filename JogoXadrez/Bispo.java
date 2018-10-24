import java.util.ArrayList;
/**
 * Escreva a descrição da classe Bispo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Bispo extends Peca
{
    
    /**
     * Construtor para objetos da classe Torre
     */
    public Bispo(Casa casa, int tipo)
    {
        super(casa, tipo);
        primeiroMovimento = true;
    }
    
    
    /**Movimenta o bispo para uma nova casa
     * @param destino casa para onde ira o bispo
     */
    public void mover(Casa destino){      
        boolean podeIr = false;
        int destinoX = destino.getX();
        int origemX = casa.getX();
        int destinoY = destino.getY();
        int origemY = casa.getY();
        
        if(destinoX > origemX && destinoY > origemY && (destinoX - origemX) == (destinoY - origemY) ){
            //Movimentacao sudoeste-nordeste
            podeIr = true;

            for(int count = 1; count <= (destinoX - origemX); count++){
                if(Jogo.possuiP(origemX+count, origemY+count)){
                    podeIr = false;
                    return;
                }
            }
        }else if(destinoX < origemX && destinoY < origemY && (origemX - destinoX) == (origemY - destinoY)){
            //Movimentacao nordeste-sudoeste
            podeIr = true;

            for(int count = 1; count <= (origemX - destinoX); count++){
                if(Jogo.possuiP(origemX-count, origemY-count)){
                    podeIr = false;
                    return;
                }
            }
        }else if(destinoX > origemX && destinoY < origemY && (destinoX - origemX) == (origemY - destinoY)){
            //Movimentacao noroeste-sudeste
            podeIr = true;

            for(int count = 1; count <= (destinoX - origemX); count++){
                if(Jogo.possuiP(origemX+count, origemY-count)){
                    podeIr = false;
                    return;
                }
            }            
        }else if(destinoX < origemX && destinoY > origemY && (origemX - destinoX) == (destinoY - origemY)){
            //Movimentacao Sudeste-Noroeste
            podeIr = true;

            for(int count = 1; count <= (origemX - destinoX); count++){
                if(Jogo.possuiP(origemX-count, origemY+count)){
                    podeIr = false;
                    return;
                }
            }             
        }
        
        if(podeIr){
            super.mover(destino);
            primeiroMovimento = true;
        }        
    }
    
    public void capturar(Casa destino){
        boolean podeIr = false;
        int destinoX = destino.getX();
        int origemX = casa.getX();
        int destinoY = destino.getY();
        int origemY = casa.getY();
        
        if(destinoX > origemX && destinoY > origemY && (destinoX - origemX) == (destinoY - origemY) ){
            //Movimentacao sudoeste-nordeste
            podeIr = true;

            for(int count = 1; count < (destinoX - origemX); count++){
                if(Jogo.possuiP(origemX+count, origemY+count)){
                    podeIr = false;
                }
            }
        }else if(destinoX < origemX && destinoY < origemY && (origemX - destinoX) == (origemY - destinoY)){
            //Movimentacao nordeste-sudoeste
            podeIr = true;

            for(int count = 1; count < (origemX - destinoX); count++){
                if(Jogo.possuiP(origemX-count, origemY-count)){
                    podeIr = false;
                }
            }
        }else if(destinoX > origemX && destinoY < origemY && (destinoX - origemX) == (origemY - destinoY)){
            //Movimentacao noroeste-sudeste
            podeIr = true;

            for(int count = 1; count < (destinoX - origemX); count++){
                if(Jogo.possuiP(origemX+count, origemY-count)){
                    podeIr = false;
                }
            }            
        }else if(destinoX < origemX && destinoY > origemY && (origemX - destinoX) == (destinoY - origemY)){
            //Movimentacao Sudeste-Noroeste
            podeIr = true;

            for(int count = 1; count < (origemX - destinoX); count++){
                if(Jogo.possuiP(origemX-count, origemY+count)){
                    podeIr = false;
                }
            }             
        }
        

        
        if(podeIr){
            super.capturar(destino);
            primeiroMovimento = true;
        }        
           
    }        
    
    public ArrayList<Casa> possibilidades(Casa casa, Casa verifica){
        return movimentosPossiveis;
    }
}
