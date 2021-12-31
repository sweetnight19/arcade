import java.util.List;

import edu.salleurl.arcade.labyrinth.controller.LabyrinthRenderer;
import edu.salleurl.arcade.labyrinth.model.LabyrinthSolver;
import edu.salleurl.arcade.labyrinth.model.enums.Cell;
import edu.salleurl.arcade.labyrinth.model.enums.Direction;

public class Labyrinth implements LabyrinthSolver {

    List<Direction> directions = null;

    @Override
    public List<Direction> solve(Cell[][] arg0, LabyrinthRenderer arg1) {
        // TODO Auto-generated method stub
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);
        return directions;
    }
}
