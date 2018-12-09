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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    EditText name,phonenumber,password,email;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.editText1);
        phonenumber = findViewById(R.id.editText2);
        password = findViewById(R.id.editText4);
        email = findViewById(R.id.editText3);

        signup = findViewById(R.id.button4);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                create();


            }
        });

    }


    public void create()
    {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        final String emails= email.getText().toString();
        String passwords = password.getText().toString();
        String names = name.getText().toString();
        String phonenumbers = phonenumber.getText().toString();

        final Userinformation u = new Userinformation(emails,names,phonenumbers);
        try {
            databaseReference.child("User Information").child("adsfascd").setValue(u);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        mAuth.createUserWithEmailAndPassword(emails,passwords)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            String identifier = emails.replace("."," ");
                            Intent i = new Intent(getApplicationContext(),aftersuccess.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}
