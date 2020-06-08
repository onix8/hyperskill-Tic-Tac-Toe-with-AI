package tictactoe;

public interface Movable {
    void doMove(Field field, char playerCharacter);

    /**
     * Records the player's move on the field.
     *
     * @param field           playing field.
     * @param positionOnField address of a field cell.
     * @param playerCharacter player 'X' or 'O'.
     */
    default void move(Field field, int positionOnField, char playerCharacter) {
        char[] f = field.getField();
        f[positionOnField] = playerCharacter;
    }
}
