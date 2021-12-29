import edu.salleurl.arcade.Arcade;
import edu.salleurl.arcade.ArcadeBuilder;
import com.github.lalyos.jfiglet.FigletFont;

public class App {
    public static void main(String[] args) throws Exception {
        String asciiArt1 = FigletFont.convertOneLine("ARCADE");
        System.out.println(asciiArt1);
        System.out.println("by Pere Cusó && David Marquet");

        Arcade arcade = new ArcadeBuilder()
                .setLabyrinthColumns(24)
                .setLabyrinthRows(24)
                .setWordsColumns(12)
                .setWordsRows(12)
                // Opcional, per fixar un input en comptes d'obtenir-ne un d'aleatori
                .setSeed(42)
                // DemoLabyrinthSolver implementa LabyrinthSolver
                .setLabyrinthSolver(new laberinto())
                // DemoWordsSolver implementa WordsSolver
                .setWordsSolver(new pasaletra())
                .build();
        arcade.run();

    }
}
