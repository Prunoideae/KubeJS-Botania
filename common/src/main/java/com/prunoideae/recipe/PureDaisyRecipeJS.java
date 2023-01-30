package com.prunoideae.recipe;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class PureDaisyRecipeJS extends BotaniaRecipeJS {
    public String input = null;
    public ItemStack output = null;
    public int time = 150;

    @Override
    public void create(RecipeArguments args) {
        output = parseItemOutput(args.get(0));
        input = (String) args.get(1);
        if (args.size() > 2)
            time = (int) (double) args.get(2);
    }

    @Override
    public void deserialize() {
        output = parseItemOutput(json.get("output").getAsJsonObject().get("name").getAsString());
        input = deserializeBlockInput(json.get("input").getAsJsonObject());
        if (json.has("time"))
            time = json.get("time").getAsInt();
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            var outputBlock = new JsonObject();
            outputBlock.addProperty("name", getBlockId(getBlock(output)));
            json.add("output", outputBlock);
        }
        if (serializeInputs) {
            json.add("input", serializeBlockInput(input));
        }
        json.addProperty("time", time);
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(IngredientJS.of(input));
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if (match.contains(IngredientJS.of(input))) {
            Set<String> transformed = transformer.transform(this, match, IngredientJS.of(input), with).kjs$getItemIds();
            if (transformed.size() > 1)
                KubeJS.LOGGER.warn("Ingredient has more than one item! This is not allowed in Orechid recipes, using the first item as default.");
            if (transformed.isEmpty())
                KubeJS.LOGGER.warn("Ingredient has no item inside! This is not allowed in Orechid recipes, not replacing items.");
            input = transformed.stream().findFirst().orElse(null);
            return true;
        }
        return false;
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
