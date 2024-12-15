import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTestChatGPT {

    private PathFinder pathFinder;

    @BeforeEach
    void setUp() {
        // Initialize the implementation of PathFinder.
        pathFinder = new PathFinderImpl();
    }

    // Jó teszt
    //Chat GPT: jó kimenet
    //Qwen: jó kimenet
    //Copilot: jó kimenet
    @Test
    void testAddEdge_ValidEdge() {
        assertDoesNotThrow(() -> pathFinder.addEdge(1, 2, 1));
    }

    // Jó teszt, de nem konzisztens a hibaüzenet
    //Chat GPT: nem konzisztens az implementációban az Exception üzenettel
    //Qwen: nem konzisztens az implementációban az Exception üzenettel
    //Qwen: nem konzisztens az implementációban az Exception üzenettel
    @Test
    void testAddEdge_InvalidWeight() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pathFinder.addEdge(1, 2, 5); // Invalid weight (not 0 or 1)
        });
        // No specific exception message required as per the interface, so skipping assertEquals for message
    }

    // Jó teszt
    //Chat GPT: nem konzisztens az implementációban az Exception üzenettel
    //Qwen:  jó kimenet
    //Copilot: a kód nem megfelelően dob Exceptiont
    @Test
    void testAddEdge_DuplicateEdge() {
        pathFinder.addEdge(1, 2, 1); // Add a valid edge
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            pathFinder.addEdge(1, 2, 1); // Add the same edge again
        });
        // No specific exception message required as per the interface, so skipping assertEquals for message
    }

    // Jó teszt
    //Chat GPT: jó kimenetel
    //Qwen:  jó kimenetel
    //Copilot: jó kimenetel
    @Test
    void testGetShortestPathLength_SimplePath() {
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 0);
        pathFinder.addEdge(1, 3, 1);

        assertEquals(1, pathFinder.getShortestPathLength(1, 3));
    }

    // Jó teszt
    //Chat GPT: jó kimenetel
    //Qwen:  jó kimenetel
    //Copilot: jó kimenetel
    @Test
    void testGetShortestPathLength_NoPathExists() {
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(3, 4, 0);

        assertEquals(-1, pathFinder.getShortestPathLength(1, 4));
    }

    // Jó teszt
    //Chat GPT: elkapott hiba
    //Qwen: jó kimenetel
    //Copilot: jó kimenetel
    @Test
    void testGetShortestPathLength_ZeroWeightPath() {
        pathFinder.addEdge(1, 2, 0);
        pathFinder.addEdge(2, 3, 0);
        pathFinder.addEdge(1, 3, 1);
        // Chat GPT: A kód nem jól választja ki a legrövidebb utat
        assertEquals(0, pathFinder.getShortestPathLength(1, 3));
    }

    // Jó teszt
    //Chat GPT: elkapott hiba
    //Qwen: jó kimenetel
    //Copilot: jó kimenetel
    @Test
    void testGetShortestPathLength_SameNode() {
        // Chat GPT: Rossz kimeneti érték, a kódnak 0 értéket kellene visszaadjon
        assertEquals(0, pathFinder.getShortestPathLength(1, 1)); // Same source and target
    }

    // Jó teszt
    //Chat GPT: jó kimenetel
    //Qwen: jó kimenetel
    //Copilot: jó kimenetel
    @Test
    void testAddEdge_AndShortestPathComplexGraph() {
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 0);
        pathFinder.addEdge(3, 4, 1);
        pathFinder.addEdge(1, 4, 1);

        assertEquals(1, pathFinder.getShortestPathLength(1, 4));
    }
}