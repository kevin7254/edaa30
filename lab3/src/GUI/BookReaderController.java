package GUI;

import textproc.GeneralWordCounter;
import textproc.SortedListModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class BookReaderController {
    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "Bookreader", 100,300));
    }

    private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        SortedListModel<Map.Entry<String, Integer>> model = new SortedListModel<>(counter.getWordList());
        model.sort((e1,e2) -> e2.getValue() - e1.getValue());
        JList<Map.Entry<String, Integer>> listView = new JList<>(model);
        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listView);
        scrollPane.setPreferredSize(new Dimension(200,100));

        pane.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

    }
}
