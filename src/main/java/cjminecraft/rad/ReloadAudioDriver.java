package cjminecraft.rad;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

/**
 * A simple mod which will reload the audio drivers when pressing F3 + R
 */
@Mod(modid = ReloadAudioDriver.MODID, name = ReloadAudioDriver.NAME, version = ReloadAudioDriver.VERSION, clientSideOnly = true)
@Mod.EventBusSubscriber
public class ReloadAudioDriver {

    static final String MODID = "rad";
    static final String NAME = "Reload Audio Driver";
    static final String VERSION = "${version}";
    private static Logger LOGGER = LogManager.getFormatterLogger(ReloadAudioDriver.NAME);

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_F3)) {
            if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
                Minecraft.getMinecraft().actionKeyF3 = true;
                LOGGER.info("Reloading sounds!");
                Minecraft.getMinecraft().getSoundHandler().sndManager.reloadSoundSystem();
                LOGGER.info("Reloaded sounds!");
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.GREEN + I18n.format("reload_audio_driver.success")));
            } else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentTranslation("reload_audio_driver.details"));
            }
        }
    }

}
