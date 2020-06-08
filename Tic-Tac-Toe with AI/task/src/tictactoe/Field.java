package tictactoe;

import java.util.Arrays;

class Field {
    private final char[] field = new char[9];

    public Field() {
        Arrays.fill(field, ' ');
    }

    public char[] getField() {
        return field;
    }

    /**
     * Outputs the formatted content of the field.
     */
    public void printField() {
        System.out.println("---------");
        System.out.println("| " + field[0] + " " + field[1] + " " + field[2] + " |");
        System.out.println("| " + field[3] + " " + field[4] + " " + field[5] + " |");
        System.out.println("| " + field[6] + " " + field[7] + " " + field[8] + " |");
        System.out.println("---------");
    }

    /**
     * Counts the number of specific characters on the field.
     *
     * @param c 'X' or 'O' or ' '.
     * @return number of characters.
     */
    int count(char c) {
        int counter = 0;
        for (char c1 : field) {
            if (c == c1) {
                counter++;
            }
        }
        return counter;
    }
}
