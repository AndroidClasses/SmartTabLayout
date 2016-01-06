package com.ogaclejapan.smarttablayout.demo.music;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.demo.R;
import com.ogaclejapan.smarttablayout.demo.model.MusicItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DemoMusicAdapter extends RecyclerView.Adapter<DemoMusicAdapter.ViewHolder> {
	private Context mContext;
	private int mSelectedId;

	private List<MusicItem> typeFaceArray = new ArrayList<>();

	public DemoMusicAdapter(Context context) {
		mContext = context;

		typeFaceArray.add(new MusicItem());
		typeFaceArray.add(new MusicItem());
		typeFaceArray.add(new MusicItem());
		typeFaceArray.add(new MusicItem());
		typeFaceArray.add(new MusicItem());
		typeFaceArray.add(new MusicItem());
	}

	// todo: save current selected
	public void setSelected(int id) {
		mSelectedId = id;
		notifyDataSetChanged();
	}

	public void setData(List<MusicItem> images) {
		typeFaceArray = images;
		notifyDataSetChanged();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.text_label)
		TextView mLabelView;
		@Bind(R.id.text_time)
		TextView mTimeView;

		public ViewHolder(View arg0) {
			super(arg0);
			ButterKnife.bind(this, arg0);
		}

		public String getLabel() {
			return mLabelView.getText().toString();
		}

		public void setTypeface(MusicItem musicItem, boolean selected) {
			mLabelView.setText(musicItem.getmLabel());
			mTimeView.setText(musicItem.getmTime());
			mLabelView.setTag(musicItem);
			mLabelView.setSelected(selected);
		}
	}

	@Override
	public int getItemCount()
	{
		return typeFaceArray.size();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		MusicItem typeface = typeFaceArray.get(i % typeFaceArray.size());
		View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_card_small, null);
		ViewHolder viewHolder = new ViewHolder(view);
		viewHolder.setTypeface(typeface, false);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
		final MusicItem typeface = typeFaceArray.get(i);
		viewHolder.setTypeface(typeface, (i == mSelectedId));
		viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != mItemClickListener) {
					mItemClickListener.onItemClick(viewHolder.getLabel(), typeface);
				}
			}
		});
	}

	public interface TextStickClickListener {
		void onItemClick(String label, MusicItem tf);
	};

	TextStickClickListener mItemClickListener;
	public void setOnItemClickListener(TextStickClickListener hlListOnItemClickListener) {
		mItemClickListener = hlListOnItemClickListener;
	}
}
