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
    
    /**
     * Verifica os possíveis movimentos do bispo e adiciona a um ArrayList
     * @param Casa atual do bispo.
     * @param Casa que irá ser verificada
     * @return o ArrayList com os possíveis movimentos.
     */
    public ArrayList<Casa> possibilidades(Casa casa, Casa verifica){
        movimentosPossiveis.clear();
        int x = casa.getX();
        int y = casa.getY();
        int bispoX = x+1;
        int bispoY = y+1;
        while(bispoX < 8 && bispoY < 8){
            verifica = Jogo.tabuleiro.getCasa(bispoX,bispoY);
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
            bispoX++;
            bispoY++;
        }
        
        bispoX = x-1;
        bispoY = y-1;
        while(bispoX >= 0 && bispoY >= 0){
            verifica = Jogo.tabuleiro.getCasa(bispoX,bispoY);
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
            bispoX--;
            bispoY--;
        }
        
        bispoX = x+1;
        bispoY = y-1;
        while(bispoX < 8 && bispoY >= 0){
            verifica = Jogo.tabuleiro.getCasa(bispoX,bispoY);
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
            bispoX++;
            bispoY--;
        }
        
        bispoX = x-1;
        bispoY = y+1;
        while(bispoX >= 0 && bispoY < 8){
            verifica = Jogo.tabuleiro.getCasa(bispoX,bispoY);
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
            bispoX--;
            bispoY++;
        }
        return movimentosPossiveis;
    }
}
