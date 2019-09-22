package id.co.countrypedia_v2.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import id.co.countrypedia_v2.di.DaggerAPIComponent;
import id.co.countrypedia_v2.model.CountryModel;
import id.co.countrypedia_v2.networkUtils.APICore;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>>countries = new MutableLiveData<>();
    public MutableLiveData<Boolean>countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean>loading = new MutableLiveData<>();

    @Inject
    public APICore apiCore = APICore.getInstance();

    public ListViewModel() {
        super();
        DaggerAPIComponent.create().injectModel(this);
    }

    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh(){
        fetchCountries();
    }

    private void fetchCountries() {
        /*CountryModel country1 = new CountryModel("Albania","Tirana","");
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
        list.add(country1);
        list.add(country2);
        list.add(country3);
        list.add(country4);
        list.add(country5);
        list.add(country6);

        countries.setValue(list);
        countryLoadError.setValue(false);
        loading.setValue(false);*/
        loading.setValue(true);
        disposable.add(
                apiCore.getDataCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                    @Override
                    public void onSuccess(List<CountryModel> countryModels) {
                        countries.setValue(countryModels);
                        countryLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}

