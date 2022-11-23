package me.Bucket;

import me.Bucket.features.modules.misc.RPC;
import me.Bucket.manager.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.io.IOException;

@Mod(modid = "bucket", name = "Bucket", version = "1.1.5")
public class Bucket {
    public static final String MODID = "bucket";
    public static final String MODNAME = "Bucket";
    public static final String MODVER = "1.1.5";
    public static final String NAME_UNICODE = " \u0299\u1D1C\u1D04\u1D0B\u1D07\u1D1B";
    public static final String Bucket_UNICODE = "\u0299\u1D1C\u1D04\u1D0B\u1D07\u1D1B";
    public static final String CHAT_SUFFIX = " \u23d0 \u0299\u1D1C\u1D04\u1D0B\u1D07\u1D1B";
    public static final String Bucket_SUFFIX = " \u23d0 \u0299\u1D1C\u1D04\u1D0B\u1D07\u1D1B";
    public static final Logger LOGGER = LogManager.getLogger("Bucket");
    public static ModuleManager moduleManager;
    public static SpeedManager speedManager;
    public static PositionManager positionManager;
    public static RotationManager rotationManager;
    public static CommandManager commandManager;
    public static EventManager eventManager;
    public static ConfigManager configManager;
    public static FileManager fileManager;
    public static FriendManager friendManager;
    public static TextManager textManager;
    public static ColorManager colorManager;
    public static ServerManager serverManager;
    public static PotionManager potionManager;
    public static InventoryManager inventoryManager;
    public static TimerManager timerManager;
    public static PacketManager packetManager;
    public static ReloadManager reloadManager;
    public static TotemPopManager totemPopManager;
    public static HoleManager holeManager;
    public static NotificationManager notificationManager;
    public static SafetyManager safetyManager;
    public static CosmeticsManager cosmeticsManager;
    public static NoStopManager baritoneManager;
    public static WaypointManager waypointManager;
    @Mod.Instance
    public static Bucket INSTANCE;
    private static boolean unloaded;

    static {
        unloaded = false;
    }

    public static void load() {
        LOGGER.info("\n\nLoading Bucket 1.1.5");
        unloaded = false;
        if (reloadManager != null) {
            reloadManager.unload();
            reloadManager = null;
        }
        baritoneManager = new NoStopManager();
        totemPopManager = new TotemPopManager();
        timerManager = new TimerManager();
        packetManager = new PacketManager();
        serverManager = new ServerManager();
        colorManager = new ColorManager();
        textManager = new TextManager();
        moduleManager = new ModuleManager();
        speedManager = new SpeedManager();
        rotationManager = new RotationManager();
        positionManager = new PositionManager();
        commandManager = new CommandManager();
        eventManager = new EventManager();
        configManager = new ConfigManager();
        fileManager = new FileManager();
        friendManager = new FriendManager();
        potionManager = new PotionManager();
        inventoryManager = new InventoryManager();
        holeManager = new HoleManager();
        notificationManager = new NotificationManager();
        safetyManager = new SafetyManager();
        waypointManager = new WaypointManager();
        LOGGER.info("Initialized Managers");
        moduleManager.init();
        LOGGER.info("Modules loaded.");
        configManager.init();
        eventManager.init();
        LOGGER.info("EventManager loaded.");
        textManager.init(true);
        moduleManager.onLoad();
        totemPopManager.init();
        timerManager.init();
        if (moduleManager.getModuleByClass(RPC.class).isEnabled()) {
            DiscordPresence.start();
        }
        cosmeticsManager = new CosmeticsManager();
        LOGGER.info("Bucket initialized!\n");
    }

    public static void unload(boolean unload) {
        LOGGER.info("\n\nUnloading Bucket 1.1.5");
        if (unload) {
            reloadManager = new ReloadManager();
            reloadManager.init(commandManager != null ? commandManager.getPrefix() : ".");
        }
        if (baritoneManager != null) {
            baritoneManager.stop();
        }
        Bucket.onUnload();
        eventManager = null;
        holeManager = null;
        timerManager = null;
        moduleManager = null;
        totemPopManager = null;
        serverManager = null;
        colorManager = null;
        textManager = null;
        speedManager = null;
        rotationManager = null;
        positionManager = null;
        commandManager = null;
        configManager = null;
        fileManager = null;
        friendManager = null;
        potionManager = null;
        inventoryManager = null;
        notificationManager = null;
        safetyManager = null;
        LOGGER.info("Bucket unloaded!\n");
    }

    public static void reload() {
        Bucket.unload(false);
        Bucket.load();
    }

    public static void onUnload() {
        if (!unloaded) {
            eventManager.onUnload();
            moduleManager.onUnload();
            configManager.saveConfig(Bucket.configManager.config.replaceFirst("Bucket/", ""));
            moduleManager.onUnloadPost();
            timerManager.unload();
            unloaded = true;
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Wobbly is a certified top uwu");
        LOGGER.info("Stallie is a catboy :3");
        LOGGER.info("Stop looking at logs >:(");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        customMainScreen = new GuiCustomMainScreen();
        Display.setTitle("Bucket - v.1.1.5");
        Bucket.load();
    }
}

