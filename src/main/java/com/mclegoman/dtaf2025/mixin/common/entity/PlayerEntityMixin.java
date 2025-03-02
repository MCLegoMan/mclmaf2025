/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.common.entity;

import com.mclegoman.dtaf2025.common.easter_egg.EasterEggRegistry;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	@Inject(method = "getMovementSpeed", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$getMovementSpeed(CallbackInfoReturnable<Float> cir) {
		if (EasterEggRegistry.getSanic()) cir.setReturnValue(cir.getReturnValue() * 2.0F);
	}
}
