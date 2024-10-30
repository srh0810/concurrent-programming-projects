import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class case_blanche extends JPanel {
    private int i;
    private int j;
    private Grille grille;

    public case_blanche(int i,int j){
        super();
        setSize(new Dimension(i/62,j/62));
        setPreferredSize(new Dimension(i/62,j/62));
        setBackground(Color.BLACK);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Appeler la méthode de la superclasse
    
        // Dessiner un carré plein
        g.setColor(Color.WHITE); 
        g.fillRect(0, 0, getWidth()*1000, getHeight()*1000); // Remplir le bouton de noir
    }
}
