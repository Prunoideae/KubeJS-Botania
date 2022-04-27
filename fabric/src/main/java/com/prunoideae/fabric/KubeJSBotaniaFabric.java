package com.prunoideae.fabric;

import com.prunoideae.KubeJSBotania;
import net.fabricmc.api.ModInitializer;

public class KubeJSBotaniaFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        KubeJSBotania.init();
    }
}
