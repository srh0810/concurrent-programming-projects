import java.util.concurrent.ThreadLocalRandom;

public class PI extends Thread {
    static int NUM_RECTS = 10000000;
    // 5 est la meilleure valeur (plus de 3 fois plus rapide qu'un seul thread) sur I7.
    static int NUM_THREADS = 1;    
    int myNum;
    static double pi=0.0f;
    static double somme_globale=0.0f;
    static ThreadLocalRandom tlrand = ThreadLocalRandom.current();



    public PI(int num){
        myNum = num;
    }


public void run() { 
    System.out.printf("Tache %d lancé%n", myNum);
        double local_sum=0.0f;
        double Un_pas = 1.0 / NUM_RECTS, x,y;
    for (int i = myNum; i < NUM_RECTS; i +=NUM_THREADS){
        x = tlrand.nextDouble();
        y = tlrand.nextDouble();
        
        
        
        if (x*x+y*y <=1){
            local_sum += 4; //On ajoute 4 pour directement faire la multiplication par la surfae du carré
        }
    }

    System.out.printf("Tache %d, la valeur calculée = %f%n", 
                                        myNum,local_sum* Un_pas);
    

    synchronized(this) { 
        somme_globale += Un_pas*local_sum;
    }

}
static Thread Ids[] = new Thread[NUM_THREADS]; // création
    public static void main(String[] args) {  
       long debut = System.currentTimeMillis();
       for (int i=0; i < NUM_THREADS; i++) {
           Ids[i] = new Thread(new PI(i));
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
       System.out.printf("Resultats = %.15f en %d ms. pour N = %d %n", 
               somme_globale,System.currentTimeMillis()-debut,NUM_RECTS);

   } // fin main   


}