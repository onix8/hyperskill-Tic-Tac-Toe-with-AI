package tictactoe;

public interface Movable {
    /**
     * The move depends on the type of specific player.
     *
     * @param field playing field.
     */
    void doMove(Field field);

    /**
     * Records the player's move on the field.
     *
     * @param field           playing field.
     * @param positionOnField address of a field cell.
     */
    default void move(Field field, int positionOnField) {
        FieldCharacter[] f = field.getField();
        f[positionOnField] = field.getNextMove();
    }
}
