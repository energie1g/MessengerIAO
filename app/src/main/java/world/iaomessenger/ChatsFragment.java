package world.iaomessenger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ChatsFragment extends Fragment {

    private static final String TAG = "ChatsFragment";
    private FirebaseAuth user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        Log.d(TAG, "onCreateView: Started.");

        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) view.findViewById(R.id.bottom_navigation_view_chats);
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
                        ((MainActivity) getActivity()).setViewPager(0);
                        break;

                    case R.id.ic_groups:
                        ((MainActivity) getActivity()).setViewPager(1);
                        break;

                    case R.id.ic_contacts:
                        ((MainActivity) getActivity()).setViewPager(2);
                        break;
                }
                return false;
            }
        });

        return view;
    }
}