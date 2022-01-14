import java.util.Scanner;

import LabyrinthSolver.Labyrinth;
import WordSolver.WordSearch;
import edu.salleurl.arcade.Arcade;
import edu.salleurl.arcade.ArcadeBuilder;

public class Menu {
    final int SIZE = 25;

    public void show() {

        int option = 0, backOption = 0, wordOption = 0;
        Scanner sc = new Scanner(System.in);

        while (option != 4) {
            printMenu();

            // Leer la opción del usuario
            option = sc.nextInt();

            switch (option) {
                case 1:
                    backOption = 0;
                    // Arcade de Laberintos
                    while (backOption == 0) {
                        System.out.println("\t1. Backtracking");
                        System.out.println("\t2. Backtracking con poda");
                        System.out.println("\t3. Branch and Bound");
                        System.out.print("\n\tElige una opción: ");
                        backOption = sc.nextInt();
                    }
                    break;
                case 2:
                    wordOption = 0;
                    // Arcade de Palabras
                    while (wordOption == 0) {
                        System.out.println("\t1. Backtracking");
                        System.out.println("\t2. Backtracking con poda");
                        System.out.println("\t3. Greedy");
                        System.out.print("\n\tElige una opción: ");
                        wordOption = sc.nextInt();
                    }
                    break;
                case 3:
                    // Iniciar
                    if (backOption == 0) {
                        System.out.println("Te falta elegir un algoritmo de Laberinto");
                    } else if (wordOption == 0) {
                        System.out.println("Te falta elegir un algoritmo de Palabras");
                    } else {
                        start(backOption, wordOption);

                    }
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

    private void start(int backOption, int wordOption) {
        Labyrinth laberinto = new Labyrinth(backOption);
        WordSearch palabras = new WordSearch(wordOption);

        Arcade arcade = new ArcadeBuilder()
                .setLabyrinthColumns(SIZE)
                .setLabyrinthRows(SIZE)
                .setWordsColumns(SIZE)
                .setWordsRows(SIZE)
                // Opcional, per fixar un input en comptes d'obtenir-ne un d'aleatori
                .setSeed(42)
                .setLabyrinthSolver(laberinto)
                .setWordsSolver(palabras)
                .build();
        arcade.run();
    }

}
