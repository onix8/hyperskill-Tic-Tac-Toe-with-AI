package tictactoe;

import static tictactoe.FieldCharacter.*;
import static tictactoe.State.*;

/**
 * Game "Tic-Tac-Toe with AI".
 */
class Game {
    Field field = null;
    Player firstPlayer = null;
    Player secondPlayer = null;
    private State state;

    /**
     * Controls the creation and exit of the game.
     *
     */
    void startGame() {
        boolean play = true;
        VariantCommands variantCommands;
        VariantPlayers firstVariantPlayers;
        VariantPlayers secondVariantPlayers;
        UserCommand userCommand = new UserCommand();

        while (play) {
            System.out.print("Input command: ");
            userCommand.checkedInput();
            if (userCommand.isBadParameters()) {
                System.out.println("Bad parameters!");
                continue;
            }
            variantCommands = userCommand.getCommand();
            firstVariantPlayers = userCommand.getFirstPlayer();
            secondVariantPlayers = userCommand.getSecondPlayer();

            switch (variantCommands) {
                case START:
                    switch (firstVariantPlayers) {
                        case USER:
                            firstPlayer = new PlayerHuman();
                            break;
                        case EASY:
                            firstPlayer = new PlayerAIEasy();
                            break;
                        case MEDIUM:
                            firstPlayer = new PlayerAIMedium();
                            break;
                        case HARD:
                            firstPlayer = new PlayerAIHard();
                    }
                    switch (secondVariantPlayers) {
                        case USER:
                            secondPlayer = new PlayerHuman();
                            break;
                        case EASY:
                            secondPlayer = new PlayerAIEasy();
                            break;
                        case MEDIUM:
                            secondPlayer = new PlayerAIMedium();
                            break;
                        case HARD:
                            secondPlayer = new PlayerAIHard();
                    }
                    startBattle(firstPlayer, secondPlayer);
                    break;
                case EXIT:
                    play = false;
            }
        }
    }

    /**
     * The main gameplay.
     *
     * @param firstPlayer  AI or user (enum VariantPlayers).
     * @param secondPlayer AI or user (enum VariantPlayers).
     */
    private void startBattle(Player firstPlayer, Player secondPlayer) {
        field = new Field();
        updateState();

        while (state == GAME_NOT_FINISHED) {
            System.out.println(field);

            if (state == GAME_NOT_FINISHED) {
                field.setNextMove();
                switch (field.getNextMove()) {
                    case X:
                        firstPlayer.doMove(field);
                        break;
                    case O:
                        secondPlayer.doMove(field);
                }
                updateState();
            }

            if (state != GAME_NOT_FINISHED) {
                System.out.println(field);
                System.out.println(state);
            }
        }
    }

    /**
     * Analyzes the state of the field.
     *
     */
    private void updateState() {
        state = isNotFinish() ? GAME_NOT_FINISHED :
                isDraw() ? DRAW :
                        isWinsX() ? X_WINS :
                                isWinsO() ? O_WINS :
                                        isImpossible() ? IMPOSSIBLE : IMPOSSIBLE;
    }

    /**
     * "Game not finished"
     *
     */
    private boolean isNotFinish() {
        return field.count(SPACE) > 0 &&
                !isWinsX() &&
                !isWinsO() &&
                !isImpossible();
    }

    /**
     * "Draw"
     *
     */
    private boolean isDraw() {
        return field.count(SPACE) == 0 &&
                !isWinsX() &&
                !isWinsO() &&
                !isImpossible();
    }

    /**
     * "X wins"
     *
     */
    private boolean isWinsX() {
        return isWin(X);
    }

    /**
     * "O wins"
     *
     */
    private boolean isWinsO() {
        return isWin(O);
    }

    /**
     * "Impossible"
     *
     */
    private boolean isImpossible() {
        int x = field.count(X);
        int o = field.count(O);

        if (isWin(X) && isWin(O)) {
            return true;
        } else {
            return x >= o ?
                    x - o > 1 :
                    o - x > 1;
        }
    }

    /**
     * Victory condition.
     * Determines whether the player won or not.
     *
     * @param c player 'X' or 'O'.
     * @return true if there are three in a row.
     */
    private boolean isWin(FieldCharacter c) {
        return field.findThreeInARow(c);
    }
}
