package cjminecraft.rad.event;

import cjminecraft.rad.ReloadAudioDriver;
import cjminecraft.rad.init.ModGlobals;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModGlobals.MODID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class KeyInputEvent
{
    /**
     * Triggers when a key is pressed
     * @param event
     */
    @SubscribeEvent
    public static void onKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        // keys: https://www.glfw.org/docs/latest/group__keys.html
        if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(),292)) //292 => F3
        {
            if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), 82)) //82 => R
            {
                Minecraft.getInstance().keyboardListener.actionKeyF3 = true;
                ReloadAudioDriver.LOGGER.info("Reloading sounds!");
                Minecraft.getInstance().getSoundHandler().sndManager.reload();
                ReloadAudioDriver.LOGGER.info("Reloaded sounds!");
                //Hardcoded for now
                //Minecraft.getInstance().player.sendMessage((new StringTextComponent("")).appendSibling((new TranslationTextComponent("reload_audio_driver.success")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN})));
                Minecraft.getInstance().player.sendMessage((new StringTextComponent("Successfully reloaded audio drivers!")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN}));

            }
            else if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), 81)) //81 => Q
            {
                //Hardcoded for now F3 + R = Reload audio drivers
                //Minecraft.getInstance().player.sendMessage((new StringTextComponent("")).appendSibling((new TranslationTextComponent("reload_audio_driver.details"))));
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("F3 + R = Reload audio drivers"));
            }
        }
    }
}
