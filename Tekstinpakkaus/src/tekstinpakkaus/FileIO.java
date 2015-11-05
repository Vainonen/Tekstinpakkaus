package tekstinpakkaus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Lukee tekstitiedostoja pakkausta varten ja kirjoittaa tallentaa puretun tekstin.
 */
public class FileIO {
    
    /**
     * Lukee tekstitiedon ja muuntaa sen tavuiksi. 
     * @return Tavutaulukko.
     */
    public static byte[] read() throws FileNotFoundException, IOException {
        File inputFile = new File("testi.txt");
        byte[] data = new byte[(int)inputFile.length()];
        FileInputStream fis = new FileInputStream(inputFile);
        fis.read(data, 0, data.length);
        fis.close();
        return data;
    }
    
    /**
     * Kirjoittaa tavutaulukon tekstitiedostoon. 
     */
    public static void write(byte[] data) throws FileNotFoundException, IOException {
        File outputFile = new File("testi2.txt");
        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(data, 0, data.length);
        fos.flush();
        fos.close();
    }
}
