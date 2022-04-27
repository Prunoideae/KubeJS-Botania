package com.prunoideae.recipe;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.KubeJSRegistries;
import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.world.level.block.Block;

public class ManaInfusionRecipeJS extends BotaniaRecipeJS {
    private int mana = 2000;
    public Block block = null;

    @Override
    public void create(ListJS args) {
        outputItems.add(parseResultItem(args.get(0)));
        inputItems.add(parseIngredientItem(args.get(1)).asIngredientStack());
        if (args.size() > 2)
            mana = (int) ((double) args.get(2));
        if (args.size() > 3)
            block = getBlockJS(args.get(3));
    }

    @Override
    public void deserialize() {
        outputItems.add(parseResultItem(json.get("output")));
        inputItems.add(parseIngredientItem(json.get("input")));
        mana = json.get("mana").getAsInt();
        if (json.has("catalyst"))
            block = KubeJSRegistries.blocks().get(UtilsJS.getMCID(json.getAsJsonObject("catalyst").get("block").getAsString()));
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            json.add("output", outputItems.get(0).toResultJson());
        }
        if (serializeInputs) {
            json.add("input", inputItems.get(0).toJson());
        }
        json.addProperty("mana", mana);
        if (block != null) {
            var catalyst = new JsonObject();
            catalyst.addProperty("type", "block");
            catalyst.addProperty("block", getBlockId(block));
            json.add("catalyst", catalyst);
        }
    }
}
