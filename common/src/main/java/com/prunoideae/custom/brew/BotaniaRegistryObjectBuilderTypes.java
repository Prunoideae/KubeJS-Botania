package com.prunoideae.custom.brew;

import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.minecraft.core.Registry;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.xplat.XplatAbstractions;

public class BotaniaRegistryObjectBuilderTypes {
    public static Registry<Brew> BREW_REGISTRY;
    public static RegistryInfo BREW;

    static {
        BREW_REGISTRY = XplatAbstractions.INSTANCE.getOrCreateBrewRegistry();
        BREW = RegistryInfo.of(BREW_REGISTRY.key()).type(Brew.class);
    }
}
