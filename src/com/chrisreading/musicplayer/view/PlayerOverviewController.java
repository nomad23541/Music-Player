package com.chrisreading.musicplayer.view;

import com.chrisreading.musicplayer.MainApp;
import com.chrisreading.musicplayer.model.Song;
import com.chrisreading.musicplayer.util.TimeUtil;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

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
	@FXML
	private Slider volumeSlider;
	@FXML
	private Slider seekSlider;
	@FXML
	private Label timeLabel;
	@FXML
	private Label durationLabel;
	
	/** Previous song that was played */
	private Song prevSong;
	
	/** Currently used MediaPlayer */
	private MediaPlayer player;
	
	/** Minimum changed allowed when seeking */
	private final double MIN_CHANGE = 0.5;

	/**
	 * Constructor
	 */
	public PlayerOverviewController() {
		
	}

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
		if(prevSong != null) {
			// stop song previously playing
			prevSong.getMediaPlayer().stop();
		}
		
		Song song = songTable.getSelectionModel().getSelectedItem();	
		song.getMediaPlayer().volumeProperty().bindBidirectional(volumeSlider.valueProperty());
		
		if(song.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
			song.getMediaPlayer().pause();
			playButton.setText("▶");
		} else {
			song.getMediaPlayer().play();
			playButton.setText("▮▮");
		}
		
		player = song.getMediaPlayer();
		prevSong = song;
		
		initSeekFunction(player, seekSlider);
	}
	
	/**
	 * Goes to the next song
	 */
	@FXML
	private void handleNext() {
		prevSong.getMediaPlayer().stop();
		
		// select next song below and play
		songTable.getSelectionModel().select(songTable.getSelectionModel().getSelectedIndex() + 1);
		Song song = songTable.getSelectionModel().getSelectedItem();
		song.getMediaPlayer().play();
		song.getMediaPlayer().volumeProperty().bindBidirectional(volumeSlider.valueProperty());
		
		player = song.getMediaPlayer();
		prevSong = song;
		
		initSeekFunction(player, seekSlider);
	}
	
	/**
	 * Goes to a previous song
	 */
	@FXML
	private void handlePrevious() {
		prevSong.getMediaPlayer().stop();
		
		// select next song below and play
		songTable.getSelectionModel().select(songTable.getSelectionModel().getSelectedIndex() - 1);
		Song song = songTable.getSelectionModel().getSelectedItem();
		song.getMediaPlayer().play();
		song.getMediaPlayer().volumeProperty().bindBidirectional(volumeSlider.valueProperty());
		
		player = song.getMediaPlayer();
		prevSong = song;
		
		initSeekFunction(player, seekSlider);

	}
	
	private void initSeekFunction(MediaPlayer player, Slider seekSlider) {
		player.totalDurationProperty().addListener((obs, oldDuration, newDuration) -> seekSlider.setMax(newDuration.toSeconds()));
		
		seekSlider.setMin(0);
		seekSlider.setMax(player.getTotalDuration().toSeconds());

		seekSlider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
			if (!isChanging) {
				player.seek(Duration.seconds(seekSlider.getValue()));
			}
		});

		seekSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (!seekSlider.isValueChanging()) {
				double currentTime = player.getCurrentTime().toSeconds();
				if (Math.abs(currentTime - newValue.doubleValue()) > MIN_CHANGE) {
					player.seek(Duration.seconds(newValue.doubleValue()));
				}
			}
		});

		player.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
			if (!seekSlider.isValueChanging()) {
				seekSlider.setValue(newTime.toSeconds());
				timeLabel.setText(TimeUtil.formatTime(player.getCurrentTime(), player.getTotalDuration()));
			}
		});
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
