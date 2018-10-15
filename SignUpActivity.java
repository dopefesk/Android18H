package no.hiof.ahmedak.papervault;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    // Fields for email and password
    EditText fieldRegisterEmail, fieldRegisterPassword;

    // Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        fieldRegisterEmail = (EditText) findViewById(R.id.fieldRegisterEmail);
        fieldRegisterPassword = (EditText) findViewById(R.id.fieldRegisterPassword);

        mAuth = FirebaseAuth.getInstance();
    }


    private void registerUser() {
        String user = fieldRegisterEmail.getText().toString().trim();
        String pass = fieldRegisterPassword.getText().toString().trim();

        if(user.isEmpty()) {
            fieldRegisterEmail.setError("You need to enter your email.");
            fieldRegisterEmail.requestFocus();
            return;
        }

        if(Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            fieldRegisterEmail.setError("A valid email address is required.");
        }

        if(pass.isEmpty()) {
            fieldRegisterPassword.setError("You need to enter a password.");
            fieldRegisterPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User Registration Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignup:
                registerUser();
                break;

            case R.id.btnBack:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
