package com.chrisreading.musicplayer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.MapChangeListener;
import javafx.collections.MapChangeListener.Change;
import javafx.scene.media.Media;

/**
 * Model for a song (mp3)
 */
public class Song {
	
	/** Location of the music file */
	private StringProperty file;
	
	/** Song Metadata */
	private StringProperty title, artist, album, length;
	
	/** Location turned to media */
	private Media media;
	
	public Song() {
		this(null);
	}
	
	public Song(String file) {
		this.file = new SimpleStringProperty(file);
		media = new Media(this.file.get());
		
		// retrieve the mp3 metadata
		media.getMetadata().addListener(new MapChangeListener<String, Object>() {
			@Override
			public void onChanged(Change<? extends String, ? extends Object> ch) {
				if(ch.wasAdded()) {
					handleMetaData(ch.getKey(), ch.getValueAdded());
				}
			}
		});
	}
	
	/**
	 * Grabs the Mp3's metadata values
	 */
	private void handleMetaData(String key, Object value) {
		if(key.equals("album"))
			album = new SimpleStringProperty(value.toString());
		else if(key.equals("artist"))
			artist = new SimpleStringProperty(value.toString());
		if(key.equals("title"))
			title = new SimpleStringProperty(value.toString());
		if(key.equals("length"))
			length = new SimpleStringProperty(value.toString());
		
	}
	
	/** GETTER & SETTERS */
	
	public StringProperty getProperty() {
		return file;
	}
	
	public String getFile() {
		return file.get();
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
	
	public StringProperty getLengthProperty() {
		return length;
	}
	
	public String getLength() {
		return length.get();
	}

}
