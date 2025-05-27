package com.example.examplemod.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffects;

import com.example.examplemod.ExampleMod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class itemInnit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);

    
    //example food item
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", 
    		() -> new Item(new Item.Properties()
    				.stacksTo(16)
    				.food(new FoodProperties.Builder()
    						.nutrition(5)
    						.effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1)
    						.saturationMod(4).build())
    				.rarity(Rarity.EPIC)
    			));


	public static final RegistryObject<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.register("the_block", 
			() -> new BlockItem(blockinit.EXAMPLE_BLOCK.get(), new Item.Properties()
					));

}













