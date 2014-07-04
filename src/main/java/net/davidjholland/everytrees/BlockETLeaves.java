package net.davidjholland.everytrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockETLeaves extends BlockLeavesBase implements IShearable {
	public static enum LeafCategory {
		CAT1, CAT2, CAT3, CAT4;
	}

	//leaves1
	//Emerald			(0) 31512
	//Diamond			(1) 6155509
	//Gold				(2) 16297771
	//Iron				(3) 11505271

	//leaves2
	//Glowstone			(0) 0
	//Lapis				(1) 1857478
	//Redstone			(2) 0
	//Obsidian			(3) 0
	
	//leaves3
	//Enchanted			(0) 0
	//Ender				(1) 0
	//Lava				(2) 0
	//Ice				(3) 0
	
	//leaves4
	//Slime				(0) 0
	//Bone				(1) 0
	//Meat				(2) 0
	//Leather			(3) 0

	private static final String[] leaves = new String[] {"emerald", "diamond", "gold", "iron", "glowstone", "lapis", "redstone", "obsidian", "enchanted", "ender", "lava", "ice", "slime", "bone", "meat", "leather"};
	private static final int[] color = new int[] {31512, 6155509, 16297771, 11505271, 0, 1857478, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private IIcon[][] textures;
	private final LeafCategory category;
	int[] adjacentTreeBlocks;

	public BlockETLeaves(LeafCategory cat) {

        super(Material.leaves, false);
		category = cat;
		this.setHardness(0.2F);
		this.setStepSound(Block.soundTypeGrass);
		this.setTickRandomly(true);
		this.setLightOpacity(1);
		this.setCreativeTab(EveryTrees.tabEveryTrees);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		
		textures = new IIcon[3][leaves.length];
		for (int i = 0; i < leaves.length; ++i) {
			textures[0][i] = iconRegister.registerIcon(EveryTrees.MODID + ":leaves_" + leaves[i]);
			textures[1][i] = iconRegister.registerIcon(EveryTrees.MODID + ":leaves_" + leaves[i] + "_opaque");
		}
	}

    @Override
    public int getRenderColor(int meta) {
    	int type = getTypeFromMeta(meta) + (category.ordinal() * 4);
    	return color[type];
    }

    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
    	int meta = world.getBlockMetadata(x, y, z);
    	return getRenderColor(meta);
    }

	@Override
	public IIcon getIcon(int side, int metadata) {
		int type = getTypeFromMeta(metadata) + (category.ordinal() * 4);
		return textures[(!isOpaqueCube() ? 0 : 1)][type >= leaves.length ? 0 : type];
	}

	@Override
	public boolean isOpaqueCube() {
		return Blocks.leaves.isOpaqueCube();
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 4; ++i) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (world.canLightningStrikeAt(x, y + 1, z) && !World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && random.nextInt(15) == 1) {
			double d0 = x + random.nextFloat();
			double d1 = y - 0.05D;
			double d2 = z + random.nextFloat();
			world.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		super.randomDisplayTick(world, x, y, z, random);
	}
	
	public boolean isType(int metadata, int baseMeta) {
		for (int type = baseMeta; type <= baseMeta + (4 * 3); type += 4) {
			if (metadata == type) return true;
		}
		return false;
	}

    @Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
        byte radius = 1;
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
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.isRemote)
			return;

		int meta = world.getBlockMetadata(x, y, z);

		if ((meta & 8) != 0 && (meta & 4) == 0)
		{
			byte b0 = 4;
			int i1 = b0 + 1;
			byte b1 = 32;
			int j1 = b1 * b1;
			int k1 = b1 / 2;

			if (adjacentTreeBlocks == null)
			{
				adjacentTreeBlocks = new int[b1 * b1 * b1];
			}

			int l1;

			if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1))
			{
				int i2;
				int j2;
				int k2;

				for (l1 = -b0; l1 <= b0; ++l1)
				{
					for (i2 = -b0; i2 <= b0; ++i2)
					{
						for (j2 = -b0; j2 <= b0; ++j2)
						{
                            Block block = world.getBlock(x + l1, y + i2, z + j2);

							if (block != null && block.canSustainLeaves(world, x + l1, y + i2, z + j2))
							{
								adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
							}
							else if (block != null && block.isLeaves(world, x + l1, y + i2, z + j2))
							{
								adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
							}
							else
							{
								adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
							}
						}
					}
				}

				for (l1 = 1; l1 <= 4; ++l1)
				{
					for (i2 = -b0; i2 <= b0; ++i2)
					{
						for (j2 = -b0; j2 <= b0; ++j2)
						{
							for (k2 = -b0; k2 <= b0; ++k2)
							{
								if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1)
								{
									if (adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
									{
										adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
									}
								}
							}
						}
					}
				}
			}

			l1 = adjacentTreeBlocks[k1 * j1 + k1 * b1 + k1];

			if (l1 >= 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, meta & -9, 4);
			}
			else
			{
				this.removeLeaves(world, x, y, z);
			}
		}
	}

    private void removeLeaves(World world, int x, int y, int z) {
        this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        world.setBlockToAir(x, y, z);
    }

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		int metadata = world.getBlockMetadata(x, y, z);
		if (category == LeafCategory.CAT3 && metadata == 2) {
			return 0;
		}else{
			return Blocks.fire.getFlammability(this);
		}
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		int metadata = world.getBlockMetadata(x, y, z);
		if (category == LeafCategory.CAT3 && metadata == 2) {
			return 0;
		}else{
			return Blocks.fire.getEncouragement(this);
		}
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		int metadata = world.getBlockMetadata(x, y, z);
		if (category == LeafCategory.CAT3 && metadata == 2) {
			return false;
		}else{
			return getFlammability(world, x, y, z, face) > 0;
		}
	}


    @Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemFromBlock(ETCBlocks.saplings);
    }

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune) {
		if (world.isRemote)
			return;

		if (world.rand.nextInt(20) == 0) {
			Item item = this.getItemDropped(metadata, world.rand, fortune);
			this.dropBlockAsItem(world, x, y, z, new ItemStack(item, 1, this.damageDropped(metadata)));
		}
		
		// Emerald
		if ((metadata & 3) == 0 && (world.rand.nextInt(200) == 0)) {
			if(world.rand.nextInt(2) == 0){
				this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.emerald, 1));
			}else{
				this.dropBlockAsItem(world, x, y, z, new ItemStack(ETCItems.shards, 1, 0));
			}
		}else
		// Diamond
		if ((metadata & 3) == 1 && (world.rand.nextInt(200) == 0)) {
			if(world.rand.nextInt(2) == 0){
				this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.diamond, 1));
			}else{
				this.dropBlockAsItem(world, x, y, z, new ItemStack(ETCItems.shards, 1, 1));
			}
		}else
		// Gold
		if ((metadata & 3) == 2 && (world.rand.nextInt(150) == 0)) {
			if(world.rand.nextInt(3) == 0){
				this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.golden_apple, 1, 1));
			}else if(world.rand.nextInt(2) == 0){
				this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.golden_apple, 1, 0));
			}else{
				this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.gold_nugget, 2));
			}
		}else
		// Iron
		if ((metadata & 3) == 3 && (world.rand.nextInt(150) == 0)) {
			this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.iron_ingot, 1));
		}else
		// Glowstone
		if ((metadata & 3) == 4 && (world.rand.nextInt(150) == 0)) {
			this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.glowstone_dust, 2));
		}else
		// Lapis
		if ((metadata & 3) == 5 && (world.rand.nextInt(150) == 0)) {
			this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.dye, 2, 4));
		}else
		// Redstone
		if ((metadata & 3) == 6 && (world.rand.nextInt(150) == 0)) {
			this.dropBlockAsItem(world, x, y, z, new ItemStack(Items.redstone, 2));
		}else
		// Obsidian
		if ((metadata & 3) == 7 && (world.rand.nextInt(150) == 0)) {
			this.dropBlockAsItem(world, x, y, z, new ItemStack(ETCItems.shards, 1, 2));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return (getTypeFromMeta(meta) + category.ordinal() * 4);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return getTypeFromMeta(world.getBlockMetadata(x, y, z));
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, getTypeFromMeta(world.getBlockMetadata(x, y, z))));
		return ret;
	}

	public String getLeafType(int metadata) {
		int type = getTypeFromMeta(metadata) + (category.ordinal() * 4);
		return leaves[type >= leaves.length ? 0 : type];
	}

	private static int getTypeFromMeta(int meta) {
		meta = meta & 3;
		if (meta < 0 || meta >= leaves.length) {
			meta = 0;
		}
		return meta;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return true;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
	}

	@Override
	public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}
}