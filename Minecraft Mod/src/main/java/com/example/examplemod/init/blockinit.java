package com.example.examplemod.init;

import javax.swing.text.html.parser.Entity;

import com.example.examplemod.ExampleMod;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class blockinit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MODID);
	
	//make the item be like the repair tailsman nut witha block you stand on
	public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("the_block", () -> new Block(Block.Properties.of()
			.mapColor(MapColor.COLOR_GREEN)
			.strength(5.0f, 3f)
			.requiresCorrectToolForDrops()
		));
	
	
	
}
