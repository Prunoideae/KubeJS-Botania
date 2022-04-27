package com.prunoideae.forge;

import com.prunoideae.KubeJSBotania;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KubeJSBotania.MOD_ID)
public class KubeJSBotaniaForge {
    public KubeJSBotaniaForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(KubeJSBotania.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        KubeJSBotania.init();
    }
}
