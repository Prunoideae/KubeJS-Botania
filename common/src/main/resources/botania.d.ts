/**
 * @target com.prunoideae.custom.BrewBuilder
 */
class BrewBuilderBase {
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int, amplifier: int): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int, amplifier: int, ambient: boolean, visible: boolean): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int, amplifier: int, ambient: boolean, visible: boolean, showIcon: boolean): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int, amplifier: int, ambient: boolean): com.prunoideae.custom.BrewBuilder;
}

/**
 * @target com.prunoideae.custom.BasicBrewJS$Builder
 */
class BrewBuilderBasic {
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int, amplifier: int): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int, amplifier: int, ambient: boolean, visible: boolean): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int, amplifier: int, ambient: boolean, visible: boolean, showIcon: boolean): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     * fuck
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int): com.prunoideae.custom.BrewBuilder;
    /**
     * @modify effect Internal.MobEffect_
     */
    effect(effect: net.minecraft.resources.ResourceLocation, duration: int, amplifier: int, ambient: boolean): com.prunoideae.custom.BrewBuilder;
}

class RecipeHolder {
    readonly botania: Document.BotaniaRecipes;
}

class BotaniaRecipes {

    /**
     * Default mana is 100.
     */
    mana_infusion(output: dev.latvian.mods.kubejs.item.ItemStackJS, input: dev.latvian.mods.kubejs.item.ingredient.IngredientJS): com.prunoideae.recipe.ManaInfusionRecipeJS;
    mana_infusion(output: dev.latvian.mods.kubejs.item.ItemStackJS, input: dev.latvian.mods.kubejs.item.ingredient.IngredientJS, mana: int): com.prunoideae.recipe.ManaInfusionRecipeJS;
    mana_infusion(output: dev.latvian.mods.kubejs.item.ItemStackJS, input: dev.latvian.mods.kubejs.item.ingredient.IngredientJS, mana: int, catalyst: net.minecraft.world.level.block.Block): com.prunoideae.recipe.ManaInfusionRecipeJS;

    /**
     * Default mana is 5,200, same as Basic Runes.
     */
    runic_altar(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[]): com.prunoideae.recipe.RuneAltarRecipeJS;
    runic_altar(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[], mana: int): com.prunoideae.recipe.RuneAltarRecipeJS;

    elven_trade(output: dev.latvian.mods.kubejs.item.ItemStackJS[], inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS): com.prunoideae.recipe.ElvenTradeRecipeJS;

    pure_daisy(output: net.minecraft.world.level.block.Block, input: net.minecraft.world.level.block.Block): com.prunoideae.recipe.PureDaisyRecipeJS;

    brew(output: net.minecraft.resources.ResourceLocation, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[]): com.prunoideae.recipe.BrewRecipeJS;

    petal_apothecary(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[]): com.prunoideae.recipe.PetalRecipeJS;

    /**
     * Default mana is 500,000, same as Terrasteel Ingot.
     */
    terra_plate(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[]): com.prunoideae.recipe.TerraPlateRecipeJS;
    terra_plate(output: dev.latvian.mods.kubejs.item.ItemStackJS, inputs: dev.latvian.mods.kubejs.item.ingredient.IngredientJS[], mana: int): com.prunoideae.recipe.TerraPlateRecipeJS;

    orechid(output: net.minecraft.world.level.block.Block, input: net.minecraft.world.level.block.Block, weight: int): com.prunoideae.recipe.OrechidRecipeJS;
    orechid_ignem(output: net.minecraft.world.level.block.Block, input: net.minecraft.world.level.block.Block, weight: int): com.prunoideae.recipe.OrechidRecipeJS;
    marimorphosis(output: net.minecraft.world.level.block.Block, input: net.minecraft.world.level.block.Block, weight: int, biomes: string[], biomeBonus: int): com.prunoideae.recipe.OrechidRecipeJS;
}