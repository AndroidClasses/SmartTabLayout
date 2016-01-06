package com.ogaclejapan.smarttablayout.demo;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.demo.music.DemoMusicActivity;
import com.ogaclejapan.smarttablayout.demo.photo.DemoPhotoActivity;
import com.ogaclejapan.smarttablayout.demo.video.DemoVideoActivity;

public enum Demo {

  BASIC(R.string.demo_title_basic, R.layout.demo_basic),

  BASIC2(R.string.demo_title_basic2, R.layout.demo_basic_title_offset_auto_center),

  SMART_INDICATOR(R.string.demo_title_smart_indicator, R.layout.demo_smart_indicator),

  DISTRIBUTE_EVENLY(R.string.demo_title_distribute_evenly, R.layout.demo_distribute_evenly) {
    @Override
    public int[] tabs() {
      return tab3();
    }
  },

  ALWAYS_IN_CENTER(R.string.demo_title_always_in_center, R.layout.demo_always_in_center),

  CUSTOM_TAB(R.string.demo_title_custom_tab_text, R.layout.demo_custom_tab_text),

  CUSTOM_TAB_COLORS(R.string.demo_title_custom_tab_colors, R.layout.demo_custom_tab_colors),

  CUSTOM_TAB_ICONS1(R.string.demo_title_custom_tab_icons1, R.layout.demo_custom_tab_icons1) {
    @Override
    public int[] tabs() {
      return new int[] {
          R.string.demo_tab_no_title,
          R.string.demo_tab_no_title,
          R.string.demo_tab_no_title,
          R.string.demo_tab_no_title
      };
    }

    @Override
    public void setup(SmartTabLayout layout) {
      super.setup(layout);

      final LayoutInflater inflater = LayoutInflater.from(layout.getContext());
      final Resources res = layout.getContext().getResources();

      layout.setCustomTabView(new SmartTabLayout.TabProvider() {
        @Override
        public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
          ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_icon1, container, false);
          switch (position) {
            case 0:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_home_white_24dp));
              break;
            case 1:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_search_white_24dp));
              break;
            case 2:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_person_white_24dp));
              break;
            case 3:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_flash_on_white_24dp));
              break;
            default:
              throw new IllegalStateException("Invalid position: " + position);
          }
          return icon;
        }
      });
    }
  },

  CUSTOM_TAB_ICONS2(R.string.demo_title_custom_tab_icons2, R.layout.demo_custom_tab_icons2) {
    @Override
    public int[] tabs() {
      return new int[] {
          R.string.demo_tab_no_title,
          R.string.demo_tab_no_title,
          R.string.demo_tab_no_title,
          R.string.demo_tab_no_title
      };
    }

    @Override
    public void setup(SmartTabLayout layout) {
      super.setup(layout);

      final LayoutInflater inflater = LayoutInflater.from(layout.getContext());
      final Resources res = layout.getContext().getResources();

      layout.setCustomTabView(new SmartTabLayout.TabProvider() {
        @Override
        public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
          ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_icon2, container, false);
          switch (position) {
            case 0:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_home_white_24dp));
              break;
            case 1:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_search_white_24dp));
              break;
            case 2:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_person_white_24dp));
              break;
            case 3:
              icon.setImageDrawable(res.getDrawable(R.drawable.ic_flash_on_white_24dp));
              break;
            default:
              throw new IllegalStateException("Invalid position: " + position);
          }
          return icon;
        }
      });
    }
  },

  CUSTOM_TAB_MARGIN(R.string.demo_title_custom_tab_margin, R.layout.demo_custom_tab_margin),

  INDICATOR_TRICK1(R.string.demo_title_indicator_trick1, R.layout.demo_indicator_trick1),

  INDICATOR_TRICK2(R.string.demo_title_indicator_trick2, R.layout.demo_indicator_trick2),

  RIGHT_TO_LEFT(R.string.demo_title_right_to_left, R.layout.demo_rtl) {
    @Override
    public void startActivity(Context context) {
      DemoRtlActivity.startActivity(context, this);
    }
  },

  LIKE_MEDIUM_TAG(R.string.demo_title_advanced_medium, R.layout.demo_like_a_medium_tag) {
    @Override
    public int[] tabs() {
      return new int[] {
              R.string.demo_tab_like_a_medium_top,
              R.string.demo_tab_like_a_medium_latest
      };
    }

    @Override
    public void startActivity(Context context) {
      DemoLikeMediumActivity.startActivity(context, this);
    }
  },

  PHOTO_TAG(R.string.demo_title_advanced_photo, R.layout.demo_like_a_medium_tag) {
    @Override
    public int[] tabs() {
      return new int[] {
              R.string.demo_tab_mine,
              R.string.demo_tab_all
      };
    }

    @Override
    public void startActivity(Context context) {
      DemoPhotoActivity.startActivity(context, this);
    }
  },

  MUSIC_TAG(R.string.demo_title_advanced_music, R.layout.demo_like_a_medium_tag) {
    @Override
    public int[] tabs() {
      return new int[] {
              R.string.demo_tab_mine,
              R.string.demo_tab_all
      };
    }

    @Override
    public void startActivity(Context context) {
      DemoMusicActivity.startActivity(context, this);
    }
  },

  VIDEO_TAG(R.string.demo_title_advanced_video, R.layout.demo_like_a_medium_tag) {
    @Override
    public int[] tabs() {
      return new int[] {
              R.string.demo_tab_mine,
              R.string.demo_tab_all
      };
    }

    @Override
    public void startActivity(Context context) {
      DemoVideoActivity.startActivity(context, this);
    }
  };

  public final int titleResId;
  public final int layoutResId;

  Demo(int titleResId, int layoutResId) {
    this.titleResId = titleResId;
    this.layoutResId = layoutResId;
  }

  public static int[] tab10() {
    return new int[] {
        R.string.demo_tab_1,
        R.string.demo_tab_2,
        R.string.demo_tab_3,
        R.string.demo_tab_4,
        R.string.demo_tab_5,
        R.string.demo_tab_6,
        R.string.demo_tab_7,
        R.string.demo_tab_8,
        R.string.demo_tab_9,
        R.string.demo_tab_10
    };
  }

  public static int[] tab3() {
    return new int[] {
        R.string.demo_tab_8,
        R.string.demo_tab_9,
        R.string.demo_tab_10
    };
  }

  public void startActivity(Context context) {
    DemoActivity.startActivity(context, this);
  }

  public void setup(final SmartTabLayout layout) {
    //Do nothing.
  }

  public int[] tabs() {
    return tab10();
  }

}
