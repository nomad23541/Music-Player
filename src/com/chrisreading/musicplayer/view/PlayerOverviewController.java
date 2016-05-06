package com.chrisreading.musicplayer.view;

import com.chrisreading.musicplayer.MainApp;
import com.chrisreading.musicplayer.model.Song;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller class for the PlayerOverview fxml
 */
public class PlayerOverviewController {
	
	/** Reference to the main class */
	private MainApp mainApp;
	
	@FXML
	private TableView<Song> songTable;
	@FXML
	private TableColumn<Song, String> titleColumn;
	@FXML
	private TableColumn<Song, String> artistColumn;
	@FXML
	private TableColumn<Song, String> albumColumn;
	@FXML
	private TableColumn<Song, String> lengthColumn;
	
	/**
	 * Constructor
	 */
	public PlayerOverviewController() {}

	/**
	 * Called before the constructor
	 */
	@FXML
	private void initialize() {
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
		artistColumn.setCellValueFactory(cellData -> cellData.getValue().getArtistProperty());
		albumColumn.setCellValueFactory(cellData -> cellData.getValue().getAlbumProperty());
		// don't set length album for now
	}
	
	/**
	 * Handles either playing or pausing
	 * the currently selected song
	 */
	@FXML
	private void handlePlayPause() {
		
	}
	
	/**
	 * Stops the playing completely.
	 */
	@FXML
	private void handleStop() {
		
	}
	
	/**
	 * Sets the main app, makes it accessible for this class
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// use the list from the main class to fill in the table
		songTable.setItems(mainApp.getSongData());	
	}
	
}
