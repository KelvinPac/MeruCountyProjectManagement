package com.homeautogroup.meru.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;
import com.homeautogroup.meru.R;
import com.homeautogroup.meru.adapters.ProjectsAdapter;
import com.homeautogroup.meru.api.APIService;
import com.homeautogroup.meru.api.APIUrl;
import com.homeautogroup.meru.models.Departments;
import com.homeautogroup.meru.models.Photos;
import com.homeautogroup.meru.models.Project;
import com.homeautogroup.meru.models.Projects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectViewActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    public static final String PROJECT_EXTRA = "project_extra_ss";
    private Projects projects;
    //private Project projectData;
    private SliderLayout mDemoSlider;
    private TextView textViewPName,textViewPDepartment,textViewPDesc,textViewPContractor,textViewStatus,textViewPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        Intent intent = getIntent();

        projects = (Projects) intent.getSerializableExtra(PROJECT_EXTRA);
        getSupportActionBar().setTitle(projects.getProject_name());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contact us under construction", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //find views
        textViewPName = findViewById(R.id.textViewPName);
        textViewPDepartment = findViewById(R.id.textViewPDepartment);
        textViewPDesc = findViewById(R.id.textViewPDesc);
        textViewPContractor = findViewById(R.id.textViewPContractor);
        textViewStatus = findViewById(R.id.textViewStatus);
        textViewPrice = findViewById(R.id.textViewPrice);

        mDemoSlider = findViewById(R.id.slider);


        textViewPName.setText(projects.getProject_name());
        textViewPDepartment.setText(projects.getProject_category());
        textViewPDesc.setText(projects.getProject_description());
        textViewPContractor.setText("Contractor : "+projects.getContractor());
        String completion = projects.getCompletion();
        if (completion.equals("100")){
            textViewStatus.setText("Status: Completed");
        }else {
            textViewStatus.setText("Status: "+completion+"%"+" complete");
        }
        //textViewStatus.setText(projects.getCompletion());
        textViewPrice.setText("Budget is Ksh: "+projects.getBudget());


        fetchProjects(projects.getProject_id(),projects.getProject_name());

      /*  ArrayList<String> listUrl = new ArrayList<>();
        ArrayList<String> listName = new ArrayList<>();

        listUrl.add("https://www.revive-adserver.com/media/GitHub.jpg");
        listName.add("JPG - Github");

        listUrl.add("https://tctechcrunch2011.files.wordpress.com/2017/02/android-studio-logo.png");
        listName.add("PNG - Android Studio");

        listUrl.add("http://static.tumblr.com/7650edd3fb8f7f2287d79a67b5fec211/3mg2skq/3bdn278j2/tumblr_static_idk_what.gif");
        listName.add("GIF - Disney");

        listUrl.add("http://www.gstatic.com/webp/gallery/1.webp");
        listName.add("WEBP - Mountain");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        //.placeholder(R.drawable.placeholder)
        //.error(R.drawable.placeholder);

        for (int i = 0; i < listUrl.size(); i++) {
            TextSliderView sliderView = new TextSliderView(this);
            // if you want show image only / without description text use DefaultSliderView instead

            // initialize SliderLayout
            sliderView.image(listUrl.get(i))
                    .description(listName.get(i))
                    .setRequestOption(requestOptions)
                    .setBackgroundColor(Color.WHITE)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(this);

            //add your extra information
            sliderView.bundle(new Bundle());
            sliderView.getBundle().putString("extra", listName.get(i));
            mDemoSlider.addSlider(sliderView);
        }*/

        // set Slider Transition Animation
        // mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);

        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }


    /*
     * Fetch projects retrofit*/
    private void fetchProjects(String id, final String name) {
       // showProgress();

        String url = APIUrl.getServerUrl(this);
        if (url==null){
            startActivity(new Intent(this,SettingsActivity.class));
            finish();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<Project>> call = service.getSingleProjects(id);

        call.enqueue(new Callback<List<Project>>() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onResponse(@NonNull Call<List<Project>> call, @NonNull Response<List<Project>> response) {
                //hideProgress();
                /*
                 * All successful responses from crypto compare have a http code 200
                 * Check if http code is 200 then continue
                 * */
                if (response.code() == 200) {


                    try {
                        List<Project> projectData = response.body();
                        //projectData.getPhotos();
                        Photos[] photos = projectData.get(0).getPhotos();

                        if(photos.length==0){
                            Toast.makeText(ProjectViewActivity.this, "No gallery here yet", Toast.LENGTH_LONG).show();
                        }

                        processGallery(photos,name);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        Toast.makeText(ProjectViewActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(ProjectViewActivity.this, "Some error occurred while fetching project", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Project>> call, @NonNull Throwable t) {
               // hideProgress();
                Toast.makeText(ProjectViewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void processGallery(Photos[] photos,String name) {
        ArrayList<String> listUrl = new ArrayList<>();
        ArrayList<String> listName = new ArrayList<>();

        String url = APIUrl.getServerUrl(this);
        if (url==null){
            startActivity(new Intent(this,SettingsActivity.class));
            finish();
            return;
        }

        for (Photos photosL:photos){
            listUrl.add(url+photosL.getPhoto_path());
            listName.add(name);
        }

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();

        for (int i = 0; i < listUrl.size(); i++) {
            TextSliderView sliderView = new TextSliderView(this);
            // if you want show image only / without description text use DefaultSliderView instead

            // initialize SliderLayout
            sliderView.image(listUrl.get(i))
                    .description(listName.get(i))
                    .setRequestOption(requestOptions)
                    .setBackgroundColor(Color.WHITE)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(this);

            //add your extra information
            sliderView.bundle(new Bundle());
            sliderView.getBundle().putString("extra", listName.get(i));
            mDemoSlider.addSlider(sliderView);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
