package com.example.orderpizza;

import android.content.Intent;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    private TextView mTextView;
    Product selectedProduct = null;

    Profile user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        selectedProduct = (Product) getIntent().getSerializableExtra("SELECTED_PRODUCT");
        user = (Profile) getIntent().getSerializableExtra("User");

        ImageView img = (ImageView) findViewById(R.id.img);
        img.setImageResource(selectedProduct.getImage());

        TextView tvName= (TextView) findViewById(R.id.txtName);
        tvName.setText(selectedProduct.getName());

        TextView tvPrice = (TextView) findViewById(R.id.txtPrice);
        tvPrice.setText("Price: " + selectedProduct.getSmall());

        RadioButton rb = (RadioButton) findViewById(R.id.small);
        rb.setChecked(true);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radios);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                TextView tvPrice = (TextView) findViewById(R.id.txtPrice);
                switch(id) {
                    case R.id.small:
                        tvPrice.setText("Price: " + selectedProduct.getSmall());
                        break;
                    case R.id.medium:
                        tvPrice.setText("Price: " + selectedProduct.getMedium());
                        break;
                    case R.id.large:
                        tvPrice.setText("Price: " + selectedProduct.getLarge());
                        break;
                    default:
                        break;
                }
            }});



    }

    public void onClick(View view) {
       // finish();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }

    public void onClickMinus(View view) {
        Button bt = (Button) findViewById(R.id.btnPlus);
        bt.setEnabled(true);
        bt.setBackgroundColor(0xFFFF9800);

        TextView tv= (TextView) findViewById(R.id.Quantity);
        int count = Integer.parseInt(tv.getText().toString());
        if(count > 1 ){
            count--;
            tv.setText(String.valueOf(count));
        }
        if (count == 1){
            Button btn = (Button) findViewById(R.id.btnMinus);
            btn.setEnabled(false);
            btn.setBackgroundColor(0xFFB59668);
        }

    }

    public void onClickPlus(View view) {
        Button bt = (Button) findViewById(R.id.btnMinus);
        bt.setEnabled(true);
        bt.setBackgroundColor(0xFFFF9800);

        TextView tv= (TextView) findViewById(R.id.Quantity);
        int count = Integer.parseInt(tv.getText().toString());
        if(count < 10 ){
            count++;
            tv.setText(String.valueOf(count));
        }
        if (count == 10){
            Button btn = (Button) findViewById(R.id.btnPlus);
            btn.setEnabled(false);
            btn.setBackgroundColor(0xFFB59668);
        }
    }

    public void onClickAdd(View view) {
        TextView tv= (TextView) findViewById(R.id.Quantity);
        int count = Integer.parseInt(tv.getText().toString());

        Double price = 0d;

      /*  TextView tv2= (TextView) findViewById(R.id.txtPrice);
        double sum = Integer.parseInt(tv.getText().toString());*/

        String Size = "";
        RadioButton rb = (RadioButton) findViewById(R.id.small);
        if (rb.isChecked()) {
            Size = "Small";
            price = selectedProduct.getPriceSmall();
        }
        RadioButton rb2 = (RadioButton) findViewById(R.id.medium);
        if (rb2.isChecked()) {
            Size = "Medium";
            price = selectedProduct.getPriceMedium();
        }
        RadioButton rb3 = (RadioButton) findViewById(R.id.large);
        if (rb3.isChecked()) {
            Size = "Large";
            price = selectedProduct.getPriceLarge();
        }
/// add to cart
       // sum
        CartItem itm = new CartItem(selectedProduct, Size, count, (double) count*price);
        user.AddItem(itm);

        Toast t = Toast.makeText(this, "Added to cart", Toast.LENGTH_LONG);
        t.setGravity(Gravity.TOP|Gravity.LEFT, 350, 0);
        t.show();

       /* Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);*/
    }
}