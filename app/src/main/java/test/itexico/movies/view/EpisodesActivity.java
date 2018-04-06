package test.itexico.movies.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.itexico.movies.R;

public class EpisodesActivity extends AppCompatActivity {

    @BindView(R.id.main_container) FrameLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_container);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        if (container != null) {
            if (savedInstanceState != null) {
                return;
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            EpisodesFragment episodesFragment = new EpisodesFragment();
            episodesFragment.setArguments(extras);
            fragmentTransaction.add(R.id.main_container, episodesFragment);
            fragmentTransaction.commit();
        }
    }

}
