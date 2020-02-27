package com.fongmi.android.brita.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Entity
public class Record {

	@PrimaryKey(autoGenerate = true)
	private int id;
	private long time;

	public static Record create() {
		Record record = new Record();
		record.setTime(System.currentTimeMillis());
		return record;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getFormat() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd　HH:mm:ss　EEEE", Locale.getDefault());
		return format.format(getTime());
	}
}
