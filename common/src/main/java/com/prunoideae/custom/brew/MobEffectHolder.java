package com.prunoideae.custom.brew;

import dev.latvian.mods.kubejs.registry.KubeJSRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.Objects;

public class MobEffectHolder {
    private final ResourceLocation effect;
    private final int duration;
    private final int amplifier;
    private final boolean ambient;
    private final boolean visible;
    private final boolean showIcon;

    public MobEffectHolder(ResourceLocation effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.visible = visible;
        this.showIcon = showIcon;
    }

    public MobEffectHolder(ResourceLocation effect, int duration, int amplifier, boolean ambient, boolean visible) {
        this(effect, duration, amplifier, ambient, visible, visible);
    }

    public MobEffectHolder(ResourceLocation effect, int duration, int amplifier, boolean ambient) {
        this(effect, duration, amplifier, ambient, true);
    }

    public MobEffectHolder(ResourceLocation effect, int duration, int amplifier) {
        this(effect, duration, amplifier, false);
    }

    public MobEffectHolder(ResourceLocation effect, int duration) {
        this(effect, duration, 0);
    }

    public MobEffectHolder(ResourceLocation effect) {
        this(effect, 0);
    }

    public MobEffectInstance get() {
        return new MobEffectInstance(Objects.requireNonNull(KubeJSRegistries.mobEffects().get(effect)), duration, amplifier, ambient, visible, showIcon);
    }
}
