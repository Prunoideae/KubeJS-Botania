package com.prunoideae.recipe;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.block.state.BlockStatePredicate;
import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public abstract class BlockRecipeJS extends BotaniaRecipeJS {
    protected BlockStatePredicate input;
    protected BlockStatePredicate output;

    @Override
    public void create(RecipeArguments args) {
        output = BlockStatePredicate.fromString(args.getString(0, ""));
        input = BlockStatePredicate.fromString(args.getString(1, ""));
    }

    @Override
    public void deserialize() {
        input = BlockStatePredicate.fromString(deserializeBlock(json.get("input").getAsJsonObject()));
        output = BlockStatePredicate.fromString(deserializeBlock(json.get("output").getAsJsonObject()));
    }

    @Override
    public void serialize() {
        if (serializeOutputs) {
            json.add("output", serializeBlock(output));
        }
        if (serializeInputs) {
            json.add("input", serializeBlock(input));
        }
    }

    protected String deserializeBlock(JsonObject input) {
        return switch (input.get("type").getAsString()) {
            case "tag" -> "#" + input.get("tag").getAsString();
            case "block" -> input.get("block").getAsString();
            default -> null;
        };
    }

    protected JsonObject serializeBlock(BlockStatePredicate input) {
        var output = new JsonObject();
        if (input instanceof BlockStatePredicate.TagMatch tagMatch) {
            output.addProperty("type", "tag");
            output.addProperty("tag", tagMatch.tag().location().toString());
        } else if (input instanceof BlockStatePredicate.BlockMatch blockMatch) {
            output.addProperty("type", "block");
            output.addProperty("block", Registry.BLOCK.getKey(blockMatch.block()).toString());
        } else
            throw new RecipeExceptionJS("Can't decide what block to use for recipe %s! Can only use Block or Tag!".formatted(id));
        return output;
    }

    protected boolean matchBlock(IngredientMatch match, BlockStatePredicate block) {
        if (block instanceof BlockStatePredicate.BlockMatch blockMatch) {
            return match.contains(Ingredient.of(blockMatch.block().asItem()));
        }
        return false;
    }

    protected ItemStack toItemStack(BlockStatePredicate block) {
        if (block instanceof BlockStatePredicate.BlockMatch blockMatch) {
            return new ItemStack(blockMatch.block());
        }
        return ItemStack.EMPTY;
    }

    protected BlockStatePredicate toBlock(Ingredient ingredient) {
        BlockItem blockItem = ingredient
                .kjs$getItemTypes()
                .stream()
                .filter(item -> item instanceof BlockItem)
                .map(item -> (BlockItem) item)
                .findFirst()
                .orElse(null);

        if (blockItem == null)
            return null;
        return new BlockStatePredicate.BlockMatch(blockItem.getBlock());
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(toItemStack(input));
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return match.contains(toItemStack(output));
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if (matchBlock(match, input)) {
            ItemStack itemStack = toItemStack(input);
            if (itemStack.isEmpty())
                return false;
            BlockStatePredicate transformed = toBlock(transformer.transform(this, match, Ingredient.of(itemStack), with));
            if (transformed == null)
                return false;
            input = transformed;
        }
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        if (matchBlock(match, output)) {
            ItemStack itemStack = toItemStack(output);
            if (itemStack.isEmpty())
                return false;
            BlockStatePredicate transformed = toBlock(Ingredient.of(transformer.transform(this, match, itemStack, with)));
            if (transformed == null)
                return false;
            input = transformed;
        }
        return false;
    }
}
