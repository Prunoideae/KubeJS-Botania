package com.prunoideae.recipe;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.level.block.Block;

public class PureDaisyRecipeJS extends BotaniaRecipeJS {
    public Block input = null;
    public Block output = null;

    @Override
    public void create(ListJS args) {
        output = getBlockJS(args.get(0));
        input = getBlockJS(args.get(1));
        outputItems.add(asItemStackJS(output));
        inputItems.add(asItemStackJS(input));
    }

    @Override
    public void deserialize() {
        output = getBlockJS(json.get("output").getAsJsonObject().get("name").getAsString());
        input = getBlockJS(json.get("input").getAsJsonObject().get("block").getAsString());
        outputItems.add(asItemStackJS(output));
        inputItems.add(asItemStackJS(input));
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            var outputBlock = new JsonObject();
            outputBlock.addProperty("name", getBlockId(output));
            json.add("output", outputBlock);
        }
        if (serializeInputs) {
            var inputBlock = new JsonObject();
            inputBlock.addProperty("type", "block");
            inputBlock.addProperty("block", getBlockId(input));
            json.add("input", inputBlock);
        }
    }
}
