package tictactoe;

abstract class Player implements Movable {
    VariantPlayers player;

    public Player(VariantPlayers player) {
        this.player = player;
    }
}
