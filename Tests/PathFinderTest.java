import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {

    private PathFinder pathFinder;

    @BeforeEach
    void beforeEach() {
        pathFinder = new PathFinderImpl();
    }

    @Test
    void singleEdgeTest() {
        pathFinder.addEdge(0, 1, 1);
        assertEquals(1, pathFinder.getShortestPathLength(0, 1));
    }

    @Test
    void multipleEdgeTest() {
        pathFinder.addEdge(0, 1, 1);
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 1);
        pathFinder.addEdge(3, 4, 1);
        assertEquals(1, pathFinder.getShortestPathLength(2, 3));
    }

    @Test
    void badWeightEdgeTest() {
        assertThrows(IllegalArgumentException.class, () ->pathFinder.addEdge(0, 1, -1));
    }

    @Test
    void edgeAlreadyPresentTest() {
        pathFinder.addEdge(0, 1, 1);
        assertThrows(IllegalStateException.class, () ->pathFinder.addEdge(0, 1, 1));
    }

    @Test
    void noSuchPathTest() {
        pathFinder.addEdge(0, 1, 1);
        pathFinder.addEdge(2, 3, 1);
        assertEquals(-1,pathFinder.getShortestPathLength(0, 3));
    }

    @Test
    void multipleEdgePathTest() {
        pathFinder.addEdge(0, 1, 1);
        pathFinder.addEdge(1, 2, 1);
        pathFinder.addEdge(2, 3, 1);
        pathFinder.addEdge(3, 4, 1);
        assertEquals(3, pathFinder.getShortestPathLength(0, 3));
    }

    @Test
    void multipleEdgeWeightTest() {
        pathFinder.addEdge(0, 1, 1);
        pathFinder.addEdge(1, 2, 0);
        pathFinder.addEdge(2, 3, 0);
        pathFinder.addEdge(3, 4, 1);
        assertEquals(2, pathFinder.getShortestPathLength(0, 4));
    }
}