public record Problem(String problem, int solution) {

    public boolean correctSolution(int answer) {
        if (this.problem.contains("/")) {
            // Gives space for different views on integer division
            if (this.solution + 1 == answer) {
                return true;
            }
        }
        return this.solution == answer;
    }
}
