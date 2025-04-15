# 🧩 Maze Generator & Solver in Java

Проект на Java, который генерирует случайный лабиринт с использованием алгоритма **Recursive Backtracking** и находит путь от старта до выхода с помощью **рекурсивного поиска в глубину (DFS)**.

---

## 📸 Пример работы

```
Enter maze height (>9): 15  
Enter maze width (>9): 15

Generated Labyrinth:
▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 
▓       ▓           ▓       ▓ 
▓ ▓ ▓   ▓   ▓ ▓ ▓   ▓ ▓ ▓   ▓ 
▓       ▓       ▓       ▓   ▓ 
▓   ▓ ▓ ▓ ▓ ▓   ▓ ▓ ▓   ▓   ▓ 
▓               ▓   ▓   ▓   ▓ 
▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓   ▓   ▓   ▓ 
▓                   ▓       ▓ 
▓   ▓   ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓   ▓ 
▓   ▓   ▓               ▓   ▓ 
▓   ▓   ▓   ▓ ▓ ▓ ▓ ▓   ▓   ▓ 
▓   ▓   ▓       ▓       ▓   ▓ 
▓   ▓ ▓ ▓ ▓ ▓   ▓   ▓ ▓ ▓   ▓ 
▓               ▓           ▓ 
▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 

...

Solution Path:
▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 
▓ • • • ▓ • • • • • ▓       ▓ 
▓ ▓ ▓ • ▓ • ▓ ▓ ▓ • ▓ ▓ ▓   ▓ 
▓ • • • ▓ • • • ▓ • • • ▓   ▓ 
▓ • ▓ ▓ ▓ ▓ ▓ • ▓ ▓ ▓ • ▓   ▓ 
▓ • • • • • • • ▓   ▓ • ▓   ▓ 
▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓   ▓ • ▓   ▓ 
▓                   ▓ • • • ▓ 
▓   ▓   ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ • ▓ 
▓   ▓   ▓               ▓ • ▓ 
▓   ▓   ▓   ▓ ▓ ▓ ▓ ▓   ▓ • ▓ 
▓   ▓   ▓       ▓       ▓ • ▓ 
▓   ▓ ▓ ▓ ▓ ▓   ▓   ▓ ▓ ▓ • ▓ 
▓               ▓         • ▓ 
▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ 

...
```

---

## 🚀 Запуск проекта

1. **Склонируй репозиторий:**
   ```bash
   git clone https://github.com/ТВОЙ_НИК/maze-solver.git
   cd maze-solver
   ```

2. **Скомпилируй и запусти:**
   ```bash
   javac LabyrinthEngine.java
   java LabyrinthEngine
   ```

---

## ⚙️ Особенности

- Генерация лабиринта через **recursive backtracking**
- Решение лабиринта с помощью **рекурсивного DFS**
- Отображение лабиринта и решения в консоли
- Гарантированно достижимый выход
- Умный контроль размеров (всегда нечётные и ≥ 10)

---

## 🧠 Как работает

### Генерация:
- Все клетки инициализируются как стены
- Алгоритм случайно "прорывает" стены, формируя проходы
- Используется стек для возврата, когда тупик

### Решение:
- Поиск от старта до выхода по всем возможным направлениям
- Используется массив `tracker` для отслеживания посещённых клеток
- Путь отмечается символом `•`
