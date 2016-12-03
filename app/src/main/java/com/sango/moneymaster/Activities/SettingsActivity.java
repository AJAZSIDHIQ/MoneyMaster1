package com.sango.moneymaster.Activities;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.sango.moneymaster.Adapters.DatabaseAdapter;
import com.sango.moneymaster.Adapters.SpinnerCurrAdapter;
import com.sango.moneymaster.Adapters.SpinnerLangAdapter;
import com.sango.moneymaster.Lists.ItemData;
import com.sango.moneymaster.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;


public class SettingsActivity extends ActionBarActivity {
    private DatabaseAdapter mDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDbAdapter = new DatabaseAdapter(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        ArrayList<ItemData> languages = databaseAdapter.getLocales("lang");
        ArrayList<ItemData> currencies = databaseAdapter.getLocales("currency");

        Spinner spinner_lang = (Spinner) findViewById(R.id.settingsLanguageSpinner);
        ArrayAdapter adapter_lang = new SpinnerLangAdapter(this, R.layout.spinner_layout_no_tint, R.id.spinnerText, languages);
        adapter_lang.setDropDownViewResource(R.layout.spinner_layout_no_tint);
        spinner_lang.setAdapter(adapter_lang);
        spinner_lang.setSelection(mDbAdapter.getActiveLang("s"));

        Spinner spinner_cur = (Spinner) findViewById(R.id.settingsCurrencySpinner);
        ArrayAdapter adapter_cur = new SpinnerCurrAdapter(this, R.layout.spinner_layout, R.id.spinnerText, currencies);
        adapter_cur.setDropDownViewResource(R.layout.spinner_layout);
        spinner_cur.setAdapter(adapter_cur);
        spinner_cur.setSelection(mDbAdapter.getActiveCurr("s"));

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_outcome, menu);
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
            return true;
        }

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    public void back(View v) {
        Spinner language = (Spinner) findViewById(R.id.settingsLanguageSpinner);
        Spinner currency = (Spinner) findViewById(R.id.settingsCurrencySpinner);

        mDbAdapter.setLangAsActive(language.getSelectedView().findViewById(R.id.spinnerImage).getContentDescription() + "");
        mDbAdapter.setCurrAsActive(currency.getSelectedView().findViewById(R.id.spinnerImage).getContentDescription() + "");

        System.exit(0);
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
//        Intent mStartActivity = new Intent(this, MainActivity.class);
//        int mPendingIntentId = 123456;
//        PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
//        this.finish();
    }
}
