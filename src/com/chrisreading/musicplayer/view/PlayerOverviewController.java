package com.chrisreading.musicplayer.view;

import com.chrisreading.musicplayer.MainApp;
import com.chrisreading.musicplayer.model.Song;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

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
	@FXML
	private Button playButton;	
	@FXML
	private Button previousButton;
	@FXML
	private Button nextButton;
	@FXML
	private Label songLabel;
	@FXML
	private ImageView albumImage;
	
	private Song prevSong, currentSong;
	
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
		lengthColumn.setCellValueFactory(cellData -> cellData.getValue().getYearProperty());
	}
	
	/**
	 * Handles either playing or pausing
	 * the currently selected song
	 */
	@FXML
	private void handlePlayPause() {		
		currentSong = songTable.getSelectionModel().getSelectedItem();	
		currentSong.play();
	}
	
	/**
	 * Goes to the next song
	 */
	@FXML
	private void handleNext() {	
		// stop previously playing song
		Song prevSong = songTable.getSelectionModel().getSelectedItem();
		prevSong.stop();
		prevSong.stop();
		
		// select next song below and play
		songTable.getSelectionModel().select(songTable.getSelectionModel().getSelectedIndex() + 1);
		songTable.getSelectionModel().getSelectedItem().play();
	}
	
	/**
	 * Goes to a previous song
	 */
	@FXML
	private void handlePrevious() {
		// stop previously playing song
		Song prevSong = songTable.getSelectionModel().getSelectedItem();
		prevSong.stop();
		prevSong.stop();
		
		// select next song below and play
		songTable.getSelectionModel().select(songTable.getSelectionModel().getSelectedIndex() - 1);
		songTable.getSelectionModel().getSelectedItem().play();
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
