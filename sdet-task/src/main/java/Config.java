import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sdet.task.GraphQLRequest;

public class Config {
    private static final String CONFIG_FILE = "config.properties";

    public static String getConfigProperty(String propertyName) {
        try (InputStream inputStream = GraphQLRequest.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            Properties properties = new Properties();
            if (inputStream != null) {
                properties.load(inputStream);
                return properties.getProperty(propertyName);
            }
        } catch (IOException e) {
            // Handle the exception according to your needs
            e.printStackTrace();
        }
        return null;
    }

}
