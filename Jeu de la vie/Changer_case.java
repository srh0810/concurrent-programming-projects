import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Changer_case implements ActionListener{
    private int i;
    private int j;
    private Grille grille;

    public Changer_case(int i,int j,Grille grille){
        super();
        this.i=i;
        this.j=j;
        this.grille=grille;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!grille.get_etat_du_jeu()){
            grille.modifier_cases((grille.getJeu().get_grille()[i][j]+1)%2,i, j);
            System.out.println("i = "+i+" j= "+j+ " état=" + grille.getJeu().get_grille()[i][j]);
            System.out.println("largeur de la case : "+ grille.getCases()[i][j].getHeight() + "longueur la case :" + grille.getCases()[i][j].getWidth());
            System.out.println("position x de la case : "+ grille.getCases()[i][j].getX());
        }
        
        
    }
}
