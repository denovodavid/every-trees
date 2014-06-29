package net.davidjholland.everytrees;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemEveryLeaf extends ItemBlockWithMetadata {

	public ItemEveryLeaf(Block block) {
		super(block, block);
	}

	public String getUnlocalizedName(ItemStack itemStack){
		int i = itemStack.getItemDamage();
		if (i < 0 || i >= BlockEveryLeaf.field_150133_O.length){
			i = 0;
		}
		return super.getUnlocalizedName() + "." + BlockEveryLeaf.field_150133_O[i];
	}
	
	public int getMetadata(int meta){
		return meta;
	}
}
