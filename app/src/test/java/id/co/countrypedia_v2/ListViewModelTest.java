package id.co.countrypedia_v2;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import id.co.countrypedia_v2.model.CountryModel;
import id.co.countrypedia_v2.networkUtils.APICore;
import id.co.countrypedia_v2.viewmodel.ListViewModel;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    APICore apiCore;

    @InjectMocks
    ListViewModel model = new ListViewModel();

    private Single<List<CountryModel>>testSingle;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDataCountriesSuccess(){
        CountryModel countryModel = new CountryModel("countryName","capital","flag");
        ArrayList<CountryModel> countryModelArrayList = new ArrayList<>();
        countryModelArrayList.add(countryModel);

        testSingle = Single.just(countryModelArrayList);

        Mockito.when(apiCore.getDataCountries()).thenReturn(testSingle);

        model.refresh();

        Assert.assertEquals(1,model.countries.getValue().size());
        Assert.assertEquals(false,model.countryLoadError.getValue());
        Assert.assertEquals(false,model.loading.getValue());

    }
    @Before
    public void setupRxSchedulers(){
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable->{ runnable.run();},true);
            }
        };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate );
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }
}
