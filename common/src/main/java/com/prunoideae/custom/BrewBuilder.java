package com.prunoideae.custom;

import dev.latvian.mods.kubejs.BuilderBase;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import net.minecraft.resources.ResourceLocation;
import vazkii.botania.api.brew.Brew;

import java.util.ArrayList;
import java.util.List;

public abstract class BrewBuilder extends BuilderBase<Brew> {
    public transient int cost;
    public transient boolean noPendant;
    public transient boolean noIncense;
    public transient List<MobEffectHolder> holders;

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


    @Override
    public String getTranslationKeyGroup() {
        return "kubejs";
    }

    @Override
    public RegistryObjectBuilderTypes<Brew> getRegistryType() {
        return BotaniaRegistryObjectBuilderTypes.BREW;
    }
}
