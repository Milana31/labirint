import java.util.*;
class LabyrinthEngine {
    private char[][] grid;
    private boolean[][] tracker;
    private int height, width;
    private int[] origin, destination;

    public static void main(String[] args) {
    }

    private void initialize() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter maze height (>9): ");
            height = input.nextInt();
            System.out.print("Enter maze width (>9): ");
            width = input.nextInt();
        }
    }
    private void createLabyrinth () {
    }
    private void resolvePath () {
    }
}