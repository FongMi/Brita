package com.fongmi.android.brita;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fongmi.android.brita.adapter.RecordAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements RecordAdapter.OnItemClickListener {

	@BindView(R.id.recyclerView) RecyclerView mRecyclerView;
	@BindView(R.id.count) TextView mCount;

	private RecordAdapter mAdapter;
	private Toast mToast;

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
		setRecyclerView();
		refresh(true);
	}

	private void initEvent() {
		mAdapter.setOnItemClickListener(this);
	}

	private void setRecyclerView() {
		mAdapter = new RecordAdapter();
		mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		mRecyclerView.setAdapter(mAdapter);
	}

	private void refresh(boolean scroll) {
		mCount.setText(String.valueOf(mAdapter.getItemCount()));
		if (scroll) mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
	}

	@OnClick(R.id.add)
	public void onAdd() {
		if (mAdapter.getItemCount() < getMax()) mAdapter.add();
		refresh(true);
	}

	@OnClick(R.id.reset)
	public void onReset(View view) {
		mAdapter.clear();
		refresh(false);
	}

	@Override
	public void onLongClick(int position) {
		mAdapter.remove(position);
		refresh(false);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mAdapter.sync();
	}
}
