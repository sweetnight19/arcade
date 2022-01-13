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

    /**
     * Constructor
     *
     * @param algoritmo
     */
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

        switch (algoritmo) {
            case 1: // backtracking
                start = System.nanoTime(); // Inicia el cuentareloj
                backtracking(configuracio, 0);
                elapsedTime = System.nanoTime() - start; // Termina el cuentareloj
                System.out.println("Ha terminado el backtracking: " + elapsedTime / 1000000 + " milisegundos");
                break;
            case 2: // backtracking con poda
                start = System.nanoTime(); // Inicia el cuentareloj
                backtrackingWithOpt(configuracio, 0);
                elapsedTime = System.nanoTime() - start; // Termina el cuentareloj
                System.out.println("Ha terminado el backtracking con poda: " + elapsedTime / 1000000 + " milisegundos");
                break;
            case 3: // Branch and Bound
                start = System.nanoTime(); // Inicia el cronometre
                branchAndBound();
                elapsedTime = System.nanoTime() - start; // Termina el cuentareloj
                System.out.println("Ha terminado el Branch and Bound: " + elapsedTime / 1000000 + " milisegundos");
                break;

        }

        // printem per pantalla el resultat
        arg1.render(arg0, xMejor.subList(0, vMejor));
        return xMejor.subList(0, vMejor);
    }

    /**
     * Metode que implementa el branch and bound
     * 
     * @param configuracio
     * @param v
     */
    private void branchAndBound() {

    }

    /**
     * Metodo que implementa el backtracking
     * 
     * @param configuracio
     * @param k
     */
    private void backtracking(ArrayList<Direction> configuracio, int k) {
        int numDecisiones = 1;

        configuracio = preparaRecorridoNivel(configuracio, k);
        while (haySucesor(configuracio, k, numDecisiones)) {
            configuracio = siguienteHermano(configuracio, k, numDecisiones);
            // renderer.render(matriuCells, configuracio.subList(0, k), 10);
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

    /**
     * Metodo que implementa el backtracking con poda
     * 
     * @param configuracio
     * @param k
     */
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

    /**
     * Metodo que devuelve que hay en la posicion actual
     * 
     * @param configuracio
     * @param k
     * @return Cell
     */
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

    /**
     * Metodo que inicializa el recorrido del nivel
     * 
     * @param configuracio
     * @param k
     * @return ArrayList<Direction>
     */
    private ArrayList<Direction> preparaRecorridoNivel(ArrayList<Direction> configuracio, int k) {
        configuracio.add(Direction.RIGHT);
        return configuracio;
    }

    /**
     * Metodo que devuelve si hay decisiones que tomar en ese nivel
     * 
     * @param configuracio
     * @param k
     * @param numDecisiones
     * @return boolean
     */
    private boolean haySucesor(ArrayList<Direction> configuracio, int k, int numDecisiones) {
        return numDecisiones <= 4;
    }

    /**
     * Metodo que devuelve el siguiente hermano
     * 
     * @param configuracio
     * @param k
     * @param numDecisiones
     * @return ArrayList<Direction>
     */
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

    /**
     * Metodo que devuelve si la configuracion es buena
     * 
     * @param configuracio
     * @param k
     * @param numDecisiones
     * @return boolean
     */
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

    /**
     * Metodo que trata la solucion
     * 
     * @param configuracio
     * @param k
     */
    private void tratarSolucion(ArrayList<Direction> configuracio, int k) {

        if (k < vMejor || vMejor == 0) {
            vMejor = k;
            xMejor = new ArrayList<Direction>(configuracio);
        }
    }
}
