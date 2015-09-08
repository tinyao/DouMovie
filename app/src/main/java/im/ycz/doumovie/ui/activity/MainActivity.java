package im.ycz.doumovie.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import im.ycz.doumovie.R;
import im.ycz.doumovie.api.MovieType;
import im.ycz.doumovie.ui.fragment.BlankFragment;
import im.ycz.doumovie.ui.fragment.MovieListFragment;


public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        setupViewPager();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager() {
        MFragmentAdater adapter = new MFragmentAdater(getFragmentManager());
        adapter.addFragment(MovieListFragment.newInstance(MovieType.INTHEATRE), "正在上映");
        adapter.addFragment(MovieListFragment.newInstance(MovieType.COMING), "即将上映");
        adapter.addFragment(MovieListFragment.newInstance(MovieType.WEEKLY), "口碑榜");
        adapter.addFragment(MovieListFragment.newInstance(MovieType.US), "北美票房榜");
        adapter.addFragment(MovieListFragment.newInstance(MovieType.NEWSHOW), "新片榜");
        adapter.addFragment(MovieListFragment.newInstance(MovieType.TOP250), "Top250");
        mViewPager.setOffscreenPageLimit(6);
        mViewPager.setAdapter(adapter);
    }

    @OnClick(R.id.toolbar) public void onToolbarClicked() {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
       super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MFragmentAdater extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList();
        private final List<String> titles = new ArrayList();

        public MFragmentAdater(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            titles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public String getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
