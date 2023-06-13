package bim492;

import java.util.Stack;

class RecipeModificationInvoker {
    private Stack<Command> commandHistory;

    public RecipeModificationInvoker() {
        this.commandHistory = new Stack<>();
    }

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
            System.out.println("Undone successfully.");

        } else {
            System.out.println("No commands to undo.");
        }
    }
}