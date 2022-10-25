package me.Bucket.features.modules.troll;

import me.Bucket.features.modules.Module;
import net.minecraft.network.play.client.CPacketChatMessage;


public class Suicide extends Module {
    public Suicide() {
        super("suicide", "for lazy ppl who cant type /kill", Module.Category.TROLL, true, false, false);

    }

    @Override
    public
    void onEnable() {
        if (fullNullCheck()) return;
                mc.player.connection.sendPacket(new CPacketChatMessage("/kill"));
    }
}

