package cjminecraft.rad.event;

import cjminecraft.rad.ReloadAudioDriver;
import cjminecraft.rad.init.ModGlobals;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.TextFormatting;

import net.minecraft.util.text.TranslationTextComponent;
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
        // keys: https://www.glfw.org/docs/latest/group__keys.html or InputMappings.class
        if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(),292)) //292 => F3
        {
            if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), 82)) //82 => R
            {
                Minecraft.getInstance().keyboardListener.actionKeyF3 = true;
                ReloadAudioDriver.LOGGER.info("Reloading sounds!");
                Minecraft.getInstance().getSoundHandler().sndManager.reload();
                ReloadAudioDriver.LOGGER.info("Reloaded sounds!");
                Minecraft.getInstance().player.sendMessage((new TranslationTextComponent("reload_audio_driver.success")).applyTextStyles(TextFormatting.GREEN));
            }
            else if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), 81)) //81 => Q
            {
                Minecraft.getInstance().player.sendMessage((new TranslationTextComponent("reload_audio_driver.details")));
            }
        }
    }
}
