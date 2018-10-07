/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */


public class Jogo {

    private Tabuleiro tabuleiro;
    public int jogadorAtual;
    public int jogadorAnterior;
    public int vermelhas;
    public int brancas;

    public Jogo() {
        brancas = 12;
        vermelhas = 12;
        
        jogadorAtual = 7;
        jogadorAnterior = 6;
        tabuleiro = new Tabuleiro();
        criarPecas();
    }
    
    /** Posiciona pecas no tabuleiro.
     * Utilizado na inicializacao do jogo.
     */
    private void criarPecas() {
        int x;
        int y;
        //Criacao de pecas brancas
        for(y = 0; y < 3; y++){
            for(x = 0; x < 8; x++){
                if(x%2 == 0 && y%2 == 0){
                    Casa casa = tabuleiro.getCasa(x, y);
                    Peca peca = new Peca(casa, Peca.PEDRA_BRANCA);
                }else if(x%2 != 0 && y%2 != 0){
                    Casa casa = tabuleiro.getCasa(x, y);
                    Peca peca = new Peca(casa, Peca.PEDRA_BRANCA);
                }
            }
        }
        
        //Criacao de pecas vermelhas
        for(y = 7; y > 4; y--){
            for(x = 0; x < 8; x++){
                if(x%2 != 0 && y%2 != 0){
                    Casa casa = tabuleiro.getCasa(x, y);
                    Peca peca = new Peca(casa, Peca.PEDRA_VERMELHA);
                }else if(x%2 == 0 && y%2 == 0){
                    Casa casa = tabuleiro.getCasa(x, y);
                    Peca peca = new Peca(casa, Peca.PEDRA_VERMELHA);
                }
            }
        }
    }
    
    /**
     * Comanda uma Peca na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
     * 
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
  
        //Movimentacao das pedras brancas      
        if(peca.getTipo() == Peca.PEDRA_BRANCA){
            pedraBranca(origemX, origemY, destinoX, destinoY);
            
        }else if((peca.getTipo() == Peca.DAMA_BRANCA || peca.getTipo() == Peca.DAMA_VERMELHA)){
            movDamas(origemX, origemY, destinoX, destinoY);
            
        }else if(peca.getTipo() == Peca.PEDRA_VERMELHA){
            pedraVermelha(origemX, origemY, destinoX, destinoY);
        }
    }
    
    /** Avalia se a peca recem movimentada pode virar DAMA
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     * @param tipo tipo da Peca movida;
    */
    public void viraDama(int destinoX, int destinoY, int tipo){
        if(destinoY == 0 && tipo == Peca.PEDRA_VERMELHA){
            Casa casa = tabuleiro.getCasa(destinoX, destinoY);
            casa.removerPeca();
            casa.colocarPeca(new Peca(casa, Peca.DAMA_VERMELHA));
            
        }else if(destinoY == 7 && tipo == Peca.PEDRA_BRANCA){
            Casa casa = tabuleiro.getCasa(destinoX, destinoY);
            casa.removerPeca();
            casa.colocarPeca(new Peca(casa, Peca.DAMA_BRANCA));
        }
    }
    
