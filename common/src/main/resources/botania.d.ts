class RecipeHolder {
    readonly botania: Document.BotaniaRecipes;
}

class BotaniaRecipes {

    /**
     * Default mana is 100.
     */
    mana_infusion(output: dev.latvian.mods.kubejs.item.ItemStackJS, input: dev.latvian.mods.kubejs.item.ingredient.IngredientJS): com.prunoideae.recipe.ManaInfusionRecipeJS;
    mana_infusion(output: dev.latvian.mods.kubejs.item.ItemStackJS, input: dev.latvian.mods.kubejs.item.ingredient.IngredientJS, mana: number): com.prunoideae.recipe.ManaInfusionRecipeJS;
    mana_infusion(output: dev.latvian.mods.kubejs.item.ItemStackJS, input: dev.latvian.mods.kubejs.item.ingredient.IngredientJS, mana: number, catalyst: net.minecraft.world.level.block.Block): com.prunoideae.recipe.ManaInfusionRecipeJS;

    /**
     * Default mana is 5,200, same as Basic Runes.
     */
    runic_altar(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[]): com.prunoideae.recipe.RuneAltarRecipeJS;
    runic_altar(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[], mana: java.lang.Integer): com.prunoideae.recipe.RuneAltarRecipeJS;

    elven_trade(output: dev.latvian.mods.kubejs.item.ItemStackJS[], inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS): com.prunoideae.recipe.ElvenTradeRecipeJS;

    pure_daisy(output: net.minecraft.world.level.block.Block, input: net.minecraft.world.level.block.Block): com.prunoideae.recipe.PureDaisyRecipeJS;

    brew(output: net.minecraft.resources.ResourceLocation, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[]): com.prunoideae.recipe.BrewRecipeJS;

    petal_apothecary(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[]): com.prunoideae.recipe.PetalRecipeJS;

    /**
     * Default mana is 500,000, same as Terrasteel Ingot.
     */
    terra_plate(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[]): com.prunoideae.recipe.TerraPlateRecipeJS;
    terra_plate(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[], mana: java.lang.Integer): com.prunoideae.recipe.TerraPlateRecipeJS;

    orechid(output: net.minecraft.world.level.block.Block, input: net.minecraft.world.level.block.Block, weight: java.lang.Integer): com.prunoideae.recipe.OrechidRecipeJS;
    orechid_ignem(output: net.minecraft.world.level.block.Block, input: net.minecraft.world.level.block.Block, weight: java.lang.Integer): com.prunoideae.recipe.OrechidRecipeJS;
    marimorphosis(output: net.minecraft.world.level.block.Block, input: net.minecraft.world.level.block.Block, weight: java.lang.Integer, biomes: string[], biomeBonus: java.lang.Integer): com.prunoideae.recipe.OrechidRecipeJS;
}