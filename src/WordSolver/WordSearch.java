package WordSolver;

import edu.salleurl.arcade.words.controller.WordsRenderer;
import edu.salleurl.arcade.words.model.WordsSolver;

public class WordSearch implements WordsSolver {

    private char[][] matriuCells;
    private int[] configuracio;
    private String referent;

    // constructor
    public WordSearch() {
        configuracio = new int[4];
    }

    @Override
    public int[] solve(char[][] arg0, String arg1, WordsRenderer arg2) {
        matriuCells = arg0;
        referent = arg1;

        long start = System.nanoTime(); // Inicia el cronometre
        greedy();
        long elapsedTime = System.nanoTime() - start; // Acaba el cronometre
        System.out.println("Ha acabat el Greedy amb " + elapsedTime / 1000000 + " milisegons");

        // printem per pantalla el resultat
        arg2.render(arg0, referent, configuracio);
        return configuracio;
    }

    private void greedy() {
        int contador = 0;
        boolean trobat = false;

        configuracio[0] = 0;
        configuracio[1] = 0;
        configuracio[2] = 0;
        configuracio[3] = 0;

        for (int x = 0; x < matriuCells.length && !trobat; x++) {
            for (int y = 0; y < matriuCells.length && !trobat; y++) {
                contador = 0;
                if (matriuCells[x][y] == referent.charAt(contador)) {
                    configuracio[contador] = y;
                    contador++;
                    configuracio[contador] = x;
                    contador++;
                    if (matriuCells[x + 1][y] == referent.charAt(contador) && x + 5 <= matriuCells.length) { // Derecha
                        configuracio[contador] = y + 1;
                        contador++;
                        configuracio[contador] = x;
                        System.out.println("Derecha");
                        trobat = true;
                    }
                    if (matriuCells[x][y + 1] == referent.charAt(contador) && y + 5 <= matriuCells.length) { // Abajo
                        configuracio[contador] = y + 1;
                        contador++;
                        configuracio[contador] = x;
                        System.out.println("Abajo");
                        trobat = true;
                    }
                    if (matriuCells[x + 1][y + 1] == referent.charAt(contador) && y + 5 <= matriuCells.length
                            && x + 5 <= matriuCells.length) { // Diagonal Derecha
                        configuracio[contador] = x + 1;
                        contador++;
                        configuracio[contador] = y;
                        System.out.println("Diagonal Derecha");
                        trobat = true;
                    }

                }

            }
        }
    }
}