    /**Movimentacao das Damas vermelhas ou brancas
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
    */
    public void movDamas(int origemX, int origemY, int destinoX, int destinoY){
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        
        
        
        if(destinoX > origemX && destinoY > origemY){
            if((destinoX - origemX) == (destinoY - origemY)){
                //variavel de contagem para verificar a quantidade de pecas no caminho da dama
                int count = 0;
                
                int linha = 11;
                int coluna = 13;
                //booleano utilizado para informar se ha pecas no caminho da dama
                boolean tem = false;
                
                for(int x = 1; x <= (destinoX - origemX); x++){
                    if(tabuleiro.getCasa(origemX+x, origemY+x).possuiPeca()){
                        if(!igual(tabuleiro.getCasa(origemX+x, origemY+x).getPeca(), peca)){
                            linha = origemX+x;
                            coluna = origemY+x;
                            count++;
                        }else if(igual(tabuleiro.getCasa(origemX+x, origemY+x).getPeca() , peca)){
                            count+=2;
                        }
                        
                        tem = true;
                    }
                }
                
                if(!tem && !destino.possuiPeca()){
                    peca.mover(destino);
                    
                }else if(tem && count == 1 && !destino.possuiPeca()){
                    perdePeca(origem);
                    damaCome(linha, coluna);
                    peca.mover(destino);
                    
                }else{
                    Peca pedra = origem.getPeca();
                    jogadorAnterior = origem.getPeca().getInimiga(pedra);
                }
                
            }else{
                    Peca pedra = origem.getPeca();
                    jogadorAnterior = origem.getPeca().getInimiga(pedra);
            }
                
        }else if(destinoX < origemX && destinoY < origemY){
            if((origemX - destinoX) == (origemY - destinoY)){
                int count = 0;
                int linha = 11;
                int coluna = 13;   
                
                boolean tem = false;
                
                for(int x = 1; x <= (origemX - destinoX); x++){
                    if(tabuleiro.getCasa(origemX-x, origemY-x).possuiPeca()){
                        if(!igual(tabuleiro.getCasa(origemX-x, origemY-x).getPeca() , peca)){
                            linha = origemX-x;
                            coluna = origemY-x;
                            count++;
                        }else if(igual(tabuleiro.getCasa(origemX-x, origemY-x).getPeca() , peca)){
                            count+=2;
                        }
                        tem = true;
                    }
                }
                if(!tem && !destino.possuiPeca()){
                    peca.mover(destino);
                    
                }else if(tem && count == 1 && !destino.possuiPeca()){
                    perdePeca(origem);
                    damaCome(linha, coluna);
                    peca.mover(destino);
                    
                }else{
                    Peca pedra = origem.getPeca();
                    jogadorAnterior = origem.getPeca().getInimiga(pedra);
                }
            }else{
                Peca pedra = origem.getPeca();
                jogadorAnterior = origem.getPeca().getInimiga(pedra);
            }
            
        }else if(destinoX > origemX && destinoY < origemY){
            if((destinoX - origemX) == (origemY - destinoY)){
                int count = 0;
                int linha = 11;
                int coluna = 13; 
                
                boolean tem = false;
                
                for(int x = 1; x <= (destinoX - origemX); x++){
                    if(tabuleiro.getCasa(origemX+x, origemY-x).possuiPeca()){
                        Peca p1 = tabuleiro.getCasa(origemX+x, origemY-x).getPeca();
                        
                        if(!igual(p1 , peca)){
                            linha = origemX+x;
                            coluna = origemY-x;
                            count++;
                        }else if(igual(p1 , peca)){
                            count+=2;
                        }
                        tem = true;
                    }
                }
                if(!tem && !destino.possuiPeca()){
                    peca.mover(destino);
                    
                }else if(tem && count == 1 && !destino.possuiPeca()){
                    perdePeca(origem);
                    damaCome(linha, coluna);
                    peca.mover(destino);
                    
                }else{
                Peca pedra = origem.getPeca();
                jogadorAnterior = origem.getPeca().getInimiga(pedra);
                }
            }else{
                Peca pedra = origem.getPeca();
                jogadorAnterior = origem.getPeca().getInimiga(pedra);
            }
            
        }else if(destinoX < origemX && destinoY > origemY){
            if((origemX - destinoX) == (destinoY - origemY)){
                int count = 0;
                int linha = 11;
                int coluna = 13; 
                
                boolean tem = false;
                for(int x = 1; x <= (origemX - destinoX); x++){
                    if(tabuleiro.getCasa(origemX-x, origemY+x).possuiPeca()){
                        if(!igual(tabuleiro.getCasa(origemX-x, origemY+x).getPeca() , peca)){
                            linha = origemX-x;
                            coluna = origemY+x;
                            count++;
                        }else if(igual(tabuleiro.getCasa(origemX-x, origemY+x).getPeca() , peca)){

                            count+=2;
                        }
                        tem = true;
                    }
                }
                
                if(!tem  && !destino.possuiPeca()){
                    peca.mover(destino);
                    
                }else if(tem && count == 1 && !destino.possuiPeca()){
                    perdePeca(origem);
                    damaCome(linha, coluna);
                    peca.mover(destino);
                    
                }else{
                    Peca pedra = origem.getPeca();
                    jogadorAnterior = origem.getPeca().getInimiga(pedra);
                }
                
            }else{
                Peca pedra = origem.getPeca();
                jogadorAnterior = origem.getPeca().getInimiga(pedra);
            }  
            
        }else{
            Peca pedra = origem.getPeca();
            jogadorAnterior = origem.getPeca().getInimiga(pedra);
        }
    }
    
    
    
    
    /**Movimentacao da pedra Vermelha
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void pedraVermelha(int origemX, int origemY, int destinoX, int destinoY){
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        
        if(destino.possuiPeca() && !igual(destino.getPeca(), peca)){
            pedraCome(origemX, origemY, destinoX, destinoY);
            
        }else if(!destino.possuiPeca()){
            if(origemY-1 == destinoY && (origemX+1 == destinoX || origemX-1 == destinoX)){                    
                peca.mover(destino);
                viraDama(destinoX, destinoY, Peca.PEDRA_VERMELHA);
                
            }else{
                jogadorAnterior = peca.getInimiga(peca);
            }
            
        }else{
            jogadorAnterior = peca.getInimiga(peca);
        }
  
    }
    
    /**Movimentacao das pedras brancas
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void pedraBranca(int origemX, int origemY, int destinoX, int destinoY){
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        
        if(destino.possuiPeca() && !igual(destino.getPeca(), peca)){
                pedraCome(origemX, origemY, destinoX, destinoY);
                
        }else if(!destino.possuiPeca()){
            if(origemY+1 == destinoY && (origemX+1 == destinoX || origemX-1 == destinoX)){
                peca.mover(destino);
                viraDama(destinoX, destinoY, Peca.PEDRA_BRANCA);
                
            }else{
                jogadorAnterior = peca.getInimiga(peca);
            }
                
        }else{
            jogadorAnterior = peca.getInimiga(peca);
        }
    
    }
    
    /**Metodo de captura de pecas usando uma pedra
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino
     */
    
