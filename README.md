## Group Work DCIT 204 Assignment 5

   # Explanation of Quickhull Algorithm
       The Quickhull algorithm is a method of finding the convex hull of a set of points in a plane. 
       The convex hull is the smallest convex polygon that can contain all the given points. 
       uickhull works similarly to the QuickSort algorithm and operates recursively.
       Here’s a brief outline of the Quickhull algorithm:
          1.	Find Extremes: Identify the points with the minimum and maximum x-coordinates, which are guaranteed to be part of the convex hull.
          2.	Partition Points: Use a line passing through these two points to partition the set into two subsets of points, which are on either side of the line.
          3.	Recursive Steps: For each subset, find the point that is farthest from the line segment. This point forms a triangle with the line segment, and the points inside this triangle cannot be part of the convex hull.
          4.	Repeat: Recursively apply the above steps to the new line segments formed with the farthest points, until no more points are left to process.

 # Step-by-Step Explanation
                   1. quickhull(String input) method:

                   Parses the input string to extract points.
                   Calls the quickhull(List<Point> points) method to compute the convex hull.
                   Converts the resulting list of points to a string and returns it.
                    
                  2. parseInput(String input) method:

                        Splits the input string into individual point strings.
                        Parses each point string to extract x and y coordinates.
                        Creates Point objects and adds them to a list.
                        Returns the list of points.
                  3.quickhull(List<Point> points) method:

                            Initializes the convex hull list.
                            Finds the leftmost (minPoint) and rightmost (maxPoint) points.
                            Adds these points to the convex hull.
                            Splits the remaining points into two sets: points to the left and right of the line formed by minPoint and maxPoint.
                            Recursively finds the hull for these two sets.
                            
                4. findHull(List<Point> hull, List<Point> points, Point p1, Point p2) method:
                
                     Finds the farthest point from the line p1 to p2.
                     Adds this point to the hull.
                     Splits the remaining points into two sets: points to the left of the lines formed by p1 to farthestPoint and farthestPoint to p2.
                     Recursively finds the hull for these two sets.
                     
               5. Helper methods:
               
                    isLeftOfLine(Point p1, Point p2, Point p): Determines if a point p is to the left of the line formed by p1 and p2.
                    distanceFromLine(Point p1, Point p2, Point p): Computes the perpendicular distance of point p from the line formed by p1 and p2.
                    pointsToString(List<Point> points): Converts a list of points to a string.
# Screenshot of the App
![image](https://github.com/user-attachments/assets/298914b4-a8f9-4acc-b73f-4978a9e4fef3)
![image](https://github.com/user-attachments/assets/0dbd72fe-57b9-4e26-8bfa-741f5ee50e2e)
![image](https://github.com/user-attachments/assets/d9fc9d31-b464-45bc-ba09-153b542f611b)


