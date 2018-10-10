
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
}