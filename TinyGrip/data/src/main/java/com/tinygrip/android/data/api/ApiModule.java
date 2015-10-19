package com.tinygrip.android.data.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.squareup.okhttp.OkHttpClient;
import com.tinygrip.android.data.api.util.StringConverterModule;
import com.tinygrip.android.data.service.RootService;
import com.tinygrip.android.data.service.UserService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

/**
 * A module to wrap the Data related items it to the graph.
 */
@Module
public class ApiModule {

    public static final String PRODUCTION_API_URL = ApiConfig.HOST_PROD;
    public static final String MOCK_API_URL = ApiConfig.HOST_MOCK;

    @Provides
    @Singleton
    Endpoint provideDeviceEndpoint() {
        return Endpoints.newFixedEndpoint(PRODUCTION_API_URL);
    }

    @Provides
    @Singleton
    Client provideClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides
    @Singleton
    ObjectMapper provideObjectMapper() {
        ObjectMapper jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy.PascalCaseStrategy());
        jacksonObjectMapper.registerModule(new StringConverterModule());

        // This way it won't complain if we don't have every part of the json object defined
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return jacksonObjectMapper;
    }

    @Provides
    @Singleton
    RestAdapter providesRestAdapter(Endpoint endpoint,
                                    ObjectMapper jacksonObjectMapper, Client client, ApiRequestInterceptor headers) {

        return new RestAdapter.Builder()
            .setClient(client)
            .setEndpoint(endpoint)
            .setConverter(new JacksonConverter(jacksonObjectMapper))
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();
    }

    @Provides
    @Singleton
    @HttpRestAdapter
    RestAdapter providesHttpRestAdapter(ObjectMapper jacksonObjectMapper,
                                        Client client) {

        return new RestAdapter.Builder()
            .setClient(client)
            .setEndpoint(Endpoints.newFixedEndpoint("http://"))
            .setConverter(new JacksonConverter(jacksonObjectMapper))
            .build();
    }

    @Provides
    @Singleton
    @HttpsRestAdapter
    RestAdapter providesHttpsRestAdapter(ObjectMapper jacksonObjectMapper,
                                        Client client) {

        return new RestAdapter.Builder()
            .setClient(client)
            .setEndpoint(Endpoints.newFixedEndpoint("https://"))
            .setConverter(new JacksonConverter(jacksonObjectMapper))
            .build();
    }

    @Provides
    @Singleton
    RootService providesRootService(RestAdapter restAdapter) {
        return restAdapter.create(RootService.class);
    }

    @Provides
    @Singleton
    UserService providesUserService(@HttpsRestAdapter RestAdapter restAdapter) {
        return restAdapter.create(UserService.class);
    }
}
