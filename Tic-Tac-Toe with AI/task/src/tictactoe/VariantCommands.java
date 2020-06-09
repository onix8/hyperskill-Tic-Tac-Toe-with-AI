package tictactoe;

enum VariantCommands {
    START("start"),
    EXIT("exit"),
    ERROR("Error: the wrong command was entered.");

    private final String command;

    VariantCommands(String command) {
        this.command = command;
    }

    /**
     * Finds the type of command by its header.
     *
     * @param variantCommands the command header.
     * @return the type of command.
     */
    static VariantCommands findCommand(String variantCommands) {
        for (VariantCommands value : values()) {
            if (variantCommands.equals(value.command)) {
                return value;
            }
        }
        return ERROR;
    }

    @Override
    public String toString() {
        return command;
    }
}
