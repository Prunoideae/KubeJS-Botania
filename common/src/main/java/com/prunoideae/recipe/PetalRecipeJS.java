package com.prunoideae.recipe;

import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class PetalRecipeJS extends BotaniaRecipeJS {

    private ItemStack output = null;
    private final List<Ingredient> inputs = new ArrayList<>();

    @Override
    public void create(RecipeArguments args) {
        output = parseItemOutput(args.get(0));
        inputs.addAll(parseItemInputList(args.get(1)));
    }

    @Override
    public void deserialize() {
        output=(parseItemOutput(json.get("output")));
        inputs.addAll(parseItemInputList(json.get("ingredients")));
    }

    @Override
    public void serialize() {
        if (serializeOutputs)
            json.add("output", output.kjs$toJson());
        if (serializeInputs)
            json.add("ingredients", serializeIngredientList(inputs));
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
