import java.util.*;

class LabyrinthEngine {
    // Символы для визуализации лабиринта
    private static final char BORDER = '▓';  // Стенка лабиринта
    private static final char PASSAGE = ' '; // Проход/коридор
    private static final char SOLUTION_MARKER = '•'; // Маркер найденного пути

    // Основные структуры данных
    private char[][] grid;     // 2D массив для хранения структуры лабиринта
    private boolean[][] tracker; // Массив для отслеживания посещенных клеток
    private int height, width; // Фактические размеры лабиринта
    private int[] origin;      // Стартовая позиция [строка, столбец]
    private int[] destination; // Позиция выхода [строка, столбец]

//
//     * Точка входа в программу
//     * @param args Аргументы командной строки (не используются)
//
    public static void main(String[] args) {
    }

//
//     * Инициализация параметров лабиринта
//     * - Запрос размеров у пользователя
//     * - Проверка и корректировка размеров
//     * - Создание структур данных
//
    private void initialize() {
        try (Scanner input = new Scanner(System.in)) {
            // Запрос и проверка размеров
            System.out.print("Enter maze height (>9): ");
            height = validateDimension(input.nextInt());
            System.out.print("Enter maze width (>9): ");
            width = validateDimension(input.nextInt());

            // Инициализация массивов
            grid = new char[height][width];    // Сетка лабиринта
            tracker = new boolean[height][width]; // Трекер посещений

            // Установка стартовой и конечной точек
            origin = new int[]{1, 1};               // Фиксированная стартовая позиция
            destination = new int[]{height-2, width-2}; // Фиксированный выход
        }
    }

//
//     * Проверка и корректировка размеров лабиринта
//     * @param size Введенный пользователем размер
//     * @return Корректный нечетный размер (>=10)
//     * @throws IllegalArgumentException При размере <10
//
    private int validateDimension(int size) {
        if(size < 10) throw new IllegalArgumentException("Minimum size 10x10");
        return size % 2 == 0 ? size + 1 : size; // Гарантируем нечетность для алгоритма
    }

//
//     * Генерация лабиринта алгоритмом Recursive Backtracking
//     * Алгоритм:
//     * 1. Заполнить сетку стенками
//     * 2. Случайный выбор направлений
//     * 3. "Прорывание" стенок с возвратом при тупиках
//
    private void createLabyrinth() {
        // Инициализация всех клеток как стенок
        for(char[] row : grid) Arrays.fill(row, BORDER);

        Deque<int[]> history = new ArrayDeque<>(); // Стек для отслеживания пути генерации
        history.push(origin); // Начальная точка генерации
        grid[origin[0]][origin[1]] = PASSAGE; // Открываем стартовую клетку

        Random rand = new Random();
        final int[][] offsets = {{2,0}, {0,2}, {-2,0}, {0,-2}}; // Возможные направления (шаг 2 клетки)

        // Основной цикл генерации
        while(!history.isEmpty()) {
            int[] current = history.peek();
            List<int[]> candidates = new ArrayList<>(); // Кандидаты для расширения

            // Поиск возможных направлений
            for(int[] offset : offsets) {
                int ny = current[0] + offset[0]; // Новая позиция Y
                int nx = current[1] + offset[1]; // Новая позиция X

                // Проверка на валидность новой позиции
                if(ny > 0 && nx > 0 && ny < height-1 && nx < width-1
                        && grid[ny][nx] == BORDER) {
                    candidates.add(new int[]{ny, nx});
                }
            }

            // Обработка найденных кандидатов
            if(!candidates.isEmpty()) {
                int[] next = candidates.get(rand.nextInt(candidates.size()));

                // Расчет позиции стены между текущей и новой клеткой
                int wallY = (current[0] + next[0])/2;
                int wallX = (current[1] + next[1])/2;

                // "Прорывание" стенки и новой клетки
                grid
                        [wallY][wallX] = PASSAGE;
                grid[next[0]][next[1]] = PASSAGE;
                history.push(next); // Переход к новой клетке
            } else {
                history.pop(); // Возврат при отсутствии вариантов
            }
        }

        // Гарантированное открытие выхода
        grid[destination[0]][destination[1]] = PASSAGE;
        displayGrid("Generated Labyrinth:");
    }

//
//     * Поиск пути от старта до выхода (рекурсивный DFS)
//     * @return true если путь найден, иначе false
//
    private boolean resolvePath() {
        boolean found = explore(origin[0], origin[1]); // Запуск рекурсивного поиска
        displayGrid(found ? "Solution Path:" : "No Valid Path");
        return found;
    }

//
//     * Рекурсивный метод поиска пути с возвратом
//     * @param y Текущая позиция Y (строка)
//     * @param x Текущая позиция X (столбец)
//     * @return true если путь найден
//
    private boolean explore(int y, int x) {
        // Базовый случай: достигли выхода
        if(y == destination[0] && x == destination[1]) {
            grid[y][x] = SOLUTION_MARKER; // Помечаем выход
            return true;
        }

        // Проверка на невалидные клетки
        if(y < 0 || x < 0 || y >= height || x >= width ||
        grid[y][x] != PASSAGE || tracker[y][x]) {
            return false;
        }

        tracker[y][x] = true; // Помечаем клетку как посещенную
        final int[][] moves = {{1,0}, {0,1}, {-1,0}, {0,-1}}; // Направления движения

        // Рекурсивный поиск по всем направлениям
        for(int[] move : moves) {
            if(explore(y + move[0], x + move[1])) {
                grid[y][x] = SOLUTION_MARKER; // Помечаем путь
                return true;
            }
        }
        return false; // Тупиковая ветка
    }

    /**
     * Вывод лабиринта в консоль
     * @param header Заголовок для отображения
     */
    private void displayGrid(String header) {
        System.out.println("\n" + header);
        for(char[] row : grid) {
            for(char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
