package hospital;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.IOException;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameSaveServices {

    private final S3Client s3;
    private final String bucketName;

    public GameSaveServices(String bucketName) {
        this.s3 = S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        this.bucketName = bucketName;
    }

    public void saveGame(String playerId, String gameState) {
        String key = playerId + "/save.json";
        
        s3.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                Paths.get(gameState)); // Supponendo che gameState sia il percorso del file
    }

    public String loadGame(String playerId) {
        String key = playerId + "/save.json";
        
        s3.getObject(GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                Paths.get("downloaded_save.json")); // Salva il file scaricato in questo percorso
        
        return "downloaded_save.json"; // Restituisce il percorso del file scaricato
    }
    
    public void deleteGame(String playerId) {
        String key = playerId + "/save.json";
        
        s3.deleteObject(DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build());
    }
    
    public GameState loadGameAsObject(String playerId) throws IOException {
        String key = playerId + "/save.json";
        
        s3.getObject(GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                Paths.get("downloaded_save.json"));

        // Converti il JSON in un oggetto Java
        ObjectMapper objectMapper = new ObjectMapper();
        GameState gameState = objectMapper.readValue(Paths.get("downloaded_save.json").toFile(), GameState.class);
        
        return gameState;
    }
}
