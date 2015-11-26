package tekstinpakkaus;

import FileIO.FileRead;
import LZW.Compressor;
import LZW.Decompressor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tools.ByteTranslator;

public class Tekstinpakkaus {

    public static void main(String[] args) throws IOException {
         
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Anna pakattavan tiedoston nimi: ");       
        String input = sc.nextLine();

        System.out.println("Anna pakkaukselle tiedostonimi: ");       
        String output = sc.nextLine();
       
        Compressor pakkaus = new Compressor(input, output);
        ArrayList pakattu = new ArrayList();
        pakattu = pakkaus.compress();
        
        System.out.println("Anna purettavan tiedoston nimi: ");       
        input = sc.nextLine();
        
        System.out.println("Anna tallennustiedoston nimi: ");       
        output = sc.nextLine();
        
        Decompressor purku = new Decompressor(input, output);
        ArrayList purettu = purku.decompress();

    }  
}
