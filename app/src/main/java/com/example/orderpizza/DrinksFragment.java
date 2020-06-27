package com.example.orderpizza;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A fragment representing a list of Items.
 */
public class DrinksFragment extends Fragment {

    ArrayList<Product> drinks = new ArrayList();
    ListView drinksList;

    public DrinksFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_drinks, container, false);
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        if(drinks.size()==0){
            drinks.add(new Product("Cola", "Coca Cole Zero 375 mL", "0.90 AZN", R.drawable.cola0, 0.9d, 0.9d, 0.9d));
            drinks.add(new Product("Fanta", "Fanta orange 350 mL", "0.80 AZN", R.drawable.fanta, 0.8d, 0.8d, 0.8d));
            drinks.add(new Product("Sprite", "Sprite 350 mL", "0.80 AZN", R.drawable.sprite, 0.8d, 0.8d, 0.8d));

            }

        Profile user = ((MenuActivity)this.getActivity()).User;

        drinksList = (ListView) view.findViewById(R.id.drinksList);
        DrinksAdapter adapter = new DrinksAdapter(getContext(), R.layout.fragment_pizza_list, drinks, user);
        drinksList.setAdapter(adapter);

        return view;
    }
}