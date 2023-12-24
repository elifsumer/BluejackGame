public class GameHistory {
    private static final int MAX_HISTORY_SIZE = 10;
    private int[][] gameScores;
    private int currentIndex;

    public GameHistory() {
        gameScores = new int[MAX_HISTORY_SIZE][2];  // Assuming a 2D array to store player and computer scores
        currentIndex = 0;
    }

    public void addGame(int playerScore, int computerScore) {
        gameScores[currentIndex][0] = playerScore;
        gameScores[currentIndex][1] = computerScore;
        currentIndex = (currentIndex + 1) % MAX_HISTORY_SIZE;
    }

    public void printHistory() {
        System.out.println("Game History:");
        for (int i = 0; i < MAX_HISTORY_SIZE; i++) {
            int index = (currentIndex + i) % MAX_HISTORY_SIZE;
            int playerScore = gameScores[index][0];
            int computerScore = gameScores[index][1];
            if (playerScore > 0 || computerScore > 0) {
                System.out.println("Game " + (i + 1) + ": Player " + playerScore + " - Computer " + computerScore);
            }
        }
    }
}