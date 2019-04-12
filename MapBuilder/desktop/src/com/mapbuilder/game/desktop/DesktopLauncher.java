package com.mapbuilder.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mapbuilder.game.MapBuilderMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1280;
		config.height = 1280;

		// fullscreen
		//config.fullscreen = true;
		// vSync
		config.vSyncEnabled = true;

		new LwjglApplication(new MapBuilderMain(), config);
	}
}
