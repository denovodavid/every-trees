package net.davidjholland.everytrees;

import static net.davidjholland.everytrees.ETCItems.*;
import static net.davidjholland.everytrees.ETItems.registerItem;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ETItems {
	
	public static void init() {
		registerItems();
	}

	private static void registerItems() {
		shards = registerItem(new ItemShards().setUnlocalizedName("shards"));
		food = registerItem(new ItemETFood(0).setUnlocalizedName("food"));
	}

	public static Item registerItem(Item item) {
	    GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	    return item;
	}
}
