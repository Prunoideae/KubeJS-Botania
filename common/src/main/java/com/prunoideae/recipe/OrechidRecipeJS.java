package com.prunoideae.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.block.state.BlockStatePredicate;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrechidRecipeJS extends BlockRecipeJS {
    private int weight = 100;
    private final List<String> biomes = new ArrayList<>();
    private int biomeBonus = 100;

    @Override
    public void create(RecipeArguments args) {
        super.create(args);
        if (args.size() > 2)
            weight = (int) ((double) args.get(2));
        if (args.size() > 3)
            biomes.addAll(ListJS.orSelf(args.get(3)).stream().map(String::valueOf).toList());
        if (args.size() > 4)
            biomeBonus = (int) ((double) args.get(4));
    }


    @Override
    public void deserialize() {
        super.deserialize();
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
        super.serialize();
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
}
