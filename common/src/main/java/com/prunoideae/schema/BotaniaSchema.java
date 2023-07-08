package com.prunoideae.schema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.block.state.BlockStatePredicate;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.typings.desc.DescriptionContext;
import dev.latvian.mods.kubejs.typings.desc.TypeDescJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.crafting.StateIngredientHelper;

public interface BotaniaSchema {
    RecipeComponent<BlockStatePredicate> BLOCK_INPUT = new RecipeComponent<>() {
        @Override
        public String componentType() {
            return "string";
        }

        @Override
        public Class<?> componentClass() {
            return BlockStatePredicate.class;
        }

        @Override
        public @Nullable JsonElement write(RecipeJS recipe, BlockStatePredicate value) {
            var output = new JsonObject();
            if (value instanceof BlockStatePredicate.TagMatch tagMatch) {
                output.addProperty("type", "tag");
                output.addProperty("tag", tagMatch.tag().location().toString());
            } else if (value instanceof BlockStatePredicate.BlockMatch blockMatch) {
                output.addProperty("type", "block");
                output.addProperty("block", Registry.BLOCK.getKey(blockMatch.block()).toString());
            } else if (value == BlockStatePredicate.Simple.ALL)
                return null;
            else throw new RuntimeException("Can't decide what block to use for recipe! Can only use Block or Tag!");

            return output;
        }

        @Override
        public BlockStatePredicate read(RecipeJS recipe, Object from) {
            if (from instanceof JsonObject input) {
                return BlockStatePredicate.of(switch (input.get("type").getAsString()) {
                    case "tag" -> "#" + input.get("tag").getAsString();
                    case "block" -> input.get("block").getAsString();
                    default -> null;
                });
            }
            return BlockStatePredicate.of(from);
        }
    };

    RecipeComponent<BlockState> BLOCK_OUTPUT = new RecipeComponent<>() {
        @Override
        public Class<?> componentClass() {
            return BlockState.class;
        }

        @Override
        public ComponentRole role() {
            return ComponentRole.OUTPUT;
        }

        @Override
        public TypeDescJS constructorDescription(DescriptionContext ctx) {
            return RecipeComponent.super.constructorDescription(ctx).or(ctx.javaType(Block.class));
        }

        @Override
        public JsonElement write(RecipeJS recipe, BlockState value) {
            return StateIngredientHelper.serializeBlockState(value);
        }

        @Override
        public BlockState read(RecipeJS recipe, Object from) {
            if (from instanceof Block b) {
                return b.defaultBlockState();
            } else if (from instanceof BlockState bs) {
                return bs;
            } else if (from instanceof JsonObject object) {
                return StateIngredientHelper.readBlockState(object);
            } else {
                return UtilsJS.parseBlockState(from.toString());
            }
        }
    };
}
