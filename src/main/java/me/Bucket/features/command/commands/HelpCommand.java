package me.Bucket.features.command.commands;

import me.Bucket.Bucket;
import me.Bucket.features.command.Command;

public class HelpCommand
        extends Command {
    public HelpCommand() {
        super("commands");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("You can use following commands: ");
        for (Command command : Bucket.commandManager.getCommands()) {
            HelpCommand.sendMessage(Bucket.commandManager.getPrefix() + command.getName());
        }
    }
}

