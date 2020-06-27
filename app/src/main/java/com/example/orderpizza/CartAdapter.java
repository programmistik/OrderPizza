package com.example.orderpizza;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<CartItem> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<CartItem> cartList;
    private Context context;
    private TextView tvTotal;

    CartAdapter(Context context, int resource, ArrayList<CartItem> items, TextView tvTotal) {
        super(context, resource, items);
        this.context = context;
        this.cartList = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.tvTotal = tvTotal;
        }
public View getView(int position, View convertView, ViewGroup parent) {

final CartAdapter.CartViewHolder viewHolder;
        if(convertView==null){
        convertView = inflater.inflate(this.layout, parent, false);
        viewHolder = new CartAdapter.CartViewHolder(convertView);
        convertView.setTag(viewHolder);
        }
        else{
        viewHolder = (CartAdapter.CartViewHolder) convertView.getTag();
        }
final CartItem itm = cartList.get(position);

        viewHolder.nameView.setText(itm.getProduct().getName());
        viewHolder.sizeView.setText(itm.getSize());
        viewHolder.countView.setText(String.valueOf(itm.getCount()));
        viewHolder.sumView.setText(String.format("%.2f", itm.getSum()) + " AZN");
        viewHolder.picView.setImageResource(itm.getProduct().getImage());

    viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Button bt = viewHolder.btnPlus;
            bt.setEnabled(true);
            bt.setBackgroundColor(0xFFFF9800);

            TextView tv= viewHolder.countView;
            int count = Integer.parseInt(tv.getText().toString());
            if(count > 1 ){
                count--;
                tv.setText(String.valueOf(count));
                itm.setCount(itm.getCount() - 1);
                itm.updateSum();
                viewHolder.sumView.setText(String.format("%.2f", itm.getSum()) + " AZN");
            }
            if (count == 1){
                Button btn = viewHolder.btnMinus;
                btn.setEnabled(false);
                btn.setBackgroundColor(0xFFB59668);
            }

            double total = 0d;
            for (CartItem ci: cartList) {
                total = total + ci.getSum();
            }
            tvTotal.setText("Total: "+ String.format("%.2f", total)+" AZN");

        }
    });

    viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button bt = viewHolder.btnMinus;
            bt.setEnabled(true);
            bt.setBackgroundColor(0xFFFF9800);

            TextView tv= viewHolder.countView;
            int count = Integer.parseInt(tv.getText().toString());
            if(count < 10 ){
                count++;
                tv.setText(String.valueOf(count));
                itm.setCount(itm.getCount() + 1);
                itm.updateSum();
                viewHolder.sumView.setText(String.format("%.2f", itm.getSum()) + " AZN");
            }
            if (count == 10){
                Button btn = viewHolder.btnPlus;
                btn.setEnabled(false);
                btn.setBackgroundColor(0xFFB59668);
            }

            double total = 0d;
            for (CartItem ci: cartList) {
                total = total + ci.getSum();
            }
            tvTotal.setText("Total: "+ String.format("%.2f", total)+" AZN");
        }
    });


        viewHolder.delButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
        alertDialogBuilder.setMessage("Are you sure you want to delete?");
        alertDialogBuilder.setPositiveButton("Ok",
        new DialogInterface.OnClickListener() {

@Override
public void onClick(DialogInterface arg0, int arg1) {
        cartList.remove(itm);
        notifyDataSetChanged();

        double total = 0d;
        for (CartItem ci: cartList) {
            total = total + ci.getSum();
        }
        tvTotal.setText("Total: "+ String.format("%.2f", total)+" AZN");

        }
        });

        alertDialogBuilder.setNegativeButton("cancel",
        new DialogInterface.OnClickListener() {

    @Override
    public void onClick(DialogInterface arg0, int arg1) {

        }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        }
        });


        return convertView;
        }



private class CartViewHolder {
    final ImageButton delButton;
    final Button btnMinus, btnPlus;
    final ImageView picView;
    final TextView nameView, sizeView, countView, sumView;

    CartViewHolder(View view){
        delButton = (ImageButton) view.findViewById(R.id.delButton);
        nameView = (TextView) view.findViewById(R.id.nameView);
        sizeView = (TextView) view.findViewById(R.id.pizzaSize);
        countView = (TextView) view.findViewById(R.id.pizzaCount);
        sumView = (TextView) view.findViewById(R.id.pizzaAmount);
        picView = (ImageView) view.findViewById(R.id.pictureView);
        btnMinus = (Button) view.findViewById(R.id.btnCartMinus);
        btnPlus = (Button) view.findViewById(R.id.btnCartPlus);
    }
}
}
