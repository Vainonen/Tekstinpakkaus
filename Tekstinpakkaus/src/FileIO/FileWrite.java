package FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Kirjoittaa pakattuja ja purettuja tiedostoja levylle.
 */
public class FileWrite {
    
    private FileOutputStream fos;
    private File outputFile;
    
    /**
    * @param filename Tallennettavan tiedoston nimi.
    * @throws java.io.FileNotFoundException
    */
    public FileWrite (String filename) throws FileNotFoundException {
        this.outputFile = new File(filename);
        byte[] data = new byte[(int)this.outputFile.length()];
        /*
        if (outputFile.exists()) {
            System.out.println("Tiedosto on jo olemassa, valitse toinen nimi.");
            return;
        }
        */
        this.fos = new FileOutputStream(outputFile);
    }
    
    /**
    * Kirjoittaa tavutaulukon tekstitiedostoon. 
    * @param data Tiedostoon kirjoittimelle syötteenä tavu.
    * @throws java.io.FileNotFoundException
    */
    public void write(byte data) throws FileNotFoundException, IOException {
        fos.write(data); 
    }
    
    public void close() throws IOException {
        fos.flush();
        fos.close();
    }
    
}
