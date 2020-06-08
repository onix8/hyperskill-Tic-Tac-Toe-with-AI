package tictactoe;

import java.util.Scanner;

class UserCommand {
    private boolean badParameters = true;
    private VariantCommands command;
    private VariantPlayers firstPlayer;
    private VariantPlayers secondPlayer;

    public boolean isBadParameters() {
        return badParameters;
    }

    public VariantCommands getCommand() {
        return command;
    }

    public VariantPlayers getFirstPlayer() {
        return firstPlayer;
    }

    public VariantPlayers getSecondPlayer() {
        return secondPlayer;
    }

    public void checkedInput() {
        Scanner scanner = new Scanner(System.in);
        String[] userInput = scanner.nextLine().split("\\s+");
        int countCommand = userInput.length;
        switch (countCommand) {
            case 1:
                if (VariantCommands.EXIT.getCommand().equals(userInput[0])) {
                    command = VariantCommands.findCommand(userInput[0]);
                    badParameters = false;
                }
                break;
            case 3:
                if (VariantCommands.START.getCommand().equals(userInput[0])) {
                    command = VariantCommands.findCommand(userInput[0]);
                    badParameters = false;
                } else {
                    return;
                }

                if (VariantPlayers.USER.getPlayer().equals(userInput[1])) {
                    firstPlayer = VariantPlayers.findPlayer(userInput[1]);
                    badParameters = false;
                } else if (VariantPlayers.EASY.getPlayer().equals(userInput[1])) {
                    firstPlayer = VariantPlayers.findPlayer(userInput[1]);
                    badParameters = false;
                } else {
                    badParameters = true;
                }

                if (VariantPlayers.USER.getPlayer().equals(userInput[2])) {
                    secondPlayer = VariantPlayers.findPlayer(userInput[2]);
                    badParameters = false;
                } else if (VariantPlayers.EASY.getPlayer().equals(userInput[2])) {
                    secondPlayer = VariantPlayers.findPlayer(userInput[2]);
                    badParameters = false;
                } else {
                    badParameters = true;
                }
                break;
            default:
                badParameters = true;
        }
    }
}
