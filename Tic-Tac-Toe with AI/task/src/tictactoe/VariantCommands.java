package tictactoe;

enum VariantCommands {
    START("start"),
    EXIT("exit");

    private final String command;

    VariantCommands(String command) {
        this.command = command;
    }

    static VariantCommands findCommand(String variantCommands) {
        for (VariantCommands value : values()) {
            if (variantCommands.equals(value.command)) {
                return value;
            }
        }
        return null;
    }

    public String getCommand() {
        return command;
    }
}
