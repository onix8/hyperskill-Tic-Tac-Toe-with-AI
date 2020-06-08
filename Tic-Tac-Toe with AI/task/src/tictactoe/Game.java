package tictactoe;

import static tictactoe.State.*;
import static tictactoe.VariantPlayers.EASY;
import static tictactoe.VariantPlayers.USER;

class Game {
    Field field = null;
    Player firstPlayer = null;
    Player secondPlayer = null;
    private State state;

    /**
     * Controls the creation and exit of the game.
     */
    public void startGame() {
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
                            firstPlayer = new PlayerHuman(USER);
                            break;
                        case EASY:
                            firstPlayer = new PlayerAI(EASY);
                    }
                    switch (secondVariantPlayers) {
                        case USER:
                            secondPlayer = new PlayerHuman(USER);
                            break;
                        case EASY:
                            secondPlayer = new PlayerAI(EASY);
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
        char playerCharacter;
        field = new Field();
        updateState();

        while (state == GAME_NOT_FINISHED) {
            field.printField();

            if (state == GAME_NOT_FINISHED) {
                playerCharacter = getWhoseNextTurn();
                switch (playerCharacter) {
                    case 'X':
                        firstPlayer.doMove(field, playerCharacter);
                        break;
                    case 'O':
                        secondPlayer.doMove(field, playerCharacter);
                }
                updateState();
            }

            if (state != GAME_NOT_FINISHED) {
                field.printField();
                System.out.println(state.getStateDescription());
            }
        }
    }

    /**
     * Calculates whose next move is.
     *
     * @return character player's.
     */
    private char getWhoseNextTurn() {
        return field.count('X') == field.count('O') ? 'X' : 'O';
    }

    /**
     * Analyzes the state of the field.
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
     */
    private boolean isNotFinish() {
        return field.count(' ') > 0 &&
                !isWinsX() &&
                !isWinsO() &&
                !isImpossible();
    }

    /**
     * "Draw"
     */
    private boolean isDraw() {
        return field.count(' ') == 0 &&
                !isWinsX() &&
                !isWinsO() &&
                !isImpossible();
    }

    /**
     * "X wins"
     */
    private boolean isWinsX() {
        return !isImpossible() && isWin('X');
    }

    /**
     * "O wins"
     */
    private boolean isWinsO() {
        return !isImpossible() && isWin('O');
    }

    /**
     * "Impossible"
     */
    private boolean isImpossible() {
        int x = field.count('X');
        int o = field.count('O');

        if (isWin('X') && isWin('O')) {
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
    private boolean isWin(char c) {
        char[] f = this.field.getField();
        return (f[0] == c && f[1] == c && f[2] == c) ||
                (f[3] == c && f[4] == c && f[5] == c) ||
                (f[6] == c && f[7] == c && f[8] == c) ||
                (f[0] == c && f[3] == c && f[6] == c) ||
                (f[1] == c && f[4] == c && f[7] == c) ||
                (f[2] == c && f[5] == c && f[8] == c) ||
                (f[0] == c && f[4] == c && f[8] == c) ||
                (f[2] == c && f[4] == c && f[6] == c);
    }
}
