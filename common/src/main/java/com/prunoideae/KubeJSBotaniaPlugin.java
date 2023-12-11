package com.prunoideae;

import com.prunoideae.custom.brew.BasicBrewJS;
import com.prunoideae.custom.item.RuneItemBuilder;
import com.prunoideae.schema.*;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import vazkii.botania.api.BotaniaRegistries;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.mana.ManaItemHandler;

public class KubeJSBotaniaPlugin extends KubeJSPlugin {

    @Override
    public void init() {
        RegistryInfo<Brew> brew = RegistryInfo.of(BotaniaRegistries.BREWS, Brew.class);
        brew.addType("basic", BasicBrewJS.Builder.class, BasicBrewJS.Builder::new);
        RegistryInfo.ITEM.addType("botania:rune", RuneItemBuilder.class, RuneItemBuilder::new);
    }


    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.namespace("botania")
                .register("terra_plate", TerraPlateSchema.SCHEMA)
                .register("mana_infusion", ManaInfusionSchema.SCHEMA)
                .register("pure_daisy", PureDaisySchema.SCHEMA)
                .register("elven_trade", ElvenTradeSchema.SCHEMA)
                .register("runic_altar", RunicAltarSchema.SCHEMA)
                .register("brew", BrewSchema.SCHEMA)
                .register("petal_apothecary", PetalSchema.SCHEMA)
                .register("orechid", OrechidSchema.SCHEMA)
                .register("orechid_ignem", OrechidSchema.SCHEMA)
                .register("marimorphosis", OrechidSchema.SCHEMA_BIOME)
        ;
    }


    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("ManaHandler", ManaItemHandler.instance());
        event.add("CorporeaHelper", CorporeaHelper.instance());
    }
}
