public class Player {
    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    private String name;
    private int currentScore = 0;
    private int highScore = 0;
    private Difficulty difficulty;

    public Player(String name, int difficulty){
        this.name = name;
        switch (difficulty){
            case 1 -> this.difficulty = Difficulty.EASY;
            case 2 -> this.difficulty = Difficulty.MEDIUM;
            case 3 -> this.difficulty = Difficulty.HARD;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        switch (difficulty){
            case 1 -> this.difficulty = Difficulty.EASY;
            case 2 -> this.difficulty = Difficulty.MEDIUM;
            case 3 -> this.difficulty = Difficulty.HARD;
        }
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void addCurrentScore(int addScore){
        currentScore += addScore;
    }
}
