package com.prunoideae.schema;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.registry.KubeJSRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public interface PureDaisySchema {

    RecipeComponent<Block> DAISY_BLOCK = new RecipeComponent<>() {

        @Override
        public String componentType() {
            return "string";
        }

        @Override
        public Class<?> componentClass() {
            return Block.class;
        }

        @Override
        public @Nullable JsonElement write(RecipeJS recipe, Block value) {
            if (value == Blocks.AIR)
                return null;
            return new JsonPrimitive(Registry.BLOCK.getKey(value).toString());
        }

        @Override
        public Block read(RecipeJS recipe, Object from) {
            if (from instanceof Block blk)
                return blk;
            var block = KubeJSRegistries.blocks().get(new ResourceLocation(from.toString()));
            if (block == null)
                throw new RuntimeException("Can't find block " + from);
            return block;
        }
    };

    RecipeKey<?> OUTPUT = DAISY_BLOCK.key("output");
    RecipeKey<?> INPUT = BotaniaSchema.BLOCK.key("input");
    RecipeKey<?> TIME = NumberComponent.INT.key("time").optional(150);

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, INPUT, TIME);
}
