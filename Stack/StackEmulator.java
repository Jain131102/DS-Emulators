
// Import necessary classes and packages from JavaFX
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
// Import Stack class from Java's util package
import java.util.Stack;
// Import Insets class from JavaFX geometry package
import javafx.geometry.Insets;
// Import Font and FontWeight classes from JavaFX text package
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
// Import Platform class from JavaFX application package
import javafx.application.Platform;
// Import FadeTransition and Duration classes from JavaFX animation package
import javafx.animation.FadeTransition;
import javafx.util.Duration;
// Import ScaleTransition class from JavaFX animation package
import javafx.animation.ScaleTransition;

public class StackEmulator extends Application {
    // Declare a private Stack to represent the stack data structure with Integer
    // elements
    private Stack<Integer> stack = new Stack<>();

    // Declare a private integer variable to store the size of the array
    private int arraySize = 0;

    // Declare a private VBox (Vertical Box) to visually organize UI elements
    // related to the stack
    private VBox stackBox = new VBox(10);

    // Declare private buttons for various stack operations: setSize, push, peek,
    // pop, clear, reverse, learn, and exit
    private Button setSizeButton, pushButton, peekButton, popButton, clearButton, reverseButton, learnButton,
            exitButton;

    // Declare a private ToggleButton to toggle between dark and light modes in the
    // application
    private ToggleButton toggleDarkModeButton;

