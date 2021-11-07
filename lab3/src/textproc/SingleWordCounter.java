package textproc;

import java.util.Objects;

public class SingleWordCounter implements TextProcessor {
	private String word;
	private int n;

	public SingleWordCounter(String word) {
		this.word = word;
		n = 0;
	}

	public void process(String w) {
		if (Objects.equals(w, word)) {
			n++;
		}
	}

	public void report() {
		System.out.println(word + ": " + n);
	}

}
