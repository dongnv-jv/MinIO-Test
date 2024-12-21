package dong.nv.config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "file:./src/main/resources/server.properties",
        "classpath:src/main/resources/server.properties"
})
public interface IMinioServerConfig extends Config {

    @Config.Key("ACCESS_KEY")
    String getAccessKey();

    @Config.Key("SECRET_KEY")
    String getSecretKey();

    @Config.Key("BUCKET")
    String getBucket();

    @Config.Key("TIME_SHARE_FILE")
    int getTimeShareFile();

    @Config.Key("URL")
    String getUrl();
}