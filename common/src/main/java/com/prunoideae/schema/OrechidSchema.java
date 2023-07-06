package com.prunoideae.schema;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.TagKeyComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.typings.desc.DescriptionContext;
import dev.latvian.mods.kubejs.typings.desc.GenericDescJS;
import dev.latvian.mods.kubejs.typings.desc.TypeDescJS;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public interface OrechidSchema {
    RecipeKey<?> OUTPUT = BotaniaSchema.BLOCK_INPUT.key("output");
    RecipeKey<?> INPUT = BotaniaSchema.BLOCK_INPUT.key("input");
    RecipeKey<?> WEIGHT = NumberComponent.INT.key("weight");
    RecipeKey<?> BIOMES = TagKeyComponent.BIOME.key("biome_bonus_tag");
    RecipeKey<?> BIOME_BONUS = NumberComponent.INT.key("biome_bonus").optional(100);

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INPUT, WEIGHT);
    RecipeSchema SCHEMA_BIOME = new RecipeSchema(OUTPUT, INPUT, BIOMES, WEIGHT, BIOME_BONUS);
}
