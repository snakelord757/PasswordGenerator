package com.snakelord.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    Button plusButton;
    Button minusButton;
    Button generatePasswordButton;
    CheckBox useDigits;
    CheckBox useUppercase;
    CheckBox useLowercase;
    CheckBox useSpecialSymbols;
    String passwordSymbols = "";
    TextView passwordLengthText;
    TextView password;
    int passwordLength = 6;
    final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        useDigits = findViewById(R.id.use_digits);
        useUppercase = findViewById(R.id.use_uppercase_letters);
        useLowercase = findViewById(R.id.use_lowercase_letters);
        useSpecialSymbols = findViewById(R.id.use_special_symbols);

        passwordLengthText = findViewById(R.id.password_length);
        passwordLengthText.setText(getString(R.string.password_length, passwordLength));

        password = findViewById(R.id.password);

        plusButton = findViewById(R.id.plus);
        minusButton = findViewById(R.id.minus);
        generatePasswordButton = findViewById(R.id.generate);

        OnClickListener plusMinusGenerateClick = new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case (R.id.plus):
                        {
                        password.setText("");
                        if (passwordLength == 15) {
                            password.setText(R.string.max_size_error);
                            break;
                        }
                        passwordLengthText.setText(getString(R.string.password_length, ++passwordLength));
                        break;
                        }

                    case R.id.minus:
                        {
                        password.setText("");
                        if (passwordLength == 6) {
                            password.setText(R.string.min_size_error);
                            break;
                        }
                        passwordLengthText.setText(getString(R.string.password_length, --passwordLength));
                        break;
                        }

                    case R.id.generate:
                        {
                        generatePassword();
                        break;
                        }
                }
            }
        };

        plusButton.setOnClickListener(plusMinusGenerateClick);
        minusButton.setOnClickListener(plusMinusGenerateClick);
        generatePasswordButton.setOnClickListener(plusMinusGenerateClick);
    }

    private Boolean setPasswordSymbols()
    {
        String digits = "0123456789";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialSymbols = "%*),?@#$~";

        if(!(useDigits.isChecked() || useLowercase.isChecked() || useUppercase.isChecked() || useSpecialSymbols.isChecked()))
        {
            return false;
        }

        if (useDigits.isChecked())
            passwordSymbols += digits;

        if (useUppercase.isChecked())
            passwordSymbols += uppercase;

        if (useLowercase.isChecked())
            passwordSymbols += lowercase;

        if (useSpecialSymbols.isChecked())
            passwordSymbols += specialSymbols;

        return true;
    }

    public void generatePassword()
    {
        if(!setPasswordSymbols())
            password.setText(R.string.empty_arguments);
        else
        {
            StringBuilder passBuilder = new StringBuilder();
            for (int i = 0; i < passwordLength; i++)
            {
                passBuilder.append(passwordSymbols.charAt(random.nextInt(passwordSymbols.length())));
            }
            password.setTextIsSelectable(true);
            password.setText(passBuilder.toString());
        }
    }
}
