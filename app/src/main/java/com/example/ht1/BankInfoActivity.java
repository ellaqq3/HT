package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

import static com.example.ht1.MainActivity.bankSelection;

public class BankInfoActivity extends BaseActivity {

    TextView nameText;
    TextView addressText;
    TextView numberText;
    String name;
    String address;
    String number;

    ArrayList<Bank> banks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_info);

        nameText = findViewById(R.id.textViewOtsikko);
        addressText = findViewById(R.id.textView2);
        numberText = findViewById(R.id.textView3);

        int id = bankSelection;
        banks =  MainActivity.getInstance().getBanklist();

        for (Bank bank : banks) {
            if (id == bank.getId()) {
                name = bank.getName();
                address = bank.getAddress();
                number = bank.getPhone();
            }
        }
        nameText.setText("Name: " + name);
        addressText.setText("Address: " + address);
        numberText.setText("Phone number: " + number);

    }

}

