import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimplePathFinderTest {

    private PathFinder pathFinder;

    @BeforeEach
    void setUp() {
        pathFinder = new PathFinderImpl();
    }

    // Jó teszt
    //Chat GPT: jó kimenet
    //Qwen: jó kimenet
    //Copilot: nem dobott exceptiont
    @Test
    void testAddEdge() {
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 0);
        assertThrows(IllegalStateException.class, () -> pathFinder.addEdge(1, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> pathFinder.addEdge(3, 4, 2));
    }

    // Rossz teszt
    //Chat GPT: jól dobja az exceptiont
    //Qwen: jól dobja az exceptiont
    //Copilot: jól dobja az exceptiont
    @Test
    void testGetShortestPathLength() {
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 0);
        pathFinder.addEdge(1, 3, 1);
        pathFinder.addEdge(3, 4, 1);

        assertEquals(1, pathFinder.getShortestPathLength(1, 2));
        assertEquals(0, pathFinder.getShortestPathLength(2, 3));
        assertEquals(1, pathFinder.getShortestPathLength(1, 3)); // 1 -> 2 -> 3 (1 + 0 = 1)
        assertEquals(2, pathFinder.getShortestPathLength(1, 4)); // 1 -> 3 -> 4 (1 + 1 = 2)
        assertEquals(-1, pathFinder.getShortestPathLength(4, 1)); // No path from 4 to 1

        // Test with no path
        assertEquals(-1, pathFinder.getShortestPathLength(1, 5)); // No path from 1 to 5

        // Test with self-loop
        pathFinder.addEdge(1, 1, 0);
        assertEquals(0, pathFinder.getShortestPathLength(1, 1));
    }

    // Jó teszt
    //Chat GPT: jó kimenet
    //Qwen: jó kimenet
    //Copilot: jó kimenet
    @Test
    void testGetShortestPathLengthWithCycles() {
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 1);
        pathFinder.addEdge(3, 1, 1);

        assertEquals(1, pathFinder.getShortestPathLength(1, 2));
        assertEquals(2, pathFinder.getShortestPathLength(1, 3));
        assertEquals(-1, pathFinder.getShortestPathLength(1, 4));
    }

    // Jó teszt
    //Chat GPT: jó kimenet
    //Qwen: jó kimenet
    //Copilot: jó kimenet
    @Test
    void testGetShortestPathLengthWithNoPath() {
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 1);

        assertEquals(-1, pathFinder.getShortestPathLength(1, 4));
        assertEquals(-1, pathFinder.getShortestPathLength(3, 1));
    }

    // Jó teszt
    //Chat GPT: jó kimenet
    //Qwen: jó kimenet
    //Copilot: jó kimenet
    @Test
    void testGetShortestPathLengthWithSelfLoop() {
        pathFinder.addEdge(1, 1, 0);
        pathFinder.addEdge(1, 2, 1);

        assertEquals(1, pathFinder.getShortestPathLength(1, 2));
        assertEquals(0, pathFinder.getShortestPathLength(1, 1));
    }
}