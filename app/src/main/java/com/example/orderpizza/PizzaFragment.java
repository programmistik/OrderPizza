package com.example.orderpizza;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class PizzaFragment extends Fragment {

    ArrayList<Product> products = new ArrayList();
    ListView productList;

    public PizzaFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza, container, false);
        if(products.size()==0){
            products.add(new Product("Mexican", "Grilled Сhicken, Tomatoes, Fresh Mushrooms, Green Peppers, Jalapeno Peppers, Mozzarella", "from 8 azn", R.drawable.pizza_1, 8d, 10d, 12d));
            products.add(new Product("Super Papa's", "Pepperoni, Italian Sausage, Ham, Fresh Mushrooms, Green Peppers, Black Olives, Mozzarella", "from 7 azn", R.drawable.pizza_2, 7d, 10d, 12d));
            products.add(new Product("Garden Special", "Tomatoes, Fresh Mushrooms, Green Peppers, Black Olives, Mozzarella", "from 7.5 azn", R.drawable.pizza_3, 7.5d, 10.5d, 12d));
            products.add(new Product("Chicken Buffalo", "Chicken Poppers, Ham, Buffalo Sauce, Ranch Sauce, Mozzarella", "from 8.2 azn", R.drawable.pizza_4, 8.2d, 10d, 13d));
            products.add(new Product("Margarita", "Cheese, tomato, ...", "from 9 azn", R.drawable.pizza_5, 9d, 10d, 15d));
            products.add(new Product("Margarita", "Cheese, tomato, ...", "from 9.2 azn", R.drawable.pizza_6, 9.2d, 11d, 16d));
            products.add(new Product("Margarita", "Cheese, tomato, ...", "from 9.6 azn", R.drawable.pizza_7, 9.6d, 10d, 12d));
        }

        Profile user = ((MenuActivity)this.getActivity()).User;

        productList = (ListView) view.findViewById(R.id.pizzaList);
        PizzaAdapter adapter = new PizzaAdapter(getContext(), R.layout.fragment_pizza_list, products, user);
        productList.setAdapter(adapter);

        return view;
    }
}