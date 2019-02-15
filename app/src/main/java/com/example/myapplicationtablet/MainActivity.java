package com.example.myapplicationtablet;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @SerializedName("api-key")
        @Expose
        protected final String api_key = "2kMwhWXa0xd7VRqwQrKwU5AjtBzOA1gr";
        ResApi resObj;
        Integer k = 0;
        boolean f1 = false;
        String[] titlesB ;
        String[] pathsB ;
        String[] titles ;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final Context mContext = this.getContext();
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            String typeArticle = null;//"viewed";//shared,viewed,emailed
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    typeArticle = "emailed";
                    break;
                case 2:
                    typeArticle = "shared";
                    break;
                case 3:
                    typeArticle = "viewed";
                    break;
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER) < 4) {
                NetworkService.getInstance()
                        .getJSONApi()
                        .getMostEmailedArticle(typeArticle, api_key)
                        .enqueue(new Callback<ResApi>() {
                            @Override
                            public void onResponse(@NonNull Call<ResApi> call, @NonNull Response<ResApi> response) {
                                resObj = response.body();
                                assert resObj != null;
                                k = resObj.getResults().length;
                                if (k > 0) {
                                    f1 = true;
                                    titles = new String[k];
                                    for (Integer i = 0; i < k; i++) {
                                        titles[i] = resObj.getResults()[i].getTitle();
                                    }
                                }
                                //     получаем элемент ListView
                                ListView articlesList = (ListView) rootView.findViewById(R.id.articlesList);

                                //       создаем адаптер
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                        android.R.layout.simple_list_item_1, titles);
//         устанавливаем для списка адаптер
                                articlesList.setAdapter(adapter);

                                f1 = false;
                                // добвляем для списка слушатель
                                articlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                                        // по позиции получаем выбранный элемент
                                        //  String selectedItem = countries[position];
                                        // открытие новой активити и передача статьи

                                        Article article = resObj.getResults()[position];
                                        Intent intent = new Intent(mContext, ViewArticles.class);
// передача объекта с ключом "hello" и значением "Hello World"
                                        intent.putExtra(Article.class.getSimpleName(), article);
// запуск SecondActivity
                                        startActivity(intent);

                                    }
                                });

                            }
                            @Override
                            public void onFailure(@NonNull Call<ResApi> call, @NonNull Throwable t) {
                            t.printStackTrace();
                            }
                        });
            }
      /*    else{ //в данном случае пришлоась отключить, так как вылетает ошибка ро открытию, скорее всего плохо прописано создание
                DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
                // открываем подключение
                SQLiteDatabase db = databaseHelper.getReadableDatabase();
                databaseHelper.onCreate(db);

                Cursor userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
                int b = userCursor.getColumnCount();
               titlesB = new String[b];
               pathsB = new String[b];
               final String[] paths = new String[k];

                for( int j = 0;j<b;j++){
                    titlesB[j] = userCursor.getString(j);
                }
                userCursor =  db.rawQuery("select * from "+ DatabaseHelper.path, null);

                for( int j = 0;j<b;j++){
                    pathsB[j] = userCursor.getString(j);
                }
                //     получаем элемент ListView
                ListView articlesList = (ListView) rootView.findViewById(R.id.articlesList);

                //       создаем адаптер
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                        android.R.layout.simple_list_item_1, titlesB);
//         устанавливаем для списка адаптер
                articlesList.setAdapter(adapter);

                f1 = false;
                // добвляем для списка слушатель
                articlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        // по позиции получаем выбранный элемент
                        //  String selectedItem = countries[position];
                        // открытие новой активити и передача статьи

                        Article article = new Article();
                        article.setUri(pathsB[position]);
                        article.setTitle(titlesB[position]);
                        Intent intent = new Intent(mContext, ViewArticles.class);
// передача объекта с ключом "hello" и значением "Hello World"
                        intent.putExtra(ResApi.class.getSimpleName(), article);
// запуск SecondActivity
                        startActivity(intent);

                    }
                });


            }*/
                return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }
}
