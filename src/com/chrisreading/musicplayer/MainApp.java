package com.chrisreading.musicplayer;

import java.io.File;
import java.io.IOException;

import com.chrisreading.musicplayer.model.Song;
import com.chrisreading.musicplayer.view.PlayerOverviewController;

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
		
		// add some sample data
		/*
		songData.add(new Song(new File("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3")));
		songData.add(new Song(new File("C:\\Users\\Public\\Music\\Sample Music\\Maid with the Flaxen Hair.mp3")));
		songData.add(new Song(new File("C:\\Users\\Public\\Music\\Sample Music\\Sleep Away.mp3")));
		*/
		
		songData.add(new Song(new File("C:\\Users\\Chris\\Music\\Aylius - Hoodlums (feat. SBF).mp3")));
		songData.add(new Song(new File("C:\\Users\\Chris\\Music\\Aylius - Psychotic (feat. Katie Joy).mp3")));
		songData.add(new Song(new File("C:\\Users\\Chris\\Music\\Zomboy - Here To Stay feat. Lady Chann (Aylius Remix) 1.mp3")));
		songData.add(new Song(new File("C:\\Users\\Chris\\Music\\Seven Lions feat. Fiora - Days to Come (Aylius Remix).mp3")));
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
			
			// give controller access to the mainapp
			PlayerOverviewController controller = loader.getController();
			controller.setMainApp(this);
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
