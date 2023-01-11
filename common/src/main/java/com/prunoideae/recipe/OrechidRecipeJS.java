package com.prunoideae.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrechidRecipeJS extends BotaniaRecipeJS {
    private Ingredient input;
    private ItemStack output;
    private int weight = 100;
    private final List<String> biomes = new ArrayList<>();
    private int biomeBonus = 100;

    @Override
    public void create(RecipeArguments args) {
        output = parseItemOutput(args.get(0));
        input = parseItemInput(args.get(1));
        if (args.size() > 2)
            weight = (int) ((double) args.get(2));
        if (args.size() > 3)
            biomes.addAll(ListJS.orSelf(args.get(3)).stream().map(String::valueOf).toList());
        if (args.size() > 4)
            biomeBonus = (int) ((double) args.get(4));
    }


    @Override
    public void deserialize() {
        output = parseItemOutput(json.get("output").getAsJsonObject().get("block").getAsString());
        input = parseItemInput(json.get("input").getAsString());

        weight = json.get("weight").getAsInt();
        if (json.has("biomes"))
            for (var biome : json.get("biomes").getAsJsonArray()) {
                biomes.add(biome.getAsString());
            }
        if (json.has("biome_bonus"))
            biomeBonus = json.get("biome_bonus").getAsInt();
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            var outputJS = new JsonObject();
            outputJS.addProperty("type", "block");
            outputJS.addProperty("block", getBlockId(getBlock(output)));
            json.add("output", outputJS);
        }
        if (serializeInputs) {
            json.addProperty("input", getBlockId(getBlock(input)));
        }
        json.addProperty("weight", weight);
        if (!biomes.isEmpty()) {
            var biomesJS = new JsonArray();
            for (var biome : biomes) {
                biomesJS.add(biome);
            }
            json.add("biomes", biomesJS);
            json.addProperty("biome_bonus", biomeBonus);
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
