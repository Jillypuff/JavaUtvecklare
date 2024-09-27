import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*  Uppgift 2 Frekvenstabell över tärningskast
    Jesper Lindberg
*/

public class Main {
    public static void main(String[] args) {
        Dice.createFile();
        int[] dataDump = Dice.createArray();
        int[] analysedData = Dice.analyseArray(dataDump);
        for (int i = 0; i < analysedData.length; i++) {
            System.out.println((i + 1) + ": " + analysedData[i]);
        }

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("diceresults.txt")))){
            String prompt = String.format("Resultatet av 1000 rullade tärningar är\n1: %d\n2: %d\n3: " +
                    "%d\n4: %d\n5: %d\n6: %d", analysedData[0], analysedData[1], analysedData[2], analysedData[3],
                    analysedData[4], analysedData[5]);
            printWriter.print(prompt);
        } catch (IOException e){
            e.printStackTrace();
        }

        int sum = FileAnalyzer.charsInFile("diceresults.txt");
        System.out.println("Filen är " + sum + " karaktärer lång.");
    }
}