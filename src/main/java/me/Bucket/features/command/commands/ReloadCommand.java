package me.Bucket.features.command.commands;

import me.Bucket.features.command.Command;
import me.Bucket.Bucket;

public class ReloadCommand
        extends Command {
    public ReloadCommand() {
        super("reload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        Bucket.reload();
    }
}

