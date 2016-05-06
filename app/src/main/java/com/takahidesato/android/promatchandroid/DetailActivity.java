package com.takahidesato.android.promatchandroid;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

/**
 * Created by tsato on 4/15/16.
 */
public class DetailActivity extends AppCompatActivity {
    public static final String FRAGMENT_KEY = "fragment_key";
    public static final int FRAGMENT_KEY_SUCCESS = 0;
    public static final int FRAGMENT_KEY_TWEETS = 1;

    private int key;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        key = getIntent().getExtras().getInt(FRAGMENT_KEY);

        Fragment fragment = null;
        Class fragmentClass = SuccessStoriesDetailFragment.class;
        switch(key) {
            case 0:
                fragmentClass = SuccessStoriesDetailFragment.class;
                break;
            case 1:
                fragmentClass = TweetsDetailFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(android.R.id.content, fragment).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(FRAGMENT_KEY, key);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        key = savedInstanceState.getInt(FRAGMENT_KEY);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
//            Log.d("test", "home button pushed");
//            setResult(RESULT_OK);
            super.onBackPressed();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
