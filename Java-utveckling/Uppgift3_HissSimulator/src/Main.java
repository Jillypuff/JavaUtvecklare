import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Elevator> elevators = new ArrayList<>();
        int amountOfElevators = 5;
        while (elevators.size() < amountOfElevators){
            int startLevel = inputLevel("Enter what level elevator " +
                    (elevators.size() + 1) + " should start at: ");
            try {
                Elevator elevator = new Elevator(startLevel);
                elevators.add(elevator);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        elevatorInfo(elevators);
        for (Elevator elevator : elevators){
            while (true){
                int targetLevel = inputLevel("Move elevator from " + elevator.where() + " to: ");
                try {
                    elevator.goTo(targetLevel);
                    break;
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        elevatorInfo(elevators);
    }

    public static int inputLevel(String prompt){
        Scanner input = new Scanner(System.in);
        int level;
        while (true){
            System.out.print(prompt);
            try {
                level = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Enter only a number");
                input.nextLine();
            }
        }
        return level;
    }

    public static void elevatorInfo(ArrayList<Elevator> elevators){
        int counter = 1;
        for (Elevator elevator : elevators){
            System.out.println("Elevator " + counter + " is on level " + elevator.where());
            counter++;
        }
    }
}