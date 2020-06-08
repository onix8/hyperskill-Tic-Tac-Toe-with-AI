package tictactoe;

enum VariantPlayers {
    USER("user"),
    EASY("easy");

    private final String player;

    VariantPlayers(String player) {
        this.player = player;
    }

    static VariantPlayers findPlayer(String variantPlayers) {
        for (VariantPlayers value : values()) {
            if (variantPlayers.equals(value.player)) {
                return value;
            }
        }
        return null;
    }

    public String getPlayer() {
        return player;
    }
}
