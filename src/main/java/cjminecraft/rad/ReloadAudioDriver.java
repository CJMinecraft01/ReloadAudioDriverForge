package cjminecraft.rad;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * A simple mod which will reload the audio drivers when pressing F3 + R
 */
@Mod(ReloadAudioDriver.MODID)
@Mod.EventBusSubscriber
public class ReloadAudioDriver
{
    static final String MODID = "rad";
    static final String NAME = "Reload Audio Driver";
    static final String VERSION = "${version}";
    private static Logger LOGGER = LogManager.getFormatterLogger(ReloadAudioDriver.NAME);

    @SubscribeEvent
    public static void onKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        // keys: https://www.glfw.org/docs/latest/group__keys.html
        if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(),292))
        {
            if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), 82))
            {
                Minecraft.getInstance().keyboardListener.actionKeyF3 = true;
                LOGGER.info("Reloading sounds!");
                Minecraft.getInstance().getSoundHandler().sndManager.reload();
                LOGGER.info("Reloaded sounds!");
                //Hardcoded for now
                //Minecraft.getInstance().player.sendMessage((new StringTextComponent("")).appendSibling((new TranslationTextComponent("reload_audio_driver.success")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN})));
                Minecraft.getInstance().player.sendMessage((new StringTextComponent("Successfully reloaded audio drivers!")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN}));

            }
            else if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), 81))
            {
                //Hardcoded for now F3 + R = Reload audio drivers
                //Minecraft.getInstance().player.sendMessage((new StringTextComponent("")).appendSibling((new TranslationTextComponent("reload_audio_driver.details"))));
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("F3 + R = Reload audio drivers"));
            }
        }
    }
}
