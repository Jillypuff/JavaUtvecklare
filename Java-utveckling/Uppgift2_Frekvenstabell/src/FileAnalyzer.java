import java.io.*;

public class FileAnalyzer {


    public static int charsInFile(String fileName) {
        int sum = 0;
        try (BufferedReader data = new BufferedReader(new FileReader(fileName))) {
            while(true){
                String line = data.readLine();
                if (line == null){
                    break;
                }
                sum += line.length();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return sum;
    }
}
