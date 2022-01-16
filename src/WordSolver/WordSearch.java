package WordSolver;

import java.util.Arrays;
import java.util.Scanner;

import edu.salleurl.arcade.words.controller.WordsRenderer;
import edu.salleurl.arcade.words.model.WordsSolver;

public class WordSearch implements WordsSolver {

    private char[][] matriuCells;
    private int[] configuracio;
    private int vMejor;
    private String referent;
    private int algoritmo;
    private int wordOption;
    private WordsRenderer renderer;

    // constructor
    public WordSearch(int wordOption) {
        configuracio = new int[4];
        algoritmo = 0;
        this.wordOption = wordOption;
        vMejor = 0;
    }

    @Override
    public int[] solve(char[][] arg0, String arg1, WordsRenderer arg2) {
        long start;
        long elapsedTime;

        matriuCells = arg0;
        referent = arg1;
        renderer = arg2;

        switch (wordOption) {
            case 1: // Backtracking
                start = System.nanoTime(); // Inicia el cronometre
                backtracking(configuracio, 0, referent);
                elapsedTime = System.nanoTime() - start; // Acaba el cronometre
                System.out.println("Ha terminado el Backtracking con " + elapsedTime / 1000 + " microsegundos");
                // mostramos por pantalla el resultado
                arg2.render(arg0, referent, configuracio);
                break;
            case 2: // Greedy
                chooseAlgorithm();
                start = System.nanoTime(); // Inicia el cronometre
                greedy();
                elapsedTime = System.nanoTime() - start; // Acaba el cronometre
                System.out.println("Ha terminado el Greedy con " + elapsedTime / 1000 + " microsegundos");

                // mostramos por pantala el resultado
                arg2.render(arg0, referent, configuracio);
                break;

        }

        return configuracio;
    }

    private void chooseAlgorithm() {
        while (algoritmo < 1 || algoritmo > 2) {
            System.out.println("Escoge el tipo de algorismo que quieres utilizar:");
            System.out.println("\n\t1. Heuristica rapida");
            System.out.println("\t2. Heuristica lenta");
            System.out.println("\t3. Asegurar la solucion");
            System.out.println("Elige una opción: ");
            Scanner sc = new Scanner(System.in);
            algoritmo = sc.nextInt();

            if (algoritmo < 1 || algoritmo > 2) {
                System.out.println("Has de seleccionar una opción correcta entre 1 y 2");
            }
        }
    }

    private void greedy() {
        boolean encontrado = false;

        for (int i = 0; i < configuracio.length; i++) {
            configuracio[i] = 0;
        }

        for (int y = 0; y < matriuCells.length && !encontrado; y++) {
            for (int x = 0; x < matriuCells.length && !encontrado; x++) {

                /*
                 * configuracio[0] = y;
                 * configuracio[1] = x;
                 * configuracio[2] = y;
                 * configuracio[3] = x;
                 * renderer.render(matriuCells, referent, configuracio, 100);
                 */

                if (matriuCells[y][x] == referent.charAt(0)) {
                    if (y + referent.length() - 1 < matriuCells.length) { // Abajo
                        if (heuristica(x, y, 1)) {
                            configuracio[0] = y;
                            configuracio[1] = x;
                            configuracio[2] = y + referent.length() - 1;
                            configuracio[3] = x;
                            encontrado = true;
                            break;
                        }
                    }
                    if (x + referent.length() - 1 < matriuCells.length) { // Derecha
                        if (heuristica(x, y, 2)) {
                            configuracio[0] = y;
                            configuracio[1] = x;
                            configuracio[2] = y;
                            configuracio[3] = x + referent.length() - 1;
                            encontrado = true;
                            break;
                        }
                    }
                    if (y + referent.length() - 1 < matriuCells.length
                            && x + referent.length() - 1 < matriuCells.length) { // Diagonal
                        if (heuristica(x, y, 3)) {
                            configuracio[0] = y;
                            configuracio[1] = x;
                            configuracio[2] = y + referent.length() - 1;
                            configuracio[3] = x + referent.length() - 1;
                            encontrado = true;
                            break;
                        }
                    }
                }

            }

        }
        if (encontrado) {
            System.out.println("Se ha encontrdo la palabra");
        } else {
            System.out.println("No se ha encontrado la palabra");
        }
    }

