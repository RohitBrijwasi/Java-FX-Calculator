package application;

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

public class Main extends Application {
	private TextField textfeild = new TextField();
	private long num1 = 0;
	private String op = "";
	private boolean start = true;

	public void start(Stage primaryStage) throws Exception {
		textfeild.setFont(Font.font(30));
		textfeild.setPrefHeight(60);
		textfeild.setAlignment(Pos.CENTER_RIGHT);
		textfeild.setEditable(false);

		StackPane stackpane = new StackPane();
		stackpane.setPadding(new Insets(15));
		stackpane.getChildren().add(textfeild);

		TilePane tile = new TilePane();
		tile.setHgap(10);
		tile.setVgap(10);
		tile.setAlignment(Pos.TOP_CENTER);

		BorderPane root = new BorderPane();
		root.setTop(stackpane);
		root.setCenter(tile);

		tile.getChildren().add(createButtonForNumber("7"));
		tile.getChildren().add(createButtonForNumber("8"));
		tile.getChildren().add(createButtonForNumber("9"));
		tile.getChildren().add(createButtonForOperator("/"));

		tile.getChildren().add(createButtonForNumber("4"));
		tile.getChildren().add(createButtonForNumber("5"));
		tile.getChildren().add(createButtonForNumber("6"));
		tile.getChildren().add(createButtonForOperator("x"));

		tile.getChildren().add(createButtonForNumber("1"));
		tile.getChildren().add(createButtonForNumber("2"));
		tile.getChildren().add(createButtonForNumber("3"));
		tile.getChildren().add(createButtonForOperator("-"));

		tile.getChildren().add(createButtonForNumber("0"));
		tile.getChildren().add(createButtonForClear("C"));
		tile.getChildren().add(createButtonForOperator("="));
		tile.getChildren().add(createButtonForOperator("+"));

		Scene scene = new Scene(root, 280, 360);
		primaryStage.setScene(scene);
		primaryStage.setTitle("My calculator");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	private Button createButtonForNumber(String ch) {
		Button button = new Button(ch);
		button.setFont(Font.font(22));
		button.setPrefSize(55, 55);
		button.setOnAction(this::processNumber);
		return button;
	}

	private Button createButtonForOperator(String ch) {
		Button button = new Button(ch);
		button.setFont(Font.font(22));
		button.setPrefSize(55, 55);
		button.setPrefSize(55, 55);
		button.setOnAction(this::processOperator);
		return button;
	}

	private Button createButtonForClear(String ch) {
		Button button = new Button(ch);
		button.setFont(Font.font(22));
		button.setPrefSize(55, 55);
		button.setPrefSize(55, 55);
		button.setOnAction(e -> {
			textfeild.setText("");
			op = "";
			start = true;

		});
		return button;
	}

	private void processNumber(ActionEvent e) {
		if (start) {
			textfeild.setText("");
			start = false;
		}
		String value = ((Button) e.getSource()).getText();
		textfeild.setText(textfeild.getText() + value);
	}

	private void processOperator(ActionEvent e) {
		String value = ((Button) e.getSource()).getText();
		if (!value.equals("=")) {
			if (!op.isEmpty())
				return;
			num1 = Long.parseLong(textfeild.getText());
			op = value;
			textfeild.setText("");
		} else {
			if (op.isEmpty())
				return;
			long num2 = Long.parseLong(textfeild.getText());
			float final_result = calculate(num1, num2, op);
			textfeild.setText(String.valueOf(final_result));
			start = true;
			op = "";
		}
	}

	private float calculate(long num1, long num2, String Operator) {
		switch (Operator) {
		case "+":
			return num1 + num2;
		case "-":
			return num1 - num2;
		case "x":
			return num1 * num2;
		case "/":
			if (num2 == 0)
				return 0;
			return num1 / num2;
		default:
			return 0;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
