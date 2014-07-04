package net.davidjholland.everytrees;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShards extends Item {
	
	private static String[] shards = {"emerald", "diamond", "obsidian"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemShards() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(EveryTrees.tabEveryTrees);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		textures = new IIcon[shards.length];
		for (int i = 0; i < shards.length; ++i) {
			textures[i] = iconRegister.registerIcon(EveryTrees.MODID + ":shards_" + shards[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= shards.length) {
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + shards[meta];
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}
		return textures[meta];
	}

	@Override
    //TODO: public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		for (int meta = 0; meta < shards.length; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
}
