package dong.nv.config;

import org.aeonbits.owner.ConfigFactory;


public class MinIOServerConfig {

    private static final IMinioServerConfig serverConfig = ConfigFactory.create(IMinioServerConfig.class);

    public static IMinioServerConfig getInstance() {
        return serverConfig;
    }
}