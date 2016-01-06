package com.ogaclejapan.smarttablayout.demo.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ogaclejapan.smarttablayout.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

	private static final String TAG = BaseActivity.class.getSimpleName();
	
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ButterKnife.bind(this);
		initToolBar();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		initIab();
	};

	@Override
	public void onSaveInstanceState(Bundle outState) {
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void finish() {
		super.finish();
	}

	@Nullable
	@Bind(R.id.app_toolbar)
	protected Toolbar toolbar;
	protected final void initToolBar() {
//		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//		toolbar.setTitle(demo.titleResId);
//		setSupportActionBar(toolbar);
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// Set up the toolbar.
		if (null != toolbar) {
			setSupportActionBar(toolbar);
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onBackPressed();
				}
			});
		}

		ActionBar ab = getSupportActionBar();
		if (null != ab) {
			ab.setDisplayHomeAsUpEnabled(true);
			enableDisplayHomeAsUp(ab);
		}
	}

	protected void enableDisplayHomeAsUp(ActionBar ab) {
		ab.setDisplayShowHomeEnabled(true);
	}

	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(title);
		toolbar.setTitle(title);
	}
}
