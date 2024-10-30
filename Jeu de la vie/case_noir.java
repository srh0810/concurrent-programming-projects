import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class case_noir extends JPanel {
    
    public case_noir(int i, int j){
        super();
        setSize(new Dimension(i/62,j/62));
        setPreferredSize(new Dimension(i/62,j/62));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Appeler la méthode de la superclasse
        // Dessiner un carré plein
        g.setColor(Color.BLACK); 
        g.fillRect(0, 0, getWidth(), getHeight()); // Remplir le bouton de noir
    }


}
