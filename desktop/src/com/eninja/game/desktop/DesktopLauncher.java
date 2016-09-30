package com.eninja.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.eninja.game.FlappyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = FlappyDemo.TITLE;
		config.width = FlappyDemo.WIDTH;
		config.height = FlappyDemo.HEIGHT;
		new LwjglApplication(new FlappyDemo(), config);
	}
}
