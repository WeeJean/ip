package gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import weewee.Weewee;
import weewee.parser.CommandParser;
import weewee.ui.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Weewee weewee;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser2.png"));
    private final Image weeweeImage = new Image(this.getClass().getResourceAsStream("/images/DaWeewee.png"));
    private final Image weeweeImage2 = new Image(this.getClass().getResourceAsStream("/images/DaWeewee2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setWeewee(Weewee weewee, Ui ui) {
        this.weewee = weewee;
        dialogContainer.getChildren().add(
                DialogBox.getWeeweeDialog(ui.showGreet(), weeweeImage2, CommandParser.Command.UNIDENTIFIED)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = weewee.getResponse(input);
        CommandParser.Command commandType = weewee.getCommandType();
        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getWeeweeDialog(response, weeweeImage2, commandType)
            );
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getWeeweeDialog(response, weeweeImage, commandType)
            );
        }
        userInput.clear();
    }
}

