package helpers;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private static PropertyProvider instance;
    private static final String filePath = "env_local.properties";
    @Getter
    private final Properties properties = new Properties();

    public static PropertyProvider getInstance(){
        if (instance == null){
            synchronized (PropertyProvider.class){
                if (instance == null){
                    instance = new PropertyProvider();
                }
            }
        }
        return instance;
    }

    private PropertyProvider(){
        try(InputStream propertiesInputStream =
                    getClass().getClassLoader().getResourceAsStream(filePath))
        {
            if (propertiesInputStream == null){
                throw new IOException("Properties file not found: " + filePath);
            }
            properties.load(propertiesInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from " + filePath, e);
        }
    }

    public String getProperty(String key){ return properties.getProperty(key);}
}
//public class PropertyProvider {
//    private static Properties properties;
//    private String filePath ="src/test/resources/env_local.properties";
//
//    public PropertyProvider() throws IOException {
//        properties = new Properties();
//        loadProperties();
//    }
//
//    private void loadProperties() {
//        try(FileInputStream inputStream = new FileInputStream(filePath)){
//            properties.load(inputStream);
//        }catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static String getProperty(String key){ return properties.getProperty(key);}
//}
