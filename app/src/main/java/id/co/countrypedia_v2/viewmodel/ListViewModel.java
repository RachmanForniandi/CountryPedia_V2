package id.co.countrypedia_v2.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import id.co.countrypedia_v2.model.CountryModel;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>>countries = new MutableLiveData<>();
    public MutableLiveData<Boolean>countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean>loading = new MutableLiveData<>();

    public void refresh(){
        fetchCountries();
    }

    private void fetchCountries() {
        CountryModel country1 = new CountryModel("Albania","Tirana","");
        CountryModel country2 = new CountryModel("Brazil","Rio de Janeiro","");
        CountryModel country3 = new CountryModel("Czechia","Prague","");
        CountryModel country4 = new CountryModel("Spain","Madrid","");
        CountryModel country5 = new CountryModel("Germany","Berlin","");
        CountryModel country6 = new CountryModel("British U.K","London","");

        List<CountryModel>list = new ArrayList<>();
        list.add(country1);
        list.add(country2);
        list.add(country3);
        list.add(country4);
        list.add(country5);
        list.add(country6);

        countries.setValue(list);
        countryLoadError.setValue(false);
        loading.setValue(false);
    }


}

