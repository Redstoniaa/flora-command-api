package flora;

import flora.command.FloraCommandManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FloraCommandAPI
        implements ModInitializer {
    public static final String MOD_ID = "flora-command-api";
    public static ModMetadata METADATA;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    @Override
    public void onInitialize() {
        getMetadata();
        FloraCommandManager.init();
    }
    
    private static void getMetadata() {
        METADATA = FabricLoader.getInstance()
                .getModContainer(MOD_ID).orElseThrow()
                .getMetadata();
    }
}