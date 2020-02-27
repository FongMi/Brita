package com.fongmi.android.brita.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.fongmi.android.brita.bean.Record;

import java.util.List;

@Dao
public interface RecordDao {

	@Query("SELECT * FROM record")
	List<Record> getAll();

	@Insert
	void insert(Record item);

	@Query("DELETE FROM record WHERE time = :time")
	void delete(long time);

	@Query("DELETE FROM record")
	void clear();
}
