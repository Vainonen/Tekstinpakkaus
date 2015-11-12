
import LZW.Compressor;
import LZW.Decompressor;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LZWtest {
    
    public LZWtest() {
    }
    
    @Test
    public void testScandicLetters() throws UnsupportedEncodingException {
        String s = "ääääääääääääääärjrtjrtjrtjrjrtjrtjrtjrtäjrtj";
        byte [] b = s.getBytes();
        Compressor pack = new Compressor(b);
        ArrayList packResult = new ArrayList();
        packResult = pack.compress();
        Decompressor unpack = new Decompressor(packResult);
        ArrayList unpackResult = unpack.decompress();
        byte[] ba = new byte[unpackResult.size()];
        for(int i = 0; i<unpackResult.size(); i++){
            ba[i] = (byte)unpackResult.get(i);
        }
        String test = new String(ba);
        assertTrue(test.equals(s));
    }
    
    @Test
    public void testEnglish() throws UnsupportedEncodingException {
        String s = "TOBEORNOTTOBEORTOBEORNOT";
        byte [] b = s.getBytes();
        Compressor pack = new Compressor(b);
        ArrayList packResult = new ArrayList();
        packResult = pack.compress();
        Decompressor unpack = new Decompressor(packResult);
        ArrayList unpackResult = unpack.decompress();
        byte[] ba = new byte[unpackResult.size()];
        for(int i = 0; i<unpackResult.size(); i++){
            ba[i] = (byte)unpackResult.get(i);
        }
        String test = new String(ba);
        assertTrue(test.equals(s));
    }
    
}