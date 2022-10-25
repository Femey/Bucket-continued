package me.Bucket.features.modules.client;

import me.Bucket.features.setting.Setting;
import me.Bucket.features.modules.Module;

public class Screens
        extends Module {
    public static Screens INSTANCE;
    public Setting<Boolean> mainScreen = this.register(new Setting<Boolean>("MainScreen", true));

    public Screens() {
        super("Screens", "Controls custom screens used by the client", Module.Category.CLIENT, true, false, false);
        INSTANCE = this;
    }

    @Override
    public void onTick() {
    }
}

