
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
        assertTrue(testlist.getFirst().getValue().equals(1));
    }
    
    @Test
    public void testRemovingNodes() {
        int value = 2;
        testlist.push(value);
        testlist.pop();
        assertTrue(testlist.getFirst() == null);
    }
    
    @Test
    public void testFirstValue() {
        int value1 = 1;
        int value2 = 2;
        testlist.push(value1);
        testlist.push(value2);
        int value = (int)testlist.getFirst().getValue();
        //assertTrue(testlist.getFirst().getValue() == 1));
        assertTrue(value == 1);
    }
    
    @Test
    public void testFirstValueAfterPopping() {
        int value1 = 1;
        int value2 = 2;
        testlist.push(value1);
        testlist.push(value2);
        testlist.pop();
        int value = (int)testlist.getFirst().getValue();
        //assertTrue(testlist.getFirst().getValue() == 1));
        assertTrue(value == 1);
    }
    
    @Test
    public void testRemovingTooMuchNodes() {
        int value = 2;
        testlist.push(value);
        testlist.pop();
        testlist.pop();
        assertTrue(testlist.getFirst() == null);
    }

    @Test
    public void testSize() {
        int value = 2;
        testlist.push(value);
        testlist.push(value);
        testlist.pop();
        assertTrue(testlist.Size() == 1);
    }
    
    @Test
    public void testEquals() {
        Nodes equallist = new Nodes();
        int value1 = 1;
        int value2 = 1;
        int value3 = 2;
        int value4 = 2;
        testlist.push(value1);
        equallist.push(value2);
        testlist.push(value3);
        equallist.push(value4);
        boolean e = testlist.equals(equallist);
        assertTrue(e);
    }
    
    @Test
    public void testNotEquals() {
        Nodes equallist = new Nodes();
        int value1 = 1;
        int value2 = 1;
        testlist.push(value1);
        equallist.push(value2);
        equallist.push(value1);
        boolean e = testlist.equals(equallist);
        assertFalse(e);
    }
}
