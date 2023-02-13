package com.prunoideae.recipe;

import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class TerraPlateRecipeJS extends BotaniaRecipeJS {
    public int mana = 500000;
    private ItemStack output = null;
    private final List<Ingredient> inputs = new ArrayList<>();

    @Override
    public void create(RecipeArguments args) {
        output = parseItemOutput(args.get(0));
        inputs.addAll(parseItemInputList(args.get(1)));
        if (args.size() > 2) {
            mana = (int) ((double) args.get(2));
        }
    }

    @Override
    public void deserialize() {
        output = parseItemOutput(json.get("result"));
        inputs.addAll(parseItemInputList(json.get("ingredients")));
        mana = json.get("mana").getAsInt();
    }

    @Override
    public void serialize() {
        if (serializeOutputs)
            json.add("result", itemToJson(output));
        if (serializeInputs)
            json.add("ingredients", serializeIngredientList(inputs));
        json.addProperty("mana", mana);
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return inputs.stream().anyMatch(match::contains);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        boolean anyReplaced = false;
        for (int i = 0; i < inputs.size(); i++) {
            if (match.contains(inputs.get(i))) {
                inputs.set(i, transformer.transform(this, match, inputs.get(i), with));
                anyReplaced = true;
            }
        }
        return anyReplaced;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return match.contains(output);
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        if (match.contains(output)) {
            output = transformer.transform(this, match, output, with);
            return true;
        }
        return false;
    }
}
