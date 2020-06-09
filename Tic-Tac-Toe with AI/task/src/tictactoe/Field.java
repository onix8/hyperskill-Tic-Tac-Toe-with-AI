package tictactoe;

import java.util.Arrays;
import java.util.Random;

import static tictactoe.FieldCharacter.*;

class Field {
    private final FieldCharacter[] field = new FieldCharacter[9];
    private FieldCharacter nextMove;

    public Field() {
        Arrays.fill(field, SPACE);
    }

    /**
     * Outputs the formatted content of the field.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("---------\n")
                .append("| ").append(field[0]).append(" ").append(field[1]).append(" ").append(field[2]).append(" |\n")
                .append("| ").append(field[3]).append(" ").append(field[4]).append(" ").append(field[5]).append(" |\n")
                .append("| ").append(field[6]).append(" ").append(field[7]).append(" ").append(field[8]).append(" |\n")
                .append("---------\n");
        return String.valueOf(output);
    }

    /**
     * Counts the number of specific characters on the field.
     *
     * @param c 'X' or 'O' or ' '.
     * @return number of characters.
     */
    int count(FieldCharacter c) {
        int counter = 0;
        for (FieldCharacter c1 : field) {
            if (c == c1) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Selects a random free field.
     *
     * @return empty position on the field.
     */
    int generateRandomEmptyPositionOnField() {
        int[] indexesEmptyPositions = indexesPositions(SPACE);
        Random randomIndex = new Random();
        return indexesEmptyPositions[randomIndex.nextInt(indexesEmptyPositions.length)];
    }

    /**
     * Searches for all addresses of a specific character in the field.
     *
     * @param character search symbol.
     * @return array of indexes of the desired character in the field.
     */
    private int[] indexesPositions(FieldCharacter character) {
        int countPosition = count(character);
        int[] indexes = new int[countPosition];

        for (int i = 0, j = 0; j < countPosition; i++) {
            if (field[i] == character) {
                indexes[j++] = i;
            }
        }

        return indexes;
    }

    /**
     * Finds the last missing field for the "three in a row" combination.
     *
     * @param c by which characters it searches.
     * @return the index of the position of the first match, else — -1.
     */
    int findTwoInARow(FieldCharacter c) {
        int spaceIndex = 0;
        FieldCharacter[] row;
        for (Row r : Row.values()) {
            int countCharacters = 0;
            int countSpaces = 0;
            row = getRow(r);
            for (int i = 0, rowLength = row.length; i < rowLength; i++) {
                if (row[i] == c) {
                    countCharacters++;
                } else if (row[i] == SPACE) {
                    countSpaces++;
                    spaceIndex = i;
                }
            }
            if (countCharacters == 2 && countSpaces == 1) {
                return r.getRow()[spaceIndex];
            }
        }
        return -1;
    }

    /**
     * Finds the "three in a row" combination.
     *
     * @param c character.
     * @return true if there are three in a row.
     */
    boolean findThreeInARow(FieldCharacter c) {
        for (Row r : Row.values()) {
            int countCharacters = 0;
            for (FieldCharacter character : getRow(r)) {
                if (character == c) {
                    countCharacters++;
                }
            }
            if (countCharacters == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets one specific row of symbols from the playing field.
     *
     * @param row one specific row.
     * @return array of characters.
     */
    private FieldCharacter[] getRow(Row row) {
        int[] r = row.getRow();
        return new FieldCharacter[]{field[r[0]], field[r[1]], field[r[2]]};
    }

    /**
     * Calculates whose next move is.
     */
    void setNextMove() {
        nextMove = count(X) == count(O) ? X : O;
    }

    FieldCharacter getNextMove() {
        return nextMove;
    }

    FieldCharacter[] getField() {
        return field;
    }

    /**
     * All possible variants of the rows.
     */
    private enum Row {
        ROW1(new int[]{0, 1, 2}),
        ROW2(new int[]{3, 4, 5}),
        ROW3(new int[]{6, 7, 8}),
        ROW4(new int[]{0, 3, 6}),
        ROW5(new int[]{1, 4, 7}),
        ROW6(new int[]{2, 5, 8}),
        ROW7(new int[]{0, 4, 8}),
        ROW8(new int[]{2, 4, 6});

        private final int[] row;

        Row(int[] row) {
            this.row = row;
        }

        int[] getRow() {
            return row;
        }
    }
}
