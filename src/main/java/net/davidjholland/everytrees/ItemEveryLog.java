package net.davidjholland.everytrees;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemEveryLog extends ItemBlockWithMetadata {

	public ItemEveryLog(Block block) {
		super(block, block);
	}

	public String getUnlocalizedName(ItemStack itemStack){
		int i = itemStack.getItemDamage();
		if (i < 0 || i >= BlockEveryLog.woodType.length){
			i = 0;
		}
		return super.getUnlocalizedName() + "." + BlockEveryLog.woodType[i];
	}
	
	public int getMetadata(int meta){
		return meta;
	}
}
