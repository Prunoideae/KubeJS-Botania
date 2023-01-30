package com.prunoideae.recipe;

import com.google.gson.JsonArray;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class ElvenTradeRecipeJS extends BotaniaRecipeJS {
    private final List<ItemStack> outputItems = new ArrayList<>();
    private final List<Ingredient> inputItems = new ArrayList<>();

    @Override
    public void create(RecipeArguments args) {
        outputItems.addAll(parseItemOutputList(args.get(0)));
        inputItems.addAll(parseItemInputList(args.get(1)));
    }

    @Override
    public void deserialize() {
        outputItems.addAll(parseItemOutputList(json.get("output")));
        inputItems.addAll(parseItemInputList(json.get("ingredients")));
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            var outputs = new JsonArray();
            for (var output : outputItems) {
                outputs.add(this.itemToJson(output));
            }
            json.add("output", outputs);
        }
        if (serializeInputs) {
            json.add("ingredients", serializeIngredientList(inputItems));
        }
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return inputItems.stream().anyMatch(match::contains);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        boolean anyReplaced = false;
        for (int i = 0; i < inputItems.size(); i++) {
            if (match.contains(inputItems.get(i))) {
                inputItems.set(i, transformer.transform(this, match, inputItems.get(i), with));
                anyReplaced = true;
            }
        }
        return anyReplaced;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return outputItems.stream().anyMatch(match::contains);
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        boolean anyReplaced = false;
        for (int i = 0; i < outputItems.size(); i++) {
            if (match.contains(outputItems.get(i))) {
                outputItems.set(i, transformer.transform(this, match, outputItems.get(i), with));
                anyReplaced = true;
            }
        }
        return anyReplaced;
    }
}
