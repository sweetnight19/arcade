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
        boolean encontrado = false;

        configuracio[0] = 0;
        configuracio[1] = 0;
        configuracio[2] = 0;
        configuracio[3] = 0;

        // System.out.println("x:1 y:1 -> " + matriuCells[1][1]);
        // System.out.println("x:2 y:1 -> " + matriuCells[2][1]);
        // System.out.println("x:3 y:1 -> " + matriuCells[3][1]);
        System.out.println("referent.length: " + referent.length());

        for (int y = 0; y < matriuCells.length && !encontrado; y++) {
            for (int x = 0; x < matriuCells.length && !encontrado; x++) {
                // System.out.println("x:" + x + " y:" + y + " -> " + matriuCells[y][x]);

                if (matriuCells[y][x] == referent.charAt(0)) {
                    if (y + referent.length() - 1 < matriuCells.length) { // Abajo
                        if (matriuCells[y + 1][x] == referent.charAt(1)
                                && matriuCells[y + referent.length() - 2][x] == referent
                                        .charAt(referent.length() - 2)
                                && matriuCells[y + referent.length() - 1][x] == referent
                                        .charAt(referent.length() - 1)) {
                            configuracio[0] = y;
                            configuracio[1] = x;
                            configuracio[2] = y + referent.length() - 1;
                            configuracio[3] = x;
                            System.out.println("Abajo");
                            encontrado = true;
                            break;
                        }
                    }
                    if (x + referent.length() - 1 < matriuCells.length) { // Derecha
                        if (matriuCells[y][x + 1] == referent.charAt(1)
                                && matriuCells[y][x + referent.length() - 2] == referent
                                        .charAt(referent.length() - 2)
                                && matriuCells[y][x + referent.length() - 1] == referent
                                        .charAt(referent.length() - 1)) {
                            configuracio[0] = y;
                            configuracio[1] = x;
                            configuracio[2] = y;
                            configuracio[3] = x + referent.length() - 1;
                            System.out.println("Derecha");
                            encontrado = true;
                            break;
                        }
                    }
                }

            }

        }
        if (encontrado) {
            System.out.println("S'ha trobat la paraula");
        } else {
            System.out.println("No s'ha trobat la paraula");
        }
    }
}
