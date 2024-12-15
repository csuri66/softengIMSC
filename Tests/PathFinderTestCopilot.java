import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PathFinderTestCopilot {

    private PathFinder pathFinder;

    @BeforeEach
    void setUp() {
        pathFinder = new PathFinderImpl(); // Assuming PathFinderImpl is the implementation of PathFinder
    }

    // Jó teszt
    //Chat GPT: jó kimenet
    //Qwen: jó kimenet
    //Copilot: jó kimenet
    @Test
    void testAddEdgeValid() {
        assertDoesNotThrow(() -> pathFinder.addEdge(1, 2, 1));
        assertDoesNotThrow(() -> pathFinder.addEdge(2, 3, 0));
    }

    // Jó teszt
    //Chat GPT: jó kimenet
    //Qwen: jó kimenet
    //Copilot: jó kimenet
    @Test
    void testAddEdgeInvalidWeight() {
        assertThrows(IllegalArgumentException.class, () -> pathFinder.addEdge(1, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> pathFinder.addEdge(1, 2, -1));
    }

    // Jó teszt
    //Chat GPT: jó kimenet
    //Qwen: jó kimenet
    //Copilot: nem érkezett Exception
    @Test
    void testAddEdgeAlreadyPresent() {
        pathFinder.addEdge(1, 2, 1);
        assertThrows(IllegalStateException.class, () -> pathFinder.addEdge(1, 2, 1));
    }

    // Rossz teszt
    //Chat GPT: jó exception dobva
    //Qwen: jó exception dobva
    //Copilot: jó exception dobva
    @Test
    void testGetShortestPathLength() {
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 1);
        pathFinder.addEdge(1, 3, 1);
        assertEquals(1, pathFinder.getShortestPathLength(1, 3));
    }

    // Jó teszt
    //Chat GPT: jó kimenet
    //Qwen: jó kimenet
    //Copilot: jó kimenet
    @Test
    void testGetShortestPathLengthNoPath() {
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 1);
        assertEquals(-1, pathFinder.getShortestPathLength(1, 4));
    }
}
