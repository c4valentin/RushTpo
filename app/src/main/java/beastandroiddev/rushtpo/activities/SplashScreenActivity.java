package beastandroiddev.rushtpo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import beastandroiddev.rushtpo.utils.Constants;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_SHARED_PREFERANCE,
                MODE_PRIVATE);

        String campusName = sharedPreferences.getString(Constants.USER_CAMPUS,"");

        if (campusName.isEmpty()){
            Intent intent = new Intent(this, SwitchCampusActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            finish();

        } else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            finish();
        }
    }
}
