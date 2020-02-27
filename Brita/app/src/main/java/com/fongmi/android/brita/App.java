package com.fongmi.android.brita;

import android.app.Application;

public class App extends Application {

	private static App instance;

	public App() {
		instance = this;
	}

	public static App getInstance() {
		return instance;
	}

	public static String getName() {
		return getInstance().getString(R.string.app_name).toLowerCase();
	}
}
