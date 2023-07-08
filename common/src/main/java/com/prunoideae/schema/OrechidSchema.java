package com.prunoideae.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.TagKeyComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface OrechidSchema {
    RecipeKey<?> OUTPUT = BotaniaSchema.BLOCK_INPUT.key("output");
    RecipeKey<?> INPUT = BotaniaSchema.BLOCK_INPUT.key("input");
    RecipeKey<?> WEIGHT = NumberComponent.INT.key("weight");
    RecipeKey<?> BIOMES = TagKeyComponent.BIOME.key("biome_bonus_tag");
    RecipeKey<?> BIOME_BONUS = NumberComponent.INT.key("biome_bonus").optional(100);

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INPUT, WEIGHT);
    RecipeSchema SCHEMA_BIOME = new RecipeSchema(OUTPUT, INPUT, BIOMES, WEIGHT, BIOME_BONUS);
}
