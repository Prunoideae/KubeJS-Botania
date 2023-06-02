package com.prunoideae.custom.brew;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.rhino.mod.util.color.Color;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.brew.Brew;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public abstract class BrewBuilder extends BuilderBase<Brew> {
    public transient int cost;
    public transient boolean noPendant;
    public transient boolean noIncense;
    public transient List<MobEffectHolder> holders;
    public transient ToIntFunction<ItemStack> getColor;

    public BrewBuilder(ResourceLocation i) {
        super(i);
        cost = 4000;
        noPendant = false;
        noIncense = false;
        holders = new ArrayList<>();
    }

    public BrewBuilder cost(int cost) {
        this.cost = cost;
        return this;
    }

    public BrewBuilder noPendant() {
        this.noPendant = true;
        return this;
    }

    public BrewBuilder noIncense() {
        this.noIncense = true;
        return this;
    }


    public BrewBuilder effect(ResourceLocation effect) {
        return effect(effect, 0);
    }

    public BrewBuilder effect(ResourceLocation effect, int duration) {
        return effect(effect, duration, 0);
    }

    public BrewBuilder effect(ResourceLocation effect, int duration, int amplifier) {
        return effect(effect, duration, amplifier, false);
    }

    public BrewBuilder effect(ResourceLocation effect, int duration, int amplifier, boolean ambient) {
        return effect(effect, duration, amplifier, ambient, true);
    }

    public BrewBuilder effect(ResourceLocation effect, int duration, int amplifier, boolean ambient, boolean visible) {
        return effect(effect, duration, amplifier, ambient, visible, visible);
    }

    public BrewBuilder effect(ResourceLocation effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        this.holders.add(new MobEffectHolder(effect, duration, amplifier, ambient, visible, showIcon));
        return this;
    }

    public BrewBuilder color(Color color) {
        this.getColor = (it) -> color.getRgbJS();
        return this;
    }

    public BrewBuilder color(Function<ItemStack, Color> colorProvider) {
        this.getColor = (it) -> colorProvider.apply(it).getRgbJS();
        return this;
    }

    @Override
    public RegistryInfo getRegistryType() {
        return null;
    }
}
