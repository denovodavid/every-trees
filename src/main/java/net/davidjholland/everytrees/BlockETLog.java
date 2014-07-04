package net.davidjholland.everytrees;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockETLog extends Block {

	public static enum LogCategory {
		CAT1, CAT2, CAT3, CAT4;
	}
	
	//logs1
	//Emerald			(0)
	//Diamond			(1)
	//Gold				(2)
	//Iron				(3)

	//logs2
	//Glowstone			(0)
	//Lapis				(1)
	//Redstone			(2)
	//Obsidian			(3)
	
	//logs3
	//Enchanted			(0)
	//Ender				(1)
	//Lava				(2)
	//Ice				(3)
	
	//logs4
	//Slime				(0)
	//Bone				(1)
	//Meat				(2)
	//Leather			(3)
	
	private static final String[] types = new String[] {"emerald", "diamond", "gold", "iron", "glowstone", "lapis", "redstone", "obsidian", "enchanted", "ender", "lava", "ice", "slime", "bone", "meat", "leather"};
	private IIcon[] textures;
	private IIcon[] top;
	private final LogCategory category;
	
	public BlockETLog(LogCategory cat) {
		super(Material.wood);
		category = cat;
		this.setHardness(5.0F);
		this.setHarvestLevel("axe", 0);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(EveryTrees.tabEveryTrees);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
        textures = new IIcon[types.length];
        top = new IIcon[types.length];
        for (int i = 0; i < types.length; ++i) {
            textures[i] = iconRegister.registerIcon(EveryTrees.MODID + ":log_" + types[i]);
            top[i] = iconRegister.registerIcon(EveryTrees.MODID + ":log_" + types[i] + "_top");
        }
    }
	
	@Override
	public IIcon getIcon(int side, int meta) {
		int pos = meta & 12;
		if (pos == 0 && (side == 0 || side == 1) || 
			pos == 4 && (side == 4 || side == 5) || 
			pos == 8 && (side == 2 || side == 3)) {
			return top[(getTypeFromMeta(meta) + category.ordinal() * 4)];
		}else{
			return textures[(getTypeFromMeta(meta) + category.ordinal() * 4)];
		}
	}
	
	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 4; ++i) {
			list.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
		byte radius = 4;
		int bounds = radius + 1;
		if (world.checkChunksExist(x - bounds, y - bounds, z - bounds, x + bounds, y + bounds, z + bounds)) {
			for (int i = -radius; i <= radius; ++i) {
				for (int j = -radius; j <= radius; ++j) {
					for (int k = -radius; k <= radius; ++k) {
						Block block = world.getBlock(x + i, y + j, z + k);
						if (block.isLeaves(world, x, y, z)) {
							block.beginLeavesDecay(world, x + i, y + j, z + k);
						}
					}
				}
			}
		}
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		int type = getTypeFromMeta(meta);
		byte orientation = 0;
		switch (side) {
			case 0:
			case 1:
				orientation = 0;
				break;
			case 2:
			case 3:
				orientation = 8;
				break;
			case 4:
			case 5:
				orientation = 4;
		}
		return type | orientation;
	}
	
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		if (category == LogCategory.CAT3 && world.getBlockMetadata(x, y, z) == 2) {
			return 0;
		}else{
			return Blocks.fire.getFlammability(this);
		}
	}
	
	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		if (category == LogCategory.CAT3 && world.getBlockMetadata(x, y, z) == 2) {
			return 0;
		}else{
			return Blocks.fire.getEncouragement(this);
		}
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		if (category == LogCategory.CAT3 && world.getBlockMetadata(x, y, z) == 2) {
			return false;
		}else{
			return getFlammability(world, x, y, z, face) > 0;
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return getTypeFromMeta(meta);
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(this, 1, getTypeFromMeta(meta));
	}

	@Override
	public int getRenderType() {
		return 31;
	}

	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isWood(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	public String getWoodType(int meta) {
		return types[getTypeFromMeta(meta) + category.ordinal() * 4];
	}

	private static int getTypeFromMeta(int meta) {
		return meta & 3;
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
