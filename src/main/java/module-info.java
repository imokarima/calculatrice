module imoussoura.calculatrice {
    requires javafx.controls;
    requires javafx.fxml;


    opens imoussoura.calculatrice to javafx.fxml;
    exports imoussoura.calculatrice;
}