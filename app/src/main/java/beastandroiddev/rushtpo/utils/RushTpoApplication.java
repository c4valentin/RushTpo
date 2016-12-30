package beastandroiddev.rushtpo.utils;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class RushTpoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
