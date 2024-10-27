import java.util.concurrent.ThreadLocalRandom;

public class JeuDeLaVie extends Thread {
    static int NUM_RECTS = 10000000;
    static int NUM_THREADS = 3;
    int myNum;
    static ThreadLocalRandom tlrand = ThreadLocalRandom.current();
    static LogiqueDuJeu Jeu = new LogiqueDuJeu(10, 10); // on initialise la grille

    public JeuDeLaVie(int num) {
        myNum = num;
    }

    @Override
    public void run() {
        System.out.printf("Tâche %d lancée%n", myNum);
        for (int i = myNum; i < NUM_RECTS; i += NUM_THREADS) {
            synchronized (Jeu) {
                Jeu.EvolutionGrille();
            }
        }
    }

    public static void main(String[] args) {
        Thread[] Ids = new Thread[NUM_THREADS];
        long debut = System.currentTimeMillis();

        for (int i = 0; i < NUM_THREADS; i++) {
            Ids[i] = new Thread(new JeuDeLaVie(i));
            Ids[i].start();
        }
        
        for (int i = 0; i < NUM_THREADS; i++) {
            try {
                Ids[i].join();
            } catch (InterruptedException ie) {
                // Ne devrait pas arriver car on n'appelle pas interrupt()
            }
        }

        System.out.printf("Résultats en %d ms pour N = %d %n", System.currentTimeMillis() - debut, NUM_RECTS);
    }
}
