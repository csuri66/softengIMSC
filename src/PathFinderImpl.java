import java.util.*;

public class PathFinderImpl implements PathFinder {
    // Adjacency list to store the graph
    private final Map<Integer, Map<Integer, Integer>> graph;

    public PathFinderImpl() {
        this.graph = new HashMap<>();
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        // Validate the weight
        if (weight != 0 && weight != 1) {
            throw new IllegalArgumentException("Edge weight must be either 0 or 1.");
        }

        // Check if the edge already exists
        graph.putIfAbsent(from, new HashMap<>());
        if (graph.get(from).containsKey(to)) {
            throw new IllegalStateException("Edge already exists in the graph.");
        }

        // Add the edge
        graph.get(from).put(to, weight);
    }

    @Override
    public int getShortestPathLength(int source, int target) {
        if (source == target) {
            return 0; // Shortest path to itself is 0
        }

        // Use a priority queue for Dijkstra's algorithm-like approach
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Set<Integer> visited = new HashSet<>();
        queue.add(new int[]{source, 0}); // {node, cumulative distance}

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];

            // If the node has already been visited, skip it
            if (visited.contains(node)) {
                continue;
            }

            visited.add(node);

            // If we reached the target, return the distance
            if (node == target) {
                return distance;
            }

            // Explore neighbors
            if (graph.containsKey(node)) {
                for (Map.Entry<Integer, Integer> neighbor : graph.get(node).entrySet()) {
                    int nextNode = neighbor.getKey();
                    int weight = neighbor.getValue();

                    if (!visited.contains(nextNode)) {
                        queue.add(new int[]{nextNode, distance + weight});
                    }
                }
            }
        }

        return -1; // No path found
    }
}