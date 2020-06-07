package tictactoe;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import static tictactoe.State.*;

enum State {
    GAME_NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible");

    private final String stateDescription;

    State(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    public String getStateDescription() {
        return stateDescription;
    }
}

public class Main {

    static char[] field = new char[9];
    static int positionOnField;
    static State state = GAME_NOT_FINISHED;

    public static void main(String[] args) {
        char playerCharacter;

        Arrays.fill(field, ' ');

        while (state == GAME_NOT_FINISHED) {
            printField(field);

            if (state == GAME_NOT_FINISHED) {
                playerCharacter = getWhoseNextTurn();
                switch (playerCharacter) {
                    case 'X':
                        positionOnField = enterXY();
                        doMove(positionOnField, playerCharacter);
                        break;
                    case 'O':
                        moveEasy(playerCharacter);
                }
                updateState();
            }

            if (state != GAME_NOT_FINISHED) {
                printField(field);
                System.out.println(state.getStateDescription());
            }
        }
    }

    /**
     * The "easy" AI makes a random move.
     *
     * @param playerCharacter sets the character that the AI will use.
     */
    private static void moveEasy(char playerCharacter) {
        System.out.println("Making move level \"easy\"");
        positionOnField = generateRandomEmptyPositionOnField();
        doMove(positionOnField, playerCharacter);
    }

    /**
     * Selects a random free field.
     *
     * @return empty position on the field.
     */
    private static int generateRandomEmptyPositionOnField() {
        int[] indexesEmptyPositions = indexesPositions(' ');
        Random randomIndex = new Random();
        return indexesEmptyPositions[randomIndex.nextInt(indexesEmptyPositions.length)];
    }

    /**
     * Searches for all addresses of a specific character in the field.
     *
     * @param c the desired symbol.
     * @return array of indexes of the desired character in the field.
     */
    private static int[] indexesPositions(char c) {
        int countPosition = count(c);
        int[] indexes = new int[countPosition];

        for (int i = 0, j = 0; j < countPosition; i++) {
            if (field[i] == c) {
                indexes[j++] = i;
            }
        }

        return indexes;
    }

    /**
     * Outputs the formatted content of the field.
     *
     * @param field playing field.
     */
    private static void printField(char[] field) {
        System.out.println("---------");
        System.out.println("| " + field[0] + " " + field[1] + " " + field[2] + " |");
        System.out.println("| " + field[3] + " " + field[4] + " " + field[5] + " |");
        System.out.println("| " + field[6] + " " + field[7] + " " + field[8] + " |");
        System.out.println("---------");
    }

    /**
     * Analyzes the state of the field.
     */
    private static void updateState() {
        state = isNotFinish() ? GAME_NOT_FINISHED :
                isDraw() ? DRAW :
                        isWinsX() ? X_WINS :
                                isWinsO() ? O_WINS :
                                        isImpossible() ? IMPOSSIBLE : IMPOSSIBLE;
    }

    /**
     * "Game not finished"
     */
    private static boolean isNotFinish() {
        return count(' ') > 0 &&
                !isWinsX() &&
                !isWinsO() &&
                !isImpossible();
    }

    /**
     * "Draw"
     */
    private static boolean isDraw() {
        return count(' ') == 0 &&
                !isWinsX() &&
                !isWinsO() &&
                !isImpossible();
    }

    /**
     * "X wins"
     */
    private static boolean isWinsX() {
        return !isImpossible() && isWin('X');
    }

    /**
     * "O wins"
     */
    private static boolean isWinsO() {
        return !isImpossible() && isWin('O');
    }

    /**
     * "Impossible"
     */
    private static boolean isImpossible() {
        int x = count('X');
        int o = count('O');

        if (isWin('X') && isWin('O')) {
            return true;
        } else {
            return x >= o ?
                    x - o > 1 :
                    o - x > 1;
        }
    }

    /**
     * Determines whether the player won or not.
     *
     * @param c player 'X' or 'O'.
     * @return true if there are three in a row.
     */
    private static boolean isWin(char c) {
        return (field[0] == c && field[1] == c && field[2] == c) ||
                (field[3] == c && field[4] == c && field[5] == c) ||
                (field[6] == c && field[7] == c && field[8] == c) ||
                (field[0] == c && field[3] == c && field[6] == c) ||
                (field[1] == c && field[4] == c && field[7] == c) ||
                (field[2] == c && field[5] == c && field[8] == c) ||
                (field[0] == c && field[4] == c && field[8] == c) ||
                (field[2] == c && field[4] == c && field[6] == c);
    }

    /**
     * The user enters two valid coordinates and returns the index of the field cell.
     *
     * @return positionOnField.
     */
    private static int enterXY() {
        int x;
        int y;
        int positionOnField = 0;
        boolean invalidCoordinates = true;
        String userInput = "";
        Scanner scannerSystemIn = new Scanner(System.in);
        Scanner scannerUserInput;

        while (invalidCoordinates) {
            System.out.print("Enter the coordinates: ");

            try {
                userInput = scannerSystemIn.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            scannerUserInput = new Scanner(userInput);
            try {
                x = scannerUserInput.nextInt();
                y = scannerUserInput.nextInt();
            } catch (NoSuchElementException | NumberFormatException e) {
                System.out.println("You should enter numbers!");
                invalidCoordinates = true;
                continue;
            }

            positionOnField = getPositionOnFieldFromCoordinates(x, y);
            if (positionOnField == -1) {
                System.out.println("Coordinates should be from 1 to 3!");
                invalidCoordinates = true;
                continue;
            }

            switch (field[positionOnField]) {
                case 'X':
                case 'O':
                    System.out.println("This cell is occupied! Choose another one!");
                    invalidCoordinates = true;
                    continue;
            }
            invalidCoordinates = false;
        }
        return positionOnField;
    }

    /**
     * Converts coordinates to the address of a field cell.
     *
     * @param x the column fields from left to right.
     * @param y field string, from bottom to top.
     * @return address of a field cell.
     */
    private static int getPositionOnFieldFromCoordinates(int x, int y) {
        switch (x) {
            case 1:
                switch (y) {
                    case 1:
                        return 6;
                    case 2:
                        return 3;
                    case 3:
                        return 0;
                    default:
                        return -1;
                }
            case 2:
                switch (y) {
                    case 1:
                        return 7;
                    case 2:
                        return 4;
                    case 3:
                        return 1;
                    default:
                        return -1;
                }
            case 3:
                switch (y) {
                    case 1:
                        return 8;
                    case 2:
                        return 5;
                    case 3:
                        return 2;
                    default:
                        return -1;
                }
            default:
                return -1;
        }
    }

    /**
     * Counts the number of specific characters on the field.
     *
     * @param c 'X' or 'O' or ' '.
     * @return number of characters.
     */
    private static int count(char c) {
        int counter = 0;
        for (char c1 : field) {
            if (c == c1) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Records the player's move on the field.
     *
     * @param positionOnField address of a field cell.
     * @param c               player 'X' or 'O'.
     */
    private static void doMove(int positionOnField, char c) {
        field[positionOnField] = c;
    }

    /**
     * Calculates whose next move is.
     * @return character player's.
     */
    private static char getWhoseNextTurn() {
        return count('X') == count('O') ? 'X' : 'O';
    }
}
