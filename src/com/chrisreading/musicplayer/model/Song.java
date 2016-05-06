package com.chrisreading.musicplayer.model;

import java.io.File;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;

/**
 * Model for a song (mp3)
 */
public class Song {
	
	/** Location of the music file */
	private File file;
	
	/** Song Metadata */
	private StringProperty title, artist, album, length;
	
	/** Location turned to media */
	private Media media;
	
	public Song() {
		this(null);
	}
	
	public Song(File file) {
		this.file = file;
		title = new SimpleStringProperty();
		artist = new SimpleStringProperty();
		album = new SimpleStringProperty();
		length = new SimpleStringProperty();
		
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
			length.set(value.toString());	
	}
	
	/** GETTER & SETTERS */
	
	public File getFile() {
		return file;
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
