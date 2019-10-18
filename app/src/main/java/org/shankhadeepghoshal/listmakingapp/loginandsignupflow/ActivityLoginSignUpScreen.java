package org.shankhadeepghoshal.listmakingapp.loginandsignupflow;

import android.os.Bundle;
import android.widget.Button;

import org.shankhadeepghoshal.listmakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

public class ActivityLoginSignUpScreen extends DaggerAppCompatActivity {

    private Unbinder unbinder;

    @BindView(value = R.id.BtnLogin) Button loginButton;
    @BindView(value = R.id.BtnSignUp) Button singUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up_screen);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

    private void authenticateUser(String userIdDetail, String password) {
        // Todo : Write authentication logic


    }
}
