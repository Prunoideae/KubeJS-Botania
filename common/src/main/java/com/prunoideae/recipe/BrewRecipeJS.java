package com.prunoideae.recipe;

import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.resources.ResourceLocation;

public class BrewRecipeJS extends BotaniaRecipeJS {
    private ResourceLocation brewType;

    @Override
    public void create(ListJS args) {
        brewType = UtilsJS.getMCID(args.get(0));
        inputItems.addAll(parseIngredientItemList(args.get(1)));
    }

    @Override
    public void deserialize() {
        brewType = UtilsJS.getMCID(json.get("brew").getAsString());
        inputItems.addAll(parseIngredientItemList(json.get("ingredients")));
    }

    @Override
    public void serialize() {
        if (serializeOutputs)
            json.addProperty("brew", brewType.toString());
        if (serializeInputs) {
            json.add("ingredients", serializeIngredientList(inputItems));
        }

    }
}
