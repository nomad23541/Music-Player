package com.chrisreading.musicplayer.view;

import com.chrisreading.musicplayer.MainApp;

import javafx.fxml.FXML;

/**
 * Controller class for the PlayerOverview fxml
 */
public class PlayerOverviewController {
	
	/** Reference to the main class */
	private MainApp mainApp;
	
	/**
	 * Constructor
	 */
	public PlayerOverviewController() {}

	/**
	 * Called before the constructor
	 */
	@FXML
	private void initialize() {}
	
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
	}
	
}
