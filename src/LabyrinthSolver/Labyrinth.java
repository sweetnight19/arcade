package LabyrinthSolver;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Renderer;

import edu.salleurl.arcade.labyrinth.controller.LabyrinthRenderer;
import edu.salleurl.arcade.labyrinth.model.LabyrinthSolver;
import edu.salleurl.arcade.labyrinth.model.enums.Cell;
import edu.salleurl.arcade.labyrinth.model.enums.Direction;

public class Labyrinth implements LabyrinthSolver {

    private Cell[][] matriuCells;
    private ArrayList<Direction> configuracio;
    private ArrayList<Direction> xMejor;
    private int vMejor;
    private LabyrinthRenderer renderer;
    private int algoritmo;

    public Labyrinth(int algoritmo) {
        configuracio = new ArrayList<Direction>();
        xMejor = new ArrayList<Direction>();
        vMejor = 0;
        this.algoritmo = algoritmo;
    }

    @Override
    public List<Direction> solve(Cell[][] arg0, LabyrinthRenderer arg1) {
        matriuCells = arg0;
        renderer = arg1;
        long start;
        long elapsedTime;

        if (algoritmo == 1) { // Backtracking
            start = System.nanoTime(); // Inicia el cuentareloj
            backtracking(configuracio, 0);
            elapsedTime = System.nanoTime() - start; // Termina el cuentareloj
            System.out.println("Ha terminado el backtracking: " + elapsedTime / 1000000 + " milisegundos");
        } else { // Backtracking con poda
            start = System.nanoTime(); // Inicia el cuentareloj
            backtrackingWithOpt(configuracio, 0);
            elapsedTime = System.nanoTime() - start; // Termina el cuentareloj
            System.out.println("Ha terminado el backtracking con poda: " + elapsedTime / 1000000 + " milisegundos");
        }

        // printem per pantalla el resultat
        arg1.render(arg0, xMejor.subList(0, vMejor));
        return xMejor.subList(0, vMejor);
    }

    private void branchAndBound() {

    }

    private void backtracking(ArrayList<Direction> configuracio, int k) {
        int numDecisiones = 1;

        configuracio = preparaRecorridoNivel(configuracio, k);
        while (haySucesor(configuracio, k, numDecisiones)) {
            configuracio = siguienteHermano(configuracio, k, numDecisiones);
            renderer.render(matriuCells, configuracio.subList(0, k), 10);
            switch (mirarPosicio(configuracio, k)) {
                case EXIT:
                    if (buena(configuracio, k, numDecisiones)) {
                        tratarSolucion(configuracio, k);
                    }
                    break;
                default:
                    if (buena(configuracio, k, numDecisiones)) {
                        backtracking(configuracio, k + 1);
                    }
                    break;
            }
            numDecisiones++;
        }

    }

    private void backtrackingWithOpt(ArrayList<Direction> configuracio, int k) {
        int numDecisiones = 1;

        configuracio = preparaRecorridoNivel(configuracio, k);
        while (haySucesor(configuracio, k, numDecisiones)) {
            configuracio = siguienteHermano(configuracio, k, numDecisiones);
            // renderer.render(matriuCells, configuracio.subList(0, k), 30);
            switch (mirarPosicio(configuracio, k)) {
                case EXIT:
                    if (buena(configuracio, k, numDecisiones)) {
                        tratarSolucion(configuracio, k);
                    }
                    break;
                default:
                    if (buena(configuracio, k, numDecisiones)) {
                        if (k < vMejor || vMejor == 0) { // Si puede ser mejor que el anterior o no se ha calculado
                                                         // ninguna solucion
                            backtrackingWithOpt(configuracio, k + 1);
                        }
                    }
                    break;
            }
            numDecisiones++;
        }

    }

    private Cell mirarPosicio(ArrayList<Direction> configuracio, int k) {
        int x = 1;
        int y = 1;

        // posicio actual
        for (int i = 0; i < k; i++) {
            switch (configuracio.get(i)) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
        }

        return matriuCells[y][x];
    }

    private ArrayList<Direction> preparaRecorridoNivel(ArrayList<Direction> configuracio, int k) {
        configuracio.add(Direction.RIGHT);
        return configuracio;
    }

    private boolean haySucesor(ArrayList<Direction> configuracio2, int k, int numDecisiones) {
        return numDecisiones <= 4;
    }

    private ArrayList<Direction> siguienteHermano(ArrayList<Direction> configuracio, int k, int numDecisiones) {
        switch (numDecisiones) {
            case 1:
                configuracio.set(k, Direction.RIGHT);
                break;
            case 2:
                configuracio.set(k, Direction.DOWN);
                break;
            case 3:
                configuracio.set(k, Direction.LEFT);
                break;
            case 4:
                configuracio.set(k, Direction.UP);
                break;
        }
        return configuracio;
    }

    private boolean buena(ArrayList<Direction> configuracion, int k, int numDecisiones) {
        int x = 1, x_calculada = 1;
        int y = 1, y_calculada = 1;

        if (mirarPosicio(configuracion, k) == Cell.EXIT) {
            return true;
        }

        // posicio actual
        for (int i = 0; i <= k; i++) {
            switch (configuracion.get(i)) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
        }

        // si la posicio actual es una pared, no es buena
        if (matriuCells[y][x] == Cell.WALL) {
            return false;
        }

        // posicio calculada
        for (int j = 0; j < k; j++) {
            switch (configuracion.get(j)) {
                case UP:
                    y_calculada--;
                    break;
                case DOWN:
                    y_calculada++;
                    break;
                case LEFT:
                    x_calculada--;
                    break;
                case RIGHT:
                    x_calculada++;
                    break;
            }
            // si la posicio calculada esta en la actual, significa que hemos pasado por
            // ella
            if (x == x_calculada && y == y_calculada) {
                return false;
            }
        }
        return true;
    }

    private void tratarSolucion(ArrayList<Direction> configuracio, int k) {

        if (k < vMejor || vMejor == 0) {
            vMejor = k;
            xMejor = new ArrayList<Direction>(configuracio);
        }
    }
}
