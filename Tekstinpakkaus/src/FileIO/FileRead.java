package FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Lukee tekstitiedostoja pakkausta varten.
 */
public class FileRead {
    
    private FileInputStream fis;
    private File inputFile;
       
    public FileRead (String filename) throws FileNotFoundException {
        this.inputFile = new File(filename);
        this.fis = new FileInputStream(inputFile);
    }
    
    /**
     * Lukee tekstitiedon ja muuntaa sen tavuiksi. 
     * @return Tavutaulukko.
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public byte[] read() throws FileNotFoundException, IOException {
        byte[] data = new byte[(int)this.inputFile.length()];
        this.fis.read(data, 0, data.length);
        return data;
    }

}
