package com.prunoideae.recipe;

import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class BrewRecipeJS extends BotaniaRecipeJS {
    private ResourceLocation brewType;
    private final List<Ingredient> inputs = new ArrayList<>();

    @Override
    public void create(RecipeArguments args) {
        brewType = getAsRL(args.get(0));
        inputs.addAll(parseItemInputList(args.get(1)));
    }

    @Override
    public void deserialize() {
        brewType = getAsRL(json.get("brew").getAsString());
        inputs.addAll(parseItemInputList(json.get("ingredients")));
    }

    @Override
    public void serialize() {
        if (serializeOutputs)
            json.addProperty("brew", brewType.toString());
        if (serializeInputs) {
            json.add("ingredients", serializeIngredientList(inputs));
        }
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
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        return false;
    }
}
