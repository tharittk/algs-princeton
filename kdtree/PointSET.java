/* *****************************************************************************
 *  Name: Tharit T.
 *  Date: March 31.
 *  Description: Find all points that is inside queried rectangle and the nearest point to the queried point.
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    // construct an empty set of points
    private TreeSet<Point2D> points;

    public PointSET() {
        this.points = new TreeSet<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("null point inserted !");
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("null point argument !");

        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("null rectangle queried !");

        ArrayList<Point2D> pointInside = new ArrayList<Point2D>();
        for (Point2D p : points) {
            if (rect.contains(p)) pointInside.add(p);
        }
        return pointInside;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("null point argument !");
        if (this.isEmpty()) return null;

        Point2D champion = points.first();
        for (Point2D thatPoint : points) {
            if (p.distanceSquaredTo(thatPoint) < p.distanceSquaredTo(champion))
                champion = thatPoint;
        }
        return champion;
    }

    // unit testing of the methods (optional)

    public static void main(String[] args) {
        System.out.println("Test running");
        In in = new In(args[0]);
        // Use a Red-Black BST (TreeSet) for efficient ordering
        PointSET pointSet = new PointSET();
        // Read until the end of the file
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            // Create and add Point2D objects
            pointSet.insert(new Point2D(x, y));
        }

        in.close();
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        // pointSet.draw();

        // Set-up rectangle
        double xmin, xmax, ymin, ymax;

        RectHV testRect = new RectHV(0.05, 0.35, 0.15, 0.73);

        testRect.draw();
        StdDraw.show();

        // Run range algorithms
        Iterable<Point2D> pointInside = pointSet.range(testRect);

        for (Point2D p : pointInside) {
            p.draw();
        }
        StdDraw.show();

        Point2D nearTestPoint = new Point2D(0.81, 0.3);
        // Point2D nearTestPoint = null;

        Point2D nearestPoint = pointSet.nearest(nearTestPoint);

        System.out.println("Nearest Point: " + nearestPoint);

    }
}
