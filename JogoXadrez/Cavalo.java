import java.util.ArrayList;
/**
 * Escreva a descrição da classe Cavalo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cavalo extends Peca
{
   
    /**
     * Construtor para objetos da classe Torre
     */
    public Cavalo(Casa casa, int tipo)
    {
        super(casa, tipo);        
    }
    
    public void mover(Casa destino){
        //Movimenta 2 casas para cima e uma para direita ou esquerda
        if(casa.getX()+1 == destino.getX() && casa.getY()+2 == destino.getY() || casa.getX()-1 == destino.getX() && casa.getY()+2 == destino.getY()){
            super.mover(destino);
        }
        //movimenta 2 casas para baixo e uma para direita ou esquerda
        else if(casa.getX()+1 == destino.getX() && casa.getY()-2 == destino.getY() || casa.getX()-1 == destino.getX() && casa.getY()-2 == destino.getY()){
            super.mover(destino);
        }
        //movimenta 2 casas para direita ou esquerda e uma para cima
        else if(casa.getX()+2 == destino.getX() && casa.getY()+1 == destino.getY() || casa.getX()-2 == destino.getX() && casa.getY()+1 == destino.getY()){
            super.mover(destino);
        }
        //movimenta 2 casa para direita ou esquerda e uma para baixo
        else if(casa.getX()+2 == destino.getX() && casa.getY()-1 == destino.getY() || casa.getX()-2 == destino.getX() && casa.getY()-1 == destino.getY()){
            super.mover(destino);
        }
       
    }
    
    public void capturar(Casa destino){
        if(casa.getX()+1 == destino.getX() && casa.getY()+2 == destino.getY() || casa.getX()-1 == destino.getX() && casa.getY()+2 == destino.getY()){
            super.capturar(destino);
        }
        //movimenta 2 casas para baixo e uma para direita ou esquerda
        else if(casa.getX()+1 == destino.getX() && casa.getY()-2 == destino.getY() || casa.getX()-1 == destino.getX() && casa.getY()-2 == destino.getY()){
            super.capturar(destino);
        }
        //movimenta 2 casas para direita ou esquerda e uma para cima
        else if(casa.getX()+2 == destino.getX() && casa.getY()+1 == destino.getY() || casa.getX()-2 == destino.getX() && casa.getY()+1 == destino.getY()){
            super.capturar(destino);
        }
        //movimenta 2 casa para direita ou esquerda e uma para baixo
        else if(casa.getX()+2 == destino.getX() && casa.getY()-1 == destino.getY() || casa.getX()-2 == destino.getX() && casa.getY()-1 == destino.getY()){
            super.capturar(destino);
        }
    }
    
    public ArrayList<Casa> possibilidades(Casa casa, Casa verifica){
        movimentosPossiveis.clear();
        int x = casa.getX();
        int y = casa.getY();
        int cavaloX[] = {x+1,x-1,x+2,x-2};
        int cavaloY[] = {y+1,y-1,y+2,y-2};
        
        for(int i = 0; i < 4; i++){
           for(int j = 0; j < 4; j++){ 
                if((cavaloX[i] >=0 && cavaloX[i] < 8) && (cavaloY[j] >= 0 && cavaloY[j] < 8)){
                    verifica = Jogo.tabuleiro.getCasa(cavaloX[i],cavaloY[j]);
                    if(verifica.getPeca() == null){
                        movimentosPossiveis.add(verifica);
                    }
                    else if(verifica.getPeca().getTipoGeral() == getTipoGeral()){
                        break;
                    }
                    else{
                        movimentosPossiveis.add(verifica);
                        break;
                    }
                }
           }
        }
        return movimentosPossiveis;
    }
}