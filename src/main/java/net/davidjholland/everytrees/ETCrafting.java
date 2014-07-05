package net.davidjholland.everytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ETCrafting {
    
	public static void init() {
		addOreRegistration();
		addOreRecipes();
		addCraftingRecipes();
		addSmeltingRecipes();
	}

	private static void addCraftingRecipes() {
		// Saplings
		// ==================================================================================
		// Emerald
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 0), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.emerald, 'O', Blocks.sapling
		});
		// Diamond
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 1), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.diamond, 'O', Blocks.sapling
		});
		// Gold
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 2), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.gold_ingot, 'O', Blocks.sapling
		});
		// Iron
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 3), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.iron_ingot, 'O', Blocks.sapling
		});
		// Glowstone
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 4), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Blocks.glowstone, 'O', Blocks.sapling
		});
		// Lapis
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 5), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Blocks.lapis_block, 'O', Blocks.sapling
		});
		// Redstone
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 6), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Blocks.redstone_block, 'O', Blocks.sapling
		});
		// Obsidian
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 7), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Blocks.obsidian, 'O', Blocks.sapling
		});
		// Enchanted
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 8), new Object[]{
	    	"ODO",
	    	"DSD",
	    	"ODO",
	    	'S', Blocks.sapling, 'D', new ItemStack(ETCItems.shards, 1, 1), 'O', new ItemStack(ETCItems.shards, 1, 2)
		});
		// Ender
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 9), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.ender_pearl, 'O', Blocks.sapling
		});
		// Lava
		for(int i = 0; i < BlockSapling.field_149882_a.length; i++){
			GameRegistry.addShapelessRecipe(new ItemStack(ETCBlocks.saplings, 1, 10), new Object[]{
				Items.lava_bucket, new ItemStack(Blocks.sapling, 1, i)
			});
		}
		// Ice
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 11), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Blocks.snow, 'O', Blocks.sapling
		});
		// Slime
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 12), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.slime_ball, 'O', Blocks.sapling
		});
		// Bone
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 13), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.bone, 'O', Blocks.sapling
		});
		// Bacon
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 14), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.porkchop, 'O', Blocks.sapling
		});
		// Leather
		GameRegistry.addRecipe(new ItemStack(ETCBlocks.saplings, 1, 15), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.leather, 'O', Blocks.sapling
		});
		
		// Shards
		// ==================================================================================
		// Emerald
		GameRegistry.addRecipe(new ItemStack(Items.emerald), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCItems.shards, 1, 0)});
		// Diamond
		GameRegistry.addRecipe(new ItemStack(Items.diamond), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCItems.shards, 1, 1)});
		// Obsidian
		GameRegistry.addRecipe(new ItemStack(Blocks.obsidian), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCItems.shards, 1, 2)});
		
		// Leaves
		// ==================================================================================
		// Emerald
		GameRegistry.addRecipe(new ItemStack(ETCItems.shards, 1, 0), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves1, 1, 0)});
		// Diamond
		GameRegistry.addRecipe(new ItemStack(ETCItems.shards, 1, 1), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves1, 1, 1)});
		// Gold
		GameRegistry.addRecipe(new ItemStack(Items.gold_nugget, 2), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves1, 1, 2)});
		// Iron
		GameRegistry.addRecipe(new ItemStack(Items.iron_ingot), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves1, 1, 3)});
		// Glowstone
		GameRegistry.addRecipe(new ItemStack(Items.glowstone_dust, 2), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves2, 1, 0)});
		// Lapis
		GameRegistry.addRecipe(new ItemStack(Items.dye, 2, 4), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves2, 1, 1)});
		// Redstone
		GameRegistry.addRecipe(new ItemStack(Items.redstone, 2), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves2, 1, 2)});
		// Obsidian
		GameRegistry.addRecipe(new ItemStack(ETCItems.shards, 1, 2), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves2, 1, 3)});
		
		// Slime
		GameRegistry.addRecipe(new ItemStack(Items.slime_ball), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves3, 1, 0)});
		// Bone
		GameRegistry.addRecipe(new ItemStack(Items.bone, 2), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves3, 1, 1)});
		// Bacon
		GameRegistry.addRecipe(new ItemStack(ETCItems.food, 2, 0), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves3, 1, 2)});
		// Leather
		GameRegistry.addRecipe(new ItemStack(Items.leather), new Object[]{"XXX","XXX","XXX",'X', new ItemStack(ETCBlocks.leaves3, 1, 3)});
	}

	private static void addSmeltingRecipes() {
		// Logs 1
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs1, 1, 0), new ItemStack(ETCItems.shards, 1, 0), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs1, 1, 1), new ItemStack(ETCItems.shards, 1, 1), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs1, 1, 2), new ItemStack(Items.gold_nugget, 2), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs1, 1, 3), new ItemStack(Items.iron_ingot, 1), 0.2F);
		// Logs 2
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs2, 1, 0), new ItemStack(Items.glowstone_dust, 2), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs2, 1, 1), new ItemStack(Items.dye, 2, 4), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs2, 1, 2), new ItemStack(Items.redstone, 2), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs2, 1, 3), new ItemStack(ETCItems.shards, 1, 2), 0.2F);
		// Logs 3
//		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs3, 1, 0), new ItemStack(Items.glowstone_dust, 2), 0.2F);
//		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs3, 1, 1), new ItemStack(Items.dye, 2, 4), 0.2F);
//		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs3, 1, 2), new ItemStack(Items.redstone, 2), 0.2F);
//		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs3, 1, 3), new ItemStack(ETCItems.shards, 1, 2), 0.2F);
		// Logs 4
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs4, 1, 0), new ItemStack(Items.slime_ball), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs4, 1, 1), new ItemStack(Items.bone, 2), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs4, 1, 2), new ItemStack(ETCItems.food, 2, 1), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ETCBlocks.logs4, 1, 3), new ItemStack(Items.leather, 2), 0.2F);
	}

	private static void addOreRegistration() {
		//Ore Registration
		OreDictionary.registerOre("treeSapling", new ItemStack(ETCBlocks.saplings, 1, OreDictionary.WILDCARD_VALUE));

		for (int i = 0; i <= 3; i++) {
			OreDictionary.registerOre("logWood", new ItemStack(ETCBlocks.logs1, 1, i));
			OreDictionary.registerOre("logWood", new ItemStack(ETCBlocks.logs2, 1, i));
			OreDictionary.registerOre("logWood", new ItemStack(ETCBlocks.logs3, 1, i));
			OreDictionary.registerOre("logWood", new ItemStack(ETCBlocks.logs4, 1, i));
		}

		OreDictionary.registerOre("treeLeaves", new ItemStack(ETCBlocks.leaves1, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(ETCBlocks.leaves2, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(ETCBlocks.leaves3, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(ETCBlocks.leaves4, 1, OreDictionary.WILDCARD_VALUE));
	}
	
	private static void addOreRecipes() {
		
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.diamond, 1), new Object[]{
//			"XXX",
//			"XXX",
//			"XXX",
//			'X', "logWood"
//		}));
		
	}
}
