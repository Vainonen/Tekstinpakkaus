package tekstinpakkaus;

import LZW.Compressor;
import LZW.Decompressor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tekstinpakkaus {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Anna pakattavan tiedostonimi: ");       
        String name = sc.nextLine();
        byte [] teksti = FileIO.read(name);
   
        System.out.println("");
        System.out.println("koko "+teksti.length);
    
        Compressor pakkaus = new Compressor(teksti);
        ArrayList pakattu = new ArrayList();
        pakattu = pakkaus.compress();
        System.out.println("pakattu "+pakattu);
        System.out.println("pakkauksen koko"+pakattu.size());
        Decompressor purku = new Decompressor(pakattu);
        ArrayList purettu = purku.decompress();
        System.out.println("purettu  "+purettu);
        byte[] ba = new byte[purettu.size()];
        for(int i = 0; i<purettu.size(); i++){
            ba[i] = (byte)purettu.get(i);
        }

        FileIO.write("testi2.txt", ba);
    }  
}
