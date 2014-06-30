package net.davidjholland.everytrees;

import java.util.List;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEveryLog extends BlockLog
{
    public static final String[] woodType = new String[] {"diamond", "gold", "iron", "emerald", "lapis", "glowstone", "redstone"};
    
    BlockEveryLog(){
    	this.setHardness(5.0F);
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list){
    	for(int i = 0; i < woodType.length; i++){
			list.add(new ItemStack(block, 1, i));
		}
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.field_150167_a = new IIcon[woodType.length];
        this.field_150166_b = new IIcon[woodType.length];

        for (int i = 0; i < this.field_150167_a.length; ++i)
        {
            this.field_150167_a[i] = iconRegister.registerIcon(EveryTrees.MODID + ":" + this.getTextureName() + "_" + woodType[i]);
            this.field_150166_b[i] = iconRegister.registerIcon(EveryTrees.MODID + ":" + this.getTextureName() + "_" + woodType[i] + "_top");
        }
    }
    
    /**
     * Determines if the player can harvest this block, obtaining it's drops when the block is destroyed.
     *
     * @param player The player damaging the block, may be null
     * @param meta The block's current metadata
     * @return True to spawn the drops
     */
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
    	if(player.getCurrentEquippedItem() == null){
    		return false;
    	}
    	Item item = player.getCurrentEquippedItem().getItem();
    	if(item == Items.diamond_axe || item == Items.iron_axe){
    		return true;
    	}else if(meta == 2 && item == Items.stone_axe){
    		return true;
    	}else if(meta > 2){
    		return true;
    	}else{
    		return false;
    	}
    }
    

    /**
     * On hold until lava trees.
     */
//    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player){
//    	
//    	if(!world.isRemote){
//    		
//    		double X = x + 0.5;
//    		double Y = y + 0.5;
//    		double Z = z + 0.5;
//    		
//    		System.out.println("X: " + X);
//    		System.out.println("Y: " + Y);
//    		System.out.println("Z: " + Z);
//    	
//    		double pX = player.posX - X;
//    		double pY = player.posY - Y + 0.5;
//    		double pZ = player.posZ - Z;
//    		
//    		System.out.println("pX: " + pX);
//    		System.out.println("pY: " + pY);
//    		System.out.println("pZ: " + pZ);
//    		
//    		if(pX > 0.5){
//    			X = X + 0.6;
//    		}else if(pX < - 0.5){
//    			X = X - 0.6;
//    		}
//    		
//    		if(pY > 0){
//    			Y = Y + 0.6;
//    		}else if(pY < 0){
//    			Y = Y - 0.6;
//    		}
//    		
//    		if(pZ > 0.5){
//    			Z = Z + 0.6;
//    		}else if(pZ < - 0.5){
//    			Z = Z - 0.6;
//    		}
//    		
//    		System.out.println("X: " + X);
//    		System.out.println("Y: " + Y);
//    		System.out.println("Z: " + Z);
//        
//    		EntitySmallFireball entitysmallfireball = new EntitySmallFireball(world, X, Y, Z, pX, pY, pZ);
//    		world.playAuxSFX(1009, (int)x, (int)y, (int)z, 0);
//    		world.spawnEntityInWorld(entitysmallfireball);
//    	}
//    }
    
}