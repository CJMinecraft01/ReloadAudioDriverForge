package cjminecraft.rad;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.network.FMLNetworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

/**
 * A simple mod which will reload the audio drivers when pressing F3 + R
 */
@Mod(ReloadAudioDriver.MODID)
@Mod.EventBusSubscriber
public class ReloadAudioDriver {

    public static final String MODID = "rad";
    private static final Logger LOGGER = LogManager.getLogger(MODID);

    public ReloadAudioDriver() {
        // Signal that we are client side only
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> FMLNetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        if (event.getAction() != GLFW.GLFW_PRESS)
            return;
        if (Minecraft.getInstance().screen == null) {
            if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_F3)) {
                if (event.getKey() == GLFW.GLFW_KEY_R) {
                    Minecraft.getInstance().keyboardHandler.handledDebugKey = true;
                    LOGGER.info("Reloading sounds!");
                    Minecraft.getInstance().getSoundManager().soundEngine.reload();
                    LOGGER.info("Reloaded sounds!");
//                    Minecraft.getInstance().keyboardListener.printDebugMessage("reload_audio_driver.success");
                    SystemToast.addOrUpdate(Minecraft.getInstance().getToasts(), SystemToast.SystemToastIds.TUTORIAL_HINT, new TranslatableComponent("reload_audio_driver.toast.title"), new TranslatableComponent("reload_audio_driver.toast.body"));
                } else if (event.getKey() == GLFW.GLFW_KEY_Q) {
                    Minecraft.getInstance().gui.getChat().addMessage(new TranslatableComponent("reload_audio_driver.details"));
                }
            }
        }
    }

}
