package cjminecraft.rad;

import cjminecraft.rad.init.ModGlobals;
import cjminecraft.rad.proxy.ClientProxy;
import cjminecraft.rad.proxy.CommonProxy;
import cjminecraft.rad.proxy.IProxy;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * A simple mod which will reload the audio drivers when pressing F3 + R
 */
@Mod(ModGlobals.MODID)
public class ReloadAudioDriver
{
    public static ReloadAudioDriver instance;
    public static Logger LOGGER = LogManager.getFormatterLogger(ModGlobals.NAME);
    private static IProxy proxy = DistExecutor.runForDist(
            () -> () -> new ClientProxy(),
            () -> () -> new CommonProxy()
    );

    public ReloadAudioDriver()
    {
        if (instance == null)
        {
            instance = this;
        }
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
    }

    private void init(final FMLCommonSetupEvent event) {
        proxy.init(event);
    }

    public static ReloadAudioDriver getInstance()
    {
        return instance;
    }

    public static IProxy getProxy()
    {
        return proxy;
    }
}
