import javax.swing.JFrame;

public class App_game_of_life {

    public App_game_of_life(){

    }
    public static void main(String[] args) {
        Fenetre f = new Fenetre("GAME OF LIFE");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
