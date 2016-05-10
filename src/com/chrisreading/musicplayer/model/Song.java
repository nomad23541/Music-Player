package com.chrisreading.musicplayer.model;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Model for a song (mp3)
 */
public class Song {
	
	/** Location of the music file */
	private File file;
	
	/** Song Metadata */
	private StringProperty title, artist, album, year;
	private ObjectProperty<Image> image; 
	
	/** Location turned to media */
	private Media media;
	
	/** The media player */
	private MediaPlayer player;
	
	public Song() {
		this(null);
	}
	
	public Song(File file) {
		this.file = file;
		title = new SimpleStringProperty();
		artist = new SimpleStringProperty();
		album = new SimpleStringProperty();
		year = new SimpleStringProperty();
		image = new SimpleObjectProperty<Image>();
		
		media = new Media(file.toURI().toString());
		
		// retrieve the mp3 metadata
		media.getMetadata().addListener(new MapChangeListener<String, Object>() {
			@Override
			public void onChanged(Change<? extends String, ? extends Object> ch) {
				if(ch.wasAdded()) {
					handleMetaData(ch.getKey(), ch.getValueAdded());
				}
			}
		});
		
		// ini the player
		player = new MediaPlayer(media);
	}
	
	/**
	 * Grabs the Mp3's metadata values
	 */
	private void handleMetaData(String key, Object value) {
		if(key.equals("album"))
			album.set(value.toString());
		else if(key.equals("artist"))
			artist.set(value.toString());
		if(key.equals("title"))
			title.set(value.toString());
		if(key.equals("year"))
			year.set(value.toString());
		if(key.equals("image"))
			image.set((Image) value);
			
	}
	
	/** GETTER & SETTERS */
	
	public File getFile() {
		return file;
	}
	
	public MediaPlayer getMediaPlayer() {
		return player;
	}
	
	public Media getMedia() {
		return media;
	}
	
	public StringProperty getTitleProperty() {
		return title;
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public StringProperty getAlbumProperty() {
		return album;
	}
	
	public String getAlbum() {
		return album.get();
	}
	
	public StringProperty getArtistProperty() {
		return artist;
	}
	
	public String getArtist() {
		return artist.get();
	}
	
	public StringProperty getYearProperty() {
		return year;
	}
	
	public String getYear() {
		return year.get();
	}
	
	public ObjectProperty<Image> getImageProperty() {
		return image;
	}
	
	public Image getImage() {
		return image.get();
	}

}
