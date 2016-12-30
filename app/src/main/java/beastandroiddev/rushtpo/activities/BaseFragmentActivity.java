package beastandroiddev.rushtpo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import beastandroiddev.rushtpo.R;

public abstract class BaseFragmentActivity extends AppCompatActivity {

     abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_base);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_fragment_base_container);

        if (fragment ==null){
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_fragment_base_container,fragment)
                    .commit();
        }
    }
}
