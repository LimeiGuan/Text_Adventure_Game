package hospital;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameUI extends Application
{
	private GameManager gameManager; // Declare the GameManager
	private TextArea outputBox; // Declare the outputBox at the class level

    @Override
    public void start(Stage primaryStage)
    {
    	gameManager = new GameManager(); // Initialize the GameManager
    	
        // Create the label
        Label hpLabel = new Label("HP");
        hpLabel.setFont(Font.font("Arial", 14)); // Set font size
        hpLabel.setStyle("-fx-font-weight: bold;"); // Apply bold style

        // Create the health bar
        ProgressBar healthBar = new ProgressBar(0.75);
        healthBar.getStyleClass().add("red-progress-bar");
        healthBar.setPrefWidth(100);

        // Combine the HP label and health bar into an HBox
        HBox healthBox = new HBox(5, hpLabel, healthBar); // 5 pixels spacing between label and bar
        healthBox.setAlignment(Pos.CENTER_LEFT);
        healthBox.setPadding(new Insets(0, 0, 0, 25)); // Adjust left padding to align with image box

        ImageView imageView = new ImageView();
        imageView.setFitWidth(500);
        imageView.setFitHeight(300);
        //Temp Image
        Image image = new Image("https://via.placeholder.com/500x300");
        imageView.setImage(image);

        VBox imageBox = new VBox(imageView);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(10));

        // Combine the health bar and the image box into a VBox
        VBox leftBox = new VBox(0, healthBox, imageBox); // Set spacing to 0
        leftBox.setAlignment(Pos.TOP_LEFT);

        // Create the map box
        TextArea mapBox = new TextArea("Map will be here");
        mapBox.setPrefRowCount(5);
        mapBox.setEditable(false);
        mapBox.setMaxWidth(200); // Set the maximum width for the map box

        // Create the inventory box
        TextArea inventoryBox = new TextArea("Inventory will be here");
        inventoryBox.setPrefRowCount(5);
        inventoryBox.setEditable(false);
        inventoryBox.setMaxWidth(200); // Set the maximum width for the inventory box

        // Combine the map box and inventory box into a VBox
        VBox rightBox = new VBox(10, mapBox, inventoryBox);
        rightBox.setPadding(new Insets(10));
        rightBox.setAlignment(Pos.TOP_RIGHT); // Align to the top right
        VBox.setVgrow(mapBox, Priority.ALWAYS);
        VBox.setVgrow(inventoryBox, Priority.ALWAYS);

        outputBox = new TextArea("Output will be displayed here.");
        outputBox.setPrefRowCount(2);
        outputBox.setEditable(false);
        outputBox.setMaxWidth(Double.MAX_VALUE);

        // Create the input box
        TextField inputField = new TextField();
        inputField.setPromptText("Enter your input here");
        inputField.setMaxWidth(Double.MAX_VALUE);
        
        // Handle the input submission
        inputField.setOnAction(e -> {
            String userInput = inputField.getText();
            String input = gameManager.commandControl(userInput); // Get the reversed string from GameManager
            outputBox.clear(); // Clear the previous content of the outputBox
            outputBox.appendText(input); // Display the new reversed string in the outputBox
            inputField.clear(); // Clear the input field after submission
        });


        // Arrange the output box and input field
        VBox bottomBox = new VBox(10, outputBox, inputField);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.CENTER); // Center the bottomBox
        VBox.setVgrow(outputBox, Priority.NEVER);
        VBox.setVgrow(inputField, Priority.ALWAYS);

        // Combine the leftBox (HP, health bar, image) and the rightBox (map, inventory)
        HBox topBox = new HBox(10, leftBox, rightBox);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.TOP_RIGHT); // Align to the top right
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.NEVER);

        // Create the root BorderPane and set the layout
        BorderPane root = new BorderPane(); // Instantiate the BorderPane

        root.setTop(topBox);
        root.setBottom(bottomBox);
        BorderPane.setMargin(bottomBox, new Insets(0, 10, 10, 10)); // Add margin to bottomBox

        // Create and set the scene with fixed width and height
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Load CSS
        primaryStage.setTitle("Game UI Example");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Make the stage non-resizable
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

