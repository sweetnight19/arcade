import java.util.ArrayList;
import java.util.List;

import edu.salleurl.arcade.labyrinth.controller.LabyrinthRenderer;
import edu.salleurl.arcade.labyrinth.model.LabyrinthSolver;
import edu.salleurl.arcade.labyrinth.model.enums.Cell;
import edu.salleurl.arcade.labyrinth.model.enums.Direction;

public class Labyrinth implements LabyrinthSolver {

    private Cell[][] matriuCells;
    private ArrayList<Direction> configuracio;
    private int labyrinthColumns;
    private int labyrinthRows;
    private int x_actual;
    private int y_actual;
    private ArrayList<Direction> xMejor;
    private int vMejor;
    private int numDecisiones;
    private LabyrinthRenderer renderer;

    public Labyrinth(int labyrinthColumns, int labyrinthRows) {
        this.labyrinthColumns = labyrinthColumns;
        this.labyrinthRows = labyrinthRows;
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
        System.out.println("Ha acanat el backtracking amb " + elapsedTime + " nanosegons");

        // printem per pantalla el resultat
        arg1.render(arg0, xMejor);
        return configuracio;
    }

    public void backtracking(ArrayList<Direction> configuracio, int k) {
        System.out.println("k: " + k);

        numDecisiones = 1;

        configuracio = prepararRecorridoNivel(configuracio, k);
        while (hayaSucesor(configuracio, k)) {

            System.out.println("numDecisiones: " + numDecisiones);

            configuracio = siguienteHermano(configuracio, k, numDecisiones);
            renderer.render(matriuCells, configuracio, 500);
            // System.out.println("y_actual: " + y_actual + " x_actual: " + x_actual);
            switch (matriuCells[y_actual][x_actual]) {
                case EXIT:
                    if (buena(configuracio, k)) {
                        tratarSolucion(configuracio, k);
                    }
                    break;
                default:
                    if (buena(configuracio, k)) {
                        System.out.println("no solucio, bona, completable");
                        backtracking(configuracio, k + 1);
                    }
                    numDecisiones++;
                    break;
            }
        }

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

    private boolean hayaSucesor(ArrayList<Direction> configuracio2, int k) {
        return numDecisiones <= 4;
    }

    private ArrayList<Direction> prepararRecorridoNivel(ArrayList<Direction> configuracio2, int k) {
        configuracio2.add(Direction.RIGHT);
        return configuracio2;
    }

    public boolean buena(ArrayList<Direction> configuracion, int k) {
        int x = 1;
        int y = 1;

        for (int i = 0; i <= k; i++) {
            System.out.println(configuracion.get(i));
            if (configuracion.get(i) == Direction.LEFT) {
                x -= 1;

            }
            if (configuracion.get(i) == Direction.RIGHT) {
                x += 1;

            }
            if (configuracion.get(i) == Direction.DOWN) {
                y += 1;
            }
            if (configuracion.get(i) == Direction.UP) {
                y -= 1;

            }
        }
        if (x < 0 || x >= labyrinthColumns || y < 0 || y >= labyrinthRows) {
            // System.out.println("valors negatius");
            return false;
        }
        if (matriuCells[y][x] == Cell.WALL) {
            // System.out.println("mur");
            return false;
        }
        System.out.println("buena");
        System.out.println("x: " + x + " y: " + y);
        System.out.println("x_actual: " + x_actual + " y_actual: " + y_actual);

        switch (configuracion.get(k)) {
            case UP:
                x_actual -= 1;
                break;
            case DOWN:
                x_actual += 1;
                break;
            case LEFT:
                y_actual -= 1;
                break;
            case RIGHT:
                y_actual += 1;
                break;
            default:
                break;
        }
        return true;

    }

    public void tratarSolucion(ArrayList<Direction> configuracio, int k) {

        if (k < vMejor || vMejor == 0) {
            vMejor = k;
            xMejor = (ArrayList) configuracio.clone();
        }
    }
}
