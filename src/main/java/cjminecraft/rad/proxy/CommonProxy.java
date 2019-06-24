package cjminecraft.rad.proxy;

import cjminecraft.rad.ReloadAudioDriver;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonProxy implements IProxy
{
    @Override
    public void init(FMLCommonSetupEvent event)
    {
        ReloadAudioDriver.LOGGER.info(new TranslationTextComponent("reload_audio_driver.server_startup"));
    }
}
