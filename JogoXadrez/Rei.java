/**
 * Escreva a descrição da classe Rei aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Rei extends Peca
{

    /**
     * Construtor para objetos da classe Rei
     */
    public Rei(Casa casa, int tipo)
    {
        super(casa, tipo);

    }
    
    /**
     * Movimenta o Rei para uma nova casa
     * @param destino nova casa que irá conter o Rei
     */
    public void mover(Casa destino){
        //if - movimentação para cima, para baixo e para os lados
        //else if - movimentação nas diagonais x+1 e y+1 ou x-1 e y-1
        //else if - movimentação nas diagonais x+1 e y-1 ou x-1 e y+1
        if((casa.getX() == destino.getX() && (casa.getY()+1 == destino.getY() || casa.getY()-1 == destino.getY())) || (casa.getX()+1 == destino.getX() || 
            casa.getX()-1 == destino.getX() && casa.getY() == destino.getY())){
            super.mover(destino);
        }
        else if((casa.getX()+1 == destino.getX() && casa.getY()+1 == destino.getY()) || (casa.getX()-1 == destino.getX() && casa.getY()-1 == destino.getY())){
            super.mover(destino);
        }
        else if((casa.getX()+1 == destino.getX() && casa.getY()-1 == destino.getY()) || (casa.getX()-1 == destino.getX() && casa.getY()+1 == destino.getY())){
            super.mover(destino);
        }
        
    }
    
    /**
     * Realiza a capturar de uma peça pelo Rei 
     * 
     * @param destino nova casa que irá conter o Rei e casa que contém a peça
     */
    public void capturar(Casa destino){
        if((casa.getX() == destino.getX() && (casa.getY()+1 == destino.getY() || casa.getY()-1 == destino.getY())) || (casa.getX()+1 == destino.getX() || 
            casa.getX()-1 == destino.getX() && casa.getY() == destino.getY())){
            super.mover(destino);
        }
        else if((casa.getX()+1 == destino.getX() && casa.getY()+1 == destino.getY()) || (casa.getX()-1 == destino.getX() && casa.getY()-1 == destino.getY())){
            super.mover(destino);
        }
        else if((casa.getX()+1 == destino.getX() && casa.getY()-1 == destino.getY()) || (casa.getX()-1 == destino.getX() && casa.getY()+1 == destino.getY())){
            super.mover(destino);
        }
       
    }
}
