package net.davidjholland.everytrees;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEveryLeaf extends BlockLeaves
{
    public static final String[][] field_150132_N = new String[][] {{"leaves_diamond", "leaves_gold", "leaves_iron", "leaves_emerald", "leaves_lapis", "leaves_glowstone", "leaves_redstone"}, {"leaves_diamond_opaque", "leaves_gold_opaque", "leaves_iron_opaque", "leaves_emerald_opaque", "leaves_lapis_opaque", "leaves_glowstone_opaque", "leaves_redstone_opaque"}};
    public static final String[] field_150133_O = new String[] {"diamond", "gold", "iron", "emerald", "lapis", "glowstone", "redstone"};
    
    private int color_diamond = 6155509;
    private int color_gold = 16297771;
    private int color_iron = 11505271;
    private int color_emerald = 31512;
    private int color_lapis = 1857478;
    private int color_glowstone = 0;
    private int color_redstone = 0;	
    
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_)
    {    	
    	return 
    		(p_149741_1_ & 3) == 0 ? color_diamond : 
    		((p_149741_1_ & 3) == 1 ? color_gold : 
    		((p_149741_1_ & 3) == 2 ? color_iron : 
    		((p_149741_1_ & 3) == 3 ? color_emerald : 
    		((p_149741_1_ & 3) == 4 ? color_lapis : 
    		((p_149741_1_ & 3) == 5 ? color_glowstone : 
    		((p_149741_1_ & 3) == 6 ? color_redstone : 
    		super.getRenderColor(p_149741_1_)))))));
    }
    
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
    	int l = p_149720_1_.getBlockMetadata(p_149720_2_, p_149720_3_, p_149720_4_);
    	return 
    		(l & 3) == 0 ? color_diamond : 
    		((l & 3) == 1 ? color_gold : 
    		((l & 3) == 2 ? color_iron : 
    		((l & 3) == 3 ? color_emerald : 
    		((l & 3) == 4 ? color_lapis : 
    		((l & 3) == 5 ? color_glowstone : 
    		((l & 3) == 6 ? color_redstone : 
    		super.colorMultiplier(p_149720_1_, p_149720_2_, p_149720_3_, p_149720_4_)))))));
    }

    protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_, int p_150124_6_)
    {
        if ((p_150124_5_ & 3) == 0 && p_150124_1_.rand.nextInt(200) == 0){
            if(p_150124_1_.rand.nextInt(2) == 0){
            	this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Items.diamond));
            }else{
            	this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(EveryTrees.itemDiamondShards, 1, 0));
            }
        }else if ((p_150124_5_ & 3) == 1 && p_150124_1_.rand.nextInt(150) == 0){
        	if(p_150124_1_.rand.nextInt(3) == 0){
            	this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Items.golden_apple, 1, 1));
            }else if((p_150124_1_.rand.nextInt(2) == 0)){
            	this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Items.golden_apple, 1, 0));
            }else{
            	this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Items.gold_nugget, 2, 0));
            }
        }else if ((p_150124_5_ & 3) == 2 && p_150124_1_.rand.nextInt(150) == 0){
            this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Items.iron_ingot, 1, 0));
        }else if ((p_150124_5_ & 3) == 3 && p_150124_1_.rand.nextInt(200) == 0){
            this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Items.emerald, 1, 0));
        }else if ((p_150124_5_ & 3) == 4 && p_150124_1_.rand.nextInt(1) == 0){
        	this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Items.dye, 1, 4));
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return super.damageDropped(p_149692_1_);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        return p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_) & 3;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
    	this.setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
    	return 
    		(p_149691_2_ & 3) == 1 ? this.field_150129_M[this.field_150127_b][1] : 
    		((p_149691_2_ & 3) == 2 ? this.field_150129_M[this.field_150127_b][2] : 
    		((p_149691_2_ & 3) == 3 ? this.field_150129_M[this.field_150127_b][3] : 
    		((p_149691_2_ & 3) == 4 ? this.field_150129_M[this.field_150127_b][4] : 
    		((p_149691_2_ & 3) == 5 ? this.field_150129_M[this.field_150127_b][5] : 
    		((p_149691_2_ & 3) == 6 ? this.field_150129_M[this.field_150127_b][6] : 
    		this.field_150129_M[this.field_150127_b][0])))));
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        for(int i = 0; i < field_150133_O.length; i++){
        	p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
		}
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        for (int i = 0; i < field_150132_N.length; ++i)
        {
            this.field_150129_M[i] = new IIcon[field_150132_N[i].length];

            for (int j = 0; j < field_150132_N[i].length; ++j)
            {
                this.field_150129_M[i][j] = p_149651_1_.registerIcon(EveryTrees.MODID + ":" + field_150132_N[i][j]);
            }
        }
    }

    public String[] func_150125_e()
    {
        return field_150133_O;
    }
    
    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(EveryTrees.blockEverySapling);
    }
    	
}