package me.Bucket.features.modules.render;

import me.Bucket.features.modules.Module;
import me.Bucket.features.setting.Setting;

public class Ambience
        extends Module {
    public final Setting<Integer> r = this.register(new Setting<Integer>("Red", 0, 0, 255));
    public final Setting<Integer> g = this.register(new Setting<Integer>("Green", 0, 0, 255));
    public final Setting<Integer> b = this.register(new Setting<Integer>("Blue", 255, 0, 255));
    public final Setting<Integer> a = this.register(new Setting<Integer>("Alpha", 250, 0, 255));

    public Ambience() {
        super("Ambience", "Allows you to change the ambience of your world", Module.Category.RENDER, true, false, false);
    }
}
