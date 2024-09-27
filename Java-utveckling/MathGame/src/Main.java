import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Function             - Priority
        // Create new player    - high
        // Load player          - low
        // Save player          - low
        // Start new game       - high
        // Change difficulty    - mid
        // Change name          - mid
        // Exit game            - high

        Player player = createNewPlayer();
        while(true){
            int userOption = mainMenu(player);
            switch (userOption){
                case 1 -> newGame(player);
                case 2 -> changeDifficulty(player);
                case 3 -> changeName(player);
                case 4 -> exitGame(player);
            }
        }
    }

    public static void newGame(Player player){
        // While correct
            // Create problem
            // Set timer
            // Take answers until time out
            // Give points
        // If timeout
            // If score > highscore, save new highscore
            // Write out players score and highscore
            // Reset player.currentScore

        boolean gameOver = false;
        int secondsToAnswer = 5;
        MathProblemBuilder problemBuilder = new MathProblemBuilder(player);

        while (true){
            Problem problem = problemBuilder.createProblem();
            long timer = System.currentTimeMillis();
            boolean correctAnswer = false;
            while (!correctAnswer){
                long currentTime = System.currentTimeMillis();
                int secondsElapsed = (int) ((currentTime - timer) / 1000);
                presentProblem(problem.problem(), secondsToAnswer - secondsElapsed);

                int userAnswer = getUserAnswer();
                correctAnswer = problem.correctSolution(userAnswer);

                currentTime = System.currentTimeMillis();
                if ((currentTime - timer) / 1000 > secondsToAnswer){
                    gameOver = true;
                    System.out.println("Times up!");
                    break;
                }

                if (correctAnswer){
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong, try again!");
                }
            }
            if (gameOver){
                break;
            }
            long currentTime = System.currentTimeMillis();
            int timeElapsed = (int) ((currentTime - timer) / 1000);
            secondsToAnswer -= timeElapsed;
            secondsToAnswer += 5;

            int roundScore = 1;
            if (timeElapsed < 6){
                roundScore++;
            }
            if (timeElapsed < 3){
                roundScore += 2;
            }
            player.addCurrentScore(roundScore);
            problemBuilder.updateDifficultyScore();
        }
        boolean newHighScore = player.getHighScore() < player.getCurrentScore();
        if (newHighScore){
            player.setHighScore(player.getCurrentScore());
            System.out.println("Congratulations! New Highscore: " + player.getHighScore());
        } else {
            System.out.println("You scored " + player.getCurrentScore() + "! Try again to beat your highscore!");
        }
        player.setCurrentScore(0);
    }

    public static int getUserAnswer(){
        Scanner scanner = new Scanner(System.in);
        int answer;
        while (true) {
            try {
                answer = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.next();
            }
        }
        return answer;
    }

    public static void presentProblem(String problem, int secondsToAnswer){
        System.out.println(secondsToAnswer + " seconds to go.");
        System.out.print(problem + ":\t");
    }

    public static void changeDifficulty(Player player){
        System.out.print("Choose your difficulty\n1 = Easy\n2 = Medium\n3 = Hard\n");
        Scanner scanner = new Scanner(System.in);
        try {
            int difficulty = scanner.nextInt();
            switch (difficulty){
                case 1 -> player.setDifficulty(1);
                case 2 -> player.setDifficulty(2);
                case 3 -> player.setDifficulty(3);
                default -> {
                    System.out.println("Invalid difficulty");
                    changeDifficulty(player);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid difficulty");
            scanner.next();
            changeDifficulty(player);
        }
    }

    public static void changeName(Player player){
        System.out.print("Change your name from " + player.getName() + " to:\t");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        player.setName(name);
    }

    public static void exitGame(Player player){
        System.out.println("Thank you " + player.getName() + " for playing!");
        System.exit(0);
    }

    public static int mainMenu(Player player){
        Scanner scanner = new Scanner(System.in);
        mainMenuPrompt(player);
        String input = scanner.nextLine();
        return switch (input){
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            default -> -1;
        };
    }

    public static void mainMenuPrompt(Player player){
        System.out.println("Welcome " + player.getName() + "! Current difficulty is " + player.getDifficulty() +
                " and current highscore is: " + player.getHighScore() + "!\n" +
                "1\tNew game\n" +
                "2\tChange difficultry\n" +
                "3\tChange name\n" +
                "4\tExit game");
    }

    public static Player createNewPlayer(){
        String name;
        int difficulty;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name:\t");
        name = scanner.nextLine();
        while(true){
            System.out.println("Enter your difficulty\n1 = easy\n2 = medium\n3 = hard");
            try {
                difficulty = scanner.nextInt();
                if (difficulty == 1 || difficulty == 2 || difficulty == 3){
                    break;
                }
                System.out.println("Invalid input");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.next();
            }
        }
        return new Player(name, difficulty);
    }
}