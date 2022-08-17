package com.prunoideae.recipe;

import dev.latvian.mods.kubejs.util.ListJS;

public class TerraPlateRecipeJS extends BotaniaRecipeJS {
    public int mana = 500000;

    @Override
    public void create(ListJS args) {
        outputItems.add(parseResultItem(args.get(0)));
        inputItems.addAll(parseIngredientItemList(args.get(1)));
        if (args.size() > 2) {
            mana = (int) ((double) args.get(2));
        }
    }

    @Override
    public void deserialize() {
        outputItems.add(parseResultItem(json.get("result")));
        inputItems.addAll(parseIngredientItemList(json.get("ingredients")));
        mana = json.get("mana").getAsInt();
    }

    @Override
    public void serialize() {
        if (serializeOutputs)
            json.add("result", outputItems.get(0).toResultJson());
        if (serializeInputs)
            json.add("ingredients", serializeIngredientList(inputItems));
        json.addProperty("mana", mana);
    }
}
