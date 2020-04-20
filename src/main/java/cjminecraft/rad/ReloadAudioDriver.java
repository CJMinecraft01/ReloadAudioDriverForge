package cjminecraft.rad;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        if (event.getAction() != GLFW.GLFW_PRESS)
            return;
        if (Minecraft.getInstance().currentScreen == null) {
            if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), GLFW.GLFW_KEY_F3)) {
                if (event.getKey() == GLFW.GLFW_KEY_R) {
                    Minecraft.getInstance().keyboardListener.actionKeyF3 = true;
                    LOGGER.info("Reloading sounds!");
                    Minecraft.getInstance().getSoundHandler().sndManager.reload();
                    LOGGER.info("Reloaded sounds!");
                    Minecraft.getInstance().keyboardListener.printDebugMessage("reload_audio_driver.success");
                } else if (event.getKey() == GLFW.GLFW_KEY_Q) {
                    Minecraft.getInstance().ingameGUI.getChatGUI().printChatMessage(new TranslationTextComponent("reload_audio_driver.details"));
                }
            }
        }
    }

}
