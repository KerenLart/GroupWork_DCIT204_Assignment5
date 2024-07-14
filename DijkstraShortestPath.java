package assignment5;

import java.util.*;

public class DijkstraShortestPath {
    private static final int INF = Integer.MAX_VALUE;

    public static String dijkstra(String input, int source) {
        try {
            // Parse input string to create graph
            int[][] graph = parseInput(input);
            int V = graph.length;

            // Check if source node is valid
            if (source < 0 || source >= V) {
                return "Error: Invalid source node. Please enter a number between 0 and " + (V - 1) + ".";
            }

            int[] dist = new int[V];
            boolean[] sptSet = new boolean[V];

            Arrays.fill(dist, INF);
            dist[source] = 0;

            for (int count = 0; count < V - 1; count++) {
                int u = minDistance(dist, sptSet);
                sptSet[u] = true;

                for (int v = 0; v < V; v++) {
                    if (!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }

            return formatResult(dist);
        } catch (NumberFormatException e) {
            return "Error: Invalid input. Please enter only numbers separated by commas and semicolons.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static int[][] parseInput(String input) throws Exception {
        String[] rows = input.split(";");
        int[][] graph = new int[rows.length][rows.length];

        for (int i = 0; i < rows.length; i++) {
            String[] values = rows[i].split(",");
            if (values.length != rows.length) {
                throw new Exception("Invalid input: The graph must be square (same number of rows and columns).");
            }
            for (int j = 0; j < values.length; j++) {
                int value = Integer.parseInt(values[j].trim());
                if (value < 0) {
                    throw new Exception("Negative edges are not allowed in Dijkstra's algorithm.");
                }
                graph[i][j] = value;
            }
        }
        return graph;
    }

    private static int minDistance(int[] dist, boolean[] sptSet) {
        int min = INF, min_index = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    private static String formatResult(int[] dist) {
        StringBuilder result = new StringBuilder("Vertex \t Distance from Source\n");
        for (int i = 0; i < dist.length; i++) {
            result.append(i).append(" \t\t ").append(dist[i] == INF ? "INF" : dist[i]).append("\n");
        }
        return result.toString();
    }
}