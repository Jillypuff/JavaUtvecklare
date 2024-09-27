import java.io.*;
import java.util.Random;

public class Dice {

    public static void createFile(){
        Random random = new Random();

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("dicerolls.txt")))){
            for(int i = 0; i < 1000; i++){
                int roll = random.nextInt(1, 7);
                printWriter.print(roll + " ");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int[] createArray(){
        int[] result = new int[1000];
        try (BufferedReader data = new BufferedReader(new FileReader("dicerolls.txt"))){
            while(true){
                String line = data.readLine();
                if (line == null){
                    break;
                }
                for (int i = 0; i < line.length(); i += 2){
                    result[i/2] = line.charAt(i) - '0'; // - '0' för att tvinga int istället för ASCII
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public static int[] analyseArray(int[] data){
        int[] result = new int[6];
        for (int i : data){
            result[i - 1]++;
        }
        return result;
    }
}
