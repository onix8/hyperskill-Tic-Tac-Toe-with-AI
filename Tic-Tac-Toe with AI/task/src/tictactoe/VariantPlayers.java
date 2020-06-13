package tictactoe;

/**
 * All possible options of the players.
 */
enum VariantPlayers {
    USER("user"),
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
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
