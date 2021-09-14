import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[][] gameGrid = new char[3][3];
        int count = 0;
        for (char[] row : gameGrid) {
            for (int i = 0; i < row.length; i++) {
                row[i] = '_';
                count++;
            }
        }

        printGameBoard(gameGrid);

        int counter = 0;

        while (counter < 9) {
            playerXMoves(scanner, gameGrid);
            ++counter;
            printGameBoard(gameGrid);
            checkGameStatus(gameGrid);
            if (checkGameStatus(gameGrid).equals("X wins")) {
                System.out.println(checkGameStatus(gameGrid));
                break;
            }

            if (counter == 9) {
                break;
            }
            ++counter;
            playerOMoves(scanner, gameGrid);
            printGameBoard(gameGrid);
            if (checkGameStatus(gameGrid).equals("O wins")) {
                System.out.println(checkGameStatus(gameGrid));
                break;
            }

        }

    }

    private static void printGameBoard(char[][] gameGrid) {
        System.out.println("---------");
        for (char[] row : gameGrid) {
            System.out.print("| ");
            for (char rowChar : row) {
                System.out.print(rowChar + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void playerXMoves(Scanner scanner, char[][] gameGrid) {
        boolean isGameStarted = false;
        while (!isGameStarted) {
            System.out.print("Enter the coordinates: ");
            try {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                if (x > 2 || y > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (gameGrid[x][y] == 'X' || gameGrid[x][y] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    gameGrid[x][y] = 'X';
                    isGameStarted = true;
                }
            } catch (Exception NumberFormatException) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
    }

    private static void playerOMoves(Scanner scanner, char[][] gameGrid) {
        boolean isGameStarted = false;
        while (!isGameStarted) {
            System.out.print("Enter the coordinates: ");
            try {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                if (x > 2 || y > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (gameGrid[x][y] == 'X' || gameGrid[x][y] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    gameGrid[x][y] = 'O';
                    isGameStarted = true;
                }
            } catch (Exception NumberFormatException) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
    }

    private static String checkGameStatus(char[][] gameGrid) {
        int countX = 0;
        int countO = 0;
        for (char[] row : gameGrid) {
            for (char c : row) {
                if (c == 'X') {
                    countX++;
                }
                if (c == 'O') {
                    countO++;
                }
            }
        }

        boolean haveXinARow = false;
        boolean haveOinARow = false;

        //to check if we have X or O in a horizontal row
        int countHorizontalX;
        int countHorizontalO;
        for (char[] row : gameGrid) {
            countHorizontalO = 0;
            countHorizontalX = 0;
            for (char c : row) {
                if (c == 'X') {
                    countHorizontalX++;
                }
                if (countHorizontalX == 3) {
                    haveXinARow = true;
                }
                if (c == 'O') {
                    countHorizontalO++;
                }
                if (countHorizontalO == 3) {
                    haveOinARow = true;
                }
            }
        }

        //to check if we have X or O in a vertical row
        int countVerticalX;
        int countVerticalO;
        for (int i = 0; i < gameGrid.length; i++) {
            countVerticalX = 0;
            countVerticalO = 0;
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (gameGrid[j][i] == 'X') {
                    countVerticalX++;
                }
                if (countVerticalX == 3) {
                    haveXinARow = true;
                }
                if (gameGrid[j][i] == 'O') {
                    countVerticalO++;
                }
                if (countVerticalO == 3) {
                    haveOinARow = true;
                }
            }
        }

        //to check if we have X or O in a diagonal1 row
        int countDiagonal1_X = 0;
        int countDiagonal1_O = 0;
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (i == j && gameGrid[i][j] == 'X') {
                    countDiagonal1_X++;
                }
                if (countDiagonal1_X == 3) {
                    haveXinARow = true;
                }
                if (i == j && gameGrid[i][j] == 'O') {
                    countDiagonal1_O++;
                }
                if (countDiagonal1_O == 3) {
                    haveOinARow = true;
                }
            }
        }

        //to check if we have X or O in a diagonal2 row
        int countDiagonal2_X = 0;
        int countDiagonal2_O = 0;
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (i + j == 2 && gameGrid[i][j] == 'X') {
                    countDiagonal2_X++;
                }
                if (countDiagonal2_X == 3) {
                    haveXinARow = true;
                }
                if (i + j == 2 && gameGrid[i][j] == 'O') {
                    countDiagonal2_O++;
                }
                if (countDiagonal2_O == 3) {
                    haveOinARow = true;
                }
            }
        }

        // Print state of entered cells
        if (haveOinARow && haveXinARow || Math.abs(countX - countO) > 1) {
            return "Impossible";
        } else if (haveXinARow) {
            return "X wins";
        } else if (haveOinARow) {
            return "O wins";
        } else if (countX == countO || countX < 4) {
            return "Game not finished";
        } else {
            return "Draw";
        }
    }

}

