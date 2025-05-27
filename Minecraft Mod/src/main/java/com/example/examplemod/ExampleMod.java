package com.example.examplemod;


import com.example.examplemod.init.blockinit;
import com.example.examplemod.init.itemInnit;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExampleMod.MODID)
public class ExampleMod{
	public static final String MODID = "examplemod";

	public ExampleMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		itemInnit.ITEMS.register(bus);
		blockinit.BLOCKS.register(bus);
		
	}


}
