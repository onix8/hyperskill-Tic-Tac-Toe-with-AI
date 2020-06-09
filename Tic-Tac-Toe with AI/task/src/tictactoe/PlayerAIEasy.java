package tictactoe;

public class PlayerAIEasy extends Player {
    public PlayerAIEasy(VariantPlayers player) {
        super(player);
    }

    /**
     * The "easy" AI makes a random move.
     *
     * @param field playing field.
     */
    @Override
    public void doMove(Field field) {
        System.out.printf("Making move level \"%s\"\n", player);
        int positionOnField = field.generateRandomEmptyPositionOnField();
        move(field, positionOnField);
    }
}
