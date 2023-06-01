package com.prunoideae.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface RunicAltarSchema {
    RecipeKey<?> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeKey<?> INGREDIENTS = ItemComponents.INPUT_ARRAY.key("ingredients");
    RecipeKey<?> MANA = ItemComponents.INPUT.key("mana");

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INGREDIENTS, MANA);
}
