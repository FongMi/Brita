package com.fongmi.android.brita;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.max) TextView mMax;
	@BindView(R.id.count) TextView mCount;

	private int getMax() {
		return Prefers.getInt("max", 50);
	}

	private int getCount() {
		return Prefers.getInt("count");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		initView();
	}

	private void initView() {
		mMax.setText(getString(R.string.main_max, getMax()));
		mCount.setText(String.valueOf(getCount()));
	}

	@OnClick({R.id.sub, R.id.reset, R.id.add})
	public void onClick(View view) {
		setCount(view.getId(), getCount());
		mCount.setText(String.valueOf(getCount()));
	}

	private void setCount(int id, int count) {
		switch (id) {
			case R.id.sub:
				if (count == 0) return;
				Prefers.put("count", --count);
				break;
			case R.id.reset:
				Prefers.put("count", 0);
				break;
			case R.id.add:
				if (count == getMax()) return;
				Prefers.put("count", ++count);
				break;
		}
	}
}