    private boolean heuristica(int x, int y, int direccion) {
        switch (algoritmo) {
            case 1: // Heuristica rapida
                switch (direccion) {
                    case 1: // Abajo
                        if (matriuCells[y + referent.length() - 1][x] == referent.charAt(referent.length() - 1)) {
                            return true;
                        }
                        break;
                    case 2: // Derecha
                        if (matriuCells[y][x + referent.length() - 1] == referent.charAt(referent.length() - 1)) {
                            return true;
                        }
                        break;
                    case 3: // Diagonal
                        if (matriuCells[y + referent.length() - 1][x + referent.length() - 1] == referent
                                .charAt(referent.length() - 1)) {
                            return true;
                        }
                        break;
                }
                break;

            case 2: // Heuristica lenta
                switch (direccion) {
                    case 1: // Abajo
                        if (matriuCells[y + 1][x] == referent.charAt(1)
                                && matriuCells[y + referent.length() - 2][x] == referent.charAt(referent.length() - 2)
                                && matriuCells[y + referent.length() - 1][x] == referent
                                        .charAt(referent.length() - 1)) {
                            return true;
                        }
                        break;
                    case 2: // Derecha
                        if (matriuCells[y][x + 1] == referent.charAt(1)
                                && matriuCells[y][x + referent.length() - 2] == referent.charAt(referent.length() - 2)
                                && matriuCells[y][x + referent.length() - 1] == referent
                                        .charAt(referent.length() - 1)) {
                            return true;
                        }
                        break;
                    case 3: // Diagonal
                        if (matriuCells[y + 1][x + 1] == referent.charAt(1)
                                && matriuCells[y + referent.length() - 2][x + referent.length() - 2] == referent
                                        .charAt(referent.length() - 2)
                                && matriuCells[y + referent.length() - 1][x + referent.length() - 1] == referent
                                        .charAt(referent.length() - 1)) {
                            return true;
                        }
                        break;
                }
                break;
            case 3: // Asegurar la solucion
                switch (direccion) {
                    case 1: // Abajo
                        for (int i = 0; i < referent.length(); i++) {
                            if (matriuCells[y + i][x] != referent.charAt(i)) {
                                return false;
                            }
                        }
                        return true;
                    case 2: // Derecha
                        for (int j = 0; j < referent.length(); j++) {
                            if (matriuCells[y][x + j] != referent.charAt(j)) {
                                return false;
                            }
                        }
                        return true;
                    case 3: // Diagonal
                        for (int i = 0, j = 0; i < referent.length(); i++, j++) {
                            if (matriuCells[y + i][x + j] != referent.charAt(i)) {
                                return false;
                            }
                        }
                        return true;
                }
                break;
        }

        return false;
    }

    private boolean haySucesor(int[] configuracio, int k) { // altura
        return configuracio[k] < matriuCells.length && configuracio[k] < matriuCells.length && configuracio[2] == 0
                && configuracio[3] == 0;
    }

    private int[] preparaRecorridoNivel(int[] configuracio, int k) {
        Arrays.fill(configuracio, 0);
        return configuracio;
    }

    private int[] siguienteHermano(int[] configuracio, int k) { // amplada
        if (k > matriuCells.length) {
            configuracio[k] = 0;
            configuracio[k + 1] += 1;
        } else {
            configuracio[k] += 1;
            configuracio[k + 1] = 0;
        }
        return configuracio;
    }

    private void tratarSolucion(int[] configuracioBkt, int k) {

        if (k >= vMejor || vMejor == 0) {
            vMejor = k;
        }
    }

    private boolean solucion(int[] configuracio, int k) {
        return k == 2;
    }

    private boolean buena(int[] configuracio, int k, String referent) {
        boolean buena = false;
        algoritmo = 3;

        System.out.println("algoritmo: " + algoritmo);

        for (int y = 0; y < matriuCells.length && !buena; y++) {
            for (int x = 0; x < matriuCells.length && !buena; x++) {

                /*** Render ***/
                configuracio[0] = y;
                configuracio[1] = x;
                configuracio[2] = y;
                configuracio[3] = x;
                renderer.render(matriuCells, referent, configuracio, 200);
                /*** Render ***/

                if (matriuCells[y][x] == referent.charAt(0)) {
                    if (y + referent.length() - 1 < matriuCells.length) { // Abajo
                        if (heuristica(x, y, 1)) {
                            configuracio[0] = y;
                            configuracio[1] = x;
                            configuracio[2] = y + referent.length() - 1;
                            configuracio[3] = x;
                            buena = true;

                        }
                    }
                    if (x + referent.length() - 1 < matriuCells.length) { // Derecha
                        if (heuristica(x, y, 2)) {
                            configuracio[0] = y;
                            configuracio[1] = x;
                            configuracio[2] = y;
                            configuracio[3] = x + referent.length() - 1;
                            buena = true;
                        }
                    }
                    if (y + referent.length() - 1 < matriuCells.length
                            && x + referent.length() - 1 < matriuCells.length) { // Diagonal
                        if (heuristica(x, y, 3)) {
                            configuracio[0] = y;
                            configuracio[1] = x;
                            configuracio[2] = y + referent.length() - 1;
                            configuracio[3] = x + referent.length() - 1;
                            buena = true;
                        }
                    }

                }

            }

        }
        if (buena) {
            System.out.println("Se ha encontrdo la palabra");
        } else {
            System.out.println("No se ha encontrado la palabra");
        }
        return buena;
    }

    private void backtracking(int[] configuracio, int k, String referent) {

        configuracio = preparaRecorridoNivel(configuracio, k);

        while (haySucesor(configuracio, k)) {

            configuracio = siguienteHermano(configuracio, k);

            renderer.render(matriuCells, referent, configuracio);

            if (solucion(configuracio, k)) {

                if (buena(configuracio, k, referent)) {

                    tratarSolucion(configuracio, k);
                }

            } else {
                if (buena(configuracio, k, referent)) {

                    backtracking(configuracio, k + 2, referent);
                }
            }
        }

    }

}
