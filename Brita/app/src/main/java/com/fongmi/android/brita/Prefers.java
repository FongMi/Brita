package com.fongmi.android.brita;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class Prefers {

	private static SharedPreferences getPrefers() {
		return PreferenceManager.getDefaultSharedPreferences(App.getInstance());
	}

	static void put(String key, Object obj) {
		if (obj == null) return;
		if (obj instanceof String) {
			getPrefers().edit().putString(key, (String) obj).apply();
		} else if (obj instanceof Boolean) {
			getPrefers().edit().putBoolean(key, (Boolean) obj).apply();
		} else if (obj instanceof Float) {
			getPrefers().edit().putFloat(key, (Float) obj).apply();
		} else if (obj instanceof Integer) {
			getPrefers().edit().putInt(key, (Integer) obj).apply();
		} else if (obj instanceof Long) {
			getPrefers().edit().putLong(key, (Long) obj).apply();
		}
	}

	static Integer getInt(String key) {
		return getPrefers().getInt(key, 0);
	}

	static Integer getInt(String key, int defaultValue) {
		return getPrefers().getInt(key, defaultValue);
	}
}