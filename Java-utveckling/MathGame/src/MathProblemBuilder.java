import java.util.*;

public class MathProblemBuilder {
    private final Player player;
    private int difficultyScore = 1;
    private ArrayList<Character> allowedOperators = new ArrayList<>();
    private final Random random = new Random();

    public MathProblemBuilder(Player playerStats) {
        this.player = playerStats;
        updateDifficultyScore();
    }

    public Problem createProblem(){
        char operator = randomOperator();
        int maxDigits = maxDigits(operator);
        int amountOfDigitsFirst = randomAmountOfDigits(maxDigits);
        int amountOfDigitsSecond = randomAmountOfDigits(maxDigits);
        int firstDigit = 0;
        int secondDigit = 0;
        for (int i = 0; i < amountOfDigitsFirst; i++) {
            if (firstDigit == 0) {
                firstDigit = random.nextInt(1, 10);
            } else {
                firstDigit *= 10;
                firstDigit += random.nextInt(10);
            }
        }
        for (int i = 0; i < amountOfDigitsSecond; i++) {
            if (secondDigit == 0) {
                secondDigit = random.nextInt(1,10);
            } else {
                secondDigit *= 10;
                secondDigit += random.nextInt(10);
            }
        }
        if (operator == '%' && firstDigit < secondDigit) {
            firstDigit = firstDigit ^ secondDigit;
            secondDigit = firstDigit ^ secondDigit;
            firstDigit = firstDigit ^ secondDigit;
        }
        String prompt = firstDigit + " " + operator + " " + secondDigit;
        int solution = getSolution(operator, firstDigit, secondDigit);

        return new Problem(prompt, solution);
    }

    public int getSolution(char operator, int firstDigit, int secondDigit){
        return switch (operator) {
            case '+' -> firstDigit + secondDigit;
            case '-' -> firstDigit - secondDigit;
            case '*' -> firstDigit * secondDigit;
            case '/' -> firstDigit / secondDigit;
            case '%' -> firstDigit % secondDigit;
            default -> -1;
        };
    }

    public int randomAmountOfDigits(int maxDigits){
        return random.nextInt(1, maxDigits + 1);
    }

    public int maxDigits(char operator){
        int operatorDifficultyScore = -1;
        switch (operator){
            case '+' -> operatorDifficultyScore = 1;
            case '-' -> operatorDifficultyScore = 2;
            case '*' -> operatorDifficultyScore = 5;
            case '/' -> operatorDifficultyScore = 10;
            case '%' -> operatorDifficultyScore = 20;
        }
        int difficultyScore = this.difficultyScore / operatorDifficultyScore;
        int maxDigits = 1;
        if (difficultyScore >= 10){
            maxDigits++;
        }
        if (difficultyScore >= 30){
            maxDigits++;
        }
        if (difficultyScore >= 100){
            maxDigits++;
        }
        if (difficultyScore >= 200){
            maxDigits++;
        }
        return maxDigits;
    }

    public char randomOperator(){
        return this.allowedOperators.get(random.nextInt(0, this.allowedOperators.size()));
    }

    public void updateDifficultyScore(){
        int difficultyScore = player.getCurrentScore();
        switch (player.getDifficulty()){
            case MEDIUM -> difficultyScore *= 2;
            case HARD -> difficultyScore *= 3;
        }
        this.difficultyScore = difficultyScore;
        updateAllowedOperators();
    }

    public void updateAllowedOperators(){
        ArrayList<Character> allowedOperators = new ArrayList<>();
        allowedOperators.add('+');
        if (difficultyScore >= 10){
            allowedOperators.add('-');
        }
        if (difficultyScore >= 20){
            allowedOperators.add('*');
        }
        if (difficultyScore >= 50){
            allowedOperators.add('/');
        }
        if (difficultyScore >= 100){
            allowedOperators.add('%');
        }
        this.allowedOperators = allowedOperators;
    }

    public int getDifficultyScore() {
        return difficultyScore;
    }

    public void setDifficultyScore(int difficultyScore) {
        this.difficultyScore = difficultyScore;
    }
}
