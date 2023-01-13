package com.prunoideae.custom.brew;

import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.common.brew.BotaniaBrews;
import vazkii.botania.common.lib.ResourceLocationHelper;
import vazkii.botania.xplat.XplatAbstractions;

public class BotaniaRegistryObjectBuilderTypes {
    public static RegistryObjectBuilderTypes<Brew> BREW;

    static {
        //BREW = RegistryObjectBuilderTypes.add((ResourceKey<Registry<Brew>>) BotaniaBrews.registry.key(), Brew.class);
    }
}
