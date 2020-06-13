package tictactoe;

/**
 * Abstract player.
 */
abstract class Player implements Movable {
    final VariantPlayers player;

    Player(VariantPlayers player) {
        this.player = player;
    }
}
