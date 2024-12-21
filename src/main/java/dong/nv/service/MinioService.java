package dong.nv.service;

import dong.nv.bean.MinioBean;
import dong.nv.config.IMinioServerConfig;
import dong.nv.config.MinIOServerConfig;
import dong.nv.config.MinioConfig;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
@Setter
public class MinioService {

    private MinioBean minioBean;
    private MinioConfig minioConfig;

    private static final class SingletonHolder {
        private static final MinioService INSTANCE = new MinioService();
    }

    public static MinioService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public MinioConfig initMinio() {
        IMinioServerConfig minioServerConfig = MinIOServerConfig.getInstance();
        minioBean = minioBean.builder()
                .url(minioServerConfig.getUrl())
                .accessKey(minioServerConfig.getAccessKey())
                .secretKey(minioServerConfig.getSecretKey())
                .bucket(minioServerConfig.getBucket())
                .timeShareFile(minioServerConfig.getTimeShareFile())
                .build();
        minioConfig = new MinioConfig(minioBean);
        minioConfig.init();
        return minioConfig;
    }


    public String getUrl(String fileName,
                         InputStream inputStream) {
        try {
            minioConfig.uploadFileToMinIO(fileName, inputStream);
            String url = minioConfig.getUrlPublicFromMinIO(fileName);
            log.info("GET URL after config: URL = [{}]", url);
            return url;
        } catch (Exception ex) {
            log.info("Export file: [{}] have ex", fileName);
            log.error("Export file have ex", ex);
            return null;
        }
    }
}
