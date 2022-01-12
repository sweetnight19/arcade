import java.util.Scanner;

import edu.salleurl.arcade.Arcade;
import edu.salleurl.arcade.ArcadeBuilder;

public class Menu {

    final int MIN = 4;
    final int SIZE = 21;

    public void show() {

        int option = 0;
        Scanner sc = new Scanner(System.in);

        while (option != 4) {
            printMenu();

            // Leer la opción del usuario
            option = sc.nextInt();

            switch (option) {
                case 1:
                    // Arcade de Laberintos
                    break;
                case 2:
                    // Arcade de Palabras
                    break;
                case 3:
                    // Iniciar
                    start();
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

    private void start() {
        Labyrinth laberinto = new Labyrinth();
        WordSearch palabras = new WordSearch();

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
