package com.prunoideae.mixins.forge;

import net.minecraft.core.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.forge.xplat.ForgeXplatImpl;

/*
 * Mixin into the XPlatImpl so we can call createBrewRegistry for multiple times
 *
 * This avoids the loading of BotaniaBrews, which contains a lot of unregistered mob effects.
 */
@Mixin(value = ForgeXplatImpl.class, remap = false)
public abstract class ForgeBrewRegistryMixin {

    private static Registry<Brew> brewRegistry = null;

    @Inject(method = "createBrewRegistry", at = @At("HEAD"), cancellable = true)
    public void createBrewRegistry(CallbackInfoReturnable<Registry<Brew>> cir) {
        if (brewRegistry != null) {
            cir.setReturnValue(brewRegistry);
        }
    }

    @Inject(method = "createBrewRegistry", at = @At("RETURN"))
    public void getBrewRegistry(CallbackInfoReturnable<Registry<Brew>> cir) {
        brewRegistry = cir.getReturnValue();
    }
}
