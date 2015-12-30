package tekstinpakkaus;

import FileIO.FileRead;
import LZW.Compressor;
import LZW.Decompressor;
import java.io.IOException;
import java.util.ArrayList;

public class Testaus {
    
    public static void testaa() throws IOException {
        System.out.println("Aleksis Kivi, Seitsemän veljestä (suomeksi)");
        testi("Kivi.txt");
        System.out.println("Shakespeare, Hamlet (englanniksi)");
        testi("HamletENG.txt");
        System.out.println("Shakespeare, Hamlet (suomeksi)");
        testi("HamletFIN.txt");
        System.out.println("Shakespeare, Hamlet (ranskaksi)");
        testi("HamletFRE.txt");
        System.out.println("Täysin satunnainen tiedosto");
        testi("random.txt");
        System.out.println("James Joyce, Ulysses (englanniksi)");
        testi("Ulysses.txt");
        System.out.println("Hanshangmengren (kiinaksi)");
        testi("Hanshangmengren.txt");
    }
    
    private static void testi (String filename) throws IOException {    
        String output = "pakkaus.txt";
        for (int i = 0; i < 10; i++) {
            System.out.println((i+1)+". pakkaustesti:");
        try {
            Compressor pakkaus = new Compressor(filename, "pakkaus.txt");
            
            pakkaus.compress();                   
                }
        catch (Exception e) {
            System.out.println("Testitiedostoa ei löytynyt!");
            }
        }
            FileRead fr = new FileRead(filename);
            long filesize = fr.filesize();
            System.out.println("Pakattavan tiedoston koko on "+filesize+" tavua");          
            fr = new FileRead(output);        
            long packsize = fr.filesize();
            double shrink = 100*(1-(double)packsize/filesize);
            System.out.println("Pakatun tiedoston koko on "+packsize+" tavua");
            System.out.println("Alkuperäinen tiedosto kutistui "+String.format("%.0f%%",shrink)+" prosenttia"); 
          
            
        for (int i = 0; i < 10; i++) {
            System.out.println((i+1)+". purkutesti:");
        try {
            Decompressor purku = new Decompressor("pakkaus.txt", "purettu.txt");
            purku.decompress();
            }
        catch (Exception e) {
            System.out.println("Testitiedostoa ei löytynyt!");
            }
        }
    }
}
