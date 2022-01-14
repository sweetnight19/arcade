package WordSolver;

import java.util.Scanner;

import edu.salleurl.arcade.words.controller.WordsRenderer;
import edu.salleurl.arcade.words.model.WordsSolver;

public class WordSearch implements WordsSolver {

    private char[][] matriuCells;
    private int[] configuracio;
    private String referent;
    private int algoritmo;
    private int heuristica;

    /**
     * Constructor
     *
     * @param algoritmo
     */
    public WordSearch(int algoritmo) {
        configuracio = new int[4];
        this.algoritmo = algoritmo;
        heuristica = 0;

    }

    @Override
    public int[] solve(char[][] arg0, String arg1, WordsRenderer arg2) {
        matriuCells = arg0;
        referent = arg1;

        switch (algoritmo) {
            case 1: // backtracking

                break;
            case 2: // backtracking con poda

                break;
            case 3: // Greedy
                chooseAlgorithm();
                long start = System.nanoTime(); // Inicia el cronometre
                greedy();
                long elapsedTime = System.nanoTime() - start; // Acaba el cronometre
                System.out.println("Ha acabat el Greedy amb " + elapsedTime / 1000 + " microsegons");

                // mostramos por pantall el resultado
                arg2.render(arg0, referent, configuracio);
                break;

        }

        return configuracio;
    }

    /**
     * Este metodo se encarga de elegir el algoritmo que se va a utilizar en el
     * greedy
     */
    private void chooseAlgorithm() {
        while (heuristica < 1 || heuristica > 2) {
            System.out.println("Escoge el tipo de algorismo que quieres utilizar:");
            System.out.println("1. Heuristica rapida");
            System.out.println("2. Heuristica lenta");
            Scanner sc = new Scanner(System.in);
            heuristica = sc.nextInt();

            if (heuristica < 1 || heuristica > 2) {
                System.out.println("Has de seleccionar una opción correcta entre 1 y 2");
            }

        }
    }

    /**
     * Este metodo implementa el algoritmo greedy
     */
    private void greedy() {
        int contador = 0;
        boolean trobat = false;

<<<<<<< HEAD
        for (int i = 0; i < configuracio.length; i++) {
            configuracio[i] = 0;
        }

        for (int y = 0; y < matriuCells.length && !encontrado; y++) {
            for (int x = 0; x < matriuCells.length && !encontrado; x++) {
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
=======
        configuracio[0] = 0;
        configuracio[1] = 0;
        configuracio[2] = 0;
        configuracio[3] = 0;

        for (int x = 0; x < matriuCells.length && !trobat; x++) {
            for (int y = 0; y < matriuCells.length && !trobat; y++) {
                contador = 0;
                if (matriuCells[y][x] == referent.charAt(contador)) {
                    configuracio[contador] = y;
                    contador++;
                    configuracio[contador] = x;
                    contador++;
                    if (matriuCells[y][x + 1] == referent.charAt(contador) && x + 5 <= matriuCells.length) { // Derecha
                        configuracio[contador] = y + 1;
                        contador++;
                        configuracio[contador] = x;
                        System.out.println("Derecha");
                        trobat = true;
                    }
                    if (matriuCells[y + 1][x] == referent.charAt(contador) && y + 5 <= matriuCells.length) { // Abajo
                        configuracio[contador] = y + 1;
                        contador++;
                        configuracio[contador] = x;
                        System.out.println("Abajo");
                        trobat = true;
                    }
                    if (matriuCells[y + 1][x + 1] == referent.charAt(contador) && y + 5 <= matriuCells.length
                            && x + 5 <= matriuCells.length) { // Diagonal Derecha
                        configuracio[contador] = x + 1;
                        contador++;
                        configuracio[contador] = y;
                        System.out.println("Diagonal Derecha");
                        trobat = true;
>>>>>>> parent of 22fda7e (greedy word solver done it ;))
                    }

                }

            }
        }
<<<<<<< HEAD
        if (encontrado) {
            System.out.println("Se ha encontrdo la palabra");
=======
        if (trobat) {
            System.out.println("S'ha trobat la paraula");
>>>>>>> parent of 22fda7e (greedy word solver done it ;))
        } else {
            System.out.println("No se ha encontrado la palabra");
        }
    }

    /**
     * Este metodo se encarga de comprobar si la palabra existe en la
     * posicion indicada
     *
     * @param x
     * @param y
     * @param direccion
     * @return
     */
    private boolean heuristica(int x, int y, int direccion) {
        switch (algoritmo) {
            case 1: // Heuristica rapida
                switch (direccion) {
                    case 1: // Abajo
                        if (matriuCells[y + referent.length() - 1][x] == referent
                                .charAt(referent.length() - 1)) {
                            return true;
                        }
                        break;
                    case 2: // Derecha
                        if (matriuCells[y][x + referent.length() - 1] == referent
                                .charAt(referent.length() - 1)) {
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
                                && matriuCells[y + referent.length() - 2][x] == referent
                                        .charAt(referent.length() - 2)
                                && matriuCells[y + referent.length() - 1][x] == referent
                                        .charAt(referent.length() - 1)) {
                            return true;
                        }
                        break;
                    case 2: // Derecha
                        if (matriuCells[y][x + 1] == referent.charAt(1)
                                && matriuCells[y][x + referent.length() - 2] == referent
                                        .charAt(referent.length() - 2)
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
        }

        return false;
    }
}
