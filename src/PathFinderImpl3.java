import java.util.*;

public class PathFinderImpl3 implements PathFinder {
    private final Map<Integer, Map<Integer, Integer>> adjList = new HashMap<>();

    @Override
    public void addEdge(int from, int to, int weight) {
        if (weight != 0 && weight != 1) {
            throw new IllegalArgumentException("Edge weight must be 0 or 1");
        }
        adjList.computeIfAbsent(from, k -> new HashMap<>());
        if (adjList.get(from).containsKey(to)) {
            throw new IllegalStateException("Edge already present");
        }
        adjList.get(from).put(to, weight);
    }

    @Override
    public int getShortestPathLength(int source, int target) {
        if (source == target) {
            return 0;
        }
        if (!adjList.containsKey(source)) {
            return -1; // Source node doesn't exist
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        pq.add(new int[]{source, 0});
        distances.put(source, 0);

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            // Debug output
            System.out.println("Visiting node: " + currentNode + " with current distance: " + currentDistance);

            if (!visited.add(currentNode)) {
                continue;
            }

            if (currentNode == target) {
                return currentDistance;
            }

            if (!adjList.containsKey(currentNode)) {
                continue; // Skip nodes with no neighbors
            }

            for (Map.Entry<Integer, Integer> neighbor : adjList.get(currentNode).entrySet()) {
                int nextNode = neighbor.getKey();
                int newDist = currentDistance + neighbor.getValue();

                // Debug output
                System.out.println("Evaluating neighbor: " + nextNode + " with new distance: " + newDist);

                if (newDist < distances.getOrDefault(nextNode, Integer.MAX_VALUE)) {
                    distances.put(nextNode, newDist);
                    pq.add(new int[]{nextNode, newDist});
                }
            }
        }
        return -1; // Path not found
    }
}
