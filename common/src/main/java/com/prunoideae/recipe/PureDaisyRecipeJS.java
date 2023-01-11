package com.prunoideae.recipe;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class PureDaisyRecipeJS extends BotaniaRecipeJS {
    public Ingredient input = null;
    public ItemStack output = null;
    public int time = 150;

    @Override
    public void create(RecipeArguments args) {
        output = parseItemOutput(args.get(0));
        input = parseItemInput(args.get(1));
        if (args.size() > 2)
            time = (int) (double) args.get(2);
    }

    @Override
    public void deserialize() {
        output = parseItemOutput(json.get("output").getAsJsonObject().get("name").getAsString());
        input = parseItemInput(json.get("input").getAsJsonObject().get("block").getAsString());
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
            var inputBlock = new JsonObject();
            inputBlock.addProperty("type", "block");
            inputBlock.addProperty("block", getBlockId(getBlock(input)));
            json.add("input", inputBlock);
        }
        json.addProperty("time", time);
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(input);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if (match.contains(input)) {
            input = transformer.transform(this, match, input, with);
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
