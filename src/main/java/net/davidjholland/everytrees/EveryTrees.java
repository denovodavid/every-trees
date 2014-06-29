package net.davidjholland.everytrees;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

// Define Basic Information for Mod
@Mod(modid = EveryTrees.MODID, name = EveryTrees.MODNAME, version = EveryTrees.VERSION)

public class EveryTrees {
	
	@Instance(value = EveryTrees.MODID)
	public static EveryTrees instance;
	
	public static final String MODID = "everytrees";
	public static final String MODNAME = "Every Trees";
	public static final String VERSION = "0.1";
	
	// Define Blocks
	public static Block blockEveryLog;
	public static Block blockEveryLeaf;
	public static Block blockEverySapling;
	
	// Define Items
	public static Item itemDiamondShards;
	
	// Define Creative Tabs
	public static CreativeTabs tabEveryTrees;
	
	@EventHandler
	public void Load(FMLPreInitializationEvent event){
		
		// Settings for Custom Tabs
		tabEveryTrees = new CreativeTabs("tabEveryTrees"){
			public Item getTabIconItem() {
				return Item.getItemFromBlock(blockEveryLog);
			}
		};
		
		// Settings for Blocks
		blockEveryLog = new BlockEveryLog()
			.setBlockTextureName("log")
			.setBlockName("blockEveryLog")
			.setCreativeTab(tabEveryTrees);
		blockEveryLeaf = new BlockEveryLeaf()
			.setBlockTextureName("leaf")
			.setBlockName("blockEveryLeaves")
			.setCreativeTab(tabEveryTrees);
		blockEverySapling = new BlockEverySapling()
			.setBlockTextureName("sapling")
			.setBlockName("blockEverySapling")
			.setCreativeTab(tabEveryTrees);
		
		// Settings for Items
		itemDiamondShards = new ItemDiamondShards()
			.setUnlocalizedName("itemDiamondShards")
			.setCreativeTab(tabEveryTrees);
		
		// Register Blocks
		GameRegistry.registerBlock(blockEveryLog, ItemEveryLog.class, blockEveryLog.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blockEveryLeaf, ItemEveryLeaf.class, blockEveryLeaf.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blockEverySapling, ItemEverySapling.class, blockEverySapling.getUnlocalizedName().substring(5));
		
		// Register Items
		GameRegistry.registerItem(itemDiamondShards, itemDiamondShards.getUnlocalizedName().substring(5));
		
		// Define Recipes for Items		
		GameRegistry.addRecipe(new ItemStack(blockEverySapling, 1, 0), new Object[]{
	    	"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.diamond, 'O', Blocks.sapling
		});
		GameRegistry.addRecipe(new ItemStack(blockEverySapling, 1, 1), new Object[]{
			"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.gold_ingot, 'O', Blocks.sapling
		});
		GameRegistry.addRecipe(new ItemStack(blockEverySapling, 1, 2), new Object[]{
			"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.iron_ingot, 'O', Blocks.sapling
		});
		GameRegistry.addRecipe(new ItemStack(blockEverySapling, 1, 3), new Object[]{
			"XXX",
	    	"XOX",
	    	"XXX",
	    	'X', Items.emerald, 'O', Blocks.sapling
		});
		GameRegistry.addRecipe(new ItemStack(Items.diamond), new Object[]{
	    	"XXX",
	    	"XXX",
	    	"XXX",
	    	'X', itemDiamondShards
		});
		
		// Define Smelting for Items
		GameRegistry.addSmelting(new ItemStack(blockEveryLog, 1, 0), new ItemStack(itemDiamondShards, 1), 0.2F);
		GameRegistry.addSmelting(new ItemStack(blockEveryLog, 1, 1), new ItemStack(Items.gold_nugget, 2), 0.2F);
		GameRegistry.addSmelting(new ItemStack(blockEveryLog, 1, 2), new ItemStack(Items.iron_ingot, 1), 0.2F);
		GameRegistry.addSmelting(new ItemStack(blockEveryLog, 1, 3), new ItemStack(Items.emerald, 1), 0.2F);
	}
}
