public class pi_taylor extends Thread {
    static int NUM_RECTS = 1000000000;
    static int NUM_THREADS = 3;
    int myNum;
    static double somme_globale=0.0;

    public pi_taylor(int num){
        myNum = num;
    }

    @Override
    public void run() { 
        double local_sum=0.0;
        int parite = 0;
        System.out.printf("Tache %d lancé%n", myNum);
        // calcul des termes de la série de Taylor
        for (int i = myNum; i < NUM_RECTS; i +=NUM_THREADS){
            parite = (i % 2 == 0 ? 1 : -1);
            local_sum += parite * 1.0/(2*i+1);
        }
        synchronized(pi_taylor.class) {
            somme_globale += local_sum;
        }
}
static Thread Ids[] = new Thread[NUM_THREADS]; // création
    public static void main(String[] args) {
        long debut = System.currentTimeMillis();
        for (int i=0; i < NUM_THREADS; i++){
            Ids[i] = new Thread(new pi_taylor(i));
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
                pi, System.currentTimeMillis()-debut, NUM_RECTS);

   } // fin main   


}