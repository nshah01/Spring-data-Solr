package com.solr.model;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "books")
public class Books {
	
	@Id
	@Field
	private String id;
	
	@Field
	private String name;
	
	@Field
	private String genre;
	
	@Field
	private String author;


	@Override
	public String toString() {
		return "Books [id=" + id + ", name=" + name + ", genre=" + genre + ", author=" + author + "]";
	}

	public Books() {
		
	}
	public Books(String value, long valueCount) {
		// TODO Auto-generated constructor stub
	}
    
	public Books(String id, String name, String genre, String author) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
	
	
	
	

}
