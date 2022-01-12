package WordSolver;

import java.util.ArrayList;

import edu.salleurl.arcade.words.controller.WordsRenderer;
import edu.salleurl.arcade.words.model.WordsSolver;

public class WordSearch implements WordsSolver {

    private char[][] matriuCells;
    private int[] configuracio;

    @Override
    public int[] solve(char[][] arg0, String arg1, WordsRenderer arg2) {
        matriuCells = arg0;

        long start = System.nanoTime(); // Inicia el cronometre
        // backtracking(configuracio, 0);
        long elapsedTime = System.nanoTime() - start; // Acaba el cronometre
        System.out.println("Ha acabat el backtracking amb " + elapsedTime / 1000000 + " milisegons");

        // printem per pantalla el resultat
        // arg1.render(arg0, xMejor.subList(0, vMejor));
        return configuracio;
    }

}
