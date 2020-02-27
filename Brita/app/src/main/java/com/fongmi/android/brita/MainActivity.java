package com.fongmi.android.brita;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fongmi.android.brita.adapter.RecordAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.recyclerView) RecyclerView mRecyclerView;
	@BindView(R.id.count) TextView mCount;
	@BindView(R.id.max) TextView mMax;

	private RecordAdapter mAdapter;

	private int getMax() {
		return Prefers.getInt("max", 50);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		initView();
		initEvent();
	}

	private void initView() {
		mMax.setText(getString(R.string.max, getMax()));
		setRecyclerView();
		updateCount();
	}

	private void initEvent() {
		mAdapter.setOnItemClickListener(this::onSub);
	}

	private void setRecyclerView() {
		mAdapter = new RecordAdapter();
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(mAdapter);
	}

	private void updateCount() {
		mCount.setText(String.valueOf(mAdapter.getItemCount()));
		mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
	}

	public void onSub(int position) {
		mAdapter.sub(position);
		updateCount();
	}

	@OnClick(R.id.add)
	public void onAdd() {
		if (mAdapter.getItemCount() < getMax()) mAdapter.add();
		updateCount();
	}

	@OnClick(R.id.reset)
	public void onReset(View view) {
		mAdapter.reset();
		updateCount();
	}
}
