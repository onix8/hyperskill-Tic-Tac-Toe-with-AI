package tictactoe;

import java.util.Random;

public class PlayerAI extends Player {
    public PlayerAI(VariantPlayers player) {
        super(player);
    }

    @Override
    public void doMove(Field field, char playerCharacter) {
        moveEasy(field, playerCharacter);
    }

    /**
     * The "easy" AI makes a random move.
     *
     * @param field           playing field.
     * @param playerCharacter sets the character that the AI will use.
     */
    private void moveEasy(Field field, char playerCharacter) {
        System.out.println("Making move level \"easy\"");
        int positionOnField = generateRandomEmptyPositionOnField(field);
        move(field, positionOnField, playerCharacter);
    }

    /**
     * Selects a random free field.
     *
     * @param field playing field.
     * @return empty position on the field.
     */
    private int generateRandomEmptyPositionOnField(Field field) {
        int[] indexesEmptyPositions = indexesPositions(field);
        Random randomIndex = new Random();
        return indexesEmptyPositions[randomIndex.nextInt(indexesEmptyPositions.length)];
    }

    /**
     * Searches for all addresses of a specific character in the field.
     *
     * @param field playing field.
     * @return array of indexes of the desired character in the field.
     */
    private int[] indexesPositions(Field field) {
        char[] f = field.getField();
        int countPosition = field.count(' ');
        int[] indexes = new int[countPosition];

        for (int i = 0, j = 0; j < countPosition; i++) {
            if (f[i] == ' ') {
                indexes[j++] = i;
            }
        }

        return indexes;
    }
}
