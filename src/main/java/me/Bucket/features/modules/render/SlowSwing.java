package me.Bucket.features.modules.render;

import me.Bucket.features.modules.*;
import me.Bucket.features.setting.*;

public class SlowSwing extends Module
{
    public static SlowSwing instance;
    public Setting<Integer> speed = this.register(new Setting("Speed", 6, 1, 50));
    public boolean isPressed;

    public SlowSwing() {
        super("SwingSpeed", "", Module.Category.RENDER, true, false, false);
        this.isPressed = false;
        SlowSwing.instance = this;
    }

    public static SlowSwing getInstance() {
        if (instance == null) {
            instance = new SlowSwing();
        }
        return instance;
    }
}