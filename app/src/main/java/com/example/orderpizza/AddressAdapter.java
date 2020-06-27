package com.example.orderpizza;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class AddressAdapter extends ArrayAdapter<Address> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Address> addressList;
    Context context;

    AddressAdapter(Context context, int resource, ArrayList<Address> addresses) {
        super(context, resource, addresses);
        this.context = context;
        this.addressList = addresses;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        final AddressAdapter.ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new AddressAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (AddressAdapter.ViewHolder) convertView.getTag();
        }
        final Address address = addressList.get(position);

        viewHolder.addrView.setText(address.getDesc());
        viewHolder.descView.setText(address.getAddress());


        viewHolder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                alertDialogBuilder.setMessage("Are you sure you want to delete?");
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                               addressList.remove(address);
                               notifyDataSetChanged();
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

    private String formatValue(int count, String unit){
        return String.valueOf(count) + " " + unit;
    }

    private class ViewHolder {
        final ImageButton delButton;
        final TextView addrView, descView;

        ViewHolder(View view){
            delButton = (ImageButton) view.findViewById(R.id.delAddress);
            addrView = (TextView) view.findViewById(R.id.nameView);
            descView = (TextView) view.findViewById(R.id.descView);
            ;
        }
    }
}
