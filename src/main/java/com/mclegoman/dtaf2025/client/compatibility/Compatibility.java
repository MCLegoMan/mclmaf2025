/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.compatibility;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.luminance.client.util.CompatHelper;
import com.mclegoman.luminance.common.util.Couple;
import com.mclegoman.luminance.common.util.DateHelper;
import com.terraformersmc.modmenu.config.ModMenuConfig;

public class Compatibility {
	public static void init() {
		CompatHelper.addLuminanceModMenuBadge(Data.getVersion().getID());
	}
	public static boolean isModMenuInstalled() {
		return Data.isModInstalled("modmenu");
	}
	public static String getModsButtonStyle() {
		String value = "none";
		if (isModMenuInstalled()) {
			if (ModMenuConfig.MODIFY_TITLE_SCREEN.getValue()) value = ModMenuConfig.MODS_BUTTON_STYLE.getValue().name();
		}
		return value;
	}
}