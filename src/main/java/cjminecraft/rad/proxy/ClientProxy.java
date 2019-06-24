package cjminecraft.rad.proxy;

import cjminecraft.rad.ReloadAudioDriver;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy extends CommonProxy
{
    /**
     * Overriding init, we dont want both messages in the log.
     * @param event
     */
    @Override
    public void init(final FMLCommonSetupEvent event)
    {
        ReloadAudioDriver.LOGGER.info(new TranslationTextComponent("reload_audio_driver.client_startup"));
    }
}
