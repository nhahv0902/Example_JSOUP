package com.nhahv.parsehtml.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nhahv.parsehtml.R;
import com.nhahv.parsehtml.app.MyApplication;
import com.nhahv.parsehtml.fragments.AlbumFragment;
import com.nhahv.parsehtml.fragments.MusicFragment;
import com.nhahv.parsehtml.fragments.TopFragment;
import com.nhahv.parsehtml.fragments.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int SIZE_TAB = 3;
    private final String TAG = getClass().getSimpleName();
    private int mPosition;

    private int[] iconTab;
    private int[] iconTabSelect;
    private String[] titleTabIcon;
    private String[] titleTabTitle;
    private boolean isViewPagerIcon;

    private TabLayout mTabLayout, mTabLayoutTitle;
    private ViewPager mViewPager, mViewPagerTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTabIcon = getResources().getStringArray(R.array.tab_layout);
        titleTabTitle = getResources().getStringArray(R.array.title_tab);
        iconTab = new int[]{
                R.drawable.ic_queue_music_un_select,
                R.drawable.ic_album_un_select,
                R.drawable.ic_music_video_un_select};

        iconTabSelect = new int[]{
                R.drawable.ic_queue_music_white_48dp,
                R.drawable.ic_album_white_48dp,
                R.drawable.ic_music_video_white_48dp};
        initViews();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        startFragment(new AlbumFragment());
        mPosition = 0;
    }

    private void initViews() {

        List<Fragment> mListFragmentIcon = new ArrayList<>();
        mListFragmentIcon.add(new MusicFragment());
        mListFragmentIcon.add(new AlbumFragment());
        mListFragmentIcon.add(new VideoFragment());

        List<Fragment> mListFragmentTitle = new ArrayList<>();
        mListFragmentTitle.add(TopFragment.getInstances(MyApplication.TOP_VN));
        mListFragmentTitle.add(TopFragment.getInstances(MyApplication.TOP_US));
        mListFragmentTitle.add(TopFragment.getInstances(MyApplication.TOP_POP));

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout_icon);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_icon);

        mTabLayoutTitle = (TabLayout) findViewById(R.id.tab_layout_title);
        mViewPagerTitle = (ViewPager) findViewById(R.id.view_pager_title);


        ViewIconPagerAdapter mIconAdapter = new ViewIconPagerAdapter
                (getSupportFragmentManager(), mListFragmentIcon, titleTabIcon);

        ViewTitlePagerAdapter mTitleAdapter;
        mTitleAdapter = new ViewTitlePagerAdapter
                (getSupportFragmentManager(), mListFragmentTitle, titleTabTitle);

        mViewPager.setAdapter(mIconAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPagerTitle.setAdapter(mTitleAdapter);
        mTabLayoutTitle.setupWithViewPager(mViewPagerTitle);

        visibilityTabLayoutIcon();

        isViewPagerIcon = true;

        for (int i = 0; i < SIZE_TAB; i++) {
            mTabLayout.getTabAt(i).setIcon(iconTab[i]);
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mTabLayout.getTabAt(position).setIcon(iconTabSelect[position]);
                setTitle(titleTabIcon[position]);
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mTabLayout.getTabAt(tab.getPosition()).setIcon(iconTab[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                visibilityTabLayoutIcon();
                mViewPager.setCurrentItem(1);
                isViewPagerIcon = true;
                break;
            case R.id.nav_music:

                visibilityTabLayoutIcon();
                mViewPager.setCurrentItem(0);
                isViewPagerIcon = true;
                break;
            case R.id.nav_album:

                visibilityTabLayoutIcon();
                mViewPager.setCurrentItem(1);
                isViewPagerIcon = true;
                break;
            case R.id.nav_video:

                visibilityTabLayoutIcon();
                mViewPager.setCurrentItem(2);
                isViewPagerIcon = true;
                break;
            case R.id.nav_vi:
                setTitle(getString(R.string.charts));

                visibilityTabLayoutTitle();
                mViewPagerTitle.setCurrentItem(0);
                isViewPagerIcon = false;
                break;
            case R.id.nav_us_uk:
                setTitle(getString(R.string.charts));
                visibilityTabLayoutTitle();
                mViewPagerTitle.setCurrentItem(1);
                isViewPagerIcon = false;
                break;
            case R.id.nav_k_pop:
                setTitle(getString(R.string.charts));
                visibilityTabLayoutTitle();
                mViewPagerTitle.setCurrentItem(2);
                isViewPagerIcon = false;
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void visibilityTabLayoutTitle() {
        mViewPager.setVisibility(View.GONE);
        mTabLayout.setVisibility(View.GONE);

        mViewPagerTitle.setVisibility(View.VISIBLE);
        mTabLayoutTitle.setVisibility(View.VISIBLE);
    }

    private void visibilityTabLayoutIcon() {
        mViewPager.setVisibility(View.VISIBLE);
        mTabLayout.setVisibility(View.VISIBLE);

        mViewPagerTitle.setVisibility(View.GONE);
        mTabLayoutTitle.setVisibility(View.GONE);
    }

    private class ViewIconPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mListFragment;
        private String[] mTitle;

        public ViewIconPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
            super(fm);
            mListFragment = fragments;
            mTitle = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mListFragment.size();
        }
    }

    private class ViewTitlePagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mListFragment;
        private String[] mTitle;

        public ViewTitlePagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
            super(fm);
            mListFragment = fragments;
            mTitle = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mListFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }
}
