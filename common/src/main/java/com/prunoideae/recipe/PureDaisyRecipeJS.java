package com.prunoideae.recipe;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Set;

public class PureDaisyRecipeJS extends BlockRecipeJS {
    public int time = 150;

    @Override
    public void create(RecipeArguments args) {
        super.create(args);
        if (args.size() > 2)
            time = (int) (double) args.get(2);
    }

    @Override
    public void deserialize() {
        super.deserialize();
        if (json.has("time"))
            time = json.get("time").getAsInt();
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            var jsonObject = new JsonObject();
            jsonObject.addProperty("name", output.getBlockIds().stream().findFirst().orElse(new ResourceLocation("stone")).toString());
            json.add("output", jsonObject);
        }
        if (serializeInputs) {
            json.add("input", serializeBlock(input));
        }
        json.addProperty("time", time);
    }

    @Override
    protected String deserializeBlock(JsonObject input) {
        if (!input.has("type")) {
            return input.get("name").getAsString();
        } else return super.deserializeBlock(input);
    }
}
