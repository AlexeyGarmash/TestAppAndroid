package com.example.testapp.dagger.modules.api;

import com.example.testapp.retrofit.TestAppApi;
import com.example.testapp.retrofit.TestAppService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (includes = {ApiModule.class})
public class TestAppServiceModule {

    @Provides
    @Singleton
    public TestAppService provideTestAppService(TestAppApi api){
        return new TestAppService(api);
    }
}
