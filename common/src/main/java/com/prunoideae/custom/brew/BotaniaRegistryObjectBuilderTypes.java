package com.prunoideae.custom.brew;

import dev.latvian.mods.kubejs.registry.RegistryInfo;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.xplat.XplatAbstractions;

public class BotaniaRegistryObjectBuilderTypes {
    public static RegistryInfo BREW;

    static {
        BREW = RegistryInfo.of(XplatAbstractions.INSTANCE.getOrCreateBrewRegistry().key()).type(Brew.class);
    }
}
