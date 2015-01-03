package com.baraccasoftware.baraccalauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.baraccasoftware.baraccalauncher.appobject.AppContent;

import java.util.List;


public class MainActivity extends Activity  implements AppListFragment.OnAppChooseListener,HomeFragment.OnHomeFragmentInteractionListener, SlidingPaneLayout.PanelSlideListener {

    private AppListFragment appListFragment;
    private PackageManager packageManager;
    private SlidingPaneLayout mSlidingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        packageManager = getPackageManager();
        appListFragment = (AppListFragment) getFragmentManager().findFragmentById(R.id.list_pane);


        mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.slide_pane);

        mSlidingLayout.setPanelSlideListener(this);
        mSlidingLayout.setSliderFadeColor(getResources().getColor(R.color.trasparent_background));
        //avvio ricerca app
        new LoadAppTask().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
 //       getMenuInflater().inflate(R.menu.menu_main, menu);

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

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(appListFragment.isVisible()){
            appListFragment.addItems();
        }
    }

    @Override
    public void OnAppChoose(AppContent.AppItem item) {

    }

    @Override
    public void onHomeFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        //per ora nulla
    }

    @Override
    public void onPanelOpened(View panel) {
        if(appListFragment.isClear()){
            if(AppContent.ITEMS.isEmpty()){
                new LoadAppTask().execute();
            }else{
                appListFragment.addItems();
            }
        }
    }

    @Override
    public void onPanelClosed(View panel) {

    }

    class LoadAppTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            final Intent mainIntent = new Intent(Intent.ACTION_MAIN,null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            List<ResolveInfo> appInfo = packageManager.queryIntentActivities(mainIntent,0);
            for(ResolveInfo info:appInfo){
                AppContent.addItem(info.activityInfo.packageName,
                        info.loadLabel(packageManager).toString(),
                        info.loadIcon(packageManager));
            }
            return AppContent.ITEMS.size() >0;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            appListFragment.addItems(true);
        }
    }
}
