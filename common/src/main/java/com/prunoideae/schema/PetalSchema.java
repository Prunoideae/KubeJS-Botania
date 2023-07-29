package com.prunoideae.schema;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface PetalSchema {
	RecipeKey<?> OUTPUT = ItemComponents.OUTPUT.key("output");
	RecipeKey<?> INGREDIENTS = ItemComponents.INPUT_ARRAY.key("ingredients");
	RecipeKey<?> REAGENT = ItemComponents.INPUT.key("reagent").optional(InputItem.of("#botania:seed_apothecary_reagent")).alwaysWrite();

	RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INGREDIENTS, REAGENT);
			/*
			.constructor(OUTPUT, INGREDIENTS, REAGENT)
			.constructor((recipe, schemaType, keys, from) -> {
				recipe.setValue(OUTPUT, from.get(OUTPUT));
				recipe.setValue(INGREDIENTS, from.get(INGREDIENTS));
				recipe.setValue(REAGENT, InputItem.of("#botania:seed_apothecary_reagent"));
			}, OUTPUT, INGREDIENTS);
			 */
}
