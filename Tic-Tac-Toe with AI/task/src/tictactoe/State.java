package tictactoe;

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

    @Override
    public String toString() {
        return stateDescription;
    }
}
