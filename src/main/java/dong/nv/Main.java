package dong.nv;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import dong.nv.constant.Constant;
import dong.nv.service.MinioService;
import dong.nv.utils.NanoIdUtils;
import dong.nv.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Main {
    public static void main(String[] args) {

        try {
            MDC.put(Constant.TOKEN, NanoIdUtils.randomNanoId());
            initLoggerConfig();
            MinioService minioService = MinioService.getInstance();
            minioService.initMinio();
            String filePath = "src/main/resources/fileTest.xlsx";

            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("File không tồn tại!");
                return;
            }

            InputStream inputStream = new FileInputStream(file);
            String fileName = Utils.getFileName("test/", "BAOCAO");
            String url = minioService.getUrl(fileName, inputStream);
            System.out.println(url);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initLoggerConfig() throws IOException, JoranException {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        String location = new File(".").getCanonicalPath() + "/src/main/resources/logback.xml";
        configurator.setContext(loggerContext);
        configurator.doConfigure(location);
        log.info("Start logger success");
    }
}