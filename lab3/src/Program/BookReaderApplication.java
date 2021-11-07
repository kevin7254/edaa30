package Program;

import GUI.BookReaderController;
import textproc.GeneralWordCounter;
import textproc.SortedListModel;
import textproc.TextProcessor;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BookReaderApplication {

    public static final String[] REGIONS = {"blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
            "halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
            "södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
            "öland", "östergötland"};

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("/Users/kevinnilsson/Downloads/edaa30-workspace/lab1/src/undantagsord.txt"));
        scanner.findWithinHorizon("\uFEFF", 1);
        scanner.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

        Set<String> set = new HashSet<>();

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();

            set.add(word);

        }
        scanner.close();

        List<TextProcessor> lista = new ArrayList<>();
        /*TextProcessor p = new SingleWordCounter("nils");
        TextProcessor p2 = new SingleWordCounter("norge");
        TextProcessor multi = new MultiWordCounter(REGIONS);*/
        GeneralWordCounter r = new GeneralWordCounter(set);

        /*lista.add(p);
        lista.add(p2);
        lista.add(multi);*/
        lista.add(r);

        for (TextProcessor textProcessor : lista) {
            Scanner s = new Scanner(new File("/Users/kevinnilsson/Downloads/edaa30-workspace/lab1/src/nilsholg.txt"));
            s.findWithinHorizon("\uFEFF", 1);
            s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

            while (s.hasNext()) {
                String word = s.next().toLowerCase();

                textProcessor.process(word);
            }
            s.close();
            textProcessor.report();
        }

        BookReaderController gui = new BookReaderController(r);
    }
}
