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

public class Driver_Signup extends AppCompatActivity implements View.OnClickListener{
    private EditText driver_id;
    private EditText user;
    private EditText password;
    private EditText retypepassword;
    private Button signup;
    private TextView textView;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Driver SignUp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_driver__signup);

        driver_id=findViewById(R.id.driver_id);
        user=findViewById(R.id.driver_email);
        password=findViewById(R.id.driver_password);
        retypepassword=findViewById(R.id.driver_retype_password);
        signup=findViewById(R.id.driver_signup_btn);
        textView=findViewById(R.id.driver_signup_text_View_id);
        progressBar=findViewById(R.id.driver_progressup_id);


        textView.setOnClickListener(this);
        signup.setOnClickListener(this);

    }




    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.driver_signup_btn:
                userregister();
                break;
            case R.id.driver_signup_text_View_id:
                Intent intent=new Intent(getApplicationContext(), Driver_Login.class);
                //JAVA CLASS ER NAME DITE HOY
                startActivity(intent);
                break;
        }

    }

    private void userregister()
    {
        String driverid=driver_id.getText().toString();
        String email=user.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String repass=retypepassword.getText().toString().trim();

        // FOR DRIVER ID VALIDITY CHECKING
        if (driverid.isEmpty())
        {
            driver_id.setError("Enter Driver Id");
            driver_id.requestFocus();
            return;
        }
        if ((!driverid.equals("10001"))&&(!driverid.equals("10002"))&&(!driverid.equals("10003"))&&(!driverid.equals("10004")))
        {
            driver_id.setError("Enter Correct Driver Id");
            driver_id.requestFocus();
            return;
        }
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
                            if (task.isSuccessful())
                            {
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