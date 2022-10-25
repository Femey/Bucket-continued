package me.Bucket.mixin;

import java.util.Map;
import me.Bucket.Bucket;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

public class BucketMixinLoader
implements IFMLLoadingPlugin {
    private static boolean isObfuscatedEnvironment = false;

    public BucketMixinLoader() {
        Bucket.LOGGER.info("Bucket mixins initialized");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.Bucket.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        Bucket.LOGGER.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        isObfuscatedEnvironment = (Boolean)data.get("runtimeDeobfuscationEnabled");
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

