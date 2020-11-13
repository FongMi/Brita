package com.fongmi.android.brita.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.github.bassaer.library.MDColor;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Entity
public class Record {

	@PrimaryKey
	private long time;

	public static Record create() {
		Record record = new Record();
		record.setTime(System.currentTimeMillis());
		return record;
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

	public String getText() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm", Locale.getDefault());
		return format.format(getTime());
	}
}
