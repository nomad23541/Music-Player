package com.chrisreading.musicplayer;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class
 */
public class MainApp extends Application {
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Music Player");
	}
	
	/**
	 * Gets the primary stage
	 * @return the primary stage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
