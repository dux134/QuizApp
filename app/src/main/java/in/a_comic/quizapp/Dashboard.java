package in.a_comic.quizapp;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import in.a_comic.quizapp.current_affairs_quiz.CurrentAffairAdapter;
import in.a_comic.quizapp.current_affairs_quiz.CurrentAffairModel;
import in.a_comic.quizapp.exam_list_util.ExamListAdapter;
import in.a_comic.quizapp.exam_list_util.ExamModel;
import in.a_comic.quizapp.quiz_activity_util.QuizActivity;
import me.relex.circleindicator.CircleIndicator;

public class Dashboard extends AppCompatActivity {

    public ViewPager viewpager;
    private ArrayList<String> sliderImagesUrlList;
    private SliderViewPager sliderViewPager;

    private RecyclerView currentAffairsRecyclerView,sscRecyclerView,bankRecyclerView,upscRecyclerView;
    private RecyclerView.Adapter currentAffairsAdapter,sscAdapter,bankAdapter,upscAdapter;
    private static ArrayList<CurrentAffairModel> currentAffairsList = new ArrayList<>();
    private static ArrayList<ExamModel> sscList = new ArrayList<>();
    private static ArrayList<ExamModel> bankList = new ArrayList<>();
    private static ArrayList<ExamModel> upscList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ImageView toolbar_image = findViewById(R.id.toolbar_image);
        toolbar_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        loadImageInSlider();

        currentAffairsRecyclerView = findViewById(R.id.current_affairs_recycler);
        currentAffairsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        currentAffairsAdapter = new CurrentAffairAdapter(new CurrentAffairAdapter.RecyclerClickListener() {
            @Override
            public void onClick(View view, int adapterPosition) {
                startActivity(new Intent(Dashboard.this,QuizActivity.class));
            }
        },currentAffairsList,Dashboard.this);
        currentAffairsRecyclerView.setAdapter(currentAffairsAdapter);
        currentAffairsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        sscRecyclerView = findViewById(R.id.ssc_recycler);
        sscRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        sscAdapter = new ExamListAdapter(new ExamListAdapter.RecyclerClickListener() {
            @Override
            public void onClick(View view, int adapterPosition) {

            }
        },sscList, Dashboard.this);
        sscRecyclerView.setAdapter(sscAdapter);
        sscRecyclerView.setItemAnimator(new DefaultItemAnimator());

        bankRecyclerView = findViewById(R.id.bank_recycler);
        bankRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        bankAdapter = new ExamListAdapter(new ExamListAdapter.RecyclerClickListener() {
            @Override
            public void onClick(View view, int adapterPosition) {

            }
        },bankList, Dashboard.this);
        bankRecyclerView.setAdapter(bankAdapter);
        bankRecyclerView.setItemAnimator(new DefaultItemAnimator());

        upscRecyclerView = findViewById(R.id.upsc_recycler);
        upscRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        upscAdapter = new ExamListAdapter(new ExamListAdapter.RecyclerClickListener() {
            @Override
            public void onClick(View view, int adapterPosition) {

            }
        },upscList, Dashboard.this);
        upscRecyclerView.setAdapter(upscAdapter);
        upscRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private void loadImageInSlider() {
        sliderImagesUrlList = new ArrayList<String>();
        sliderImagesUrlList.add("https://quotes4ever.com/wp-content/uploads/2017/09/luxury-quotes-46.jpg");
        sliderImagesUrlList.add("https://quotes4ever.com/wp-content/uploads/2017/09/luxury-quotes-46.jpg");
        sliderImagesUrlList.add("https://quotes4ever.com/wp-content/uploads/2017/09/luxury-quotes-46.jpg");
        sliderImagesUrlList.add("https://quotes4ever.com/wp-content/uploads/2017/09/luxury-quotes-46.jpg");
        sliderImagesUrlList.add("https://quotes4ever.com/wp-content/uploads/2017/09/luxury-quotes-46.jpg");


        viewpager = (ViewPager) findViewById(R.id.viewpager);
        sliderViewPager = new SliderViewPager(viewpager,Dashboard.this, sliderImagesUrlList);
        viewpager.setAdapter(sliderViewPager);

        CircleIndicator indicator = (CircleIndicator)
                findViewById(R.id.indicator);

        viewpager.setCurrentItem(2);
        indicator.setViewPager(viewpager);

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//}

    static {
        currentAffairsList.add(new CurrentAffairModel("","Quiz 1","01/01/2019","30 Question"));
        currentAffairsList.add(new CurrentAffairModel("","Quiz 1","16/01/2019","30 Question"));
        currentAffairsList.add(new CurrentAffairModel("","Quiz 1","26/01/2019","30 Question"));
        currentAffairsList.add(new CurrentAffairModel("","Quiz 1","14/01/2019","30 Question"));
        currentAffairsList.add(new CurrentAffairModel("","Quiz 1","01/01/2019","30 Question"));

        sscList.add(new ExamModel("https://sscportal.in/sites/default/files/SSC-CGL-LOGO.gif","title 1"));
        sscList.add(new ExamModel("https://sscportal.in/sites/default/files/SSC-CPO-LOGO.jpg","title 1"));
        sscList.add(new ExamModel("https://www.anuprama.com/wp-content/uploads/2017/11/SSC-Junior-Engineer-JE.jpeg","title 1"));
        sscList.add(new ExamModel("https://timesofindia.indiatimes.com/thumb/msid-63067873,imgsize-125025,width-400,resizemode-4/63067873.jpg","title 1"));
        sscList.add(new ExamModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3WMujivVvgiLAKbXrcXt_4gGrya915IzIZTpFqrTK1_5sJKh1","title 1"));
        bankList.add(new ExamModel("https://timesofindia.indiatimes.com/thumb/msid-63186449,imgsize-37929,width-400,resizemode-4/63186449.jpg","title 1"));
        upscList.add(new ExamModel("","title 1"));
    }
}
