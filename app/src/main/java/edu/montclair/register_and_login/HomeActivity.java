package edu.montclair.register_and_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    int duration = Toast.LENGTH_SHORT; //length of toast
    Context context; //needed for toast and color changes
    CharSequence toast_msg; //toast message
    Toast toast; //variable for toast

    EditText email, password; //takes input from user
    Button button; //allows user to confirm input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //checks if registration was previously done
        Intent intent = getIntent();
        intent.getExtras();

        //outputs toast if registration was successful
        if (intent.hasExtra("reg_success")) {
            context = getApplicationContext();
            toast_msg = "Registration successful!";

            toast = Toast.makeText(context, toast_msg, duration);
            toast.show();
        }

        //views are found through their ids
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        button = (Button)findViewById(R.id.li_button);

        //sets up event handler for button clicks
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //outputs toast with error message if fields are empty
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    context = getApplicationContext();
                    toast_msg = "Fields cannot be left empty.";

                    toast = Toast.makeText(context, toast_msg, duration);
                    toast.show();
                }

                //logs user in if correct credentials are entered
                else if (email.getText().toString().equals("test@email.com") && password.getText().toString().equals("pass123")) {
                    context = getApplicationContext();
                    toast_msg = "Login successful!";

                    toast = Toast.makeText(context, toast_msg, duration);
                    toast.show();
                }

                //informs user credentials are incorrect
                else {
                    context = getApplicationContext();
                    toast_msg = "Invalid login info.";

                    toast = Toast.makeText(context, toast_msg, duration);
                    toast.show();
                }
            }
        } );
    }

    //redirects to registration screen
    public void openReg(View view) {
        Intent reg = new Intent(HomeActivity.this, RegActivity.class);
        startActivity(reg);
    }
}