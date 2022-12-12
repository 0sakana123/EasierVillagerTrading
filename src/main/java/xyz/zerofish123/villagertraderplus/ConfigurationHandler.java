package xyz.zerofish123.villagertraderplus;

import de.guntram.mcmod.fabrictools.ConfigChangedEvent;
import de.guntram.mcmod.fabrictools.Configuration;
import de.guntram.mcmod.fabrictools.ModConfigurationHandler;

import java.io.File;

public class ConfigurationHandler implements ModConfigurationHandler {

    private static ConfigurationHandler instance;

    private Configuration config;
    private String configFileName;

    public static ConfigurationHandler getInstance() {
        if (instance==null)
            instance=new ConfigurationHandler();
        return instance;
    }
    private boolean swapShiftBehavior;
    private String whitelistedServers;

    public void load(final File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            configFileName=configFile.getPath();
            loadConfig();
        }
    }

    @Override
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        // System.out.println("OnConfigChanged for "+event.getModID());
        if (event.getModID().equalsIgnoreCase(EasierVillagerTrading.MODID)) {
            loadConfig();
        }
    }
    
    private void loadConfig() {
        swapShiftBehavior=config.getBoolean("easiervillagertrading.config.swapshift", Configuration.CATEGORY_CLIENT, false, "easiervillagertrading.config.tt.swapshift");
        whitelistedServers=config.getString("easiervillagertrading.config.allowedservers", Configuration.CATEGORY_CLIENT, "none", "easiervillagertrading.config.tt.allowedservers");

        if (config.hasChanged())
            config.save();
    }
    
    @Override
    public Configuration getConfig() {
        return config;
    }
    
    public static String getConfigFileName() {
        return getInstance().configFileName;
    }
    
    public static boolean isShiftSwapped() {
        return getInstance().swapShiftBehavior;
    }

    public static String whitelistedServerAddress() { return getInstance().whitelistedServers; }
}