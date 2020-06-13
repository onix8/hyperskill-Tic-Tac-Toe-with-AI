package tictactoe;

/**
 * AI-hard complexity.
 */
public class PlayerAIHard extends Player {
    PlayerAIHard() {
        super(VariantPlayers.HARD);
    }

    /**
     * The "hard" AI makes a best move.
     * Selects the best move for himself and the worst for the opponent,
     * checking all possible options, up to each outcome of the game.
     *
     * @param field playing field.
     */
    @Override
    public void doMove(Field field) {
        System.out.printf("Making move level \"%s\"\n", player);
        int positionOnField = field.bestMove();
        move(field, positionOnField);
    }
}
