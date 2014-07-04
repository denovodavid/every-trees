package net.davidjholland.everytrees;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockSapling extends ItemBlock {
	
	private static final int MAX = 15;

	public ItemBlockSapling(Block block) {
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta & 15;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamageForDisplay() > MAX ? 0 : itemStack.getItemDamageForDisplay();
		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(BlockETSapling.saplings[meta]).toString();
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		//TODO:	linkedBlock   getIcon
		return field_150939_a.getIcon(0, meta);
	}
}
