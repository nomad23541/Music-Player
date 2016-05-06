package com.chrisreading.musicplayer;

import java.io.IOException;

import com.chrisreading.musicplayer.model.Song;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main class
 */
public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout; // layout of the root 
	
	private ObservableList<Song> songData = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Music Player");
		
		initRootLayout();
		showPlayerOverview();
	}
	
	/**
	 * Gets the song data
	 * @return the song data
	 */
	public ObservableList<Song> getSongData() {
		return songData;
	}
	
	/**
	 * Initializes the root layout
	 */
	public void initRootLayout() {
		try {
			// load root layout from file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// show the scene containing rootlayout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Shows the player overview inside of the rootlayout
	 */
	public void showPlayerOverview() {
		try {
			// load to do overview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PlayerOverview.fxml"));
			AnchorPane todoOverview = (AnchorPane) loader.load();
			
			// set person overview to center of root layout
			rootLayout.setCenter(todoOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
