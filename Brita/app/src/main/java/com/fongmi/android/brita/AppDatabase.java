package com.fongmi.android.brita;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fongmi.android.brita.bean.Record;
import com.fongmi.android.brita.dao.RecordDao;

@Database(entities = {Record.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

	private static volatile AppDatabase instance;

	public static synchronized AppDatabase getInstance() {
		if (instance == null) instance = create(App.getInstance());
		return instance;
	}

	private static AppDatabase create(Context context) {
		return Room.databaseBuilder(context, AppDatabase.class, App.getName()).allowMainThreadQueries().fallbackToDestructiveMigration().build();
	}

	public abstract RecordDao getDao();
}

