import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class case_blanche extends JPanel {
    private int i;
    private int j;
    private Grille grille;

    public case_blanche(int i){
        super();
        //setSize(new Dimension(i,j));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(i,i));
        
        setBackground(Color.BLACK);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Appeler la méthode de la superclasse
    
        // Dessiner un carré plein
        g.setColor(Color.WHITE); 
        g.fillRect(0, 0, getWidth(), getHeight()); // Remplir le bouton de noir
    }
}
