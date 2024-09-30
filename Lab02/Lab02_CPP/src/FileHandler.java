import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;

public class FileHandler {
    private String fileName;

    public String getFileName() {return fileName;}
    public void setFileName(String fileName) {this.fileName = fileName;}
    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public String readFile() throws IOException {

        FileReader fr = null;

        do{
            try {
                fr = new FileReader(fileName);
            } catch (FileNotFoundException e) {
                writeFile(fileName);
            }
        }while (fr == null);

        int c;
        String data = new String();

        while ((c = fr.read()) != -1) {
            data += (char) c;
        }
        fr.close();

        return data;
    }

    public void writeFile(String data) throws IOException {
        FileWriter fw = new FileWriter(fileName);

        for(int i = 0; i < data.length(); i++){
            fw.write(data.charAt(i));
        }

        fw.close();
    }
}
