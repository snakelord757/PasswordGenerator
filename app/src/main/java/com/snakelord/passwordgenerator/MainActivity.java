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
    private CheckBox useDigitsCheckBox;
    private CheckBox useUppercaseCheckBox;
    private CheckBox useLowercaseCheckBox;
    private CheckBox useSpecialSymbolsCheckBox;
    private TextView passwordLengthTextView;
    private TextView passwordTextView;
    private int passwordLength = 6;
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        useDigitsCheckBox = findViewById(R.id.use_digits);
        useUppercaseCheckBox = findViewById(R.id.use_uppercase_letters);
        useLowercaseCheckBox = findViewById(R.id.use_lowercase_letters);
        useSpecialSymbolsCheckBox = findViewById(R.id.use_special_symbols);

        passwordLengthTextView = findViewById(R.id.password_length);
        passwordLengthTextView.setText(getString(R.string.password_length, passwordLength));

        passwordTextView = findViewById(R.id.password);

        Button plusButton = findViewById(R.id.plus);
        Button minusButton = findViewById(R.id.minus);
        Button generatePasswordButton = findViewById(R.id.generate);

        OnClickListener plusMinusGenerateClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case (R.id.plus): {
                        passwordTextView.setText("");
                        if (passwordLength == 15) {
                            passwordTextView.setText(R.string.max_size_error);
                            break;
                        }
                        passwordLengthTextView.setText(getString(R.string.password_length, ++passwordLength));
                        break;
                    }

                    case R.id.minus: {
                        passwordTextView.setText("");
                        if (passwordLength == 6) {
                            passwordTextView.setText(R.string.min_size_error);
                            break;
                        }
                        passwordLengthTextView.setText(getString(R.string.password_length, --passwordLength));
                        break;
                    }

                    case R.id.generate: {
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

    private String setPasswordSymbols(String passwordSymbols) {
        String digits = "0123456789";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialSymbols = "%*),?@#$~";

        if(!(useDigitsCheckBox.isChecked() || useLowercaseCheckBox.isChecked() || useUppercaseCheckBox.isChecked() || useSpecialSymbolsCheckBox.isChecked())) {
            return "";
        }

        if (useDigitsCheckBox.isChecked())
            passwordSymbols += digits;

        if (useUppercaseCheckBox.isChecked())
            passwordSymbols += uppercase;

        if (useLowercaseCheckBox.isChecked())
            passwordSymbols += lowercase;

        if (useSpecialSymbolsCheckBox.isChecked())
            passwordSymbols += specialSymbols;

        return passwordSymbols;
    }

    public void generatePassword() {
        String passwordSymbols = setPasswordSymbols("");
        if(passwordSymbols.isEmpty())
            passwordTextView.setText(R.string.empty_arguments);
        else {
            StringBuilder passBuilder = new StringBuilder();
            for (int i = 0; i < passwordLength; i++)
            {
                passBuilder.append(passwordSymbols.charAt(random.nextInt(passwordSymbols.length())));
            }
            passwordTextView.setTextIsSelectable(true);
            passwordTextView.setText(passBuilder.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        useDigitsCheckBox = null;
        useUppercaseCheckBox = null;
        useLowercaseCheckBox = null;
        useSpecialSymbolsCheckBox = null;
        passwordLengthTextView = null;
        passwordTextView = null;
    }
}