    public void pedraCome(int origemX, int origemY, int destinoX, int destinoY){  
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        Casa casa = origem;
        
        origem = destino;
        
        if(origemX+1 == destinoX && origemY+1 == destinoY && podeComer(destinoX+1,destinoY+1)){
            destino = tabuleiro.getCasa(destinoX+1, destinoY+1);
                    
            if(!destino.possuiPeca()){
                perdePeca(casa);
                origem.removerPeca();
                peca.mover(destino);
                viraDama(destinoX+1, destinoY+1, Peca.PEDRA_BRANCA);

                
            }else{
                jogadorAnterior = peca.getInimiga(peca);
            }
            
        }else if(destinoX == origemX-1 && origemY+1 == destinoY && podeComer(destinoX-1,destinoY+1)){
            destino = tabuleiro.getCasa(destinoX-1, destinoY+1);
                    
            if(!destino.possuiPeca()){
                perdePeca(casa);
                origem.removerPeca();
                peca.mover(destino); 
                viraDama(destinoX-1, destinoY+1, Peca.PEDRA_BRANCA);
            }else{
                jogadorAnterior = peca.getInimiga(peca);
            }
        
        }else if(origemX+1 == destinoX && origemY-1 == destinoY && podeComer(destinoX+1,destinoY-1)){
            destino = tabuleiro.getCasa(destinoX+1, destinoY-1);
                
            if(!destino.possuiPeca()){
                perdePeca(casa);
                origem.removerPeca();
                peca.mover(destino);
                viraDama(destinoX+1, destinoY-1, Peca.PEDRA_VERMELHA);
            }else{
                Peca pedra = casa.getPeca();
                jogadorAnterior = casa.getPeca().getInimiga(pedra);
            }
            
        }else if(origemX - 1 == destinoX && origemY-1 == destinoY && podeComer(destinoX-1,destinoY-1)){
            destino = tabuleiro.getCasa(destinoX-1, destinoY-1);
            
            if(!destino.possuiPeca()){              
                perdePeca(casa);
                origem.removerPeca();
                peca.mover(destino);
                viraDama(destinoX-1, destinoY-1, Peca.PEDRA_VERMELHA);
                
            }else{
                
                jogadorAnterior = peca.getInimiga(peca);
            }
                        
        }else{
            jogadorAnterior = peca.getInimiga(peca);
        }
        
    }
    
    
    /**Metodo de captura de pecas usando a dama
     * 
     */
    
    public void damaCome(int linha, int coluna){
        Casa casa = tabuleiro.getCasa(linha, coluna);
        casa.removerPeca();
    }
    
    /** Julga se eh permitido comer a peça adversaria
     * @param destinoX linha para qual se deseja ir
     * @param destinoY coluna para qual se deseja ir
     * @return se pode comer a peca sem que ultrapasse o limite de casas
     */
    public boolean podeComer(int destinoX, int destinoY){
        if(destinoX <= 7 && destinoX >= 0 && destinoY <= 7 && destinoY >= 0){
            return true;
        }else{
            return false;
        }
    }
    
    /**Diminui a quantidade de pecas inimigas quando comidas
     * 
     */
    public void perdePeca(Casa casa){
        if(casa.getPeca().getTipo() == Peca.DAMA_BRANCA || 
        casa.getPeca().getTipo() == Peca.PEDRA_BRANCA){
            vermelhas--;
            
        }else if(casa.getPeca().getTipo() == Peca.DAMA_VERMELHA || 
        casa.getPeca().getTipo() == Peca.PEDRA_VERMELHA){
            brancas--;
            
        }
    }
    
    /**Verifica se o jogo acabou
     * @return se esgotaram o numero de pecas de um dos times
     */
    public boolean jogoAcaba(){
        if(vermelhas == 0 || brancas == 0){
            return true;
        }else{
        
            return false;
        }
    }
    
    /**Metodo utilizado na movimentacao das damas
    * Analisa se a peca no caminho eh do mesmo time
    * @param p1 Peca a ser analisada
    * @param p2 Peca a ser analisada
    * @return se as pecas sao do mesmo time
    */
    
    public boolean igual(Peca p1, Peca p2){
        if(p1.getTipo() == p2.getTipo()){
            return true;
        }else if(p1.getTipo() == Peca.DAMA_BRANCA && p2.getTipo() == Peca.PEDRA_BRANCA){
            return true;
        }else if(p1.getTipo() == Peca.PEDRA_BRANCA && p2.getTipo() == Peca.DAMA_BRANCA){
            return true;
        }else if(p1.getTipo() == Peca.PEDRA_VERMELHA && p2.getTipo() == Peca.DAMA_VERMELHA){
            return true;
        }else if(p1.getTipo() == Peca.PEDRA_VERMELHA && p2.getTipo() == Peca.DAMA_VERMELHA){
            return true;
        }
        
        return false;
    }
    
    /**
     * @return o vencedor do jogo
     * 
     */
    public int getVencedor(){
        if(vermelhas == 0){
            return CasaGUI.PECA_BRANCA;
        }else{
            return CasaGUI.PECA_VERMELHA;
        }
        
    }
    
    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
