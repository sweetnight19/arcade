import edu.salleurl.arcade.Arcade;
import edu.salleurl.arcade.ArcadeBuilder;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Arcade arcade = new ArcadeBuilder()
                .setLabyrinthColumns(24)
                .setLabyrinthRows(24)
                .setWordsColumns(12)
                .setWordsRows(12)
                // Opcional, per fixar un input en comptes d'obtenir-ne un d'aleatori
                .setSeed(42)
                // DemoLabyrinthSolver implementa LabyrinthSolver
                .setLabyrinthSolver(new DemoLabyrinthSolver())
                // DemoWordsSolver implementa WordsSolver
                .setWordsSolver(new DemoWordsSolver())
                .build();
        arcade.run();
    }
}
