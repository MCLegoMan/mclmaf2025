/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client;

import com.mclegoman.dtaf2025.client.gui.TitleScreenHelper;
import com.mclegoman.dtaf2025.client.registry.ClientBlockRegistry;
import com.mclegoman.dtaf2025.client.registry.ItemGroupRegistry;
import com.mclegoman.dtaf2025.client.space.SkyDataloader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class AprilFoolsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientBlockRegistry.init();
		TitleScreenHelper.init();
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SkyDataloader());
		ItemGroupRegistry.init();
	}
}
