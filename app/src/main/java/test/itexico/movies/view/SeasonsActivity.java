package test.itexico.movies.view;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import test.itexico.movies.R;
import test.itexico.movies.presenter.SeasonsListPresenterImpl;

public class SeasonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        FrameLayout container = findViewById(R.id.main_container);

        if (container != null) {
            if (savedInstanceState != null) {
                return;
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main_container, new SeasonsFragment());
            fragmentTransaction.commit();
        }
    }
}
