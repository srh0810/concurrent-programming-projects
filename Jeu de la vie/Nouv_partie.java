import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Nouv_partie implements ActionListener {
    private Grille g1;

    public Nouv_partie(Grille g1){
        this.g1 = g1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        g1.arreter_jeu();
        g1.reset();
        
    }

    
    }


