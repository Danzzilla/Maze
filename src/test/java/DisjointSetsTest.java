import generation.DisjointSets;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * Test for disjointSets class
 * @author Daniel Svirida
 * @version 1
 */
public class DisjointSetsTest {

    private DisjointSets set;

    /**
     * Tests the Union method
     */
    @Test
    public void testUnion(){
        set = new DisjointSets(7);
        set.union(2, 3);
        set.union(2, 5);
        set.union(6, 0);

        assertEquals("DisjointSets{ [6, -1, -2, 2, -1, 2, -2]}", set.toString());

        set.union(2, 6);
        set.union(2, 4);

        assertEquals("DisjointSets{ [6, -1, -3, 2, 2, 2, 2]}", set.toString());
    }

    /**
     * Tests the find method
     */
    @Test
    public void testFind(){
        set = new DisjointSets(7);
        set.union(2, 3);
        set.union(2, 5);
        set.union(6, 0);
        set.union(2, 6);
        set.union(2, 4);
        assertEquals(2, set.find(5));
        assertEquals(2, set.find(0));
    }

    /**
     * Tests the sameSet method
     */
    @Test
    public void testSameSet(){
        set = new DisjointSets(7);
        set.union(2, 3);
        set.union(2, 5);
        set.union(6, 0);
        set.union(2, 6);
        set.union(2, 4);

        assertTrue(set.sameSet(5, 6));
        assertFalse(set.sameSet(1, 4));
    }
}
