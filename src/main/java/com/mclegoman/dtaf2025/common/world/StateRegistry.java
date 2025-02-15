/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.world;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.world.dimension.DimensionRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

public class StateRegistry {
	public static PersistentState getState(StateType type, MinecraftServer server) {
		return switch (type) {
			case spaceStation -> SpaceStationState.getServerState(server);
		};
	}
	public static class SpaceStationState extends PersistentState {
		public Boolean hasGeneratedSpaceStation = false;

		public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
			nbt.putBoolean("hasGeneratedSpaceStation", hasGeneratedSpaceStation);
			return nbt;
		}

		public static SpaceStationState createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registries) {
			SpaceStationState state = new SpaceStationState();
			state.hasGeneratedSpaceStation = tag.getBoolean("hasGeneratedSpaceStation");
			return state;
		}

		private static final Type<SpaceStationState> type = new Type<>(SpaceStationState::new, SpaceStationState::createFromNbt, null);

		public static SpaceStationState getServerState(MinecraftServer server) {
			ServerWorld world = server.getWorld(DimensionRegistry.spaceStation.getWorld());
			if (world != null) {
				PersistentStateManager persistentStateManager = world.getPersistentStateManager();
				SpaceStationState state = persistentStateManager.getOrCreate(type, Data.getVersion().getID());
				state.markDirty();
				return state;
			} else return null;
		}
	}
	public enum StateType {
		spaceStation
	}
}