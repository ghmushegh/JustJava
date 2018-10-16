package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int totalPrice = 0;
    //    TextView priceTextView;
    CheckBox whippedCreamCheckBox;
    CheckBox chocolateCheckBox;
    EditText inputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whippedCreamCheckBox = findViewById(R.id.whipped_cream_cb);
        chocolateCheckBox = findViewById(R.id.chocolate_cb);
        inputEditText = findViewById(R.id.name_field);
    }

    public void submitOrder(View view) {
        calculatePrice(5);
        String name = inputEditText.getText().toString();
        String message = getString(R.string.order_summary_name, name);
        message += "\n" + getString(R.string.question_1) + whippedCreamCheckBox.isChecked();
        message += "\n" + getString(R.string.question_2) + chocolateCheckBox.isChecked();
        message += "\n" + getString(R.string.quantity) + ": " + quantity + "\n" + getString(R.string.total) + totalPrice + "\n" + getString(R.string.thank_you);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_for, name));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.warning), Toast.LENGTH_SHORT).show();
        }

    }

    private void calculatePrice(int pricePerCup) {
        int basePrice = pricePerCup;
        if (whippedCreamCheckBox.isChecked()) {
            basePrice += 1;
        }
        if (chocolateCheckBox.isChecked()) {
            basePrice += 2;
        }
        totalPrice = basePrice * quantity;
    }

    private void displayQuantity(int n) {
        TextView quantityTextView = findViewById(R.id.quaintity_text_view);
        quantityTextView.setText(String.valueOf(n));
    }


    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You can not have more cups", Toast.LENGTH_SHORT).show();
            return;
        } else {
            quantity++;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You can not have less cups", Toast.LENGTH_SHORT).show();
            return;
        } else {
            quantity--;
        }
        displayQuantity(quantity);
    }
}
