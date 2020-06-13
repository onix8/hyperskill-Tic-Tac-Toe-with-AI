package tictactoe;

import static tictactoe.FieldCharacter.O;
import static tictactoe.FieldCharacter.X;

/**
 * AI-medium complexity.
 */
public class PlayerAIMedium extends Player {
    PlayerAIMedium() {
        super(VariantPlayers.MEDIUM);
    }

    /**
     * The "medium" level difficulty makes a move using the following process:<p>
     * <ol>
     * <li>If it can win in one move (if it has two in a row), it places a
     * third to get three in a row and win.</li>
     * <li>If the opponent can win in one move, it plays the third itself to
     * block the opponent to win.</li>
     * <li>Otherwise, it makes a random move.</li>
     *</ol>
     * @param field playing field.
     */
    @Override
    public void doMove(Field field) {
        System.out.printf("Making move level \"%s\"\n", player);
        // 1.
        int thirdInARow = field.findTwoInARow(field.getNextMove());
        if (thirdInARow >= 0) {
            move(field, thirdInARow);
        } else {
            // 2.
            thirdInARow = field.findTwoInARow(field.getNextMove() == X ? O : X);
            if (thirdInARow >= 0) {
                move(field, thirdInARow);
            } else {
                // 3.
                int positionOnField = field.generateRandomEmptyPositionOnField();
                move(field, positionOnField);
            }
        }
    }
}
