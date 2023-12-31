package com.apitesting.factory;

import com.apitesting.config.FrameworkConfiguration;
import org.aeonbits.owner.ConfigCache;

public final class ConfigFactory {

    private ConfigFactory(){}

    public static FrameworkConfiguration getConfig(){
        return ConfigCache.getOrCreate(FrameworkConfiguration.class);
    }
}
