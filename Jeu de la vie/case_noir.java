import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class case_noir extends JPanel {
    
    public case_noir(int i){
        super();
        //setSize(new Dimension(i,j));
        setLayout(new BorderLayout());
        setPreferredSize((new Dimension(i,i)));
        
        
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Appeler la méthode de la superclasse
        // Dessiner un carré plein
        g.setColor(Color.BLACK); 
        g.fillRect(0, 0, getWidth(), getHeight()); // Remplir le bouton de noir
    }


}
