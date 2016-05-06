package com.chrisreading.musicplayer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;

/**
 * Model for a song (mp3)
 */
public class Song {
	
	/** Location of the music file */
	private StringProperty file;
	
	/** Location turned to media */
	private Media media;
	
	public Song() {
		this(null);
	}
	
	public Song(String file) {
		this.file = new SimpleStringProperty(file);
		media = new Media(this.file.get());
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

}
