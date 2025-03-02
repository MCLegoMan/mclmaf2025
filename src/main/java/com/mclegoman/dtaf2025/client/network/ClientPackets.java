/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.network;

import com.mclegoman.dtaf2025.client.screen.locked_chest.LockedChestScreen;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.easter_egg.EasterEggRegistry;
import com.mclegoman.dtaf2025.common.network.Packets;
import com.mclegoman.dtaf2025.common.network.payload.OpenScreenPayload;
import com.mclegoman.dtaf2025.common.network.payload.RequestPayload;
import com.mclegoman.dtaf2025.common.network.payload.SanicPayload;
import com.mclegoman.luminance.client.data.ClientData;
import com.mclegoman.luminance.common.util.LogType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ClientPackets {
	public static void init() {
		ClientPlayNetworking.registerGlobalReceiver(RequestPayload.id, ClientPackets::receiveRequest);
		ClientPlayNetworking.registerGlobalReceiver(SanicPayload.id, ClientPackets::receiveIsSanic);
		ClientPlayNetworking.registerGlobalReceiver(OpenScreenPayload.id, ClientPackets::receiveOpenScreen);
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> sendRequest(Packets.isSanic));
	}
	public static void sendRequest(Identifier requestId) {
		ClientData.minecraft.execute(() -> {
			ClientPlayNetworking.send(new RequestPayload(requestId));
		});
	}
	public static void receiveRequest(RequestPayload payload, ClientPlayNetworking.Context context) {
		ClientData.minecraft.execute(() -> {
			// We don't currently have anything to send to the server.
			Data.getVersion().sendToLog(LogType.WARN, "[CLIENT] Unexpectedly received RequestPayload('" + payload.identifier() + "') from server!");
		});
	}
	public static void receiveIsSanic(SanicPayload payload, ClientPlayNetworking.Context context) {
		ClientData.minecraft.execute(() -> {
			EasterEggRegistry.setSanic(payload.isSanic());
		});
	}
	public static void receiveOpenScreen(OpenScreenPayload payload, ClientPlayNetworking.Context context) {
		ClientData.minecraft.execute(() -> {
			if (payload.identifier().equals(Identifier.of(Data.getVersion().getID(), "locked_chest"))) ClientData.minecraft.setScreen(new LockedChestScreen());
			else Data.getVersion().sendToLog(LogType.WARN, "[CLIENT] Unexpectedly received OpenScreenPayload('" + payload.identifier() + "') from server!");
		});
	}
}
