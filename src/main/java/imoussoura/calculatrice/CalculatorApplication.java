package imoussoura.calculatrice;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApplication extends Application {
    private TextField textField=new TextField();
    private long num1=0;
    private String op="";
    private boolean start=true;
    @Override
    public void start(Stage primaryStage) throws IOException {
        textField.setFont(Font.font(20));
        textField.setPrefHeight(50);
        textField.setAlignment(Pos.CENTER_RIGHT);
        StackPane stackpane=new StackPane();
        stackpane.setPadding(new Insets(10));
        stackpane.getChildren().add(textField);

        TilePane tile=new TilePane();
        tile.setHgap(10);
        tile.setVgap(10);
        tile.setAlignment(Pos.TOP_CENTER);
        tile.getChildren().addAll(
                createNumbers("7"),
                createNumbers("8"),
                createNumbers("9"),
                createOperators("/"),

                createNumbers("4"),
                createNumbers("5"),
                createNumbers("6"),
                createOperators("x"),

                createNumbers("1"),
                createNumbers("2"),
                createNumbers("3"),
                createOperators("-"),

                createNumbers("0"),
                createClear("C"),
                createOperators("="),
                createOperators("+")

        );

        BorderPane root=new BorderPane();
        root.setTop(stackpane);
        root.setCenter(tile);
        Scene scene=new Scene(root,250,310);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Calculator");
        primaryStage.setResizable(false);
        primaryStage.show();

        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
    }

    private Button createNumbers(String s){
        Button button=new Button(s);
        button.setFont(Font.font(18));
        button.setPrefSize(50,50);
        button.setOnAction(this::processNumber);
        return button;
    }
    private Button createOperators(String s){
        Button button=new Button(s);
        button.setFont(Font.font(18));
        button.setPrefSize(50,50);
        button.setOnAction(this::processOperator);

        return button;
    }
    private Button createClear(String s){
        Button button=new Button(s);
        button.setFont(Font.font(18));
        button.setPrefSize(50,50);
        button.setOnAction(e->{
            textField.setText((""));
            op="";
            start=true;
        });
        return button;
    }
    private void processNumber(ActionEvent e){
        if(start){
            textField.setText("");
            start=false;
        }
        String value=((Button)e.getSource()).getText();
        textField.setText(textField.getText()+value);

    }

    private void processOperator(ActionEvent e) {
        String value = ((Button) e.getSource()).getText();
        if (!value.equals("=")) {
            if (!op.isEmpty())
                return;
                num1 = Long.parseLong(textField.getText());
                op = value;
                textField.setText("");

            } else {
                if(op.isEmpty())
                    return;
                Long num2=Long.parseLong(textField.getText());
                float res=calculate(num1,num2,op);
                textField.setText(String.valueOf(res));
                start=true;
                op="";
            }
    }

    private float calculate(Long num1, Long num2, String operator) {
        switch (operator){
            case "+": return num1+num2;
            case "-": return num1-num2;
            case "x": return num1*num2;
            case "/":
                if(num2==0)
                    return 0;
                return num1/num2;
            default: return 0;
        }
    }
    public static void main(String[] args) {
        launch();
    }
}