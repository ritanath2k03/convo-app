package com.example.chatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chatsapp.Models.Users;
import com.example.chatsapp.databinding.ActivitySignInBinding;
import com.example.chatsapp.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding activitySignupBinding;
    FirebaseAuth auth;
    Button button;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(activitySignupBinding.getRoot());
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
    database=FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Just a moment....");

        progressDialog.setMessage("We are Creating your Account");

        activitySignupBinding.UserSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email="",password="";

                email=activitySignupBinding.Email.getText().toString();
                password=activitySignupBinding.UserPassword.getText().toString();
                if((email!="")&&(password!="")){
                    progressDialog.show();
                    Log.d("Clicked","in if");
                    auth.createUserWithEmailAndPassword(activitySignupBinding.Email.getText().toString(),activitySignupBinding.UserPassword.getText().toString()).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if(task.isSuccessful()){
                                        Users user= new Users(activitySignupBinding.UserName.getText().toString(),
                                                activitySignupBinding.Email.getText().toString(),activitySignupBinding.UserPassword.getText().toString());
                                        String id=task.getResult().getUser().getUid();
                                        Log.d("id",id);
                                       database.getReference().child("Users").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               startActivity(new Intent(SignUpActivity.this,Chat_Fragment.class));
                                               Log.d("data","data added in firebase");
                                           }
                                       });
                                        Toast.makeText(SignUpActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

                                    }
                                    else{
                                        Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else
                {
                    Log.d("Clicked","working");
                    Toast.makeText(SignUpActivity.this, "Enter Credentials Properly", Toast.LENGTH_SHORT).show();
                }

            }
        });

        activitySignupBinding.AlreadyAccount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
    }
});


    }
}