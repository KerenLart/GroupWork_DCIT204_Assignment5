package assignment5;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairProblem {
    private static class Point {
        double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    private static class Result {
        double distance;
        Point point1, point2;
        Result(double distance, Point point1, Point point2) {
            this.distance = distance;
            this.point1 = point1;
            this.point2 = point2;
        }
    }

    public static String closestPairProblem(String input) {
        try {
            String[] pointsInput = input.split(";");
            Point[] points = new Point[pointsInput.length];

            for (int i = 0; i < pointsInput.length; i++) {
                String[] coordinates = pointsInput[i].split(",");
                if (coordinates.length != 2) throw new IllegalArgumentException("Invalid input format.");
                points[i] = new Point(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
            }

            Point[] sortedByX = points.clone();
            Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
            Point[] sortedByY = points.clone();
            Arrays.sort(sortedByY, Comparator.comparingDouble(p -> p.y));

            long startTime = System.nanoTime();
            Result result = closestPair(sortedByX, sortedByY);
            long endTime = System.nanoTime();
            long runningTime = endTime - startTime;

            return "Minimum Distance: " + result.distance + " between points " + result.point1 + " and " + result.point2 +
                    ", Running Time: " + runningTime + " nanoseconds";
        } catch (NumberFormatException e) {
            return "Error: Non-numeric value encountered. Please enter valid numbers.";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    private static Result closestPair(Point[] sortedByX, Point[] sortedByY) {
        if (sortedByX.length <= 3) {
            return bruteForce(sortedByX);
        }

        int mid = sortedByX.length / 2;
        Point midPoint = sortedByX[mid];

        Point[] leftOfCenter = Arrays.copyOfRange(sortedByX, 0, mid);
        Point[] rightOfCenter = Arrays.copyOfRange(sortedByX, mid, sortedByX.length);

        Point[] tempArray = new Point[sortedByY.length];
        int leftCount = 0, rightCount = 0;

        for (Point point : sortedByY) {
            if (point.x < midPoint.x || (point.x == midPoint.x && leftCount < leftOfCenter.length)) {
                tempArray[leftCount++] = point;
            } else {
                tempArray[mid + rightCount++] = point;
            }
        }

        Point[] leftSortedY = Arrays.copyOfRange(tempArray, 0, leftCount);
        Point[] rightSortedY = Arrays.copyOfRange(tempArray, leftCount, leftCount + rightCount);

        Result leftResult = closestPair(leftOfCenter, leftSortedY);
        Result rightResult = closestPair(rightOfCenter, rightSortedY);

        Result minResult = leftResult.distance < rightResult.distance ? leftResult : rightResult;

        return closestSplitPair(sortedByX, sortedByY, minResult);
    }

    private static Result closestSplitPair(Point[] sortedByX, Point[] sortedByY, Result delta) {
        int mid = sortedByX.length / 2;
        Point midPoint = sortedByX[mid];

        Point[] strip = Arrays.stream(sortedByY)
                .filter(point -> Math.abs(point.x - midPoint.x) < delta.distance)
                .toArray(Point[]::new);

        Result minResult = delta;

        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < minResult.distance; j++) {
                double dist = distance(strip[i], strip[j]);
                if (dist < minResult.distance) {
                    minResult = new Result(dist, strip[i], strip[j]);
                }
            }
        }

        return minResult;
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    private static Result bruteForce(Point[] points) {
        Result minResult = new Result(Double.POSITIVE_INFINITY, null, null);
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = distance(points[i], points[j]);
                if (dist < minResult.distance) {
                    minResult = new Result(dist, points[i], points[j]);
                }
            }
        }
        return minResult;
    }
}
