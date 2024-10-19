public class pi_inverse extends Thread {
    static int NUM_RECTS = 1000000000;
    static int NUM_THREADS = 3;
    int myNum;
    static double somme_globale=0.0;

    public pi_inverse(int num){
        myNum = num;
    }

    @Override
    public void run() { 
        double local_sum=0.0;
        System.out.printf("Tache %d lancé%n", myNum);
        // calcul des termes par développement de la somme des inverses des carrés
        for (int i = myNum; i < NUM_RECTS; i +=NUM_THREADS){
            local_sum += 1.0 / (i*i);
        }
        synchronized(pi_inverse.class) {
            somme_globale += local_sum;
        }
}
static Thread Ids[] = new Thread[NUM_THREADS]; // création
    public static void main(String[] args) {
        long debut = System.currentTimeMillis();
        for (int i=0; i < NUM_THREADS; i++){
            Ids[i] = new Thread(new pi_inverse(i));
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
        double pi = Math.sqrt(6 * somme_globale);
        System.out.printf("Resultats = %.15f en %d ms. pour N = %d %n", 
                pi, System.currentTimeMillis()-debut, NUM_RECTS);

   } // fin main   


}