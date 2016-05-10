package com.chrisreading.musicplayer.view;

import com.chrisreading.musicplayer.MainApp;
import com.chrisreading.musicplayer.model.Song;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

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
	private TableColumn<Song, String> yearColumn;
	@FXML
	private Button playButton;	
	@FXML
	private Button previousButton;
	@FXML
	private Button nextButton;
	@FXML
	private ImageView albumImage;
	@FXML
	private Label songLabel;
	@FXML
	private Label artistLabel;
	@FXML
	private Label albumLabel;

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
		yearColumn.setCellValueFactory(cellData -> cellData.getValue().getYearProperty());
		
		// update song details when a row is selected
		songTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				Song selectedSong = songTable.getSelectionModel().getSelectedItem();
				albumImage.setImage(selectedSong.getImage());
				songLabel.setText(selectedSong.getTitle());
				artistLabel.setText(selectedSong.getArtist());
				albumLabel.setText(selectedSong.getAlbum());
				
			}
		});
		
		// play song if row is double clicked on
		songTable.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					handlePlayPause();
				}
			}
		});
	}
	
	/**
	 * Handles either playing or pausing
	 * the currently selected song
	 */
	@FXML
	private void handlePlayPause() {
		Song currentSong = songTable.getSelectionModel().getSelectedItem();	
		
		if(currentSong.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
			currentSong.getMediaPlayer().pause();
			playButton.setText("▶");
		} else {
			currentSong.getMediaPlayer().play();
			playButton.setText("▮▮");
		}
	}
	
	/**
	 * Goes to the next song
	 */
	@FXML
	private void handleNext() {	
		// stop previously playing song
		Song prevSong = songTable.getSelectionModel().getSelectedItem();
		prevSong.getMediaPlayer().stop();
		
		// select next song below and play
		songTable.getSelectionModel().select(songTable.getSelectionModel().getSelectedIndex() + 1);
		songTable.getSelectionModel().getSelectedItem().getMediaPlayer().play();
	}
	
	/**
	 * Goes to a previous song
	 */
	@FXML
	private void handlePrevious() {
		// stop previously playing song
		Song prevSong = songTable.getSelectionModel().getSelectedItem();
		prevSong.getMediaPlayer().stop();
		
		// select next song below and play
		songTable.getSelectionModel().select(songTable.getSelectionModel().getSelectedIndex() - 1);
		songTable.getSelectionModel().getSelectedItem().getMediaPlayer().play();
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
