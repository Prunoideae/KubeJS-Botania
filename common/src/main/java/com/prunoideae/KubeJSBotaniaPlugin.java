package com.prunoideae;

import com.prunoideae.custom.BasicBrewJS;
import com.prunoideae.custom.BotaniaRegistryObjectBuilderTypes;
import com.prunoideae.recipe.*;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeHandlersEvent;
import vazkii.botania.api.recipe.*;

public class KubeJSBotaniaPlugin extends KubeJSPlugin {

    @Override
    public void init() {
        BotaniaRegistryObjectBuilderTypes.BREW.addType("basic", BasicBrewJS.Builder.class, BasicBrewJS.Builder::new);
    }

    @Override
    public void addRecipes(RegisterRecipeHandlersEvent event) {
        super.addRecipes(event);
        event.register(IManaInfusionRecipe.TYPE_ID, ManaInfusionRecipeJS::new);
        event.register(IElvenTradeRecipe.TYPE_ID, ElvenTradeRecipeJS::new);
        event.register(IPureDaisyRecipe.TYPE_ID, PureDaisyRecipeJS::new);
        event.register(IBrewRecipe.TYPE_ID, BrewRecipeJS::new);
        event.register(IPetalRecipe.TYPE_ID, PetalRecipeJS::new);
        event.register(IRuneAltarRecipe.TYPE_ID, RuneAltarRecipeJS::new);
        event.register(ITerraPlateRecipe.TYPE_ID, TerraPlateRecipeJS::new);
        event.register(IOrechidRecipe.TYPE_ID, OrechidRecipeJS::new);
        event.register(IOrechidRecipe.IGNEM_TYPE_ID, OrechidRecipeJS::new);
        event.register(IOrechidRecipe.MARIMORPHOSIS_TYPE_ID, OrechidRecipeJS::new);
    }

    @Override
    public void afterInit() {
        //Brew registration
        super.afterInit();
    }
}
