


import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox implements EventHandler<ActionEvent>{
	
	private Stage window;
	private Scene scene;
	private VBox layout;
	private Button okButton;
	private Label message;
	
	public AlertBox(String title, String messageStr) {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		
		layout = new VBox(20);
		layout.setAlignment(Pos.CENTER);
		scene = new Scene(layout, 300, 100);
		okButton = new Button("OK");
		okButton.setOnAction(this);
		okButton.setAlignment(Pos.CENTER);
		
		
		message = new Label(messageStr);
		message.setAlignment(Pos.CENTER);
		message.setFont(new Font("Cambria", 10));
		
		
		layout.getChildren().addAll(message, okButton);
		window.setScene(scene);
		window.showAndWait();
		
	}
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == okButton) {
			window.close();
		}
	}
}
