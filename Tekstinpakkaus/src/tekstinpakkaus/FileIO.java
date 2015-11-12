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
     * @param filename Tiedostonimi String-merkkijonona.
     * @return Tavutaulukko.
     * @throws java.io.IOException
     */
    public static byte[] read(String filename) throws FileNotFoundException, IOException {
        File inputFile = new File(filename);
        byte[] data = new byte[(int)inputFile.length()];
        try (FileInputStream fis = new FileInputStream(inputFile)) {
            fis.read(data, 0, data.length);
        }
        catch (Exception e) {
            System.out.println("Tiedostovirhe");
        }
        return data;
    }
    
    /**
     * Kirjoittaa tavutaulukon tekstitiedostoon. 
     * @param filename Tiedostonimi String-merkkijonona.
     * @param data Tiedostoon kirjoittimelle syötteenä tavutaulukko.
     * @throws java.io.FileNotFoundException
     */
    public static void write(String filename, byte[] data) throws FileNotFoundException, IOException {
        File outputFile = new File(filename);
        /*
        if (outputFile.exists()) {
            System.out.println("Tiedosto on jo olemassa, valitse toinen nimi.");
            return;
        }
        */
        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(data, 0, data.length);
        fos.flush();
        fos.close();
    }
}
