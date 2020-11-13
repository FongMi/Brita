package com.fongmi.android.brita;

import android.app.Application;
import android.provider.Settings;

public class App extends Application {

	private static App instance;

	public App() {
		instance = this;
	}

	public static App get() {
		return instance;
	}

	public static String getName() {
		return get().getString(R.string.app_name).toLowerCase();
	}

	public static String getAndroidId() {
		return Settings.Secure.getString(App.get().getContentResolver(), Settings.Secure.ANDROID_ID);
	}
}
