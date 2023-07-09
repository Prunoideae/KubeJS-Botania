package com.prunoideae.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.TimeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface PureDaisySchema {
    RecipeKey<?> OUTPUT = BotaniaSchema.BLOCK_OUTPUT.key("output");
    RecipeKey<?> INPUT = BotaniaSchema.BLOCK_INPUT.key("input");
    RecipeKey<?> TIME = TimeComponent.TICKS.key("time").optional(150L);
    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INPUT, TIME);
}
