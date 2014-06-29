package net.davidjholland.everytrees;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDiamondShards extends Item
{
    @SideOnly(Side.CLIENT)
    private IIcon field_111220_a;

    public ItemDiamondShards()
    {
    	super();
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setTextureName(EveryTrees.MODID + ":diamond_shards");
    }

}