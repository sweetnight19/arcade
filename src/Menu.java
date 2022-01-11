import java.util.Scanner;

import edu.salleurl.arcade.Arcade;
import edu.salleurl.arcade.ArcadeBuilder;

public class Menu {

    public void show() {
        final int MIN = 4;

        int option = 0, labyrinthColumns = 9, labyrinthRows = 9, wordsColumns = 20, wordsRows = 20;
        Scanner sc = new Scanner(System.in);

        while (option != 4) {
            printMenu();

            // Leer la opción del usuario
            option = sc.nextInt();

            switch (option) {
                case 1:
                    // Arcade de Laberintos
                    labyrinthColumns = readOption("Ingrese el número de columnas del laberinto: ", MIN,
                            labyrinthColumns, sc);

                    labyrinthRows = readOption("Ingrese el número de filas del laberinto: ", MIN,
                            labyrinthRows, sc);

                    break;
                case 2:
                    // Arcade de Palabras
                    wordsColumns = readOption("Ingrese el número de columnas de la sopa de palabras: ", MIN,
                            wordsColumns, sc);

                    wordsRows = readOption("Ingrese el número de filas de la sopa de palabras: ", MIN, wordsRows, sc);

                    break;
                case 3:
                    if (labyrinthColumns == 0 || labyrinthRows == 0) {
                        System.out.println("No se han ingresado las dimensiones del laberinto");
                    } else {
                        if (wordsColumns == 0 || wordsRows == 0) {
                            System.out.println("No se han ingresado las dimensiones de la sopa de palabras");
                        } else {
                            // Iniciar
                            start(labyrinthColumns, labyrinthRows, wordsColumns, wordsRows);
                        }
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

    private int readOption(String string, int mIN, int labyrinthColumns, Scanner sc) {
        int var = 10;

        while (var <= mIN) {
            System.out.print(string);
            var = sc.nextInt();
            if (var <= mIN) {
                System.out.println("El número de debe ser mayor a " + mIN);
                labyrinthColumns = 0;
            }
        }
        return var;
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

    private void start(int labyrinthColumns, int labyrinthRows, int wordsColumns, int wordsRows) {
        Labyrinth laberinto = new Labyrinth(labyrinthColumns, labyrinthRows);
        WordSearch palabras = new WordSearch();

        Arcade arcade = new ArcadeBuilder()
                .setLabyrinthColumns(labyrinthColumns)
                .setLabyrinthRows(labyrinthRows)
                .setWordsColumns(wordsColumns)
                .setWordsRows(wordsRows)
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
