package world.iaomessenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth user;
    private EditText loginEmail, loginPassword;
    private Button loginBtn;
    private TextView needRegister, forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getFieldByIds();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                logIn();
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
