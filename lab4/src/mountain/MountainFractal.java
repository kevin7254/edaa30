package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

import java.util.HashMap;

public class MountainFractal extends Fractal {
    private final Point p1, p2, p3;
    private final double dev;
    private final HashMap<Side, Point> map;

    public MountainFractal(Point p1, Point p2, Point p3, double dev) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.dev = dev;
        this.map = new HashMap<>();
    }

    @Override
    public String getTitle() {
        return "Mountain";
    }

    @Override
    public void draw(TurtleGraphics turtle) {
        fractalLine(turtle, order, p1,p2,p3,dev);
    }

    private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
        if(order == 0) {
            turtle.moveTo(a.getX(), a.getY());
            turtle.forwardTo(b.getX(), b.getY());
            turtle.forwardTo(c.getX(), c.getY());
            turtle.forwardTo(a.getX(), a.getY());
        } else {
            Point AB = midPoint(a,b,dev);
            Point AC = midPoint(a,c,dev);
            Point BC = midPoint(b,c,dev);

            fractalLine(turtle,order-1,a, AB, AC, dev / 2);
            fractalLine(turtle,order-1,AB,b,BC, dev / 2);
            fractalLine(turtle,order-1,AC,BC,c, dev / 2);
            fractalLine(turtle,order-1,AC,AB,BC, dev / 2);
        }
    }
    private Point midPoint(Point a, Point b, double dev) {
        Side s = new Side(a,b);
        Side s2 = new Side(b,a);
        if (map.containsKey(s)) {
            return map.remove(s);
        }else if(map.containsKey(s2)) {
            return map.remove(s2);
        } else {
            Point p = new Point(((a.getX() + b.getX()) / 2), ((a.getY() + b.getY()) /2) + (int) RandomUtilities.randFunc(dev));
            map.put(s,p);
            return p;
        }
    }
}
