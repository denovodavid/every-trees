package net.davidjholland.everytrees;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemEverySapling extends ItemBlockWithMetadata {

	public ItemEverySapling(Block block) {
		super(block, block);
	}

	public String getUnlocalizedName(ItemStack itemStack){
		int i = itemStack.getItemDamage();
		if (i < 0 || i >= BlockEverySapling.field_149882_a.length){
			i = 0;
		}
		return super.getUnlocalizedName() + "." + BlockEverySapling.field_149882_a[i];
	}
	
	public int getMetadata(int meta){
		return meta;
	}
}
