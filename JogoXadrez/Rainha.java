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
        primeiroMovimento = false;
    }

    public void mover(Casa destino){
        
        if(casa.getX() == destino.getX() || casa.getY() == destino.getY()){
            movimentoComum(destino);
        }else{
            movDiagonal(destino);
        }
        
    }  
    
    
    
    public void capturar(Casa destino){
        
        if(casa.getX() == destino.getX() || casa.getY() == destino.getY()){
            capturaComum(destino);
        }else{
            capturaDiagonal(destino);
        }        
        
        
    }
    
    /**Captura de pecas nas direcoes vertical e horizontal
     * 
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
            primeiroMovimento = true;
        }        
    }
    
    
    public void movimentoComum(Casa destino){
        //temPeca - avalia se tem alguma peca no caminho ou se pode ir
        boolean podeIr = false;
        //if - movimentação para cima e para baixo
        //else if - movimentação para os lados;
        //NOVO ALGORITMO
        
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
            primeiroMovimento = true;
        }
    }     
    
    
    
    
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
            primeiroMovimento = true;
        }        
    }    
    
    
    /**
     * Captura pecas nas direcoes diagonais
     * 
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
            primeiroMovimento = true;
        }        
           
    }
    
}