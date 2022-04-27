package com.prunoideae.recipe;

import com.google.gson.JsonArray;
import dev.latvian.mods.kubejs.util.ListJS;

public class ElvenTradeRecipeJS extends BotaniaRecipeJS {
    @Override
    public void create(ListJS args) {
        outputItems.addAll(parseResultItemList(args.get(0)));
        inputItems.addAll(parseIngredientItemList(args.get(1)));
    }

    @Override
    public void deserialize() {
        outputItems.addAll(parseResultItemList(json.get("output")));
        inputItems.addAll(parseIngredientItemList(json.get("ingredients")));
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            var outputs = new JsonArray();
            for (var output : outputItems) {
                outputs.add(output.toResultJson());
            }
            json.add("output", outputs);
        }
        if (serializeInputs) {
            json.add("ingredients", serializeIngredientList(inputItems));
        }
    }
}
