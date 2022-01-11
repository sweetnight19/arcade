import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.List;

import edu.salleurl.arcade.labyrinth.controller.LabyrinthRenderer;
import edu.salleurl.arcade.labyrinth.model.LabyrinthSolver;
import edu.salleurl.arcade.labyrinth.model.enums.Cell;
import edu.salleurl.arcade.labyrinth.model.enums.Direction;

public class Labyrinth implements LabyrinthSolver {

    Cell[][] matriuCells;
    ArrayList<Direction> configuracio;
    int distancia;
    int labyrinthColumns;
    int labyrinthRows;

    public Labyrinth(int labyrinthColumns, int labyrinthRows) {
        this.labyrinthColumns = labyrinthColumns;
        this.labyrinthRows = labyrinthRows;
    }

    @Override
    public List<Direction> solve(Cell[][] arg0, LabyrinthRenderer arg1) {

        matriuCells = arg0;

        // cridar algoritme a traves del arg0
        backtracking(configuracio, k);

        // printem per pantalla el resultat
        arg1.render(arg0, configuracio);
        return configuracio;
    }

    public void backtracking(ArrayList<Direction> configuracio, int k) {

        configuracio = prepararRecorridoNivel(configuracio, k);
        while (hayaSucesor(configuracio, k)) {
            configuracio = siguienteHermano(configuracio, k);
            switch (key) {
                case m[x_actual][y_actual] = Cell.EXIT:
                    // caso Factible(x): // tratar solución según necesidades
                    // caso ¬Factible(x): // no hacer nada, solución incorrecta
                    break;
                case m[x_actual][y_actual] != Cell.EXIT:
                    // caso completable(x,k): Backtracking(x,k+1)
                    // caso ¬completable(x,k): // no hacer nada, poda
                    break;
                default:
                    break;
            }
        }
    }

    private ArrayList<Direction> siguienteHermano(ArrayList<Direction> configuracio2, int k) {
        configuracio2.get(k) = configuracio2.get(i) + 1;

        return configuracio2;
    }

    private boolean hayaSucesor(ArrayList<Direction> configuracio2, int k) {
        // return configuracio2.get(k) < (labyrinthColumns * labyrinthRows);
        return true;
    }

    private ArrayList<Direction> prepararRecorridoNivel(ArrayList<Direction> configuracio2, int k) {
        configuracio2.get(Pk - 1) = 0;
        return null;
    }

    public boolean buena(ArrayList<Direction> configuracion, int k) {
        boolean correcto = true;

        distancia = 0;
        for (int i = 0; i < k; i++) {
            if (configuracion.get(i) == Direction.LEFT) {
                // x_actual := x_actual-1
            }
            if (configuracion.get(i) == Direction.RIGHT) {
                // x_actual := x_actual+1
            }
            if (configuracion.get(i) == Direction.DOWN) {
                // y_actual := y_actual+1
            }
            if (configuracion.get(i) == Direction.UP) {
                // y_actual := y_actual-1
            }
            // si m[x_actual][y_actual] = 1 || m[x_actual][y_actual] = -1
            // devuelve falso
            // finsi
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
