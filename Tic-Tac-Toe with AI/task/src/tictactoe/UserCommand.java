package tictactoe;

import java.util.Scanner;

import static tictactoe.VariantCommands.*;
import static tictactoe.VariantPlayers.findPlayer;

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
                command = findCommand(userInput[0]);
                badParameters = command != EXIT;
                return;
            case 3:
                command = findCommand(userInput[0]);
                if (command == START) {
                    badParameters = false;
                } else {
                    badParameters = true;
                    return;
                }

                firstPlayer = findPlayer(userInput[1]);
                switch (firstPlayer) {
                    case USER:
                    case EASY:
                    case MEDIUM:
                        badParameters = false;
                        break;
                    default:
                        badParameters = true;
                        return;
                }

                secondPlayer = findPlayer(userInput[2]);
                switch (secondPlayer) {
                    case USER:
                    case EASY:
                    case MEDIUM:
                        badParameters = false;
                        break;
                    default:
                        badParameters = true;
                        return;
                }
                break;
            default:
                badParameters = true;
        }
    }
}
