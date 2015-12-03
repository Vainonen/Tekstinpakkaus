
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tools.Node;
import tools.Nodes;

public class NodesTest {
    
    private Nodes testlist;
    
    public NodesTest() {
    }
    
    @Before
    public void setUp() {
        testlist = new Nodes();
    }
    
    @Test
    public void testAddingNodes() {
        int value = 1;
        testlist.push(value);
        assertTrue(testlist.getFirst().equals(1));
    }
    
    @Test
    public void testRemovingNodes() {
        int value = 2;
        testlist.push(value);
        testlist.pop();
        assertTrue(testlist.getFirst().equals(null));
    }
    
    @Test
    public void testFirstValue() {
        int value1 = 1;
        int value2 = 2;
        testlist.push(value1);
        testlist.push(value2);
        assertTrue(testlist.getFirst().equals(1));
    }   

}
