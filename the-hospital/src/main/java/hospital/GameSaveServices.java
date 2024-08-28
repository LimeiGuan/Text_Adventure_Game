package hospital;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The GameSaveServices class provides methods for saving, loading, and deleting game state data
 * to and from Amazon S3 cloud storage. It manages interactions with S3 using the AWS SDK and handles
 * JSON serialization and deserialization using Jackson's {@link ObjectMapper}.
 * <p>
 * This class implements {@link AutoCloseable} to ensure proper resource management, closing the S3 client
 * when no longer needed.
 * </p>
 */
public class GameSaveServices implements AutoCloseable
{
	private static final Logger LOGGER = Logger.getLogger(GameSaveServices.class.getName());
	
    private final S3Client s3;
    private final String bucketName;
    private final ObjectMapper objectMapper;

    /**
     * Constructs a GameSaveServices instance and initializes the S3 client and ObjectMapper.
     * <p>
     * Configures the S3 client with region and credentials from the default profile and retrieves
     * the bucket name from the Config class.
     * </p>
     */
    public GameSaveServices()
    {
        this.s3 = S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        this.bucketName = Config.getBucketName();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Saves the provided GameState object to Amazon S3.
     * <p>
     * Serializes the GameState to a temporary JSON file and uploads it to S3 using the specified
     * playerId. The temporary file is deleted after the upload is complete.
     * </p>
     *
     * @param playerId the ID of the player whose game state is being saved
     * @param gameState the GameState object to be saved
     */
    public void saveGame(String playerId, GameState gameState)
    {
        String key = playerId + "/save.json";
        Path tempFile = null;
        try 
        {
            tempFile = Files.createTempFile("game_state_", ".json");
            objectMapper.writeValue(tempFile.toFile(), gameState);

            s3.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    tempFile);
        } catch (IOException e)
        {
        	LOGGER.log(Level.SEVERE, "Failed to save game state to S3: " + e.getMessage(), e);
        } finally
        {
        	 deleteTempFile(tempFile);
        }
    }

    /**
     * Loads a GameState object from Amazon S3.
     * <p>
     * Downloads the game state JSON file from S3 using the specified playerId and deserializes it
     * into a GameState object. The temporary file used for downloading is deleted after reading.
     * </p>
     *
     * @param playerId the ID of the player whose game state is being loaded
     * @return the loaded GameState object
     * @throws IOException if there is an error downloading or deserializing the game state
     */
    public GameState loadGame(String playerId) throws IOException
    {
        String key = playerId + "/save.json";
        Path tempFile = Files.createTempFile("downloaded_save_", ".json");

        try 
        {
            s3.getObject(GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    tempFile);

            return objectMapper.readValue(tempFile.toFile(), GameState.class);
        } catch (S3Exception e) 
        {
        	LOGGER.log(Level.SEVERE, "Failed to load game state from S3: " + e.awsErrorDetails().errorMessage(), e);
        	throw new IOException("Failed to load game state", e);
        } finally 
        {
            deleteTempFile(tempFile);
        }
    }

    /**
     * Deletes the game state file from Amazon S3.
     * <p>
     * Removes the file associated with the specified playerId from S3.
     * </p>
     *
     * @param playerId the ID of the player whose game state is being deleted
     */
    public void deleteGame(String playerId)
    {
        String key = playerId + "/save.json";
        try 
        {
            s3.deleteObject(DeleteObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build());
        } catch (S3Exception e)
        {
        	 LOGGER.log(Level.SEVERE, "Failed to delete game state from S3: " + e.awsErrorDetails().errorMessage(), e);
        }
    }
    
    /**
     * Deletes the specified temporary file if it exists.
     * <p>
     * This method is used to clean up temporary files created during save and load operations.
     * </p>
     *
     * @param tempFile the temporary file to be deleted
     */
    private void deleteTempFile(Path tempFile)
    {
        if (tempFile != null && Files.exists(tempFile))
        {
            try
            {
                Files.delete(tempFile);
            } catch (IOException e)
            {
                LOGGER.log(Level.WARNING, "Failed to delete temporary file: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Closes the S3 client and releases any associated resources.
     * <p>
     * This method is required by the {@link AutoCloseable} interface and should be called
     * to properly release resources when the GameSaveServices instance is no longer needed.
     * </p>
     */
    @Override
    public void close() 
    {
        s3.close();
    }
}

