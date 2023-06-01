package com.prunoideae.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

import java.util.List;

public interface OrechidSchema {
    RecipeKey<?> OUTPUT = BotaniaSchema.BLOCK.key("output");
    RecipeKey<?> INPUT = BotaniaSchema.BLOCK.key("input");
    RecipeKey<?> WEIGHT = NumberComponent.INT.key("weight").optional(100);
    RecipeKey<?> BIOMES = StringComponent.NON_BLANK.key("biome_bonus_tag");
    RecipeKey<?> BIOME_BONUS = NumberComponent.INT.key("biome_bonus").optional(100);

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INPUT, WEIGHT);
    RecipeSchema SCHEMA_BIOME = new RecipeSchema(OUTPUT, INPUT, WEIGHT, BIOMES, BIOME_BONUS);
}
