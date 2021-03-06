package world.iaomessenger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private TextView needLogin;
    private EditText registerName, registerLastName, registerEmail, registerPassword, registerRepeatPassword;
    private Button registerBtn;
    private String rName, rLastName, rEmail, rPassword, rRepeatPassword;

    private FirebaseAuth currAuth;
    private DatabaseReference databaseReference;

    ProgressDialog progressDialog;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.d(TAG, "onCreate: STARTED");

        currAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        getFieldByIds();

        needLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectUserToLoginActivity();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createANewAccount();
            }
        });
    }

    private void getFieldByIds() {
        needLogin = (TextView) findViewById(R.id.need_login_2nd_half);
        registerName = (EditText) findViewById(R.id.register_name);
        registerLastName = (EditText) findViewById(R.id.register_last_name);
        registerEmail = (EditText) findViewById(R.id.register_email);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerRepeatPassword = (EditText) findViewById(R.id.register_repeat_password);
        registerBtn = (Button) findViewById(R.id.register_btn);
        progressDialog = new ProgressDialog(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutRegister);
    }

    private void createANewAccount() {

        rName = registerName.getText().toString();
        rLastName = registerLastName.getText().toString();
        rEmail = registerEmail.getText().toString();
        rPassword = registerPassword.getText().toString();
        rRepeatPassword = registerRepeatPassword.getText().toString();



        if(TextUtils.isEmpty(rName)
                || TextUtils.isEmpty(rLastName)
                || TextUtils.isEmpty(rEmail)
                || TextUtils.isEmpty(rPassword)
                || TextUtils.isEmpty(rRepeatPassword)) {

            Toast.makeText(RegisterActivity.this, "All fields are REQUIRED", Toast.LENGTH_LONG).show();

        } else {
            progressDialog.setTitle("Creating New Account");
            progressDialog.setMessage("Please wait..");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();

            currAuth.createUserWithEmailAndPassword(rEmail, rPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {

                        String currentUserID = currAuth.getUid();
                        databaseReference.child("Users").child(currentUserID).setValue("");
                        redirectUserToMainActivity();
                        Snackbar.make(coordinatorLayout, "The account has been created successfully", Snackbar.LENGTH_LONG).show();
                        progressDialog.dismiss();

                    } else {

                        Snackbar snackbarLoginUnsuccessful = Snackbar.make(coordinatorLayout, task.getResult().toString(), Snackbar.LENGTH_LONG);
                        snackbarLoginUnsuccessful.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        snackbarLoginUnsuccessful.setActionTextColor(Color.WHITE);
                        snackbarLoginUnsuccessful.show();
                        progressDialog.dismiss();

                    }
                }
            });
        }
    }

    private void redirectUserToLoginActivity() {
        Intent loginActivityIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginActivityIntent);
    }

    private void redirectUserToMainActivity() {
        Intent mainActivityIntent = new Intent(RegisterActivity.this, MainActivity.class);
        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivityIntent);
        finish();
    }
}
