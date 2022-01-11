import java.util.ArrayList;
import java.util.List;

import edu.salleurl.arcade.labyrinth.controller.LabyrinthRenderer;
import edu.salleurl.arcade.labyrinth.model.LabyrinthSolver;
import edu.salleurl.arcade.labyrinth.model.enums.Cell;
import edu.salleurl.arcade.labyrinth.model.enums.Direction;

public class Labyrinth implements LabyrinthSolver {

    private Cell[][] matriuCells;
    private ArrayList configuracio = new ArrayList<Direction>();
    private int distancia;
    private int labyrinthColumns;
    private int labyrinthRows;
    private int x_actual;
    private int y_actual;
    private ArrayList<Direction> xMejor;
    private int vMejor;

    public Labyrinth(int labyrinthColumns, int labyrinthRows) {
        this.labyrinthColumns = labyrinthColumns;
        this.labyrinthRows = labyrinthRows;
        x_actual = 0;
        y_actual = 0;
        configuracio = new ArrayList<>();
    }

    @Override
    public List<Direction> solve(Cell[][] arg0, LabyrinthRenderer arg1) {

        matriuCells = arg0;

        // cridar algoritme a traves del arg0
        backtracking(configuracio, 0);

        // printem per pantalla el resultat
        arg1.render(arg0, configuracio);
        return configuracio;
    }

    public void backtracking(ArrayList<Direction> configuracio, int k) {

        configuracio = prepararRecorridoNivel(configuracio, k);
        while (hayaSucesor(configuracio, k)) {
            configuracio = siguienteHermano(configuracio, k);
            if (matriuCells[x_actual][y_actual] == Cell.EXIT) {
                configuracio = prepararRecorridoNivel(configuracio, k);
            } else {
                backtracking(configuracio, k + 1);
            }

        }
    }

    private ArrayList<Direction> siguienteHermano(ArrayList<Direction> configuracio2, int k) {
        // configuracio2.set(k, configuracio2.get(k) + 1);
        return configuracio2;
    }

    private boolean hayaSucesor(ArrayList<Direction> configuracio2, int k) {
        // return configuracio2.get(k) < (labyrinthColumns * labyrinthRows);
        return true;
    }

    private ArrayList<Direction> prepararRecorridoNivel(ArrayList<Direction> configuracio2, int k) {
        configuracio2.set(k - 1, Direction.UP);
        return null;
    }

    public boolean buena(ArrayList<Direction> configuracion, int k) {

        distancia = 0;
        for (int i = 0; i < k; i++) {
            if (configuracion.get(i) == Direction.LEFT) {
                x_actual -= 1;
            }
            if (configuracion.get(i) == Direction.RIGHT) {
                x_actual += 1;
            }
            if (configuracion.get(i) == Direction.DOWN) {
                y_actual += 1;
            }
            if (configuracion.get(i) == Direction.UP) {
                y_actual -= 1;
            }
            if (matriuCells[x_actual][y_actual] == Cell.WALL || matriuCells[x_actual][y_actual] == Cell.EMPTY) {
                return false;
            }

        }
        distancia++;
        return true;
    }

    public void tratarSolucion(ArrayList<Direction> configuracio, int k) {
        int dist = 0;

        for (int i = 0; i < k; i++) {
            dist = distancia + 1;
        }
        if (dist < vMejor) {
            xMejor = configuracio;
            vMejor = dist;
        }
    }
}
