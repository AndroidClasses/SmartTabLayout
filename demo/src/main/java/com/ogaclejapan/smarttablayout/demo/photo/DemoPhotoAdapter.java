package com.ogaclejapan.smarttablayout.demo.photo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.demo.R;
import com.ogaclejapan.smarttablayout.demo.model.CommonItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DemoPhotoAdapter extends RecyclerView.Adapter<DemoPhotoAdapter.ViewHolder> {
	private Context mContext;
	private int mSelectedId;

	private List<CommonItem> typeFaceArray = new ArrayList<>();

	public DemoPhotoAdapter(Context context) {
		mContext = context;

		typeFaceArray.add(new CommonItem());
		typeFaceArray.add(new CommonItem());
		typeFaceArray.add(new CommonItem());
		typeFaceArray.add(new CommonItem());
		typeFaceArray.add(new CommonItem());
		typeFaceArray.add(new CommonItem());
	}

	// todo: save current selected
	public void setSelected(int id) {
		mSelectedId = id;
		notifyDataSetChanged();
	}

	public void setData(List<CommonItem> images) {
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

		public void setTypeface(CommonItem commonItem, boolean selected) {
			mLabelView.setText(commonItem.getmLabel());
			mTimeView.setText(commonItem.getmTime());
			mLabelView.setTag(commonItem);
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
		CommonItem typeface = typeFaceArray.get(i % typeFaceArray.size());
		View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_card_small, null);
		ViewHolder viewHolder = new ViewHolder(view);
		viewHolder.setTypeface(typeface, false);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
		final CommonItem typeface = typeFaceArray.get(i);
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
		void onItemClick(String label, CommonItem tf);
	};

	TextStickClickListener mItemClickListener;
	public void setOnItemClickListener(TextStickClickListener hlListOnItemClickListener) {
		mItemClickListener = hlListOnItemClickListener;
	}
}
