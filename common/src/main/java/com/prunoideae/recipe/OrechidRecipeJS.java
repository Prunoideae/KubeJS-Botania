package com.prunoideae.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrechidRecipeJS extends BotaniaRecipeJS {
    private Block input;
    private Block output;
    private int weight = 100;
    private final List<String> biomes = new ArrayList<>();
    private int biomeBonus = 100;

    @Override
    public void create(ListJS args) {
        output = getBlockJS(args.get(0));
        input = getBlockJS(args.get(1));
        if (args.size() > 2)
            weight = (int) ((double) args.get(2));
        if (args.size() > 3)
            biomes.addAll(ListJS.orSelf(args.get(3)).stream().map(String::valueOf).collect(Collectors.toList()));
        if (args.size() > 4)
            biomeBonus = (int) ((double) args.get(4));
        outputItems.add(asItemStackJS(output));
        inputItems.add(asItemStackJS(input));
    }


    @Override
    public void deserialize() {
        output = getBlockJS(json.get("output").getAsJsonObject().get("block").getAsString());
        input = getBlockJS(json.get("input").getAsString());
        outputItems.add(asItemStackJS(output));
        inputItems.add(asItemStackJS(input));

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
            outputJS.addProperty("block", getBlockId(output));
            json.add("output", outputJS);
        }
        if (serializeInputs) {
            json.addProperty("input", getBlockId(input));
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
}
