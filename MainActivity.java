/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.HashMap;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox WhippedCreamCheckBox = findViewById(R.id.check_box);
        boolean HasWhippedCream = WhippedCreamCheckBox.isChecked();

        CheckBox ChochlateCheckbox = findViewById((R.id.choco));
        boolean HasChocolate = ChochlateCheckbox.isChecked();

        EditText NameField =findViewById(R.id.plain_text_input);
        Editable Name = NameField.getText();
                composeEmail(createOrderSummary(calculatePrice(HasWhippedCream,HasChocolate),HasWhippedCream,HasChocolate,Name),"Just Java");
    }
     private int calculatePrice(boolean cream, boolean chocolate)
     { int baseValue = 5;
         if(cream)
             baseValue++;
         if(chocolate)
             baseValue+=2;

         return baseValue*quantity;
     }
    public void composeEmail(String addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */


    public void increment(View view) {
        quantity++;
        if(quantity>100)
        {
            quantity--;
            Context context = getApplicationContext();
            CharSequence text = "You can't order more than 100 coffees";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        display(quantity);
    }

    public void decrement(View view) {
        quantity--;
        if(quantity<0)
        {
           quantity++;
            Context context = getApplicationContext();
            CharSequence text = "You can order atleast 1 coffee";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
           return;
        }
        display(quantity);
    }
    /**
     * This method displays the given text on the screen.
     */


    private String createOrderSummary(int price,boolean HasWhippedCream,boolean HasChocolate,Editable Name)
    {
        String summary ="Name: "+Name+ "\nAdd Whipped Cream? "+ HasWhippedCream+ "\nAdd Chocolate? "+ HasChocolate+"\nQuantity: "+ quantity + "\nTotal:$ "+price+"\nThankyou";
        return summary;
    }
}
