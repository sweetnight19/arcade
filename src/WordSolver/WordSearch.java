package WordSolver;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

import edu.salleurl.arcade.words.controller.WordsRenderer;
import edu.salleurl.arcade.words.model.WordsSolver;

public class WordSearch implements WordsSolver {

    private char[][] matriuCells;
    private int[] configuracio;
    private String referent;
    private int algoritmo;
    private int wordOption;
    private WordsRenderer renderer;
    private Point[] configuracioFinal;
    private boolean encontrada = false;

    // constructor
    public WordSearch(int wordOption) {
        configuracio = new int[4];
        Arrays.fill(configuracio, 0);

        algoritmo = 0;
        this.wordOption = wordOption;
    }

    @Override
    public int[] solve(char[][] arg0, String arg1, WordsRenderer arg2) {
        long start;
        long elapsedTime;

        matriuCells = arg0;
        referent = arg1;
        renderer = arg2;

        configuracioFinal = new Point[referent.length()];
        for (int i = 0; i < referent.length(); i++) {
            configuracioFinal[i] = new Point(-1, -1);
        }

        switch (wordOption) {
            case 1: // Backtracking
                start = System.nanoTime(); // Inicia el cronometre
                backtracking(configuracioFinal, 0, referent);
                elapsedTime = System.nanoTime() - start; // Acaba el cronometre
                System.out.println("Ha terminado el Backtracking con " + elapsedTime / 1000000 + " milisegundos");
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
        while (algoritmo < 1 || algoritmo > 3) {
            System.out.println("Escoge el tipo de algorismo que quieres utilizar:");
            System.out.println("\n\t1. Heuristica rapida");
            System.out.println("\t2. Heuristica lenta");
            System.out.println("\t3. Asegurar la solucion");
            System.out.println("Elige una opción: ");
            Scanner sc = new Scanner(System.in);
            algoritmo = sc.nextInt();

            if (algoritmo < 1 || algoritmo > 3) {
                System.out.println("Has de seleccionar una opción correcta entre 1 y 2");
            }
        }
    }

    private void greedy() {
        boolean encontrado = false;

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

    private boolean haySucesor(Point[] configuracio, int k, int x, int y) { // altura
        return x < matriuCells.length && y < matriuCells.length;
    }

    private Point[] preparaRecorridoNivel(Point[] configuracioFinal, int k) {
        configuracioFinal[k].setLocation(-1, -1);
        return configuracioFinal;
    }

    private Point[] siguienteHermano(Point[] configuracioFinal, int k, int x, int y) { // amplada
        configuracioFinal[k].setLocation(x, y);
        return configuracioFinal;
    }

    private void tratarSolucion(Point[] configuracioFinal, int k) {

        if (!encontrada) {
            configuracio[0] = (int) configuracioFinal[0].getY();
            configuracio[1] = (int) configuracioFinal[0].getX();
            configuracio[2] = (int) configuracioFinal[referent.length() - 1].getY();
            configuracio[3] = (int) configuracioFinal[referent.length() - 1].getX();
            encontrada = true;
            System.out.println("Se ha encontrado la palabra");
        }
    }

    private boolean solucion(Point[] configuracionFinal, int k) {

        if (k < referent.length() - 1) {
            return false;
        }
        return true;

    }

    private boolean buena(Point[] configuracionFinal, int k, String referent) {
        boolean encontrada = true;
        int j = -1;

        // comprovar si la posicio es la correcta
        if (matriuCells[(int) configuracionFinal[k].getY()][(int) configuracionFinal[k].getX()] != referent.charAt(k)) {
            return false;
        }

        if (k == 1) {
            if (configuracionFinal[k - 1].getX() + 1 == configuracionFinal[k].getX()
                    && configuracionFinal[k - 1].getY() + 1 == configuracionFinal[k].getY()) { // Diagonal
                return true;
            }
            if (configuracionFinal[k - 1].getX() + 1 == configuracionFinal[k].getX()
                    && configuracionFinal[k - 1].getY() == configuracionFinal[k].getY()) { // Derecha
                return true;
            }
            if (configuracionFinal[k - 1].getX() == configuracionFinal[k].getX()
                    && configuracionFinal[k - 1].getY() + 1 == configuracionFinal[k].getY()) { // Abajo
                return true;
            }
            return false;
        }

        if (k > 1) {
            if (configuracionFinal[0].getX() + 1 == configuracionFinal[1].getX()
                    && configuracionFinal[0].getY() + 1 == configuracionFinal[1].getY()) { // Diagonal
                j = 2;
            } else if (configuracionFinal[0].getX() + 1 == configuracionFinal[1].getX()
                    && configuracionFinal[0].getY() == configuracionFinal[1].getY()) { // Derecha
                j = 0;
            } else if (configuracionFinal[0].getX() == configuracionFinal[1].getX()
                    && configuracionFinal[0].getY() + 1 == configuracionFinal[1].getY()) { // Abajo
                j = 1;
            }
            for (int i = 1; i < k; i++) {
                switch (j) {
                    case 0: // Derecha
                        if (configuracionFinal[i].getX() + 1 == configuracionFinal[i + 1].getX()
                                && configuracionFinal[i].getY() == configuracionFinal[i + 1].getY()) {
                            encontrada = true;
                        } else {
                            return false;
                        }
                        break;
                    case 1: // Abajo
                        if (configuracionFinal[i].getX() == configuracionFinal[i + 1].getX()
                                && configuracionFinal[i].getY() + 1 == configuracionFinal[i + 1].getY()) {
                            encontrada = true;
                        } else {
                            return false;
                        }
                        break;
                    case 2: // Diagonal
                        if (configuracionFinal[i].getX() + 1 == configuracionFinal[i + 1].getX()
                                && configuracionFinal[i].getY() + 1 == configuracionFinal[i + 1].getY()) {
                            encontrada = true;
                        } else {
                            return false;
                        }
                        break;
                }

            }
            return encontrada;

        }

        return true;
    }

    private void backtracking(Point[] configuracioFinal, int k, String referent) {
        int x = 0, y = 0;

        configuracioFinal = preparaRecorridoNivel(configuracioFinal, k);
        while (haySucesor(configuracioFinal, k, x, y)) {
            configuracioFinal = siguienteHermano(configuracioFinal, k, x, y);

            if (solucion(configuracioFinal, k)) {
                if (buena(configuracioFinal, k, referent)) {

                    // int[] test = new int[4];
                    // test[0] = (int) configuracioFinal[0].getY();
                    // test[1] = (int) configuracioFinal[0].getX();
                    // test[2] = y;
                    // test[3] = x;
                    // renderer.render(matriuCells, referent, test, 10);

                    tratarSolucion(configuracioFinal, k);
                }
            } else {
                if (buena(configuracioFinal, k, referent)) {

                    // int[] test = new int[4];
                    // test[0] = (int) configuracioFinal[0].getY();
                    // test[1] = (int) configuracioFinal[0].getX();
                    // test[2] = y;
                    // test[3] = x;
                    // renderer.render(matriuCells, referent, test, 10);

                    backtracking(configuracioFinal, k + 1, referent);
                }
            }
            if (x < matriuCells.length - 1) {
                x++;
            } else {
                x = 0;
                y++;
            }
        }
    }
}
