package fractal;

import koch.Koch;
import mountain.MountainFractal;
import mountain.Point;
import mountain.RandomUtilities;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(300);
		fractals[0] = new MountainFractal(new Point(500,400), new Point(100,400), new Point(375,100), RandomUtilities.randFunc(20));
		//new FractalView(fractals, "Mountain", 600, 600);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}
}
