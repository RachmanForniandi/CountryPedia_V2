package id.co.countrypedia_v2.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.countrypedia_v2.R;
import id.co.countrypedia_v2.adapter.CountryAdapter;
import id.co.countrypedia_v2.viewmodel.ListViewModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_of_countries)
    RecyclerView listOfCountries;

    @BindView(R.id.txt_error_occured)
    TextView txtErrorOccurred;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    CountryAdapter countryAdapter;

    ListViewModel listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        countryAdapter = new CountryAdapter(new ArrayList<>());
        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        listViewModel.refresh();
        listOfCountries.setLayoutManager(new LinearLayoutManager(this));
        listOfCountries.setAdapter(countryAdapter);

        observeViewModel();

    }

    private void observeViewModel() {
        listViewModel.countries.observe(this,countryModels -> {
            if (countryModels != null){
                listOfCountries.setVisibility(View.VISIBLE);
                countryAdapter.updateCountries(countryModels);

            }
        });
        listViewModel.countryLoadError.observe(this, isError -> {
            if (isError != null){
                txtErrorOccurred.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        listViewModel.loading.observe(this, isLoading -> {
            if (isLoading != null){
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading){
                    txtErrorOccurred.setVisibility(View.GONE);
                    listOfCountries.setVisibility(View.GONE);
                }
            }
        });


    }


}
