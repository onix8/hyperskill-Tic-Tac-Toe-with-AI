package tictactoe;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class PlayerHuman extends Player {
    public PlayerHuman(VariantPlayers player) {
        super(player);
    }

    /**
     * Moves along the coordinates entered by the user.
     *
     * @param field playing field.
     */
    @Override
    public void doMove(Field field) {
        int positionOnField = enterXY(field);
        move(field, positionOnField);
    }

    /**
     * The user enters two valid coordinates and returns the index of the field cell.
     *
     * @param field playing field.
     * @return the index of the field cell.
     */
    private int enterXY(Field field) {
        int x;
        int y;
        int positionOnField = 0;
        boolean invalidCoordinates = true;
        String userInput = "";
        FieldCharacter[] f = field.getField();
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

            switch (f[positionOnField]) {
                case X:
                case O:
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
    private int getPositionOnFieldFromCoordinates(int x, int y) {
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
}
