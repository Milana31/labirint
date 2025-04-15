import java.util.*;
class LabyrinthEngine {
    private static final char BORDER = '▓';
    private static final char PASSAGE = ' ';

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

            grid = new char[height][width];
            tracker = new boolean[height][width];
            origin = new int[]{1, 1};
            destination = new int[]{height - 2, width - 2};
        }
    }
    private void createLabyrinth() {
        for (char[] row : grid) Arrays.fill(row, BORDER);

        Deque<int[]> history = new ArrayDeque<>();
        history.push(origin);

        Random rand = new Random();
        final int[][] offsets = {{2, 0}, {0, 2}, {-2, 0}, {0, -2}};

        while (!history.isEmpty()) {
            int[] current = history.peek();
            List<int[]> candidates = new ArrayList<>();

            for (int[] offset : offsets) {
                int ny = current[0] + offset[0];
                int nx = current[1] + offset[1];

                if (ny > 0 && nx > 0 && ny < height - 1 && nx < width - 1
                        && grid[ny][nx] == BORDER) {
                    candidates.add(new int[]{ny, nx});
                }
            }

            if (!candidates.isEmpty()) {
                int[] next = candidates.get(rand.nextInt(candidates.size()));
                int wallY = (current[0] + next[0]) / 2;
                int wallX = (current[1] + next[1]) / 2;

                grid[wallY][wallX] = PASSAGE;
                grid[next[0]][next[1]] = PASSAGE;
                history.push(next);
            } else {
                history.pop();
            }
        }

    }

    private void resolvePath () {
    }


}