package id.co.countrypedia_v2.di;

import dagger.Component;
import id.co.countrypedia_v2.networkUtils.APICore;
import id.co.countrypedia_v2.viewmodel.ListViewModel;

@Component(modules = {APIModule.class})

public interface APIComponent {
    void injectData(APICore service);

    void injectModel(ListViewModel modelView);
}
