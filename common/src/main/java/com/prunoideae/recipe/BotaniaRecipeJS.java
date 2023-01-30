package com.prunoideae.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.architectury.registry.registries.Registrar;
import dev.latvian.mods.kubejs.KubeJSRegistries;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
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
        return blockRegistry.get(getAsRL(o));
    }

    protected ResourceLocation getAsRL(Object o) {
        if (o instanceof ResourceLocation rl)
            return rl;
        return new ResourceLocation(o.toString());
    }

    protected String getBlockId(Block block) {
        return Objects.requireNonNull(blockRegistry.getId(block)).toString();
    }

    protected JsonArray serializeIngredientList(List<Ingredient> ingredients) {
        var result = new JsonArray();
        for (var ingredient : ingredients) {
            result.add(ingredient.toJson());
        }
        return result;
    }

    protected ItemStack asItemStackJS(Block block) {
        return ItemStackJS.of(block.asItem());
    }

    protected Block getBlock(ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem blockItem) {
            return blockItem.getBlock();
        }
        return null;
    }

    protected Block getBlock(Ingredient ingredient){
        for (ItemStack item : ingredient.getItems()) {
            Block block = getBlock(item);
            if (block != null)
                return block;
        }
        return null;
    }
}
