package com.prunoideae.recipe;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.KubeJSRegistries;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class ManaInfusionRecipeJS extends BotaniaRecipeJS {
    public int mana = 2000;
    public Block block = null;

    private ItemStack output = null;
    private Ingredient input = null;

    @Override
    public void create(RecipeArguments args) {
        output = parseItemOutput(args.get(0));
        input = parseItemInput(args.get(1));
        if (args.size() > 2)
            mana = (int) ((double) args.get(2));
        if (args.size() > 3)
            block = getBlockJS(args.get(3));
    }

    @Override
    public void deserialize() {
        output = parseItemOutput(json.get("output"));
        input = parseItemInput(json.get("input"));
        mana = json.get("mana").getAsInt();
        if (json.has("catalyst"))
            block = KubeJSRegistries.blocks().get(getAsRL(json.getAsJsonObject("catalyst").get("block").getAsString()));
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            json.add("output", itemToJson(output));
        }
        if (serializeInputs) {
            json.add("input", input.toJson());
        }
        json.addProperty("mana", mana);
        if (block != null) {
            var catalyst = new JsonObject();
            catalyst.addProperty("type", "block");
            catalyst.addProperty("block", getBlockId(block));
            json.add("catalyst", catalyst);
        }
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
