package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import static com.example.ht1.Main2Activity.userIdSelection;

public class CardActivity extends BaseActivity {

    Spinner spinner;
    Button check_switch;
    Button amortise;
    Context context;
    ArrayList<Credit_card> credit_cards = new ArrayList<>();
    ArrayList<Debit_card> debit_cards = new ArrayList<>();
    ArrayList<String> credit_cards_list = new ArrayList<>();
    ArrayList<String> debit_cards_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        check_switch = findViewById(R.id.button8);
        amortise = findViewById(R.id.button6);
        spinner = findViewById(R.id.spinner);
        context = CardActivity.this;
        int userId = userIdSelection;

        credit_cards = MainActivity.getInstance().getCreditCards();
        debit_cards = MainActivity.getInstance().getDebitCards();


        // creating the lists of cards that the user has
        credit_cards_list.add("Show credit cards");
        for (int i = 0; i < credit_cards.size(); i++) {
            if (userId == credit_cards.get(i).getUserID()) {
                String card_num = credit_cards.get(i).getCardNum();
                credit_cards_list.add(card_num);
            }
        }

        debit_cards_list.add("Show debit cards");
        for (int i = 0; i < debit_cards.size(); i++) {
            if (userId == credit_cards.get(i).getUserID()) {
                String card_num = debit_cards.get(i).getCardNum();
                debit_cards_list.add(card_num);
            }
        }

        //////Using switch to choose debit or credit card///////////////////////////////////////////////////////////////

        check_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch sw;
                sw = (Switch) findViewById(R.id.switch1);
                if (sw.isChecked()) {
                    // listing credit cards in spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, credit_cards_list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                } else {
                    // listing debit cards in spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, debit_cards_list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////

        ///////Amortise card////////////////////////////////////////////////////////////////////////////////

        //checking if the card is debit or credit card with switch
        amortise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch sw;
                sw = (Switch) findViewById(R.id.switch1);
                if (sw.isChecked()) {    //is credit card

                    // saving the chosen item from spinner
                    String card = spinner.getSelectedItem().toString();
                    if (card.equals("Show credit cards")) {
                        System.out.println("The header of spinner");
                    } else {
                        //deleting the card from all lists
                        for (int i = 0; i < credit_cards.size(); i++) {
                            if (card == credit_cards.get(i).getCardNum()) {
                                credit_cards.remove(i);
                                credit_cards_list.remove(i);
                            }
                        }
                    }
                } else {     //is debit card
                    // saving the chosen item from spinner
                    String card = spinner.getSelectedItem().toString();
                    if (card.equals("Show debit cards")) {
                        System.out.println("The header of spinner");
                    } else {
                        //deleting the card from all lists
                        for (int i = 0; i < debit_cards.size(); i++) {
                            if (card == debit_cards.get(i).getCardNum()) {
                                debit_cards.remove(i);
                                debit_cards_list.remove(i);
                            }
                        }
                    }
                }
            }
        });
    }

    public void addCard(View v){
        Intent intent = new Intent(CardActivity.this, AddCardActivity.class);
        startActivity(intent);
    }
}