    // The main method, starting point for the JavaFX application
    public static void main(String[] args) {
        // Launch the JavaFX application by calling the launch method with the provided
        // arguments
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Set the title of the primary stage
        primaryStage.setTitle("Stack Emulator");

        // Create UI elements
        TextField elementInput = new TextField();
        elementInput.setPromptText("Enter an integer");

        TextField sizeInput = new TextField();
        sizeInput.setPromptText("Enter array size");

        // Add "User Guide" button
        Button startButton = new Button("User Guide");
        startButton.setOnAction(e -> showGuide());

        // Initialize buttons for various stack operations
        setSizeButton = new Button("Set Size");
        setSizeButton.setOnAction(e -> setArraySize(sizeInput.getText()));

        pushButton = new Button("Push");
        pushButton.setOnAction(e -> pushElement(elementInput.getText()));

        peekButton = new Button("Peek");
        peekButton.setOnAction(e -> peekElement());

        popButton = new Button("Pop");
        popButton.setOnAction(e -> popElement());

        clearButton = new Button("Clear Stack");
        clearButton.setOnAction(e -> clearStack());

        reverseButton = new Button("Reverse");
        reverseButton.setOnAction(e -> reverseStack());

        // Add "Learn" button
        learnButton = new Button("Learn");
        learnButton.setOnAction(e -> showLearnText());

        // Add "Exit" button
        exitButton = new Button("Exit");
        exitButton.setOnAction(e -> Platform.exit());

        // Refine button styling
        setSizeButton.setStyle("-fx-base: #0000FF;"); // Blue
        pushButton.setStyle("-fx-base: #00FF00;"); // Green
        peekButton.setStyle("-fx-base: #FF0000;"); // Red
        popButton.setStyle("-fx-base: #FFA500;"); // Orange
        clearButton.setStyle("-fx-base: #FFFF00;"); // Yellow
        reverseButton.setStyle("-fx-base: #800080;"); // Purple
        learnButton.setStyle("-fx-base: #4B0082;"); // Dark Blue for "Learn" button
        exitButton.setStyle("-fx-base: #e74c3c;"); // Red for "Exit" button

        // Add tooltips
        setSizeButton.setTooltip(new Tooltip("Set Array Size"));
        pushButton.setTooltip(new Tooltip("Push Element"));
        peekButton.setTooltip(new Tooltip("Peek Element"));
        popButton.setTooltip(new Tooltip("Pop Element"));
        clearButton.setTooltip(new Tooltip("Clear Stack"));
        reverseButton.setTooltip(new Tooltip("Reverse Stack"));
        startButton.setTooltip(new Tooltip("User Guide"));
        learnButton.setTooltip(new Tooltip("Learn about Stacks"));
        exitButton.setTooltip(new Tooltip("Exit Application"));

        HBox exitBox = new HBox(10);
        exitBox.setAlignment(Pos.BOTTOM_RIGHT); // Position at the bottom right
        exitBox.getChildren().addAll(exitButton);

        stackBox.setMaxWidth(200);
        HBox buttonBox = new HBox(10); // Horizontal container for buttons
        buttonBox.setAlignment(Pos.CENTER); // Center-align the buttons horizontally
        buttonBox.getChildren().addAll(peekButton, popButton, clearButton, reverseButton, learnButton); // Added "Learn"
                                                                                                        // button

        HBox inputBox = new HBox(10); // Horizontal container for input elements
        inputBox.setAlignment(Pos.CENTER); // Center-align the input elements
        inputBox.getChildren().addAll(setSizeButton, sizeInput); // "Set Size" button and size input on the same line,
                                                                 // "Push" button and element input on the same line

        HBox pushBox = new HBox(10);
        pushBox.setAlignment(Pos.CENTER);
        pushBox.getChildren().addAll(pushButton, elementInput);

        VBox layout = new VBox(20); // Increased vertical spacing to 20
        layout.setAlignment(Pos.TOP_CENTER); // Center-align the main layout at the top with a margin of 20
        layout.setPadding(new Insets(20, 100, 0, 100));

        toggleDarkModeButton = new ToggleButton("Dark Mode");
        toggleDarkModeButton.setOnAction(e -> toggleDarkMode(layout));
        toggleDarkModeButton.setStyle("-fx-base: #34495e;"); // Set the initial style for the button
        layout.getChildren().addAll(startButton, inputBox, pushBox, buttonBox, stackBox, exitBox, toggleDarkModeButton);
        layout.setStyle("-fx-background-color: #2b2b2b;"); // Set the background color for the entire scene

        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void toggleDarkMode(VBox layout) {
        if (toggleDarkModeButton.isSelected()) {
            // Dark mode
            layout.setStyle("-fx-background-color: #2b2b2b;");
            // setButtonStyle("-fx-base: #34495e;"); // Set button color in dark mode
        } else {
            // Light mode
            layout.setStyle("-fx-background-color: #ffffff;");
            // setButtonStyle("-fx-base: #3498db;"); // Set button color in light mode
        }
    }

    private void showLearnText() {
        // Create a new stage for learning about stacks
        Stage learnStage = new Stage();
        learnStage.setTitle("Learn about Stacks");

        // Create a layout for the learning stage
        VBox learnLayout = new VBox(10);
        learnLayout.setAlignment(Pos.CENTER);
        learnLayout.setPadding(new Insets(20));

        // Learn messages
        String learnText = "What is Stack ? \n\n" +
                "Stack is a linear data structure that follows a particular order in which the operations are performed. "
                +
                "The order may be LIFO (Last In First Out) or FILO (First In Last Out). LIFO implies that the element that "
                +
                "is inserted last comes out first, and FILO implies that the element that is inserted first comes out last.\n\n"
                +
                "There are many real-life examples of a stack. Consider an example of plates stacked over one another in the "
                +
                "canteen. The plate which is at the top is the first one to be removed, i.e., the plate which has been placed "
                +
                "at the bottommost position remains in the stack for the longest period of time. So, it can be simply seen "
                +
                "to follow LIFO (Last In First Out)/FILO (First In Last Out) order.";

        Label learnLabel = new Label(learnText);
        learnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        learnLabel.setWrapText(true);

        // Create an exit button to close the learning stage
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> learnStage.close()); // Close only the learnStage
        exitButton.setStyle("-fx-base: #e74c3c;"); // Red for "Exit" button
        exitButton.setTooltip(new Tooltip("Exit Learning"));

        // Create an HBox for the exit button at the bottom right
        HBox exitBox = new HBox(10);
        exitBox.setAlignment(Pos.BOTTOM_RIGHT); // Position at the bottom right
        exitBox.getChildren().addAll(exitButton);

        // Add components to the learning layout
        learnLayout.getChildren().addAll(learnLabel, exitBox);

        // Create a scene for the learning stage
        Scene learnScene = new Scene(learnLayout, 500, 500);
        learnStage.setScene(learnScene);
        learnStage.show();
    }

    // Function to display the user guide
    private void showGuide() {
        // Create a new stage for the user guide
        Stage guideStage = new Stage();
        guideStage.setTitle("User Guide");

        // Create a layout for the user guide stage
        VBox guideLayout = new VBox(10);
        guideLayout.setAlignment(Pos.CENTER);
        guideLayout.setPadding(new Insets(20));

        // Guide messages
        String messages = "\tStack Emulator\n\n" +
                "This is a virtual environment or simulation where users can interact with and observe the operations of a "
                +
                "stack data structure.\n\n" +
                "Follow the steps below to explore the functionalities :\n\n" +
                "1. Set Size : Set the size of the array.\n" +
                "2. Push : Add an element to the stack.\n" +
                "3. Peek : View the top element without removing it.\n" +
                "4. Pop : Remove the top element from the stack.\n" +
                "5. Clear Stack : Remove all elements from the stack.\n" +
                "6. Reverse Stack : Reverse the order of elements in the stack.\n\n" +
                "Feel free to explore and use these functionalities!";

        Label label = new Label(messages);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        label.setWrapText(true);

        // Create an exit button to close the user guide stage
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> guideStage.close()); // Close only the guideStage
        exitButton.setStyle("-fx-base: #e74c3c;"); // Red for "Exit" button
        exitButton.setTooltip(new Tooltip("Exit Guide"));

