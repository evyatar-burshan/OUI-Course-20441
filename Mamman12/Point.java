/**
 * This class represents 2 dimensional point. Coordinates cannot be negative.
 */

public class Point
{
    private int _x;
    private int _y;
    private final int X_EDGE = 0;
    private final int Y_EDGE = 0;
    
    /**Constructs a point.**/
    public Point(int x, int y) {
        if (x < X_EDGE) {
            x = X_EDGE;
        }
        if (y < Y_EDGE) {
            y = Y_EDGE;
        }
        _x = x;
        _y = y;
    }
    
    /**Copy constructor for Points.**/
    public Point(Point other) {
        _x = other._x;
        _y = other._y;
    }
    
    /**Returns the x coordinate of the point.**/
    public int getX() {
        return _x;
    }
    
    /**Returns the y coordinate of the point.**/
    public int getY() {
        return _y;
    }
    
    /**Changes the x coordinate of the point.**/
    public void setX(int num) {
        if (num >= X_EDGE) {
            _x = num;
        }
    }
    
    /**Changes the y coordinate of the point.**/
    public void setY(int num) {
        if (num >= Y_EDGE) {
            _y = num;
        }
    }
    
    /**Return a string representation of this point.**/
    public String toString() {
        String output = "(" + _x + "," + _y + ")";
        return output;
    }
    
    /**Check if the received point is equal to this point.**/
    public boolean equals(Point other) {
        if (_x == other._x && _y == other._y) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**Check if this point is above a received point.**/
    public boolean isAbove(Point other) {
        if (_y > other._y) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**Check if this point is below a received point.**/
    public boolean isUnder(Point other) {
        return other.isAbove(this);
    }
    
    /**Check if this point is left of a received point.**/
    public boolean isLeft(Point other) {
        if (_x < other._x) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**Check if this point is right of a received point.**/
    public boolean isRight(Point other) {
        return other.isLeft(this);
    }
    
    /**Move the x coordinate deltaX and the y coordinate deltaY.**/
    public void move(int deltaX, int deltaY) {
        if ((_x + deltaX) >= X_EDGE && (_y + deltaY) >= Y_EDGE) {
            _x += deltaX;
            _y += deltaY;
        }
    }
    
    /**Return a new point in between this point and the received point.**/
    public Point middle (Point p) {
        int x = (_x + p._x) / 2;
        int y = (_y + p._y) / 2;
        return new Point (x,y);
    }
    
    /**Check the distance between this point and a received point.**/
    public double distance (Point p) {
        return Math.sqrt(   ((_x - p._x)*(_x - p._x)) + ((_y - p._y)*(_y - p._y))  );
    }
}
    
