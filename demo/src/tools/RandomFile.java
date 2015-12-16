package tools;

import FileIO.FileWrite;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RandomFile {
    
    public RandomFile (int size) throws FileNotFoundException, IOException {
        FileWrite fw = new FileWrite("random.txt");

        for (int i = 0; i < size; i++) {
            int character = (int)(Math.random() * 256) -127;
            byte b = (byte)character;
            fw.write(b);
        }
    }
    
    
}
