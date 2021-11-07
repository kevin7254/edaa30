package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor{
    private final Map<String, Integer> mappen;

    public MultiWordCounter(String[] landskap) {
        mappen = new HashMap<>();

        for(String s : landskap) {
            mappen.put(s,0);
        }
    }

    @Override
    public void process(String w) {
        if (mappen.containsKey(w)) {
            mappen.replace(w, mappen.get(w)+1);
        }
    }

    @Override
    public void report() {
        for (String key : mappen.keySet()) {
            System.out.println("Landskap: " + key + " " + mappen.get(key));
        }
    }
}
