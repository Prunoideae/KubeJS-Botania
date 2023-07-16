package com.prunoideae.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface BrewSchema {
    RecipeKey<?> BREW = BotaniaSchema.BREW.key("brew");
    RecipeKey<?> INGREDIENTS = ItemComponents.INPUT_ARRAY.key("ingredients");

    RecipeSchema SCHEMA = new RecipeSchema(BREW, INGREDIENTS);
}
