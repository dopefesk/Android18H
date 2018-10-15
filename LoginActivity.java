package no.hiof.ahmedak.papervault;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.btnNewAccount).setOnClickListener(this);
    }

    // Click for New Account, takes user to Sign Up activity
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnNewAccount:

                startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}
