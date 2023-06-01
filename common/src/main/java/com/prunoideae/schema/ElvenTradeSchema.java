package com.prunoideae.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface ElvenTradeSchema {
    RecipeKey<?> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeKey<?> INGREDIENTS = ItemComponents.INPUT_ARRAY.key("ingredients");

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INGREDIENTS);
}
