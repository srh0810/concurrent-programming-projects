import java.util.concurrent.ThreadLocalRandom;

public class pi_esperance extends Thread {
    static int NUM_RECTS = 10000000;
    static int NUM_THREADS = 3;
    int myNum;
    static double somme_globale=0.0f;
    static ThreadLocalRandom tlrand = ThreadLocalRandom.current();

    public pi_esperance(int num){
        myNum = num;
    }

@Override
public void run(){
    System.out.printf("Tache %d lancé%n", myNum);
        double local_sum=0.0f;
        double Un_pas = 1.0 / NUM_RECTS;
        double x;
    for (int i = myNum; i < NUM_RECTS; i +=NUM_THREADS){
        x = tlrand.nextDouble();
        local_sum += Math.sqrt(1.0 - x*x);
    }
    
    synchronized(pi_esperance.class) {
        somme_globale += Un_pas*local_sum;
    }

}
static Thread Ids[] = new Thread[NUM_THREADS]; // création
    public static void main(String[] args) {
        long debut = System.currentTimeMillis();
        for (int i=0; i < NUM_THREADS; i++) {
            Ids[i] = new Thread(new pi_esperance(i));
            Ids[i].start();
        }
        for (int i=0; i < NUM_THREADS; i++) {
            try {
                Ids[i].join();
            }
            catch (InterruptedException ie)
            {// Ne devrait pas arriver car on n'appelle pas interrupt()
            }
        }
        double pi = 4 * somme_globale;
        System.out.printf("Resultats = %.15f en %d ms. pour N = %d %n",
                pi ,System.currentTimeMillis()-debut,NUM_RECTS);

   } // fin main


}