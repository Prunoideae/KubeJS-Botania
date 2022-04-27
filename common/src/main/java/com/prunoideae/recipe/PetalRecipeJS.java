package com.prunoideae.recipe;

import dev.latvian.mods.kubejs.util.ListJS;

public class PetalRecipeJS extends BotaniaRecipeJS {
    @Override
    public void create(ListJS args) {
        outputItems.add(parseResultItem(args.get(0)));
        inputItems.addAll(parseIngredientItemList(args.get(1)));
    }

    @Override
    public void deserialize() {
        outputItems.add(parseResultItem(json.get("output")));
        inputItems.addAll(parseIngredientItemList(json.get("ingredients")));
    }

    @Override
    public void serialize() {
        if (serializeOutputs)
            json.add("output", outputItems.get(0).toResultJson());
        if (serializeInputs)
            json.add("ingredients", serializeIngredientList(inputItems));
    }
}
