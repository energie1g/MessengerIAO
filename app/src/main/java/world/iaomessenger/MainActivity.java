package world.iaomessenger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FirebaseAuth user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // this line has been added from gitlab!!
        // teammate changes
        Log.d(TAG, "onCreate: Started.");

        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableItemShiftingMode(false);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_chat:
                        break;

                    case R.id.ic_groups:
                        Intent groupsActivityIntent = new Intent(MainActivity.this, GroupsActivity.class);
                        startActivity(groupsActivityIntent);
                        break;

                    case R.id.ic_contacts:
                        Intent contactsActivityIntent = new Intent(MainActivity.this, ContactsActivity.class);
                        startActivity(contactsActivityIntent);
                        break;

                    case R.id.ic_settings:
                        Intent settingsActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(settingsActivityIntent);
                        break;
                }


                return false;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(user == null) {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

}
