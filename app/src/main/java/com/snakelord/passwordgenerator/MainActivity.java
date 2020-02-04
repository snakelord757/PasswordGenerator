package com.snakelord.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
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
        passwordLengthText.setText(getString(R.string.password_length,passwordLength));

        password = findViewById(R.id.password);

        plusButton = findViewById(R.id.plus);
        plusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password.setText("");
                if (passwordLength == 100)
                {
                    password.setText(R.string.max_size_error);
                    return;
                }
                passwordLengthText.setText(getString(R.string.password_length,++passwordLength));
            }
        });

        minusButton = findViewById(R.id.minus);
        minusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password.setText("");
                if (passwordLength == 6)
                {
                    password.setText(R.string.min_size_error);
                    return;
                }
                passwordLengthText.setText(getString(R.string.password_length,--passwordLength));
            }
        });

        generatePasswordButton = findViewById(R.id.generate);
        generatePasswordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                generatePassword();
            }
        });
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
            passwordSymbols = digits;

        if (useUppercase.isChecked())
            passwordSymbols = uppercase;

        if (useLowercase.isChecked())
            passwordSymbols = lowercase;

        if (useSpecialSymbols.isChecked())
            passwordSymbols = specialSymbols;

        if (useDigits.isChecked() && useUppercase.isChecked())
            passwordSymbols = digits + uppercase;

        if (useDigits.isChecked() && useLowercase.isChecked())
            passwordSymbols = digits + lowercase;

        if (useDigits.isChecked() && useSpecialSymbols.isChecked())
            passwordSymbols = digits + specialSymbols;

        if (useLowercase.isChecked() && useUppercase.isChecked())
            passwordSymbols = lowercase + uppercase;

        if (useLowercase.isChecked() && useSpecialSymbols.isChecked())
            passwordSymbols = lowercase + specialSymbols;

        if (useUppercase.isChecked() && useSpecialSymbols.isChecked())
            passwordSymbols = uppercase + specialSymbols;

        if (useDigits.isChecked() && useUppercase.isChecked() && useLowercase.isChecked())
            passwordSymbols = digits + uppercase + lowercase;

        if (useDigits.isChecked() && useUppercase.isChecked() && useSpecialSymbols.isChecked())
            passwordSymbols = digits + uppercase + specialSymbols;

        if (useDigits.isChecked() && useLowercase.isChecked() && useSpecialSymbols.isChecked())
            passwordSymbols = digits + lowercase + specialSymbols;

        if (useLowercase.isChecked() && useUppercase.isChecked() && useSpecialSymbols.isChecked())
            passwordSymbols = lowercase + uppercase + specialSymbols;

        if (useDigits.isChecked() && useLowercase.isChecked() && useUppercase.isChecked() && useSpecialSymbols.isChecked())
            passwordSymbols = digits + lowercase + uppercase + specialSymbols;
        return true;
    }

    public void generatePassword()
    {
        if(!setPasswordSymbols())
            password.setText(R.string.empty_arguments);
        else
        {
            String generatedPassword = "";
            for (int i = 0; i < passwordLength; i++)
                generatedPassword += passwordSymbols.charAt(random.nextInt(passwordSymbols.length()));
            password.setTextIsSelectable(true);
            password.setText(generatedPassword);
        }
    }
}
