package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {
    private final Set<String> s;
    private final Map<String, Integer> mappen;
    private final int wordsToPrint = 200;

    public GeneralWordCounter(Set<String> s) {
        this.s = s;
        mappen = new TreeMap<>();
    }

    @Override
    public void process(String w) {
        if (!s.contains(w)) {
            mappen.put(w, mappen.getOrDefault(w, 0) + 1);
        }
    }

    public List<Map.Entry<String, Integer>> getWordList() {
        return new ArrayList<>(mappen.entrySet());
    }

    @Override
    public void report() {
        Set<Map.Entry<String, Integer>> wordSet = mappen.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        //wordList.sort(new WordCountComparator());
        wordList.sort((e1,e2) -> e2.getValue() - e1.getValue());
        for (int i = 0; i < wordsToPrint; i++) {
            System.out.println(wordList.get(i));
        }


        /*
        for (String key : mappen.keySet()) {
            if (mappen.get(key) > 200)  {
                System.out.println("ORD: " + key + " " + mappen.get(key));
            }
        } */
    }
}