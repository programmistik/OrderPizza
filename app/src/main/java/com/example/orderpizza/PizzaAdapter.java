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

public class PizzaAdapter extends ArrayAdapter<Product> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Product> productList;
    Context context;
    Profile user;

    PizzaAdapter(Context context, int resource, ArrayList<Product> products, Profile user) {
        super(context, resource, products);
        this.context = context;
        this.productList = products;
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
        final Product product = productList.get(position);

        viewHolder.nameView.setText(product.getName());
        viewHolder.descView.setText(product.getDesc());
        viewHolder.priceButton.setText(product.getPrice());
        viewHolder.pictureView.setImageResource(product.getImage());

        viewHolder.priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Profile user = ((MenuActivity)this.getActivity()).User;

                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.putExtra("SELECTED_PRODUCT", product);
                intent.putExtra("User", user);


                context.startActivity(intent);

                //getContext().startActivity(new Intent(getContext(), ProductActivity.class));
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