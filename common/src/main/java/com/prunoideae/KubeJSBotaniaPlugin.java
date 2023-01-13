package com.prunoideae;

import com.prunoideae.custom.item.RuneItemBuilder;
import com.prunoideae.recipe.*;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.api.recipe.*;

public class KubeJSBotaniaPlugin extends KubeJSPlugin {

    @Override
    public void init() {
        // https://github.com/VazkiiMods/Botania/issues/4264
        // BotaniaRegistryObjectBuilderTypes.BREW.addType("basic", BasicBrewJS.Builder.class, BasicBrewJS.Builder::new);
        RegistryObjectBuilderTypes.ITEM.addType("botania:rune", RuneItemBuilder.class, RuneItemBuilder::new);
    }

    @Override
    public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
        event.register(ManaInfusionRecipe.TYPE_ID, ManaInfusionRecipeJS::new);
        event.register(ElvenTradeRecipe.TYPE_ID, ElvenTradeRecipeJS::new);
        event.register(PureDaisyRecipe.TYPE_ID, PureDaisyRecipeJS::new);
        event.register(BotanicalBreweryRecipe.TYPE_ID, BrewRecipeJS::new);
        event.register(PetalApothecaryRecipe.TYPE_ID, PetalRecipeJS::new);
        event.register(RunicAltarRecipe.TYPE_ID, RuneAltarRecipeJS::new);
        event.register(TerrestrialAgglomerationRecipe.TYPE_ID, TerraPlateRecipeJS::new);
        event.register(OrechidRecipe.TYPE_ID, OrechidRecipeJS::new);
        event.register(OrechidRecipe.IGNEM_TYPE_ID, OrechidRecipeJS::new);
        event.register(OrechidRecipe.MARIMORPHOSIS_TYPE_ID, OrechidRecipeJS::new);
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("ManaHandler", ManaItemHandler.instance());
        event.add("CorporeaHelper", CorporeaHelper.instance());
    }

    @Override
    public void afterInit() {
        //Brew registration
        super.afterInit();
    }
}
