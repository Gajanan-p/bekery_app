package com.ssinfomate.bakeryapp;


import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import com.ssinfomate.bakeryapp.utils.AppPreference;
import com.ssinfomate.bakeryapp.webservices.user.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
  //  private ActivityMainBinding binding;
    UserModel userModel;
    NavController navController;
    NavigationView navigationView;
    private BottomNavigationView bottomNavView;
    private DrawerLayout drawer;
    private static final float END_SCALE = 0.85f;
    private CoordinatorLayout contentView;
    TextView textViewUserID,textViewUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        contentView = findViewById(R.id.content_view);
        textViewUserID = header.findViewById(R.id.text_user_id);
        textViewUserName =header.findViewById(R.id.textView_user_name);
        bottomNavView = findViewById(R.id.bottom_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_login,
                R.id.nav_contact,
                R.id.nav_about,
                R.id.bottom_saleReturn,
                R.id.bottom_returnReport,
                R.id.bottom_home)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavView, navController);

     //   animateNavigationDrawer();
    }
    private void animateNavigationDrawer() {
//        drawerLayout.setScrimColor(getResources().getColor(R.color.text_brown));
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForLogin();
        getUserData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    void checkForLogin(){
        userModel= AppPreference.getLoginDataPreferences(getApplicationContext());
        if(userModel!=null){
            if (userModel.getAcmasterid()!=null) {
                showMenuItem();
            }
            else {
                navController.navigate(R.id.nav_login);
                hideMenuItem();
            }
            }else{
                navController.navigate(R.id.nav_login);
                hideMenuItem();
            }
    }

    private void hideMenuItem() {
        Menu nav_Menu = bottomNavView.getMenu();
        nav_Menu.findItem(R.id.bottom_saleReturn).setVisible(false);
        nav_Menu.findItem(R.id.bottom_home).setVisible(false);
        nav_Menu.findItem(R.id.bottom_returnReport).setVisible(false);

    }

    void showMenuItem(){
        Menu nav_Menu = bottomNavView.getMenu();
        nav_Menu.findItem(R.id.bottom_saleReturn).setVisible(true);
        nav_Menu.findItem(R.id.bottom_home).setVisible(true);
        nav_Menu.findItem(R.id.bottom_returnReport).setVisible(true);
       }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    void getUserData(){

        UserModel userModel = AppPreference.getLoginDataPreferences(getApplicationContext());
        if (userModel!=null) {
            textViewUserID.setText(userModel.getAcntcode());
            textViewUserName.setText(userModel.getAcntdesc());
        }
    }
}