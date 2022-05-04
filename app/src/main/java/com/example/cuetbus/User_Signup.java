package com.example.cuetbus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class User_Signup extends AppCompatActivity implements View.OnClickListener {
    private EditText user;
    private EditText password;
    private EditText retypepassword;
    private Button signup;
    private TextView textView;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("User SignUp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_user_signup);
        user=findViewById(R.id.userid2);
        password=findViewById(R.id.passid2);
        signup=findViewById(R.id.signupid);
        textView=findViewById(R.id.signup_text_View_id);
        progressBar=findViewById(R.id.progressupid);
        retypepassword=findViewById(R.id.re_type_passid);

        textView.setOnClickListener(this);
        signup.setOnClickListener(this);
    }




    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.signupid:

                userregister();
                break;
            case R.id.signup_text_View_id:
                Intent intent=new Intent(getApplicationContext(), User_Login.class);
                //JAVA CLASS ER NAME DITE HOY
                startActivity(intent);
                break;
        }
    }

    private void userregister() {
        //trim() use for no space
        String email=user.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String repass=retypepassword.getText().toString().trim();

        //FOR EMAIL VALIDITY CHECKING
        if(email.isEmpty())
        {
            user.setError("Enter an email address");
            user.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            user.setError("Enter a valid email address");
            user.requestFocus();
            return;
        }

        //checking the validity of the password
        if(pass.isEmpty())
        {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            password.setError("Minimum length of Password should be 6");
            password.requestFocus();
        }

        if(!pass.equals(repass))
        {
            Toast.makeText(getApplicationContext(), "Your password is not correct", Toast.LENGTH_SHORT).show();
        }
        //CIRCLE WILL ROUND
        else
        {
            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                finish();
                                Toast.makeText(getApplicationContext(), "Registration Is Successful", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                    Toast.makeText(getApplicationContext(), "User All Ready Registered", Toast.LENGTH_SHORT).show();
                                else {
                                    Toast.makeText(getApplicationContext(), "Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }


                        }
                    });
        }
    }
}
