package com.prunoideae.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import vazkii.botania.api.brew.Brew;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BasicBrewJS extends Brew {
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

    public BasicBrewJS(int cost, MobEffectsHolder holders) {
        super(cost, holders.cast());
    }

    public static class Builder extends BrewBuilder {

        public Builder(ResourceLocation i) {
            super(i);
        }

        @Override
        public Brew createObject() {
            return new BasicBrewJS(this.cost, new MobEffectsHolder(this.holders));
        }
    }
}
