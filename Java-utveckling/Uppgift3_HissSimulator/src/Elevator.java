public class Elevator {
    private int level;

    public Elevator(int level) throws IllegalArgumentException{
        if (!validateLevel(level)){
            throw new IllegalArgumentException("Level must be between -2 and 10");
        }
        this.level = level;
    }

    public void goTo(int level) throws IllegalArgumentException{
        if (!validateLevel(level)){
            throw new IllegalArgumentException("Level must be between -2 and 10");
        }
        if (this.level == level){
            throw new IllegalArgumentException("Level is the same as the current level");
        }
        int oldLevel = this.level;
        this.level = level;
        elevatorMessage(oldLevel, this.level);
    }

    private static boolean validateLevel(int level){
        return level >= -2 && level <= 10;
    }

    private void elevatorMessage(int oldLevel, int newLevel){
        if (oldLevel < newLevel){
            System.out.println("The elevator goes up from " + oldLevel + " to " + newLevel);
        } else {
            System.out.println("The elevator goes down from " + oldLevel + " to " + newLevel);
        }
    }

    public int where() {
        return level;
    }
}
