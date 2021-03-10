package edu.montclair.register_and_login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegActivity extends AppCompatActivity {

    EditText fname, lname, dob, email, password; //variables to hold necessary views
    Context context; //needed for toast and color changes
    Button button; //needed for setting up handler for button

    int duration = Toast.LENGTH_SHORT; //length of toast
    CharSequence err_msg; //toast message
    Toast toast; //variable for toast

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); //used for validating date of birth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        //views are found by their ids
        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        dob = (EditText)findViewById(R.id.dob);
        email = (EditText)findViewById(R.id.regemail);
        password = (EditText)findViewById(R.id.regpass);
        button = (Button)findViewById(R.id.reg_button);

        //sets up handler for button being clicked
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //checks if any fields are empty
                if (fname.getText().toString().isEmpty() || lname.getText().toString().isEmpty() || dob.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    context = getApplicationContext();
                    err_msg = "Fields cannot be left empty.";

                    //outputs toast informing user fields are empty
                    toast = Toast.makeText(context, err_msg, duration);
                    toast.show();
                }

                //checks if first name is between 2 and 30 characters
                else if (fname.getText().toString().length() < 2 || fname.getText().toString().length() > 30) {
                    context = getApplicationContext();
                    err_msg = "First name must have between 2 and 30 characters.";

                    //outputs toast informing user fields are empty
                    toast = Toast.makeText(context, err_msg, duration);
                    toast.show();
                }

                //checks if last name is between 2 and 30 characters
                else if (lname.getText().toString().length() < 2 || lname.getText().toString().length() > 30) {
                    context = getApplicationContext();
                    err_msg = "Last name must have between 2 and 30 characters.";

                    //outputs toast informing user fields are empty
                    toast = Toast.makeText(context, err_msg, duration);
                    toast.show();
                }

                //check if email address is valid
                else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                    context = getApplicationContext();
                    err_msg = "A valid email address is required.";

                    //outputs toast informing user fields are empty
                    toast = Toast.makeText(context, err_msg, duration);
                    toast.show();
                }

                else if (!isValidDate(dob.getText().toString())) {
                    context = getApplicationContext();
                    err_msg = "A valid date of birth (mm/dd/yyyy) is required.";

                    //outputs toast informing user fields are empty
                    toast = Toast.makeText(context, err_msg, duration);
                    toast.show();
                }

                else {
                    Intent intent = new Intent(RegActivity.this, HomeActivity.class);
                    String reg_success = "success";
                    intent.putExtra("reg_success", reg_success);
                    startActivity(intent);
                }
            }

        });
    }

    //redirects to log in page
    public void openLI(View view) {
        Intent login = new Intent(RegActivity.this, HomeActivity.class);
        startActivity(login);
    }

    //function to check if entered date is valid
    //modified from https://ranjithexpertisers.medium.com/date-validation-in-android-studio-b6db24bd0107
    public boolean isValidDate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        Date testDate = null;

        try
        {
            testDate = sdf.parse(date);
        }

        catch (ParseException e)
        {
            return false;
        }

        if (!sdf.format(testDate).equals(date))
        {
            return false;
        }

        return true;
    }

}