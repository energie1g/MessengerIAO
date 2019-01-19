package world.iaomessenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth user;
    private EditText loginEmail, loginPassword;
    private Button loginBtn;
    private TextView needRegister, forgetPassword;
    private String lEmail, lPassword;

    private FirebaseAuth currAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        currAuth = FirebaseAuth.getInstance();

        getFieldByIds();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        needRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectUserToRegisterActivity();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectUserToForgetPasswordActivity();
            }
        });

        loginPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    loginBtn.performClick();
                    return true;
                }

                return false;
            }
        });
    }

    private void logIn() {
        lEmail = loginEmail.getText().toString();
        lPassword = loginPassword.getText().toString();

        boolean fieldsAreEmpty = checkIfLoginFieldsAreEmpty(lEmail, lPassword);

        if(!fieldsAreEmpty) {
            currAuth.signInWithEmailAndPassword(lEmail, lPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        redirectUserToMainActivity();
                    }
                }
            });
        }
    }

    private boolean checkIfLoginFieldsAreEmpty(String email, String pwd) {

        if(TextUtils.isEmpty(email)) {
            loginEmail.setError("Email is required");
            return true;
        }

        if(TextUtils.isEmpty(pwd)) {
            loginPassword.setError("Password is required");
            return true;
        }

        return false;
    }

    private void getFieldByIds() {
        needRegister = (TextView) findViewById(R.id.need_register_2nd_half);
        loginEmail = (EditText) findViewById(R.id.login_email);
        loginPassword = (EditText) findViewById(R.id.login_password);
        forgetPassword = (TextView) findViewById(R.id.forget_password);
        loginBtn = (Button) findViewById(R.id.login_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(user != null) {
            redirectUserToMainActivity();
        }
    }

    private void redirectUserToMainActivity() {
        Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    private void redirectUserToRegisterActivity() {
        Intent registerActivityIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerActivityIntent);
    }

    private void redirectUserToForgetPasswordActivity() {
        Intent forgetPasswordActivity = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(forgetPasswordActivity);
    }
}
