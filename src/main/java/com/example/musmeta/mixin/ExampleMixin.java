package com.example.musmeta.mixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.mas6y6.musmeta.utils.Utils")
public abstract class ExampleMixin {

    private static final Logger LOGGER = LoggerFactory.getLogger("hello-plugin.mixin");

    @Inject(method = "isRunningAsRoot", at = @At("RETURN"), remap = false)
    private static void hello$afterIsRunningAsRoot(CallbackInfoReturnable<Boolean> cir) {
        LOGGER.info("Utils.isRunningAsRoot() intercepted by template mixin (returned {})", cir.getReturnValue());
    }
}