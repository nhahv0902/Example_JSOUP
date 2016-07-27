package com.nhahv.parsehtml.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nhahv.parsehtml.R;
import com.nhahv.parsehtml.fragments.AlbumFragment;
import com.nhahv.parsehtml.fragments.MusicFragment;
import com.nhahv.parsehtml.fragments.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int SIZE_TAB = 3;
    private final String TAG = getClass().getSimpleName();
    private int mPosition;
    private int[] iconTab;
    private String[] titleTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTab = getResources().getStringArray(R.array.tab_layout);
        iconTab = new int[]{
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

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AlbumFragment());
        fragments.add(new MusicFragment());
        fragments.add(new VideoFragment());

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), fragments, titleTab);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < SIZE_TAB; i++) {
            mTabLayout.getTabAt(i).setIcon(iconTab[i]);
        }

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        Fragment fragment;
//        switch (id) {
//            case R.id.nav_home:
//                fragment = new AlbumFragment();
//                break;
//            case R.id.nav_music:
//                fragment = new MusicFragment();
//                break;
//            case R.id.nav_album:
//                fragment = new AlbumFragment();
//                break;
//            case R.id.nav_video:
//                fragment = new VideoFragment();
//                break;
//            case R.id.nav_vi:
//                fragment = TopFragment.getInstances(MyApplication.TOP_VN);
//                break;
//            case R.id.nav_us_uk:
//                fragment = TopFragment.getInstances(MyApplication.TOP_US);
//                break;
//            case R.id.nav_k_pop:
//                fragment = TopFragment.getInstances(MyApplication.TOP_POP);
//                break;
//            default:
//                fragment = new AlbumFragment();
//                break;
//        }
//
//        startFragment(fragment);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content_main, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mListFragment;
        private String[] mTitle;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
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
}
