import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileManager {
    public ArrayList<String> FileReader(String path){
        Path F = Paths.get(path);  // path string
        ArrayList<String> arrayList = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader Reader = Files.newBufferedReader(F, charset)) {
            String Line = null;
            while ((Line = Reader.readLine()) != null) {
                if(Line.isEmpty()){
                    continue;
                }
                arrayList.add(Line);
            }
            return arrayList;
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
            return null ;
        }
    }
    public  void FileWriter(String path , ArrayList<String> answer){
        Path File = Paths.get(path);  // path string
        Charset charset = Charset.forName("UTF-8");
        boolean del = true ;
        for (String ans : answer) {
            try (BufferedWriter writer = Files.newBufferedWriter(File, charset, StandardOpenOption.APPEND)) {
                if(del){
                    try(BufferedWriter writer1 = Files.newBufferedWriter(File, charset)) {
                        writer1.write("");
                        del = false ;
                    }
                }
                writer.write(ans + "\n");
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        } ;
    }
}