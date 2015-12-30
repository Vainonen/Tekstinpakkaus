import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tools.DecodingDictionary;
import tools.Nodes;

public class DecodingDictionaryTest {
    
    private Nodes testlist;
    private DecodingDictionary dictionary;
    
    public DecodingDictionaryTest() {
    }
    
    @Before
    public void setUp() {
        testlist = new Nodes();
        dictionary = new DecodingDictionary();
     
    }
    
    @Test
    public void dictionaryContainsCode() {
        int a = -127;
        int b = 127;
        testlist.push(a);
        testlist.push(b);
        dictionary.add(1,testlist);
        boolean t = dictionary.contains(1);
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
        Nodes searched = dictionary.getValue(1);
        boolean t = searched.equals(bytelist);
        assertTrue(t);
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
        dictionary.add(2,second);
        boolean t = dictionary.contains(2);
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
        boolean t = dictionary.contains(2);
        assertTrue(t);
    }
    
    @Test
    public void getRightValue() {
        int a = -127;
        int b = 127;
        testlist.push(a);
        testlist.push(b);
        dictionary.add(1,testlist);
        Nodes second = new Nodes();
        second.push(a);
        second.push(b);
        dictionary.add(2,second);
        Nodes searched  = dictionary.getValue(2);
        boolean t = searched.equals(second);
        assertTrue(t);
    }
    
    @Test
    public void dictionaryHasNotUnaddedCode() {
        int a = -127;
        int b = 127;
        testlist.push(a);
        testlist.push(b);
        dictionary.add(1,testlist);
        boolean t = dictionary.contains(2);
        assertFalse(t);
    }
}
