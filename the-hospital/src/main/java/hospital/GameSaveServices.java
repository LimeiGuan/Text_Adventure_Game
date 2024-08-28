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
import java.nio.file.StandardCopyOption;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameSaveServices implements AutoCloseable
{

    private final S3Client s3;
    private final String bucketName;
    private final ObjectMapper objectMapper;

    public GameSaveServices() {
        this.s3 = S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        this.bucketName = Config.getBucketName();
        this.objectMapper = new ObjectMapper();
    }

    public void saveGame(String playerId, GameState gameState) {
        String key = playerId + "/save.json";
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("game_state_", ".json");
            objectMapper.writeValue(tempFile.toFile(), gameState);

            s3.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    tempFile);
        } catch (IOException e) {
            System.err.println("Failed to save game state to S3: " + e.getMessage());
        } finally {
            if (tempFile != null && Files.exists(tempFile)) {
                try {
                    Files.delete(tempFile);
                } catch (IOException e) {
                    System.err.println("Failed to delete temporary file: " + e.getMessage());
                }
            }
        }
    }

    public GameState loadGame(String playerId) throws IOException {
        String key = playerId + "/save.json";
        Path tempFile = Files.createTempFile("downloaded_save_", ".json");

        try {
            s3.getObject(GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    tempFile);

            return objectMapper.readValue(tempFile.toFile(), GameState.class);
        } catch (S3Exception e) {
            System.err.println("Failed to load game state from S3: " + e.awsErrorDetails().errorMessage());
            throw new IOException("Failed to load game state", e);
        } finally {
            if (Files.exists(tempFile)) {
                try {
                    Files.delete(tempFile);
                } catch (IOException e) {
                    System.err.println("Failed to delete temporary file: " + e.getMessage());
                }
            }
        }
    }

    public void deleteGame(String playerId) {
        String key = playerId + "/save.json";
        try {
            s3.deleteObject(DeleteObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build());
        } catch (S3Exception e) {
            System.err.println("Failed to delete game state from S3: " + e.awsErrorDetails().errorMessage());
        }
    }

    @Override
    public void close() {
        s3.close();
    }
}

