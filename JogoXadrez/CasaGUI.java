
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Interface Grafica de uma Casa no tabuleiro do jogo.
 *
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class CasaGUI extends JButton {

    // Constantes 
    public static final Color COR_CLARA = new Color(190, 190, 190);
    public static final Color COR_ESCURA = new Color(204,50,153);
    private static final Color COR_DESTAQUE = new Color(0, 1, 0, 0.4f);
    
    // Icones das pecas
    private static final Icon BISPO_PRETO = new ImageIcon("PecasXadrez/bishop_black.png");
    private static final Icon BISPO_BRANCO = new ImageIcon("PecasXadrez/bishop_white.png");
    private static final Icon PEAO_BRANCO = new ImageIcon("PecasXadrez/pawn_white.png");
    private static final Icon PEAO_PRETO = new ImageIcon("PecasXadrez/pawn_black.png");
    private static final Icon TORRE_BRANCO = new ImageIcon("PecasXadrez/tower_white.png");
    private static final Icon TORRE_PRETO = new ImageIcon("PecasXadrez/tower_black.png");
    private static final Icon CAVALO_BRANCO = new ImageIcon("PecasXadrez/knight_white.png");
    private static final Icon CAVALO_PRETO = new ImageIcon("PecasXadrez/knight_black.png");
    private static final Icon RAINHA_BRANCO = new ImageIcon("PecasXadrez/queen_white.png");
    private static final Icon RAINHA_PRETO = new ImageIcon("PecasXadrez/queen_black.png");
    private static final Icon REI_BRANCO = new ImageIcon("PecasXadrez/king_white.png");
    private static final Icon REI_PRETO = new ImageIcon("PecasXadrez/king_black.png");
    
    
    // Cores das pecas
    public static final int SEM_PECA = -1;
    public static final int PECA_BRANCA = 0;
    public static final int PECA_PRETA = 1;
    

    private int x;
    private int y;
    private Color cor;

    public CasaGUI(int x, int y, Color cor, TabuleiroGUI tabuleiro) {
        this.x = x;
        this.y = y;
        this.cor = cor;
        setIcon(null);

        // Layout e cor
        setBackground(cor);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(cor, 1));
        setContentAreaFilled(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabuleiro.getJanela().reagir((CasaGUI) e.getSource());
            }
        });
    }

    public int getPosicaoX() {
        return x;
    }

    public int getPosicaoY() {
        return y;
    }

    public void colocarBispoPreto() {
        setIcon(BISPO_PRETO);
    }

    public void colocarBispoBranco() {
        setIcon(BISPO_BRANCO);
    }
    
    public void colocarPeaoBranco() {
        setIcon(PEAO_BRANCO);
    }

    public void colocarPeaoPreto() {
        setIcon(PEAO_PRETO);
    }
    
    public void colocarTorreBranco() {
        setIcon(TORRE_BRANCO);
    }
    
    public void colocarTorrePreto() {
        setIcon(TORRE_PRETO);
    }
    
    public void colocarCavaloPreto() {
        setIcon(CAVALO_PRETO);
    }
    
    public void colocarCavaloBranco() {
        setIcon(CAVALO_BRANCO);
    }
    
    public void colocarRainhaPreta() {
        setIcon(RAINHA_PRETO);
    }
    
    public void colocarRainhaBranca() {
        setIcon(RAINHA_BRANCO);
    }
    
    public void colocarReiBranco() {
        setIcon(REI_BRANCO);
    }
    
    public void colocarReiPreto() {
        setIcon(REI_PRETO);
    }
    
    public void removerPeca() {
        setIcon(null);
    }

    public boolean possuiPeca() {
        return getIcon() != null;
    }
    
    public int getCorPeca() {
        Icon icone = getIcon();
        
        if (icone == BISPO_BRANCO || icone == PEAO_BRANCO || icone == TORRE_BRANCO || icone == CAVALO_BRANCO || icone == RAINHA_BRANCO || icone == REI_BRANCO) {
            return PECA_BRANCA;
        }
        else if (icone == BISPO_PRETO || icone == PEAO_PRETO || icone == TORRE_PRETO || icone == CAVALO_PRETO || icone == RAINHA_PRETO || icone == REI_PRETO) {
             return PECA_PRETA;
        }
        else {
            return SEM_PECA;
        }
    }
    
    public void destacar() {
        setBackground(COR_DESTAQUE);
    }

    public void atenuar() {
        setBackground(cor);
    }
    
    
    /**
     * Pinta o componente com a cor de fundo, aceita valores RGBA
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

}
