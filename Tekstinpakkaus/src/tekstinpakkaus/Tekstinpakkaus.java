package tekstinpakkaus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Tekstinpakkaus {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        
        byte [] teksti = FileIO.read();
        for (int i = 0; i<teksti.length; i++) {
            System.out.print(teksti[i]+" ");
        }
        FileIO.write(teksti);
        LZW pakkaus = new LZW(teksti);
        ArrayList pakattu = new ArrayList();
        pakattu = pakkaus.compress();
        System.out.println("pakattu "+pakattu);
        LZW purku = new LZW(pakattu);
        ArrayList purettu = purku.decompress();
        System.out.println("purettu "+purettu);
        byte[] ba = new byte[purettu.size()];
        for(int i = 0; i<purettu.size(); i++){
            ba[i] = (byte)purettu.get(i);
        }
        String value = new String(ba, "UTF-8");
        System.out.println("value "+value);
    }  
}
