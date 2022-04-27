package com.prunoideae.custom;

import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.common.brew.ModBrews;

public class BotaniaRegistryObjectBuilderTypes {
    public static final RegistryObjectBuilderTypes<Brew> BREW;

    static {
        ResourceKey<Registry<Brew>> brewRegistry = (ResourceKey<Registry<Brew>>) ModBrews.registry.key();
        BREW = RegistryObjectBuilderTypes.add(brewRegistry, Brew.class);
    }
}
