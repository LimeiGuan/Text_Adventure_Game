package hospital;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Config class provides methods for retrieving application configuration values.
 * It loads configurations from a properties file located in the classpath.
 */
public class Config
{

    private static final Properties properties = new Properties();

    static
    {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")
		{
        	if (input != null)
        	{
        		properties.load(input);
            }
        	else
        	{
                throw new IOException("Configuration file 'config.properties' not found.");
            }
        }
        catch (IOException ex)
        {
        	System.err.println("Error loading config.properties: " + ex.getMessage());
        }
    }

    /**
     * Gets the S3 bucket name from the configuration file.
     *
     * @return the S3 bucket name
     */
    public static String getBucketName() {
        return properties.getProperty("aws.s3.bucket.name");
    }

    /**
     * Gets the AWS region from the configuration file.
     *
     * @return the AWS region
     */
    public static String getRegion() {
        return properties.getProperty("aws.region");
    }

    // Additional configuration methods as needed
}
