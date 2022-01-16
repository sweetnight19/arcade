import java.util.InputMismatchException;
import java.util.Scanner;

import LabyrinthSolver.Labyrinth;
import WordSolver.WordSearch;
import edu.salleurl.arcade.Arcade;
import edu.salleurl.arcade.ArcadeBuilder;

public class Menu {
    final int SIZE = 9;

    public void show() {

        int option = 0, backOption = 0, wordOption = 0;
        Scanner sc = new Scanner(System.in);

        while (option != 4) {
            printMenu();

            try {
                // Leer la opción del usuario
                option = sc.nextInt();
            } catch (InputMismatchException e) {

            }

            switch (option) {
                case 1:
                    // Arcade de Laberintos
                    backOption = 0;
                    while (backOption < 1 || backOption > 3) {
                        System.out.println("\n\t1. Backtracking");
                        System.out.println("\t2. Backtracking con poda");
                        System.out.println("\t3. Branch and Bound");
                        System.out.print("\nElige una opción: ");
                        backOption = sc.nextInt();
                    }
                    break;
                case 2:
                    // Arcade de Palabras
                    wordOption = 0;
                    while (wordOption < 1 || wordOption > 3) {
                        System.out.println("\n\t1. Backtracking (pendiente de implementar)");
                        System.out.println("\t2. Greedy");
                        System.out.print("\nElige una opción: ");
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
                    System.out.println("\nOpción incorrecta");
                    System.out.println();
                    option = 0;
                    sc.nextLine();
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
