package com.example.mukulsharma.helping_sapiens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText e1,e2;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=findViewById(R.id.email);
        e2=findViewById(R.id.password);
        login = findViewById(R.id.signin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check();
                String email=e1.getText().toString();
                Intent n=new Intent(getApplicationContext(),Navigation_drawer_activity.class);
                n.putExtra("Email",email);

                startActivity(n);

            }
        });

    }

    public void check()
    {
        String email = e1.getText().toString();
        String password = e2.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(login.this, "success",
                                    Toast.LENGTH_LONG).show();


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(login.this, "fail",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
}
