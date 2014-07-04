package net.davidjholland.everytrees;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EveryTrees.MODID, name = EveryTrees.MODNAME, version = EveryTrees.VERSION)

public class EveryTrees {
	@Instance(value = EveryTrees.MODID)
	public static EveryTrees instance;
    
    public static final String MODID = "everytrees";
	public static final String MODNAME = "Every Trees";
	public static final String VERSION = "0.2";
    
	public static CreativeTabs tabEveryTrees;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        
    	tabEveryTrees = new CreativeTabs("tabEveryTrees"){
    		public ItemStack getIconItemStack() {
    			return new ItemStack(ETCBlocks.logs1, 1, 1);
    		}
			public Item getTabIconItem() {
				return null;
			}
		};
        
        ETBlocks.init();
        ETItems.init();
        ETCrafting.init();
        
        MinecraftForge.EVENT_BUS.register(new BonemealEventHandler());
        
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
}