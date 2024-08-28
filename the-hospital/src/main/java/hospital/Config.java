package hospital;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The Config class is responsible for loading and providing access to
 * configuration properties from a properties file.
 */
public class Config
{

    private static final Properties properties = new Properties();

    static
    //Static block to load the properties file when the class is first loaded
    {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties"))
        {
            if (input != null)
            {
                properties.load(input);
            } else 
            {
                System.err.println("Unable to find config.properties");
            }
        } catch (IOException ex)
        {
            System.err.println("Error loading config.properties: " + ex.getMessage());
        }
    }

    /**
     * Retrieves the bucket name from the configuration properties.
     * 
     * @return the bucket name as a String, or null if the property is not set
     */
    public static String getBucketName() {
        return properties.getProperty("s3.bucket.name");
    }
}
