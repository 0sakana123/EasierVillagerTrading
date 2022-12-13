package xyz.zerofish123.villagertraderplus.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.zerofish123.villagertraderplus.ConfigurationHandler;
import xyz.zerofish123.villagertraderplus.EasierVillagerTrading;

import java.util.Arrays;
import java.util.Objects;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Inject(method = "joinWorld", at = @At("RETURN"))
    public void settingModEnableFlag(ClientWorld world, CallbackInfo ci) {

        String address;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.isInSingleplayer()) {
            address = "singleplayer";
        } else {
            address = client.getCurrentServerEntry().address;
        }
        String[] whitelistedServers = ConfigurationHandler.whitelistedServerAddress().split(",");
        if (Arrays.asList(whitelistedServers).contains(address) || Objects.equals(address, "singleplayer")) {
            EasierVillagerTrading.isModEnabledOnServer = true;
        }
    }
}
