package com.apitesting.factory;

import com.apitesting.config.APIFrameworkConfig;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;

public final class APIConfigFactory {

    private APIConfigFactory(){}

    public static APIFrameworkConfig getConfig(){
        return ConfigCache.getOrCreate(APIFrameworkConfig.class);
    }
}
