package tekstinpakkaus;

import FileIO.FileRead;
import LZW.Compressor;
import LZW.Decompressor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tools.ByteTranslator;
import tools.RandomFile;

public class Tekstinpakkaus {

    public static void main(String[] args) throws IOException {
        
        long filesize = 0;
        long packsize = 0;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("LZW-tekstitiedostonpakkausohjelma");
        
        while(true) {
        System.out.println("\n1 - Pakkaa tiedosto");
        System.out.println("2 - Pura tiedosto");
        System.out.println("3 - Lopeta ohjelma");
        
        System.out.print("\nValintanne herra/rouva: ");
        
        String choice = "";
        while (true) {
            choice = sc.nextLine();
            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) break;
            System.out.println("Valitse joko 1, 2 tai 3");
        }
        
        switch(choice) {
            case "1":
        
                System.out.print("Anna pakattavan tiedoston nimi: ");       
                String input = sc.nextLine();

                System.out.print("Anna pakkaukselle tiedostonimi: ");       
                String output = sc.nextLine();
                
                try {
                    Compressor pakkaus = new Compressor(input, output);
                    FileRead fr = new FileRead(input);
                    filesize = fr.filesize();
                    System.out.println("Pakattavan tiedoston koko on "+filesize+" tavua");
                    ArrayList pakattu = new ArrayList();
                    pakattu = pakkaus.compress();
                
                    fr = new FileRead(output);
                    packsize = fr.filesize();
                    double shrink = 100*(1-(double)packsize/filesize);
                    System.out.println("Pakatun tiedoston koko on "+packsize+" tavua");
                    System.out.println("Alkuperäinen tiedosto kutistui "+String.format("%.0f%%",shrink)+" prosenttia");               
                }
                catch (Exception e) {
                    System.out.println("Tiedostovirhe, yritä uudelleen");
                }
                 
                break;
            
            case "2":

                System.out.print("Anna purettavan tiedoston nimi: ");       
                input = sc.nextLine();
        
                System.out.print("Anna tallennustiedoston nimi: ");       
                output = sc.nextLine();
                
                try {
                    Decompressor purku = new Decompressor(input, output);
                    ArrayList purettu = purku.decompress();
                
                    FileRead fr = new FileRead(output);
                    filesize = fr.filesize();
                    System.out.println("Palautetun tiedoston koko on "+filesize+" tavua");
                    }
                catch (Exception e) {
                    System.out.println("Tiedostovirhe, yritä uudelleen");
                }
                break;
            
            case "3":
                
                System.exit(0);
        }
        }
    }  
}
