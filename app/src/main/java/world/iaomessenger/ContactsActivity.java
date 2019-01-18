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

public class ContactsActivity extends AppCompatActivity {

    private static final String TAG = "ContactsActivity";

    private FirebaseAuth user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Log.d(TAG, "onCreate: Started.");

        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableItemShiftingMode(false);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_chat:
                        Intent mainActivityIntent = new Intent(ContactsActivity.this, MainActivity.class);
                        startActivity(mainActivityIntent);
                        break;

                    case R.id.ic_groups:
                        Intent groupsActivityIntent = new Intent(ContactsActivity.this, GroupsActivity.class);
                        startActivity(groupsActivityIntent);
                        break;

                    case R.id.ic_contacts:
                        break;

                    case R.id.ic_settings:
                        Intent settingsActivityIntent = new Intent(ContactsActivity.this, SettingsActivity.class);
                        startActivity(settingsActivityIntent);
                        break;
                }


                return false;
            }
        });
    }
}