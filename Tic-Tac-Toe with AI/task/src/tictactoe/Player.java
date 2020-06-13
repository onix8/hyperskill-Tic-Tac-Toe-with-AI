package tictactoe;

/**
 * Abstract player.
 */
abstract class Player implements Movable {
    VariantPlayers player;

    Player(VariantPlayers player) {
        this.player = player;
    }
}
