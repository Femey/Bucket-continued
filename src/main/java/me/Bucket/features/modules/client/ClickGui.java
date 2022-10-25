package me.Bucket.features.modules.client;
import me.Bucket.Bucket;
import me.Bucket.event.events.ClientEvent;
import me.Bucket.features.command.Command;
import me.Bucket.features.gui.BucketGui;
import me.Bucket.features.modules.Module;
import me.Bucket.features.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClickGui extends Module {
    private static ClickGui INSTANCE = new ClickGui();
    public Setting<String> moduleButton = ((new Setting("Buttons:", "", v -> !((Boolean)this.openCloseChange.getValue()).booleanValue())).setRenderName(true));

    public Setting<Boolean> colorSync = (new Setting("Sync", Boolean.valueOf(false)));

    public Setting<Boolean> outline = register(new Setting("Outline", Boolean.valueOf(false)));

    public Setting<Boolean> rainbowRolling = (new Setting("RollingRainbow", Boolean.valueOf(false), v -> (((Boolean)this.colorSync.getValue()).booleanValue() && ((Boolean)Colors.INSTANCE.rainbow.getValue()).booleanValue())));

    public Setting<String> prefix = register((new Setting("Prefix", ".")).setRenderName(true));

    public Setting<Integer> red = register(new Setting("Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));

    public Setting<Integer> green = register(new Setting("Green", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));

    public Setting<Integer> blue = register(new Setting("Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));


    public Setting<Integer> hoverAlpha = register(new Setting("Alpha", Integer.valueOf(180), Integer.valueOf(0), Integer.valueOf(255)));

    public Setting<Integer> alpha = register(new Setting("HoverAlpha", Integer.valueOf(240), Integer.valueOf(0), Integer.valueOf(255)));

    public Setting<Boolean> customFov = (new Setting("CustomFov", Boolean.valueOf(false)));

    public Setting<Float> fov = register(new Setting("Fov", Float.valueOf(150.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F), v -> ((Boolean)this.customFov.getValue()).booleanValue()));

    public Setting<Boolean> devSettings = (new Setting("DevSettings", Boolean.valueOf(true)));

    public Setting<Integer> topRed = register(new Setting("TopRed", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));

    public Setting<Integer> topGreen = register(new Setting("TopGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));

    public Setting<Integer> topBlue = register(new Setting("TopBlue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));

    public Setting<Boolean> openCloseChange = (new Setting("Open/Close", Boolean.valueOf(false)));

    public Setting<String> open = ((new Setting("Open:", "", v -> ((Boolean)this.openCloseChange.getValue()).booleanValue())).setRenderName(true));

    public Setting<String> close = ((new Setting("Close:", "", v -> ((Boolean)this.openCloseChange.getValue()).booleanValue())).setRenderName(true));
    public Setting<Integer> topAlpha = register(new Setting("TopAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));

    public Setting<Boolean> rainbow = (new Setting("Rainbow", Boolean.valueOf(false)));

    public Setting<rainbowMode> rainbowModeHud = (new Setting("HRainbowMode", rainbowMode.Static, v -> ((Boolean)this.rainbow.getValue()).booleanValue()));

    public Setting<rainbowModeArray> rainbowModeA = (new Setting("ARainbowMode", rainbowModeArray.Static, v -> ((Boolean)this.rainbow.getValue()).booleanValue()));

    public Setting<Integer> rainbowHue = (new Setting("Delay", Integer.valueOf(240), Integer.valueOf(0), Integer.valueOf(600), v -> ((Boolean)this.rainbow.getValue()).booleanValue()));

    public Setting<Float> rainbowBrightness = (new Setting("Brightness ", Float.valueOf(150.0F), Float.valueOf(1.0F), Float.valueOf(255.0F), v -> ((Boolean)this.rainbow.getValue()).booleanValue()));

    public Setting<Float> rainbowSaturation = (new Setting("Saturation", Float.valueOf(150.0F), Float.valueOf(1.0F), Float.valueOf(255.0F), v -> ((Boolean)this.rainbow.getValue()).booleanValue()));

    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Module.Category.CLIENT, true, false, false);
        setInstance();
    }

    public static ClickGui getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ClickGui();
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
    @Override
    public void onUpdate() {
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                Bucket.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to \u00a7a" + Bucket.commandManager.getPrefix());
            }
            Bucket.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new BucketGui());
    }

    @Override
    public void onLoad() {
        if (this.colorSync.getValue().booleanValue()) {
            Bucket.colorManager.setColor(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), this.hoverAlpha.getValue());
        } else {
            Bucket.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        }
        Bucket.commandManager.setPrefix(this.prefix.getValue());
    }

    @Override
    public void onTick() {
        if (!(ClickGui.mc.currentScreen instanceof BucketGui)) {
            this.disable();
        }
    }

    public enum rainbowModeArray {
        Static, Up;
    }

    public enum rainbowMode {
        Static, Sideway;
    }

    @Override
    public void onDisable() {
        if (ClickGui.mc.currentScreen instanceof BucketGui) {
            mc.displayGuiScreen(null);
        }
    }
}
