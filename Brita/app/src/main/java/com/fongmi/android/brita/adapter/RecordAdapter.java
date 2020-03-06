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

		void onItemClick(String text);

		void onLongClick(int position);
	}

	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		this.mItemClickListener = itemClickListener;
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

		@BindView(R.id.time) TextView time;

		ViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
			view.setOnClickListener(this);
			view.setOnLongClickListener(this);
		}

		@Override
		public void onClick(View view) {
			mItemClickListener.onItemClick(mItems.get(getLayoutPosition()).getTimeText());
		}

		@Override
		public boolean onLongClick(View v) {
			mItemClickListener.onLongClick(getLayoutPosition());
			return true;
		}
	}

	private void notifyItemChanged() {
		for (int i = 0; i < mItems.size(); i++) {
			notifyItemChanged(i);
		}
	}

	public void remove(int position) {
		mDao.delete(mItems.get(position).getTime());
		notifyItemRemoved(position);
		mItems.remove(position);
		notifyItemChanged();
	}

	public void add() {
		mItems.add(Record.create());
		mDao.insert(mItems.get(mItems.size() - 1));
		notifyItemInserted(mItems.size() - 1);
	}

	public void clear() {
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
		holder.time.setText(item.getDateText());
		holder.itemView.setBackgroundColor(item.getColor(position));
	}
}
