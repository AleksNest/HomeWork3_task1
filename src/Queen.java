/* ЗАДАЧА размещении N шахматных ферзей на шахматной доске N × N так,
чтобы никакой ферзь не атаковал другого ферзя */
public class Queen {
    final int N = 8;                                                     // кол ферзей, размер поля 8*8

    void printSolution(int board[][])                                   // Метод печати результата расположения ферзей
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
    }

    /* Метод проверки: может ли ферзь разместиться на доске [][] - наличие атакующих ферзей в левой стороне доски.
    Метод вызывется, когда ферзи "сol" уже размещены в столбцах от 0 до N-1. */
    boolean isSafe(int board[][], int row, int col)
    {
        int i, j;
        for (i = 0; i < col; i++) {                                     //проверка строки на левой стороне
            if (board[row][i] == 1)
                return false;
        }
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {            // Проверка верхней диагонали с левой стороны
            if (board[i][j] == 1)
                return false;
        }
        for (i = row, j = col; j >= 0 && i < N; i++, j--) {             // Проверка Нижней й диагонали с левой стороны
            if (board[i][j] == 1)
                return false;
        }
        return true;
    }

    boolean solveNQUtil(int board[][], int col)                         // рекурсивный метод решения размещения ферзей на доске
    {
        if (col >= N)                                                   //выход все ферзи размещены
            return true;

		for (int i = 0; i < N; i++) {                                   // размещение ферзей во всех строках один за другим в данном столбце
			if (isSafe(board, i, col)) {                                // проверка может ли быть помещен ферзь на доску [i][col]
                board[i][col] = 1;
                if (solveNQUtil(board, col + 1) == true)            // вернуться, чтобы разместить остальных ферзей
                    return true;
    			board[i][col] = 0;                                      // отказ: если поставить ферзя на доску[i][col] тогда не приводит к решению удалить ферзя с доски[i][col]
            }
        }

		return false;                                                   //при невозможности размещения ферзя в любой из строк в данном столбце
    }

    boolean solveNQ()                                                   //Метод возвращает возвращает False - если нет решения, True - печать результата расположения ферзей
    {
        int board[][] = { { 0, 0, 0, 0 , 0, 0, 0, 0},
                { 0, 0, 0, 0 , 0, 0, 0, 0 },
                { 0, 0, 0, 0 , 0, 0, 0, 0 },
                { 0, 0, 0, 0 , 0, 0, 0, 0 },
                { 0, 0, 0, 0 , 0, 0, 0, 0 },
                { 0, 0, 0, 0 , 0, 0, 0, 0 },
                { 0, 0, 0, 0 , 0, 0, 0, 0 },
                { 0, 0, 0, 0 , 0, 0, 0, 0} };

        if (solveNQUtil(board, 0) == false) {
            System.out.print("Нет решения");
            return false;
        }

        printSolution(board);
        return true;
    }

    public static void main(String args[])
    {
        Queen Queen = new Queen();                                      // переменная Queen класса Queen
        Queen.solveNQ();                                                // вызов метода через переменную(объект) (запуск кода)
    }
}

