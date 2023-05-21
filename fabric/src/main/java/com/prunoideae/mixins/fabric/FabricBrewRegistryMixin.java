package com.prunoideae.mixins.fabric;

import net.minecraft.core.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.fabric.xplat.FabricXplatImpl;

@Mixin(value = FabricXplatImpl.class, remap = false)
public abstract class FabricBrewRegistryMixin {
    private static Registry<Brew> brewRegistry = null;


    @Inject(method = "createBrewRegistry", at = @At("HEAD"), cancellable = true)
    public void createBrewRegistry(CallbackInfoReturnable<Registry<Brew>> cir) {
        if (brewRegistry != null)
            cir.setReturnValue(brewRegistry);
    }


    @Inject(method = "createBrewRegistry", at = @At("RETURN"))
    public void getBrewRegistry(CallbackInfoReturnable<Registry<Brew>> cir) {
        brewRegistry = cir.getReturnValue();
    }
}
