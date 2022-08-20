package com.prunoideae.custom;

import dev.latvian.mods.kubejs.item.ItemStackJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.brew.Brew;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class BasicBrewJS extends Brew {

    private final ToIntFunction<ItemStackJS> getColor;

    private static class MobEffectsHolder {
        private final List<MobEffectHolder> holders;
        private List<MobEffectInstance> instances = null;

        MobEffectsHolder(List<MobEffectHolder> holders) {
            this.holders = holders;
        }

        Supplier<List<MobEffectInstance>> cast() {
            return () -> {
                if (instances == null)
                    instances = holders.stream().map(MobEffectHolder::get).collect(Collectors.toList());
                return instances;
            };
        }
    }

    @Override
    public int getColor(ItemStack stack) {
        if (this.getColor != null)
            return this.getColor.applyAsInt(ItemStackJS.of(stack));
        return super.getColor(stack);
    }

    public BasicBrewJS(Builder builder) {
        super(builder.cost, new MobEffectsHolder(builder.holders).cast());
        if (builder.noIncense)
            setNotIncenseInfusable();
        if (builder.noPendant)
            setNotBloodPendantInfusable();
        this.getColor = builder.getColor;
    }

    public static class Builder extends BrewBuilder {

        public Builder(ResourceLocation i) {
            super(i);
        }

        @Override
        public Brew createObject() {
            return new BasicBrewJS(this);
        }
    }
}
