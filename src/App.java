import edu.salleurl.arcade.Arcade;
import edu.salleurl.arcade.ArcadeBuilder;

import java.io.IOException;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;

public class App {
    public static void main(String[] args) throws Exception {
        int option = 0;
        Scanner sc = new Scanner(System.in);

        printBootMessage();
        while (option != 4) {
            System.out.println();
            System.out.println("Indicar las opciones de Arcade:\n");
            System.out.println("\t1. Arcade de Laberintos");
            System.out.println("\t2. Arcade de Palabras");
            System.out.println("\t3. Iniciar");
            System.out.println("\t4. Salir\n");
            System.out.print("Ingrese una opción: ");

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

    private static void start() {
        Labyrinth laberinto = new Labyrinth();
        WordSearch palabras = new WordSearch();

        Arcade arcade = new ArcadeBuilder()
                .setLabyrinthColumns(24)
                .setLabyrinthRows(24)
                .setWordsColumns(12)
                .setWordsRows(12)
                // Opcional, per fixar un input en comptes d'obtenir-ne un d'aleatori
                .setSeed(42)
                // DemoLabyrinthSolver implementa LabyrinthSolver
                .setLabyrinthSolver(laberinto)
                // DemoWordsSolver implementa WordsSolver
                .setWordsSolver(palabras)
                .build();
        arcade.run();
    }

    private static void printBootMessage() throws IOException {
        String asciiArt1 = FigletFont.convertOneLine("ARCADE");
        System.out.println(asciiArt1);
        System.out.println("by Pere Cusó && David Marquet");
    }
}
