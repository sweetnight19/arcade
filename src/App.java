import java.io.IOException;

import com.github.lalyos.jfiglet.FigletFont;

public class App {

    public static void main(String[] args) throws Exception {
        printBootMessage();
        Menu menu = new Menu();
        menu.show();
    }

    private static void printBootMessage() throws IOException {
        String asciiArt1 = FigletFont.convertOneLine("ARCADE");
        System.out.println(asciiArt1);
        System.out.println("by Pere Cusó && David Marquet");
    }
}
