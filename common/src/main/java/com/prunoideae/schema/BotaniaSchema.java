package com.prunoideae.schema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Either;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.component.BlockComponent;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.TagKeyComponent;
import dev.latvian.mods.kubejs.typings.desc.DescriptionContext;
import dev.latvian.mods.kubejs.typings.desc.TypeDescJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.common.crafting.StateIngredientHelper;

public interface BotaniaSchema {
    RecipeComponent<Either<Block, TagKey<Block>>> BLOCK_INPUT = new RecipeComponent<Either<Block, TagKey<Block>>>() {
        @Override
        public Class<?> componentClass() {
            return Block.class;
        }

        @Override
        public TypeDescJS constructorDescription(DescriptionContext ctx) {
            return BlockComponent.INPUT.constructorDescription(ctx).or(TagKeyComponent.BLOCK.constructorDescription(ctx));
        }

        @Override
        public JsonElement write(RecipeJS recipe, Either<Block, TagKey<Block>> value) {
            JsonObject object = new JsonObject();
            if (value.left().isPresent()) {
                object.addProperty("type", "block");
                object.addProperty("block", Registry.BLOCK.getKey(value.left().get()).toString());
            } else if (value.right().isPresent()) {
                object.addProperty("type", "tag");
                object.addProperty("tag", value.right().get().location().toString());
            }
            return object;
        }

        @Override
        public Either<Block, TagKey<Block>> read(RecipeJS recipe, Object from) {
            if (from instanceof Block block) {
                return Either.left(block);
            } else if (from instanceof TagKey<?> tag) {
                return tag.cast(Registry.BLOCK_REGISTRY).map(Either::<Block, TagKey<Block>>right).orElseThrow(() -> new IllegalArgumentException("Tag " + tag + " does not exist"));
            } else if (from instanceof JsonObject object) {
                var type = object.get("type").getAsString();
                if (type.equals("block")) {
                    return Either.left(Registry.BLOCK.get(new ResourceLocation(object.get("block").getAsString())));
                } else if (type.equals("tag")) {
                    return Either.right(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(object.get("tag").getAsString())));
                } else {
                    return null;
                }
            } else {
                var name = from.toString();
                if (name.startsWith("#")) {
                    return Either.right(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(name.substring(1))));
                } else {
                    return Either.left(Registry.BLOCK.get(new ResourceLocation(name)));
                }
            }
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
