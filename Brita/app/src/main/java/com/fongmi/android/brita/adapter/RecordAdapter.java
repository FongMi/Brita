package com.fongmi.android.brita.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fongmi.android.brita.AppDatabase;
import com.fongmi.android.brita.R;
import com.fongmi.android.brita.bean.Record;
import com.fongmi.android.brita.dao.RecordDao;
import com.github.bassaer.library.MDColor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

	private RecordDao mDao;
	private List<Record> mItems;
	private OnItemClickListener mItemClickListener;

	public RecordAdapter() {
		this.mDao = AppDatabase.getInstance().getDao();
		this.mItems = mDao.getAll();
	}

	public interface OnItemClickListener {
		void onItemClick(int position);
	}

	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		this.mItemClickListener = itemClickListener;
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		@BindView(R.id.time) TextView time;

		ViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
			view.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			mItemClickListener.onItemClick(getLayoutPosition());
		}
	}

	public void sub(int position) {
		Record item = mItems.get(position);
		notifyItemRemoved(position);
		mDao.delete(item.getTime());
		mItems.remove(position);
	}

	public void add() {
		mItems.add(Record.create());
		mDao.insert(mItems.get(mItems.size() - 1));
		notifyItemInserted(mItems.size() - 1);
	}

	public void reset() {
		mDao.clear();
		mItems.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_record, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Record item = mItems.get(position);
		holder.time.setText(item.getFormat());
		holder.itemView.setBackgroundColor(position % 2 == 0 ? MDColor.BLUE_50 : MDColor.GREEN_50);
	}
}
