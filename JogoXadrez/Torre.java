
import java.util.ArrayList;
/**
 * 
 * @author Luciane Falcao
 * @author Nathan Rodrigo
 * 
 */
public class Torre extends Peca
{
    
    /**
     * Construtor para objetos da classe Torre
     */
    public Torre(Casa casa, int tipo)
    {
        super(casa, tipo);
    }
    
    
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino){
        //temPeca - avalia se tem alguma peca no caminho ou se pode ir
        boolean podeIr = false;
        //if - movimentação para cima e para baixo
        //else if - movimentação para os lados;
        //NOVO ALGORITO
        
        //movimentacao para cima
        if(casa.getX() == destino.getX() && destino.getY() > casa.getY()){
            podeIr = true;
            for(int count = 1; count <= (destino.getY() - casa.getY()); count++){
                if(Jogo.possuiP(casa.getX(), casa.getY()+count)){
                    podeIr = false;
                }
            }
        }
        //movimentacao para baixo
        else if(casa.getX() == destino.getX() && destino.getY() < casa.getY()){
            podeIr = true;
            for(int count = 1; count <= (casa.getY() - destino.getY()); count++){
                if(Jogo.possuiP(casa.getX(), casa.getY()-count)){
                    podeIr = false;
                }
            }
        }
        //movimentacao para a direita
        else if(casa.getY() == destino.getY() && destino.getX() > casa.getX()){
            podeIr = true;
            for(int count = 1; count <= (destino.getX() - casa.getX()); count++){
                if(Jogo.possuiP(casa.getX()+count, casa.getY())){
                    podeIr = false;
                }
            }
        }
        //movimentacao para a esquerda
        else if(casa.getY() == destino.getY() && destino.getX() < casa.getX()){
            podeIr = true;
            for(int count = 1; count <= (casa.getX() - destino.getX()); count++){
                if(Jogo.possuiP(casa.getX()-count, casa.getY())){
                    podeIr = false;
                }
            }
        }
        
        
      
        
        //Movimenta a peca, caso seja permitido
        if(podeIr){
            super.mover(destino);
        }
        
    }
    
    /**
     * Faz a captura de uma peça.
     * @param destino nova casa que irá conter esta peca.
     * @param capturada a casa que conter a peça que será capturada.
     */
    public void capturar(Casa destino){
        //podeCapturar - avalia se pode pode capturar a peca alvo
        boolean podeCapturar = false;
        
        //movimentacao para cima
        if(casa.getX() == destino.getX() && destino.getY() > casa.getY()){
            podeCapturar = true;
            for(int count = 1; count < (destino.getY() - casa.getY()); count++){
                if(Jogo.possuiP(casa.getX(), casa.getY()+count)){
                    podeCapturar = false;
                }
            }
        }
        //movimentacao para baixo
        else if(casa.getX() == destino.getX() && destino.getY() < casa.getY()){
            podeCapturar = true;
            for(int count = 1; count < (casa.getY() - destino.getY()); count++){
                if(Jogo.possuiP(casa.getX(), casa.getY()-count)){
                    podeCapturar = false;
                }
            }
        }
        //movimentacao para a direita
        else if(casa.getY() == destino.getY() && destino.getX() > casa.getX()){
            podeCapturar = true;
            for(int count = 1; count < (destino.getX() - casa.getX()); count++){
                if(Jogo.possuiP(casa.getX()+count, casa.getY())){
                    podeCapturar = false;
                }
            }
        }
        //movimentacao para a esquerda
        else if(casa.getY() == destino.getY() && destino.getX() < casa.getX()){
            podeCapturar = true;
            for(int count = 1; count < (casa.getX() - destino.getX()); count++){
                if(Jogo.possuiP(casa.getX()-count, casa.getY())){
                    podeCapturar = false;
                }
            }
        }
   
        //Captura a peca, caso seja permitido
        if(podeCapturar){
            super.capturar(destino);
            
        }
        
    }
    
    /**
     * Verifica os possíveis movimentos da torre e adiciona a um ArrayList
     * @param Casa atual da torre.
     * @param Casa que irá ser verificada
     * @return o ArrayList com os possíveis movimentos.
     */
    public ArrayList<Casa> possibilidades(Casa casa, Casa verifica){
        movimentosPossiveis.clear();
        int x = casa.getX();
        int y = casa.getY();
        int posx = x-1;
        
        while(posx >= 0){
            verifica = Jogo.tabuleiro.getCasa(posx,y);
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
            posx--;
        }
        
        posx = x+1;
        while(posx < 8){
            verifica = Jogo.tabuleiro.getCasa(posx,y);
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
            posx++;
        }
        
        int posy = y-1;
        while(posy >= 0){
            verifica = Jogo.tabuleiro.getCasa(x,posy);
            
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
            posy--;
        }
        
        posy = y+1;
        while(posy < 8){
            verifica = Jogo.tabuleiro.getCasa(x,posy);
            
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
            posy++;
        }
        return movimentosPossiveis;
    }
      
    
    
}