        // Create an HBox for the exit button at the bottom right
        HBox exitBox = new HBox(10);
        exitBox.setAlignment(Pos.BOTTOM_RIGHT); // Position at the bottom right
        exitBox.getChildren().addAll(exitButton);

        // Add components to the user guide layout
        guideLayout.getChildren().addAll(label, exitBox);

        // Create a scene for the user guide stage
        Scene guideScene = new Scene(guideLayout, 500, 500);
        guideStage.setScene(guideScene);
        guideStage.show();
    }

    // Function to set the size of the array
    private void setArraySize(String input) {
        try {
            arraySize = Integer.parseInt(input);
            stack.clear();
            updateStackView();
        } catch (NumberFormatException e) {
            displayMessage("Invalid array size. Please enter a valid integer.");
        }
    }

    // Function to push an element onto the stack
    private void pushElement(String input) {
        if (arraySize == 0) {
            displayMessage("Please set the array size first.");
            return;
        }

        if (stack.size() < arraySize) {
            try {
                int element = Integer.parseInt(input);
                stack.push(element);
                updateStackView();
            } catch (NumberFormatException e) {
                displayMessage("Invalid input. Please enter an integer.");
            }
        } else {
            displayMessage("Stack is full. Cannot push more elements.");
        }
    }

    // Function to pop an element from the stack
    private void popElement() {
        if (stack.isEmpty()) {
            displayMessage("Stack is empty. Nothing to pop.");
        } else {
            // Apply fade-out animation to the top element before removing it
            StackBox topElement = (StackBox) stackBox.getChildren().get(stackBox.getChildren().size() - 1);
            applyFadeOutAnimation(topElement, () -> {
                stack.pop();
                updateStackView();
            });
        }
    }

    // Function to peek at the top element of the stack
    private void peekElement() {
        if (stack.isEmpty()) {
            displayMessage("Stack is empty.");
        } else {
            int val = stack.peek();
            displayMessage("Top Element: " + val);
        }
    }

    // Function to clear all elements from the stack
    private void clearStack() {
        if (stack.isEmpty()) {
            displayMessage("Stack is empty. Nothing to clear.");
        } else {
            stack.clear();
            updateStackView();
        }
    }

    // Function to reverse the order of elements in the stack
    private void reverseStack() {
        if (stack.isEmpty()) {
            displayMessage("Stack is empty. Nothing to reverse.");
        } else {
            Stack<Integer> reversedStack = new Stack<>();
            while (!stack.isEmpty()) {
                reversedStack.push(stack.pop());
            }
            stack = reversedStack;
            updateStackView();
        }
    }

    // Function to display a message in an alert dialog
    private void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Function to update the visual representation of the stack
    private void updateStackView() {
        stackBox.getChildren().clear();

        for (int i = stack.size() - 1; i >= 0; i--) {
            StackBox stackElement = new StackBox(stack.get(i).toString());
            stackElement.setPrefWidth(200); // Set the preferred width to 200

            // Apply fade-in animation to all elements
            applyFadeInAnimation(stackElement);

            if (i == stack.size() - 1) {
                // Change the arrow pointer to a text label that says "TOP"
                Label topLabel = new Label("TOP");
                topLabel.setStyle("-fx-text-fill: white;");
                topLabel.setTranslateX(20); // Adjust the position of the label
                topLabel.setTranslateY(-5); // Adjust the position of the label
                stackElement.getChildren().add(topLabel);

                // Apply bouncing animation to the top element
                applyBounceAnimation(stackElement);
            }

            stackBox.getChildren().add(stackElement);
        }
    }

    // Function to apply a bouncing animation to a stack element
    private void applyBounceAnimation(StackBox stackElement) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), stackElement);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.play();
    }

    // Function to apply a fade-in animation to a stack element
    private void applyFadeInAnimation(StackBox stackElement) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), stackElement);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    // Function to apply a fade-out animation to a stack element
    private void applyFadeOutAnimation(StackBox stackElement, Runnable onFinish) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), stackElement);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> onFinish.run());
        fadeTransition.play();
    }

    // Custom StackPane class representing a stack element
    private class StackBox extends StackPane {
        public StackBox(String value) {
            setPrefSize(50, 50);
            setStyle("-fx-background-color: #009688; -fx-border-color: #004D40; -fx-border-width: 1px;");
            Label label = new Label(value);
            label.setStyle("-fx-text-fill: white;");
            getChildren().add(label);
        }
    }
}
