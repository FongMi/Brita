package com.fongmi.android.brita.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.github.bassaer.library.MDColor;

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

	public int getColor(int position) {
		return position % 2 == 0 ? MDColor.AMBER_200 : MDColor.GREEN_200;
	}

	public String getDateText() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd (EEE)", Locale.getDefault());
		return format.format(getTime()).replace("週", "");
	}

	public String getTimeText() {
		SimpleDateFormat format = new SimpleDateFormat("a hh:mm:ss", Locale.getDefault());
		return format.format(getTime());
	}
}
