package me.Bucket.features.command.commands;

import me.Bucket.Bucket;
import me.Bucket.features.command.Command;

public class UnloadCommand
        extends Command {
    public UnloadCommand() {
        super("unload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        Bucket.unload(true);
    }
}

