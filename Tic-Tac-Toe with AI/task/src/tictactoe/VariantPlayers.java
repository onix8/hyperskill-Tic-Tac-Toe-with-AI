package tictactoe;

enum VariantPlayers {
    USER("user"),
    EASY("easy"),
    MEDIUM("medium"),
    ERROR("Error: the wrong player was entered.");

    private final String player;

    VariantPlayers(String player) {
        this.player = player;
    }

    /**
     * Finds the player type by its header.
     *
     * @param variantPlayers the player header.
     * @return the player type.
     */
    static VariantPlayers findPlayer(String variantPlayers) {
        for (VariantPlayers value : values()) {
            if (variantPlayers.equals(value.player)) {
                return value;
            }
        }
        return ERROR;
    }

    @Override
    public String toString() {
        return player;
    }
}
