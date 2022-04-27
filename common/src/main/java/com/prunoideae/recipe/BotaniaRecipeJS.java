package com.prunoideae.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.architectury.registry.registries.Registrar;
import dev.latvian.mods.kubejs.KubeJSRegistries;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Objects;

public abstract class BotaniaRecipeJS extends RecipeJS {
    private final Registrar<Block> blockRegistry;

    protected BotaniaRecipeJS() {
        super();
        this.blockRegistry = KubeJSRegistries.blocks();
    }

    protected Block getBlockJS(Object o) {
        return blockRegistry.get(UtilsJS.getMCID(o));
    }

    protected String getBlockId(Block block) {
        return Objects.requireNonNull(blockRegistry.getId(block)).toString();
    }

    protected JsonArray serializeIngredientList(List<IngredientJS> ingredients) {
        var result = new JsonArray();
        for (var ingredient : ingredients) {
            result.add(ingredient.toJson());
        }
        return result;
    }

    protected ItemStackJS asItemStackJS(Block block){
        return ItemStackJS.of(block.asItem());
    }
}
