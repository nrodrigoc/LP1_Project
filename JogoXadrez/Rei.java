import java.util.ArrayList;
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
            super.capturar(destino);
        }
        else if((casa.getX()+1 == destino.getX() && casa.getY()+1 == destino.getY()) || (casa.getX()-1 == destino.getX() && casa.getY()-1 == destino.getY())){
            super.capturar(destino);
        }
        else if((casa.getX()+1 == destino.getX() && casa.getY()-1 == destino.getY()) || (casa.getX()-1 == destino.getX() && casa.getY()+1 == destino.getY())){
            super.capturar(destino);
        }
       
    }
    
    /**
     * Verifica os possíveis movimentos do rei e adiciona a um ArrayList
     * @param Casa atual do rei.
     * @param Casa que irá ser verificada
     * @return o ArrayList com os possíveis movimentos.
     */
    public ArrayList<Casa> possibilidades(Casa destino, Casa verifica){
        movimentosPossiveis.clear();
        int x = casa.getX();
        int y = casa.getY();
        int reiX[] = {x,x+1,x-1};
        int reiY[] = {y,y+1,y-1};
        
        for(int i = 0; i < 3; i++){
           for(int j = 0; j < 3; j++){ 
                if((reiX[i] >=0 && reiX[i] < 8) && (reiY[j] >= 0 && reiY[j] < 8)){
                    verifica = Jogo.tabuleiro.getCasa(reiX[i],reiY[j]);
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
