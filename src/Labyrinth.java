import java.util.ArrayList;
import java.util.List;

import edu.salleurl.arcade.labyrinth.controller.LabyrinthRenderer;
import edu.salleurl.arcade.labyrinth.model.LabyrinthSolver;
import edu.salleurl.arcade.labyrinth.model.enums.Cell;
import edu.salleurl.arcade.labyrinth.model.enums.Direction;

public class Labyrinth implements LabyrinthSolver {

    private Cell[][] matriuCells;
    private ArrayList<Direction> configuracio;
    private int size;
    private int x_actual;
    private int y_actual;
    private ArrayList<Direction> xMejor;
    private int vMejor;
    private int numDecisiones;
    private LabyrinthRenderer renderer;

    public Labyrinth(int size) {
        this.size = size;
        x_actual = 1;
        y_actual = 1;
        configuracio = new ArrayList<Direction>();
        xMejor = new ArrayList<Direction>();
        vMejor = 0;
    }

    @Override
    public List<Direction> solve(Cell[][] arg0, LabyrinthRenderer arg1) {
        matriuCells = arg0;
        renderer = arg1;

        long start = System.nanoTime(); // Inicia el cronometre
        backtracking(configuracio, 0);
        long elapsedTime = System.nanoTime() - start; // Acaba el cronometre
        System.out.println("Ha acabat el backtracking amb " + elapsedTime / 1000000000 + " segons");

        // printem per pantalla el resultat
        arg1.render(arg0, xMejor);
        return configuracio;
    }

    private void backtracking(ArrayList<Direction> configuracio, int k) {
        System.out.println("k: " + k);
        System.out.println("Que hi ha? -> " + matriuCells[y_actual][x_actual]);
        // System.out.println("y_actual: " + y_actual + " x_actual: " + x_actual);

        numDecisiones = 1;
        configuracio = preparaRecorridoNivel(configuracio, k);

        while (haySucesor(configuracio, k)) {

            configuracio = siguienteHermano(configuracio, k, numDecisiones);
            renderer.render(matriuCells, configuracio, 100);

            switch (matriuCells[y_actual][x_actual]) {
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

    private ArrayList<Direction> preparaRecorridoNivel(ArrayList<Direction> configuracio, int k) {
        configuracio.add(Direction.RIGHT);
        return configuracio;
    }

    private boolean haySucesor(ArrayList<Direction> configuracio2, int k) {
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
        int x = 1, y = 1;

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

        // Si la posicio actual es la sortida, retornem true
        if (matriuCells[y][x] == Cell.EXIT) {
            System.out.println("Sortida " + x + " " + y);
            x_actual = x;
            y_actual = y;
            return true;
        }

        // Si la posicio actual es una pared, retornem false
        if (matriuCells[y][x] == Cell.WALL) {
            System.out.println("Pared " + numDecisiones);
            return false;
        }

        x_actual = x;
        y_actual = y;
        System.out.println("Buena decision: " + numDecisiones);
        return true;
    }

    private void tratarSolucion(ArrayList<Direction> configuracio, int k) {

        if (k < vMejor || vMejor == 0) {
            vMejor = k;
            xMejor = (ArrayList) configuracio.clone();
        }
    }
}
