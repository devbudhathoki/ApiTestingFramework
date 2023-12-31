package com.apitesting.config;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/src/test/resources/config.properties"
})
public interface APIFrameworkConfig extends Config{

    @Key("baseurl")
    String baseurl();
}
