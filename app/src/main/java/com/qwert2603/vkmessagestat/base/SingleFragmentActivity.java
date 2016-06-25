package com.qwert2603.vkmessagestat.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.qwert2603.vkmessagestat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbar_title_text_view)
    TextView mToolbarTitleTextView;

    @NonNull
    protected abstract BaseFragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(SingleFragmentActivity.this, SingleFragmentActivity.this);
        setSupportActionBar(mToolbar);

        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commitAllowingStateLoss();
        }
    }

    public void setTitle(@NonNull String title) {
        mToolbarTitleTextView.setText(title);
    }
}
