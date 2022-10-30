package imoussoura.calculatrice;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalculatorController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}