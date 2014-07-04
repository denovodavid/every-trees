package net.davidjholland.everytrees;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BonemealEventHandler {
	
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event) {
		World world = event.world;
		
		int x = event.x;
		int y = event.y;
		int z = event.z;

		Block block = event.block;
		int meta = event.world.getBlockMetadata(x, y, z);
		
		if (block == ETCBlocks.saplings) {
			
			event.setResult(Result.ALLOW);

			if (!world.isRemote) {
				double chance = 0D;

				switch (meta) {
//				case 3: // Magic Sapling
//					chance = 0.1D;
//					break;
//
//				case 7: // Holy Sapling
//					chance = 0.15D;
//					break;
//
//				case 9: // Origin Sapling
//					chance = 1D;
//					break;

				default:
					chance = 0.45D;
					break;
				}

				if (world.rand.nextFloat() < chance) {
					//TODO:											  growTree()
					((BlockETSapling)ETCBlocks.saplings).func_149878_d(event.world, x, y, z, event.world.rand);
				}
			}
		}
	}
}