package net.davidjholland.everytrees;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemETFood extends ItemFood {
	
	private static final String[] foodTypes = new String[] {"bacon_raw", "bacon_cooked"};
	private IIcon[] textures;
	
	public ItemETFood(int healAmount) {
		super(healAmount, 0, false);
		this.setHasSubtypes(true);
		this.setCreativeTab(EveryTrees.tabEveryTrees);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
//		if (itemstack.getItemDamage() == 2) {
//			if (player.canEat(true)) {
//				player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
//			}
//		}else{
			if (player.canEat(false)) {
				player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
			}
//		}
        return itemstack;
    }
	
	@Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player) {
		--itemstack.stackSize;
		switch (itemstack.getItemDamage()) {
			case 0:
				player.getFoodStats().addStats(3, 0.1F);
				break;
			case 1:
				player.getFoodStats().addStats(6, 0.1F);
				break;				
			default:
				player.getFoodStats().addStats(0, 0.0F);
				break;
		}
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemstack, world, player);        
        return itemstack;
    }
	
    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
//    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 10) {
//    		return EnumAction.drink;
//    	}
    	return EnumAction.eat;
    }
    
	@Override
    public int getItemStackLimit(ItemStack itemStack)
    {
//    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 4)
//    	{
//    		return 1;
//    	}    	
        return 64;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
//    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 0)
//    	{             
//    		return 8; 
//    	}    	
        return 32;
    }
	
//	@Override
//	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
//    {
//        if (!world.isRemote)
//        {
//            switch (itemstack.getItemDamage())
//            {
//            	case 1:
//            		if (world.rand.nextFloat() < 0.6F)
//           				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 225, 0));
//            		break;
//            }
//        }
//    }
	
	//@Override
    //TODO: public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < foodTypes.length; ++i) {
			list.add(new ItemStack(item, 1, i));
		}
    }

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		textures = new IIcon[foodTypes.length];
		for (int i = 0; i < foodTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon(EveryTrees.MODID + ":food_" + foodTypes[i]);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}
		return textures[meta];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= foodTypes.length) {
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + foodTypes[meta];
	}
}
