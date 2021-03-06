package ru.techmas.androidtemplate;


import android.app.Application;

import ru.techmas.androidtemplate.di.components.DaggerPresenterComponent;
import ru.techmas.androidtemplate.di.components.DaggerViewComponent;
import ru.techmas.androidtemplate.di.components.PresenterComponent;
import ru.techmas.androidtemplate.di.components.ViewComponent;
import ru.techmas.androidtemplate.di.modules.RestModule;
import ru.techmas.androidtemplate.di.modules.UtilsModule;
import ru.techmas.androidtemplate.di.modules.ViewModule;
import ru.techmas.androidtemplate.utils.Injector;
import timber.log.Timber;


/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        setupDagger2();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void setupDagger2() {
        PresenterComponent presenterComponent = DaggerPresenterComponent.builder()
                .restModule(new RestModule(this))
                .utilsModule(new UtilsModule(this))
                .build();
        presenterComponent.inject(this);

        ViewComponent viewComponent = DaggerViewComponent.builder()
                .viewModule(new ViewModule(this))
                .build();
        viewComponent.inject(this);

        Injector.setPresenterComponent(presenterComponent);
        Injector.setViewComponent(viewComponent);
    }


}
