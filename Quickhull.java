package assignment5;

import java.util.ArrayList;
import java.util.List;

public class Quickhull {

    // Main method to compute the Quickhull algorithm
    public static String quickhull(String input) {
        // Parse the input string into a list of points
        List<Point> points = parseInput(input);
        // Compute the convex hull
        List<Point> hull = quickhull(points);
        // Convert the hull points to a string and return
        return pointsToString(hull);
    }

    // Method to parse input string into a list of points
    private static List<Point> parseInput(String input) {
        List<Point> points = new ArrayList<>();
        String[] pointStrings = input.split(";");
        for (String pointString : pointStrings) {
            String[] coordinates = pointString.split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            points.add(new Point(x, y));
        }
        return points;
    }

    // Method to compute the convex hull using Quickhull algorithm
    private static List<Point> quickhull(List<Point> points) {
        List<Point> convexHull = new ArrayList<>();
        if (points.size() < 3) {
            return points;
        }

        // Find the leftmost and rightmost points
        Point minPoint = points.get(0);
        Point maxPoint = points.get(0);
        for (Point point : points) {
            if (point.x < minPoint.x) {
                minPoint = point;
            } else if (point.x > maxPoint.x) {
                maxPoint = point;
            }
        }

        // Add the leftmost and rightmost points to the convex hull
        convexHull.add(minPoint);
        convexHull.add(maxPoint);

        // Split the points into left and right sets relative to the line minPoint-maxPoint
        List<Point> leftSet = new ArrayList<>();
        List<Point> rightSet = new ArrayList<>();
        for (Point point : points) {
            if (point != minPoint && point != maxPoint) {
                if (isLeftOfLine(minPoint, maxPoint, point)) {
                    leftSet.add(point);
                } else {
                    rightSet.add(point);
                }
            }
        }

        // Recursively find the hull points for the left and right sets
        findHull(convexHull, leftSet, minPoint, maxPoint);
        findHull(convexHull, rightSet, maxPoint, minPoint);

        return convexHull;
    }

    // Recursive method to find the hull points
    private static void findHull(List<Point> hull, List<Point> points, Point p1, Point p2) {
        if (points.isEmpty()) {
            return;
        }

        // Find the farthest point from the line p1-p2
        Point farthestPoint = points.get(0);
        double maxDistance = 0;
        for (Point point : points) {
            double distance = distanceFromLine(p1, p2, point);
            if (distance > maxDistance) {
                maxDistance = distance;
                farthestPoint = point;
            }
        }

        // Add the farthest point to the hull
        hull.add(farthestPoint);
        List<Point> leftSet1 = new ArrayList<>();
        List<Point> leftSet2 = new ArrayList<>();

        // Split the points into left sets relative to the lines p1-farthestPoint and farthestPoint-p2
        for (Point point : points) {
            if (point != farthestPoint) {
                if (isLeftOfLine(p1, farthestPoint, point)) {
                    leftSet1.add(point);
                } else if (isLeftOfLine(farthestPoint, p2, point)) {
                    leftSet2.add(point);
                }
            }
        }

        // Recursively find the hull points for the new left sets
        findHull(hull, leftSet1, p1, farthestPoint);
        findHull(hull, leftSet2, farthestPoint, p2);
    }

    // Helper method to determine if a point is to the left of a line
    private static boolean isLeftOfLine(Point p1, Point p2, Point p) {
        return (p2.x - p1.x) * (p.y - p1.y) - (p2.y - p1.y) * (p.x - p1.x) > 0;
    }

    // Helper method to compute the perpendicular distance from a point to a line
    private static double distanceFromLine(Point p1, Point p2, Point p) {
        return Math.abs((p2.y - p1.y) * p.x - (p2.x - p1.x) * p.y + p2.x * p1.y - p2.y * p1.x) /
                Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x));
    }

    // Helper method to convert a list of points to a string
    private static String pointsToString(List<Point> points) {
        StringBuilder sb = new StringBuilder();
        for (Point point : points) {
            sb.append(point.x).append(",").append(point.y).append(";");
        }
        return sb.toString();
    }

    // Inner class to represent a point with x and y coordinates
    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
