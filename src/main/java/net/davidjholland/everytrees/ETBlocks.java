package net.davidjholland.everytrees;

import static net.davidjholland.everytrees.ETCBlocks.*;
import static net.davidjholland.everytrees.ETBlocks.registerBlock;
import net.davidjholland.everytrees.BlockETLeaves.LeafCategory;
import net.davidjholland.everytrees.BlockETLog.LogCategory;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class ETBlocks {

	public static void init() {
		registerBlocks();
	}

	private static void registerBlocks() {
		logs1 = registerBlock(new BlockETLog(LogCategory.CAT1).setBlockName("logs1"), ItemBlockLog.class);
		logs2 = registerBlock(new BlockETLog(LogCategory.CAT2).setBlockName("logs2"), ItemBlockLog.class);
		logs3 = registerBlock(new BlockETLog(LogCategory.CAT3).setBlockName("logs3"), ItemBlockLog.class);
		logs4 = registerBlock(new BlockETLog(LogCategory.CAT4).setBlockName("logs4"), ItemBlockLog.class);
		
		leaves1 = registerBlock(new BlockETLeaves(LeafCategory.CAT1).setBlockName("leaves1"), ItemBlockLeaves.class);
		leaves2 = registerBlock(new BlockETLeaves(LeafCategory.CAT2).setBlockName("leaves2"), ItemBlockLeaves.class);
		leaves3 = registerBlock(new BlockETLeaves(LeafCategory.CAT3).setBlockName("leaves3"), ItemBlockLeaves.class);
		leaves4 = registerBlock(new BlockETLeaves(LeafCategory.CAT4).setBlockName("leaves4"), ItemBlockLeaves.class);
		
		saplings = registerBlock(new BlockETSapling().setBlockName("saplings"), ItemBlockSapling.class);
	}
	
	public static Block registerBlock(Block block) {
	    GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
	    return block;
	}
	
    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
        return block;
    }
    
    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, Object... constructorArgs) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""), constructorArgs);
        return block;
    }
	
}