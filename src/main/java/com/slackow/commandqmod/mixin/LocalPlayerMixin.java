package com.slackow.commandqmod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.mojang.blaze3d.platform.InputConstants.KEY_LSUPER;
import static com.mojang.blaze3d.platform.InputConstants.KEY_RSUPER;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    @Inject(method = "drop", at = @At("HEAD"))
    private void dropIfCommand(CallbackInfoReturnable<Boolean> cir, @Local(argsOnly = true) LocalBooleanRef bl) {
        if (!bl.get()) {
            var window = Minecraft.getInstance().getWindow();
            boolean isCommandDown = InputConstants.isKeyDown(window, KEY_LSUPER) || InputConstants.isKeyDown(window, KEY_RSUPER);
            bl.set(isCommandDown);
        }
    }

}
