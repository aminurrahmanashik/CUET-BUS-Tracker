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

import static com.example.cuetbus.R.id.loginid;
import static com.example.cuetbus.R.id.signin_text_View_id;

public class Driver_Login extends AppCompatActivity implements View.OnClickListener{
    private EditText userid;
    private EditText user;
    private EditText password;
    private Button login;
    private TextView textView;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.setTitle("Driver Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //we use it for back button
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__login);
        mAuth = FirebaseAuth.getInstance();

        userid=findViewById(R.id.driver_login_id);
        user=findViewById(R.id.driver_login_email);
        password=findViewById(R.id.driver_login_password);
        login=findViewById(R.id.driver_login_button);
        textView=findViewById(R.id.driver_signin_text_View_id);
        progressBar=findViewById(R.id.driver_progress_logid);

        textView.setOnClickListener(this);
        login.setOnClickListener(this);
    }



    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.driver_login_button:
                userlogin();
                break;
            case R.id.driver_signin_text_View_id:
                Intent intent=new Intent(getApplicationContext(), Driver_Signup.class);
                startActivity(intent);
                break;
        }
    }

    private void userlogin()
    {
        String driv_id=userid.getText().toString().trim();
        String email=user.getText().toString().trim();
        String pass=password.getText().toString().trim();

        // FOR DRIVER ID VALIDITY CHECKING
        if (driv_id.isEmpty())
        {
            userid.setError("Enter Driver Id");
            userid.requestFocus();
            return;
        }

        if ((!driv_id.equals("10001"))&&(!driv_id.equals("10002"))&&(!driv_id.equals("10003"))&&(!driv_id.equals("10004")))
        {
            userid.setError("Enter Correct Driver Id");
            userid.requestFocus();
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

        //CIRCLE WILL ROUND
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    finish();
                    // AFTER CLICKING THE CLICKING LOGIN BUTTON IT GOES TO ...........
                    Intent intent =new Intent(getApplicationContext(),send_bus_location.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}