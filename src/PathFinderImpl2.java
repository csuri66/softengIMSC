import java.util.*;

public class PathFinderImpl2 implements PathFinder {
    private final Map<Integer, List<int[]>> adjacencyList = new HashMap<>();

    @Override
    public void addEdge(int from, int to, int weight) {
        if (weight != 0 && weight != 1) {
            throw new IllegalArgumentException("Edge weight must be 0 or 1.");
        }
        for (int[] edge : adjacencyList.getOrDefault(from, Collections.emptyList())) {
            if (edge[0] == to) {
                throw new IllegalStateException("Edge already exists.");
            }
        }
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[]{to, weight});
    }

    @Override
    public int getShortestPathLength(int source, int target) {
        if (source == target) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> distances = new HashMap<>();
        queue.add(source);
        distances.put(source, 0);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            int currentDistance = distances.get(currentNode);

            for (int[] edge : adjacencyList.getOrDefault(currentNode, Collections.emptyList())) {
                int to = edge[0];
                int weight = edge[1];
                int newDistance = currentDistance + weight;
                if (!distances.containsKey(to) || distances.get(to) > newDistance) {
                    distances.put(to, newDistance);
                    queue.add(to);
                }
            }
        }

        return distances.getOrDefault(target, -1); // Return -1 if no path found
    }
}