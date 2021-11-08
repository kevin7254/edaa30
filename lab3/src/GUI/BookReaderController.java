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
        JList<Map.Entry<String, Integer>> listView = new JList<>(model);
        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listView);
        scrollPane.setPreferredSize(new Dimension(200,100));

        pane.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        JPanel panel = new JPanel();
        JTextField field = new JTextField(10);
        JButton alphabutton = new JButton("Alphabetic");
        JButton freqbtn = new JButton("Frequency");
        JButton searchBtn = new JButton("Search");
        frame.getRootPane().setDefaultButton(searchBtn);

        panel.add(alphabutton);
        panel.add(freqbtn);
        panel.add(searchBtn);
        panel.add(field);

        pane.add(panel, BorderLayout.SOUTH);

        alphabutton.addActionListener(event -> model.sort(Map.Entry.comparingByKey())); // ... -> getKey().compareTo() funkar oxå
        freqbtn.addActionListener(event -> model.sort((e1, e2) -> e2.getValue() - e1.getValue()));
        searchBtn.addActionListener(event -> {
            boolean exists = false;
            for (Map.Entry<String, Integer> s: counter.getWordList()) {
                if (s.getKey().equals(field.getText().toLowerCase().trim())) {
                    listView.ensureIndexIsVisible(counter.getWordList().indexOf(s));
                    exists = true;
                    break;
                }
            }
            if(!exists) JOptionPane.showMessageDialog(null, field.getText() + " finns ej i listan.");
        });
    }
}