package com.prunoideae.schema;

import com.mojang.datafixers.util.Either;
import dev.latvian.mods.kubejs.block.state.BlockStatePredicate;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public interface ManaInfusionSchema {

    RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("output");
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<Integer> MANA = NumberComponent.INT.key("mana").optional(2000);
    RecipeKey<Either<Block, TagKey<Block>>> CATALYST = BotaniaSchema.BLOCK_INPUT.key("catalyst").defaultOptional();

    RecipeSchema SCHEMA = new RecipeSchema(RESULT, INPUT, MANA, CATALYST);
}
