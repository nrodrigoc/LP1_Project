import java.util.ArrayList;
/**
 * Escreva a descrição da classe Torre aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Rainha extends Peca
{
    
    /**
     * Construtor para objetos da classe Torre
     */
    public Rainha(Casa casa, int tipo)
    {
        super(casa, tipo);
    }

    /**
     * Verifica que tipo de movimento a peça irá fazer
     * @param Casa destino da peça
     */
    public void mover(Casa destino){
        if(casa.getX() == destino.getX() || casa.getY() == destino.getY()){
            movimentoComum(destino);
        }else{
            movDiagonal(destino);
        }
    }  
    
    /**
     * Verifica que tipo de movimento a peça irá fazer
     * @param Casa destino da peça
     */
    public void capturar(Casa destino){
        if(casa.getX() == destino.getX() || casa.getY() == destino.getY()){
            capturaComum(destino);
        }else{
            capturaDiagonal(destino);
        }        
    }
    
    /**
     * Captura de pecas nas direcoes vertical e horizontal
     * @param Casa destino da peça
     */
    
    public void capturaComum(Casa destino){
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
     * Movimentação para os lados, para cima e para baixo
     * @param Casa destino da peça.
     */
    public void movimentoComum(Casa destino){
        //temPeca - avalia se tem alguma peca no caminho ou se pode ir
        boolean podeIr = false;
        
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
     * Mover a peça na diagonal
     * @param Casa destino da peça
     */
    public void movDiagonal(Casa destino){
        
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
                }
            }
        }else if(destinoX < origemX && destinoY < origemY && (origemX - destinoX) == (origemY - destinoY)){
            //Movimentacao nordeste-sudoeste
            podeIr = true;

            for(int count = 1; count <= (origemX - destinoX); count++){
                if(Jogo.possuiP(origemX-count, origemY-count)){
                    podeIr = false;
                }
            }
        }else if(destinoX > origemX && destinoY < origemY && (destinoX - origemX) == (origemY - destinoY)){
            //Movimentacao noroeste-sudeste
            podeIr = true;

            for(int count = 1; count <= (destinoX - origemX); count++){
                if(Jogo.possuiP(origemX+count, origemY-count)){
                    podeIr = false;
                }
            }            
        }else if(destinoX < origemX && destinoY > origemY && (origemX - destinoX) == (destinoY - origemY)){
            //Movimentacao Sudeste-Noroeste
            podeIr = true;

            for(int count = 1; count <= (origemX - destinoX); count++){
                if(Jogo.possuiP(origemX-count, origemY+count)){
                    podeIr = false;
                }
            }             
        }
       
        if(podeIr){
            super.mover(destino);
        }        
    }    
    
    /**
     * Captura pecas nas direcoes diagonais
     * @param Casa destino da rainha
     */
    public void capturaDiagonal(Casa destino){
        
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
        }         
    }
    
    /**
     * Verifica os possíveis movimentos da rainha e adiciona a um ArrayList
     * @param Casa atual da rainha.
     * @param Casa que irá ser verificada
     * @return o ArrayList com os possíveis movimentos.
     */
    public ArrayList<Casa> possibilidades(Casa casa, Casa verifica){
        movimentosPossiveis.clear();
        int x = casa.getX();
        int y = casa.getY();
        //movimento horizontal
        int rainhaX = x-1;
        while(rainhaX >= 0){
            verifica = Jogo.tabuleiro.getCasa(rainhaX,y);
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
            rainhaX--;
        }
        
        rainhaX = x+1;
        while(rainhaX < 8){
            verifica = Jogo.tabuleiro.getCasa(rainhaX,y);
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
            rainhaX++;
        }
        
        //Movimento vertical
        int rainhaY = y-1;
        while(rainhaY >= 0){
            verifica = Jogo.tabuleiro.getCasa(x,rainhaY);
            
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
            rainhaY--;
        }
        
        rainhaY = y+1;
        while(rainhaY < 8){
            verifica = Jogo.tabuleiro.getCasa(x,rainhaY);
            
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
            rainhaY++;
        }
        
        //Movimento diagonal
        rainhaX = x+1;
        rainhaY = y+1;
        while(rainhaX < 8 && rainhaY < 8){
            verifica = Jogo.tabuleiro.getCasa(rainhaX,rainhaY);
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
            rainhaX++;
            rainhaY++;
        }
        
        rainhaX = x-1;
        rainhaY = y-1;
        while(rainhaX >= 0 && rainhaY >= 0){
            verifica = Jogo.tabuleiro.getCasa(rainhaX,rainhaY);
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
            rainhaX--;
            rainhaY--;
        }
        
        rainhaX = x-1;
        rainhaY = y+1;
        while(rainhaX >= 0 && rainhaY < 8){
            verifica = Jogo.tabuleiro.getCasa(rainhaX,rainhaY);
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
            rainhaX--;
            rainhaY++;
        }
        
        rainhaX = x+1;
        rainhaY = y-1;
        while(rainhaX < 8 && rainhaY >= 0){
            verifica = Jogo.tabuleiro.getCasa(rainhaX,rainhaY);
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
            rainhaX++;
            rainhaY--;
        }
        return movimentosPossiveis;
    }
    
}
