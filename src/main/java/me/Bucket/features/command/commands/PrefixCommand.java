package me.Bucket.features.command.commands;

import me.Bucket.features.modules.client.ClickGui;
import me.Bucket.Bucket;
import me.Bucket.features.command.Command;

public class PrefixCommand
        extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage("\u00a7cSpecify a new prefix.");
            return;
        }
        Bucket.commandManager.setPrefix(commands[0]);
        Bucket.moduleManager.getModuleByClass(ClickGui.class).prefix.setValue(commands[0]);
        Command.sendMessage("Prefix set to \u00a7a" + Bucket.commandManager.getPrefix());
    }
}

