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

/**
 * The GameUI class represents the user interface for the game application.
 * It extends the JavaFX Application class to set up and display the game's main window.
 * The UI includes a health bar, an image, a map, an inventory area, an output area, and an input field.
 */
public class GameUI extends Application
{
	/** Instance of GameManager to handle game logic*/
	private GameManager gameManager;
	/**TextArea for displaying output*/
	private TextArea outputBox;
	// Declare ImageView for map
    	private ImageView mapImageView;

    /**
     * Initializes and starts the JavaFX application.
     * 
     * @param primaryStage the primary stage for this application, onto which the application scene will be set
     */
    @Override
    public void start(Stage primaryStage)
    {
        gameManager = new GameManager(); // Initialize the GameManager

        // Load the images for the map
        Image image1 = new Image("/floor1.png");
        Image image2 = new Image("/floor2.png");
        Image image3 = new Image("/groundfloor.png");
        Image image4 = new Image("/lift.png");

        // Create the label
        Label hpLabel = new Label("HP");
        hpLabel.setFont(Font.font("Arial", 14)); // Set font size
        hpLabel.setStyle("-fx-font-weight: bold;"); // Apply bold style

        // Create the health bar
        ProgressBar healthBar = new ProgressBar(1);
        healthBar.getStyleClass().add("red-progress-bar");
        healthBar.setPrefWidth(100);

        // Combine the HP label and health bar into an HBox
        HBox healthBox = new HBox(5, hpLabel, healthBar); // 5 pixels spacing between label and bar
        healthBox.setAlignment(Pos.CENTER_LEFT);
        healthBox.setPadding(new Insets(0, 0, 0, 25)); // Adjust left padding to align with image box

        ImageView imageView = new ImageView();
        imageView.setFitWidth(500);
        imageView.setFitHeight(300);
        // Temp Image
        Image image = new Image("/Background.png");
        imageView.setImage(image);

        VBox imageBox = new VBox(imageView);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(10));

        // Combine the health bar and the image box into a VBox
        VBox leftBox = new VBox(0, healthBox, imageBox); // Set spacing to 0
        leftBox.setAlignment(Pos.TOP_LEFT);

        // Create the map box using an ImageView
        mapImageView = new ImageView();
        mapImageView.setFitWidth(200); // Set width of the map box
        mapImageView.setFitHeight(200); // Set height of the map box
        mapImageView.setPreserveRatio(false); // Do not preserve aspect ratio

        // Create a VBox to enforce the height of the map box
        VBox mapBoxContainer = new VBox(mapImageView);
        mapBoxContainer.setPrefSize(200, 200); // Set preferred size of the container to 200x200 pixels
        mapBoxContainer.setMinSize(200, 200); // Ensure minimum size is 200x200
        mapBoxContainer.setMaxSize(200, 200); // Ensure maximum size is 200x200

        // Create the inventory box
        TextArea inventoryBox = new TextArea("Inventory:");
        inventoryBox.setPrefRowCount(5);
        inventoryBox.setEditable(false);
        inventoryBox.setMaxWidth(200); // Set the maximum width for the inventory box

        // Combine the map box and inventory box into a VBox
        VBox rightBox = new VBox(10, mapBoxContainer, inventoryBox);
        rightBox.setPadding(new Insets(10));
        rightBox.setAlignment(Pos.TOP_RIGHT); // Align to the top right

        outputBox = new TextArea("Write NewGame [player name] to start a new game!");
        outputBox.setPrefRowCount(2);
        outputBox.setEditable(false);
        outputBox.setMaxWidth(Double.MAX_VALUE);

        // Create the input box
        TextField inputField = new TextField();
        inputField.setPromptText("Enter your input here");
        inputField.setMaxWidth(Double.MAX_VALUE);

        updateMapBox(999);

        // Handle the input submission
        inputField.setOnAction(e -> {
            String userInput = inputField.getText();

            if (userInput.toLowerCase().equals("exit"))
            {
                primaryStage.close(); // Close the application
                return; // Exit the handler to prevent further processing
            }

            String[] temp = userInput.split(" ");
            boolean stop = false;

            if (temp[0].toLowerCase().equals("newgame"))
            {
                stop = false;
                gameManager = new GameManager();
            }
            else
            {
                if (gameManager.getEndGame())
                {
                    outputBox.clear();
                    outputBox.appendText("Write NewGame [player name] to start a new game!");
                    stop = true;
                }
            }

            if (!stop)
            {
                String input = gameManager.commandControl(userInput); // Get the reversed string from GameManager
                if(temp[0].toLowerCase().equals("inventory"))
                {
                	String outsplit = "";
                	outsplit = gameManager.inventory().replace("+", "\n");
                	outputBox.clear();
                    outputBox.appendText("Inventory:\n"+outsplit);
                }
                else
                {
                	outputBox.clear(); // Clear the previous content of the outputBox
                    outputBox.appendText(input); // Display the new string in the outputBox
                }
            }

            inputField.clear(); // Clear the input field after submission

            // Example of how to update the map based on a value
            if(!gameManager.getEndGame())
            	updateMapBox(gameManager.player.getPosition()); // Replace with the actual value to be checked
            
            if(!gameManager.getEndGame())
            {
            	String outsplit = "";
            	outsplit = gameManager.inventory().replace("+", "\n");
            	inventoryBox.setText("Inventory:\n"+outsplit);
            	
            	healthBar.setProgress((float)gameManager.player.getCurrHealth()/100);
            }
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
        primaryStage.setTitle("The Hospital");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Make the stage non-resizable
        primaryStage.show();
    }

	/**
     * Updates the map image based on the given value.
     *
     * @param value the value to check for determining which image to display
     */
    private void updateMapBox(int value)
    {
        if (value >= 200 && value <= 203)
        {
            mapImageView.setImage(new Image("/floor2.png"));
        }
        else if (value >= 100 && value <= 103)
        {
            mapImageView.setImage(new Image("/floor1.png"));
        }
        else if (value >= 0 && value <= 3)
        {
            mapImageView.setImage(new Image("/groundfloor.png"));
        }
        else if (value == 999)
        {
            mapImageView.setImage(new Image("/lift.png"));
        }
    }

    /**
     * Launches the JavaFX application.
     * 
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}

