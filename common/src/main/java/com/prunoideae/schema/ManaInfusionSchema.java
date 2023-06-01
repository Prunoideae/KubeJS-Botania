package com.prunoideae.schema;

import dev.latvian.mods.kubejs.block.state.BlockStatePredicate;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface ManaInfusionSchema {

    RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("output");
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<Integer> MANA = NumberComponent.INT.key("mana").optional(2000);
    RecipeKey<BlockStatePredicate> CATALYST = BotaniaSchema.BLOCK.key("catalyst").optional(BlockStatePredicate.Simple.ALL);

    RecipeSchema SCHEMA = new RecipeSchema(RESULT, INPUT, MANA, CATALYST);
}
