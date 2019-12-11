package com.fongmi.android.brita;

import android.app.Application;
import android.content.Context;

public class App extends Application {

	private static App instance;

	public static App getInstance() {
		return instance;
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		App.instance = this;
	}
}
