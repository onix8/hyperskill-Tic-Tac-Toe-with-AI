package tictactoe;

/**
 * All possible symbols of the playing field.
 */
public enum FieldCharacter {
    X('X'),
    O('O'),
    SPACE(' ');

    private final char character;

    FieldCharacter(char character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
