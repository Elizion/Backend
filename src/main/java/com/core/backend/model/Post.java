package com.core.backend.model;

public class Post {
	  //adding fields
	  private int id;
	  private String title;
	  private String content;

	  //adding super constructor
	  public Post() {
		  
	  super();
	  // TODO Auto-generated constructor stub
	  }

	  //adding parameterized constructor
	  public Post(int id, String title, String content) {
		  super();
		  this.id = id;
		  this.title = title;
		  this.content = content;
	  }

	  //adding getters and setters
	  public int getId() {
	    return id;
	  }
	  public void setId(int id) {
	    this.id = id;
	  }
	  public String getTitle() {
	    return title;
	  }
	  public void setTitle(String title) {
	    this.title = title;
	  }
	  public String getContent() {
	    return content;
	  }
	  public void setContent(String content) {
	    this.content = content;
	  }

	  //adding to toString() method
	  @Override
	  public String toString() {
	    return "Post [id=" + id + ", title=" + title + ", content=" + content + "]";
	  }
	  
}