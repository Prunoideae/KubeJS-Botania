package com.prunoideae.schema;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface PetalSchema {
    RecipeKey<?> OUTPUT = ItemComponents.OUTPUT.key("output");
    RecipeKey<?> INGREDIENTS = ItemComponents.INPUT_ARRAY.key("ingredients");
    RecipeKey<?> REAGENT = ItemComponents.INPUT.key("reagent").optional(InputItem.of("#botania:seed_apothecary_reagent"));

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INGREDIENTS, REAGENT);
}
