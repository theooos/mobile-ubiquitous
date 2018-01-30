package dev.theodo.red.coffeerecomender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class FindCoffeeActivity extends AppCompatActivity {

    private CoffeeExpert coffeeExpert = new CoffeeExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_coffee);
    }

    public void onClickFindCoffee(View view){
        TextView brands = (TextView) findViewById(R.id.brands);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String coffeeType = spinner.getSelectedItem().toString();

        String result = TextUtils.join(",", coffeeExpert.getBrands(coffeeType));
        brands.setText(result);
    }
}
