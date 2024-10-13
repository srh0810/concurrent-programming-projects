import java.util.Arrays;
import java.util.Random;

public class Tri_Fusion extends Thread{
    static double[] listeatrier =new double[10000000];
    // 5 est la meilleure valeur (plus de 3 fois plus rapide qu'un seul thread) sur I7.
    static int NUM_THREADS =1;    
    double[] mylist;
    static double[] liste_trie = {};
    
    int myNum;

    public Tri_Fusion(int num){
        myNum = num;
    }

    public void run() { 
        System.out.printf("Tache %d lancé%n", myNum);
        
        int start = myNum * (listeatrier.length / NUM_THREADS);
        int end = 0;
        if (myNum+1==NUM_THREADS){
            end =listeatrier.length;
        }
        else{
            end = (myNum+1) * (listeatrier.length / NUM_THREADS);
        }
        double[] local_list = new double[end-start] ;
        for (int i = start; i < end; i++) {
        local_list[i - start] = listeatrier[i];
        }
    
        
        local_list = Tri(local_list);
        synchronized(this){
            liste_trie = fusion(local_list,liste_trie) ;

        }
    }

    static Thread Ids[] = new Thread[NUM_THREADS]; // création

    public static void main(String[] args) {  
        for (int i=0;i<listeatrier.length;i++){
            listeatrier[i] = Math.random();
        }
        long debut = System.currentTimeMillis();
       for (int i=0; i < NUM_THREADS; i++) {
           Ids[i] = new Thread(new Tri_Fusion(i));
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
    System.out.printf("Resultats = "+ Arrays.toString(liste_trie)+" en %d ms. pour N = %d %n Thread", 
               System.currentTimeMillis()-debut,NUM_THREADS);

    }

    public double[] Tri(double[] tab){
        if (tab.length<2){
            return tab;
        }
        else{
            double[] tab1 =  Arrays.copyOfRange(tab,0,tab.length/2);

            double[] tab2 = Arrays.copyOfRange(tab,tab.length/2,tab.length);
            tab1 = Tri(tab1);
            tab2 = Tri(tab2);
            tab = fusion(tab1,tab2);
            return tab;
        }
    }
    
    public double[] fusion(double[] tab1,double[] tab2){
        int i = 0, j = 0, k = 0;
        double[] result = new double[tab1.length + tab2.length];

        while (i < tab1.length && j < tab2.length) {
            if (tab1[i] < tab2[j]) {
                result[k] = tab1[i];
                k++;
                i++;
                    } else {
                result[k] = tab2[j];
                k++;
                j++;
                    }
            }

        // Ajouter les éléments restants si l'un des tableaux n'est pas encore épuisé
        while (i < tab1.length) {
            
            result[k] = tab1[i];
            k++;
            i++;
        }

        while (j < tab2.length) {
            result[k] = tab2[j];
            k++;
            j++;
        }

        return result;
            }
}


