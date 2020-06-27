package com.example.orderpizza;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DrinksAdapter extends ArrayAdapter<Product> {
private LayoutInflater inflater;
private int layout;
private ArrayList<Product> drinksList;
        Context context;
        Profile user;

    DrinksAdapter(Context context, int resource, ArrayList<Product> drinks, Profile user) {
        super(context, resource, drinks);
        this.context = context;
        this.drinksList = drinks;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.user = user;
        }
public View getView(int position, View convertView, ViewGroup parent) {

final ViewHolder viewHolder;
        if(convertView==null){
        convertView = inflater.inflate(this.layout, parent, false);
        viewHolder = new ViewHolder(convertView);
        convertView.setTag(viewHolder);
        }
        else{
        viewHolder = (ViewHolder) convertView.getTag();
        }
    final Product drink = drinksList.get(position);

        viewHolder.nameView.setText(drink.getName());
        viewHolder.descView.setText(drink.getDesc());
        viewHolder.priceButton.setText(drink.getPrice());
        viewHolder.pictureView.setImageResource(drink.getImage());

        viewHolder.priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // add to cart
                CartItem itm = new CartItem(drink, "", 1, drink.getPriceSmall());
                user.AddItem(itm);
            }
        });


        return convertView;
    }


private class ViewHolder {
    final Button priceButton;
    final TextView nameView, descView;
    ImageView pictureView;

    ViewHolder(View view){
        priceButton = (Button) view.findViewById(R.id.priceButton);
        nameView = (TextView) view.findViewById(R.id.nameView);
        descView = (TextView) view.findViewById(R.id.descView);
        pictureView = (ImageView) view.findViewById( R.id.pictureView );
    }
}
}