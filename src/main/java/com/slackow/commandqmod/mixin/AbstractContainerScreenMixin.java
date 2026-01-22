package com.slackow.commandqmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {
    @ModifyExpressionValue(method = "keyPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/KeyEvent;hasControlDown()Z"))
    public boolean dropIfCommand(boolean original, @Local(argsOnly = true) KeyEvent keyEvent) {
        return original || keyEvent.hasControlDownWithQuirk();
    }
}
