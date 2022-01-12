import java.util.Scanner;

import edu.salleurl.arcade.Arcade;
import edu.salleurl.arcade.ArcadeBuilder;
import edu.salleurl.arcade.labyrinth.model.LabyrinthSolver;
import edu.salleurl.arcade.words.model.WordsSolver;

public class Menu {

    final int MIN = 4;
    final int SIZE = 85;

    public void show() {

        int option = 0, backOption = 0;
        Scanner sc = new Scanner(System.in);

        while (option != 4) {
            printMenu();

            // Leer la opción del usuario
            option = sc.nextInt();

            switch (option) {
                case 1:
                    // Arcade de Laberintos

                    while (backOption == 0) {
                        System.out.println("1. Backtracking");
                        System.out.println("2. Backtracking con poda");
                        backOption = sc.nextInt();
                    }

                    break;
                case 2:
                    // Arcade de Palabras
                    break;
                case 3:
                    // Iniciar
                    start(backOption);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    sc.close();
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    System.out.println();
                    break;
            }

        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Indicar las opciones de Arcade:\n");
        System.out.println("\t1. Arcade de Laberintos");
        System.out.println("\t2. Arcade de Palabras");
        System.out.println("\t3. Iniciar");
        System.out.println("\t4. Salir\n");
        System.out.print("Ingrese una opción: ");
    }

    private void start(int backOption) {

        if (backOption == 1) {
            // Backtracking
            Backtracking laberinto = new Backtracking();
            WordSearch palabras = new WordSearch();
            launch(laberinto, palabras);

        } else if (backOption == 2) {
            // Backtracking con poda
            BacktrackingWithOpt laberinto = new BacktrackingWithOpt();
            WordSearch palabras = new WordSearch();
            launch(laberinto, palabras);
        }
    }

    private void launch(LabyrinthSolver laberinto, WordsSolver palabras) {
        Arcade arcade = new ArcadeBuilder()
                .setLabyrinthColumns(SIZE)
                .setLabyrinthRows(SIZE)
                .setWordsColumns(SIZE)
                .setWordsRows(SIZE)
                // Opcional, per fixar un input en comptes d'obtenir-ne un d'aleatori
                .setSeed(42)
                // DemoLabyrinthSolver implementa LabyrinthSolver
                .setLabyrinthSolver(laberinto)
                // DemoWordsSolver implementa WordsSolver
                .setWordsSolver(palabras)
                .build();
        arcade.run();
    }

}
