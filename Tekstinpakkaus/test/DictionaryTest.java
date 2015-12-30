
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tools.EncodingDictionary;
import tools.Nodes;

public class DictionaryTest {
    
    private Nodes testlist;
    private EncodingDictionary dictionary;
    
    public DictionaryTest() {
    }
    
    @Before
    public void setUp() {
        testlist = new Nodes();
        dictionary = new EncodingDictionary();
     
    }
    
    @Test
    public void addingNodes() {
        int a = -127;
        int b = 127;
        testlist.push(a);
        testlist.push(b);
        dictionary.add(1,testlist);
    }
    
    @Test
    public void dictionaryContainsSameNodes() {
        int a = -127;
        int b = 127;
        testlist.push(a);
        testlist.push(b);
        dictionary.add(1,testlist);
        Nodes second = new Nodes();
        second.push(a);
        second.push(b);
        boolean t = dictionary.contains(second);
        assertTrue(t);
    }
    
    @Test
    public void addBytes() {
        Nodes <Byte> bytelist = new Nodes();
        byte a = -127;
        byte b = 127;
        bytelist.push(a);
        bytelist.push(b);
        dictionary.add(1,bytelist);
        int code = dictionary.getCode(bytelist);
        assertTrue(code == 1);
    }
    
    @Test
    public void dictionaryCanHaveNodesInDifferentOrder() {
        int a = -127;
        int b = 127;
        testlist.push(a);
        testlist.push(b);
        dictionary.add(1,testlist);
        Nodes second = new Nodes();
        second.push(b);
        second.push(a);
        dictionary.add(2 ,second);
        boolean t = dictionary.contains(second);
        assertTrue(t);
    }
    
    @Test
    public void dictionaryHasTwoNodesStartingWithSameNumbers() {
        int a = -127;
        int b = 127;
        int c = 0;
        int d = 10;
        testlist.push(a);
        testlist.push(b);
        testlist.push(c);
        dictionary.add(1,testlist);
        Nodes second = new Nodes();
        second.push(a);
        second.push(b);
        second.push(c);
        second.push(d);
        dictionary.add(2 ,second);
        boolean t = dictionary.contains(second);
        assertTrue(t);
    }
    
    @Test
    public void getRightCode() {
        int a = -127;
        int b = 127;
        testlist.push(a);
        testlist.push(b);
        dictionary.add(1,testlist);
        Nodes second = new Nodes();
        second.push(a);
        second.push(b);
        int code = dictionary.getCode(second);
        assertTrue(code == 1);
    }
}
