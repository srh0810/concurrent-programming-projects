public class LogiqueDuJeu {
    //implémente la logique du jeu Game of Life

    int[][] grille;
    int nb_lignes;
    int nb_colonnes;

    //constructeur de la grille avec l'emplacement initial des cases

    // on initialise sur une grille vide mais il faudrait avoir des valeurs au départ
    public LogiqueDuJeu(int nb_lignes, int nb_colonnes){
        grille = new int[nb_lignes][nb_colonnes]; 
    }

    // regarder les cases vivantes autour d'une cellule
    public int NbCellulesVivantesAutour(int i, int j) {
        int nb_cellules = 0;

        // parcourir les 8 cellules autour de la cellule (i, j)
        for (int ii = i - 1; ii <= i + 1; ii++) {
            for (int jj = j - 1; jj <= j + 1; jj++) {
                // il faut vérifier qu'on ne sort pas des limites de la grille
                if (ii >= 0 && ii < grille.length && jj >= 0 && jj < grille[0].length) {
                    if (ii == i && jj == j) continue; // pour ne pas compter la cellule elle-même
                    if (grille[ii][jj] == 1) {
                        nb_cellules++;
                    }
                }
            }
        }

        return nb_cellules;
    }
        

    // on peut sûrement fusionner cellule morte et vivante
    //cellule morte 
    public void CelluleMorte(int i, int j){
        if (NbCellulesVivantesAutour(i,j) < 2 || NbCellulesVivantesAutour(i,j) > 3){
            grille[i][j] =0;
        }
    }

    //cellule vivante 
    public void CelluleVivante(int i, int j){
        if (NbCellulesVivantesAutour(i, j) ==3){
            grille[i][j]=1;
        }
    }


    // évolution de la grille 
    public void EvolutionGrille(int nb_tours) {
        for (int nb = 0; nb < nb_tours; nb++) {
            // on crée une copie de la grille actuelle pour que les cellules changées n'affectent pas le résultat
            int[][] nouvelleGrille = new int[nb_lignes][nb_colonnes];
            for (int i = 0; i < grille.length; i++) {
                for (int j = 0; j < grille[i].length; j++) {
                    nouvelleGrille[i][j] = grille[i][j];

                    if (grille[i][j] == 0) {
                        CelluleVivante(i, j); // on vérifie si elle peut devenir vivante
                    } else {
                        CelluleMorte(i, j); // on vérifie si elle doit mourir
                    }
                }
            }
            grille = nouvelleGrille;
        }
    }

    public int[][] get_grille(){
        return grille;
    }
    // ajouter la possibilité d'arrêt si le jeu est stable


}
