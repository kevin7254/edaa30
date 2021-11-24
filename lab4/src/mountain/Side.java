package mountain;

import java.util.Objects;

public class Side {
    private Point a,b;

    public Side(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Side side = (Side) o;
        return (Objects.equals(a, side.a) && Objects.equals(b, side.b));
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
