package dev.xkmc.l2complements.mixin;

import com.mojang.authlib.GameProfile;
import dev.xkmc.l2complements.content.feature.EntityFeature;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {

	public LocalPlayerMixin(ClientLevel p_250460_, GameProfile p_249912_) {
		super(p_250460_, p_249912_);
	}

	@Inject(at = @At("TAIL"), method = "hurtTo")
	public void l2complements_hurtTo_stableBody(float v, CallbackInfo ci) {
		LocalPlayer self = (LocalPlayer) (Object) this;
		if (EntityFeature.STABLE_BODY.test(self)) {
			self.hurtTime = 0;
		}
	}

	@Override
	public void handleDamageEvent(DamageSource source) {
		super.handleDamageEvent(source);
		LocalPlayer self = (LocalPlayer) (Object) this;
		if (EntityFeature.STABLE_BODY.test(self)) {
			self.hurtTime = 0;
		}
	}

	@Override
	public void animateHurt(float amount) {
		super.animateHurt(amount);
		LocalPlayer self = (LocalPlayer) (Object) this;
		if (EntityFeature.STABLE_BODY.test(self)) {
			self.hurtTime = 0;
		}
	}

}
