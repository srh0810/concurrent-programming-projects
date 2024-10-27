public class LogiqueDuJeu {
    int[][] grille;
    int nb_lignes;
    int nb_colonnes;

    public LogiqueDuJeu(int nb_lignes, int nb_colonnes) {
        this.nb_lignes = nb_lignes;
        this.nb_colonnes = nb_colonnes;
        grille = new int[nb_lignes][nb_colonnes];
    }

    public int NbCellulesVivantesAutour(int i, int j) {
        int nb_cellules = 0;
        for (int ii = i - 1; ii <= i + 1; ii++) {
            for (int jj = j - 1; jj <= j + 1; jj++) {
                if (ii >= 0 && ii < nb_lignes && jj >= 0 && jj < nb_colonnes) {
                    if (ii == i && jj == j) continue; // ne pas compter la cellule elle-même
                    if (grille[ii][jj] == 1) nb_cellules++;
                }
            }
        }
        return nb_cellules;
    }

    public void CelluleMorte(int i, int j, int[][] nouvelleGrille) {
        int voisins = NbCellulesVivantesAutour(i, j);
        if (voisins < 2 || voisins > 3) {
            nouvelleGrille[i][j] = 0;
        } else {
            nouvelleGrille[i][j] = grille[i][j]; // garder l'état actuel
        }
    }

    public void CelluleVivante(int i, int j, int[][] nouvelleGrille) {
        int voisins = NbCellulesVivantesAutour(i, j);
        if (voisins == 3) {
            nouvelleGrille[i][j] = 1;
        } else {
            nouvelleGrille[i][j] = grille[i][j]; // garder l'état actuel
        }
    }

    // Évolution de la grille pour un tour
    public void EvolutionGrille() {
        int[][] nouvelleGrille = new int[nb_lignes][nb_colonnes];
        for (int i = 0; i < nb_lignes; i++) {
            for (int j = 0; j < nb_colonnes; j++) {
                if (grille[i][j] == 1) {
                    CelluleMorte(i, j, nouvelleGrille); // appliquer règles pour cellule vivante
                } else {
                    CelluleVivante(i, j, nouvelleGrille); // appliquer règles pour cellule morte
                }
            }
        }
        grille = nouvelleGrille; // mise à jour de la grille avec la nouvelle génération
    }
}
