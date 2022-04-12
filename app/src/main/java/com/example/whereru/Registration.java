package com.example.whereru;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtm;

    TextView mLoginBtn;
    FirebaseAuth fAuth;
    Switch driverSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFullName = findViewById(R.id.rfullNameText_id);
        mEmail = findViewById(R.id.remailText_id);
        mPassword = findViewById(R.id.rpasswordText_id);
        mPhone = findViewById(R.id.rphoneText_id);
        mRegisterBtm = findViewById(R.id.rregisterButton_id);
        mLoginBtn = findViewById(R.id.ralreadyLoginText_id);
        driverSwitch = findViewById(R.id.switchDriver);

        fAuth = FirebaseAuth.getInstance();

//         auto login
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegisterBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                boolean isDriver = driverSwitch.isChecked();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Your password length too short.");
                    return;
                }

                // registring users in firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            DBUsers dbUsers = new DBUsers();
                            User user = new User(mFullName.getText().toString(), email, mPhone.getText().toString(), fAuth.getUid(), isDriver);
                            dbUsers.add(user).addOnSuccessListener(sucess -> {
                                Toast.makeText( Registration.this, "Db success", Toast.LENGTH_LONG).show();
                            }).addOnFailureListener(failer -> {
                                Toast.makeText( Registration.this, "Db FAILED", Toast.LENGTH_LONG).show();
                            });

                            finish();
                        } else {
                            Toast.makeText(Registration.this, "Error !! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}