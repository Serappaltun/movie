package tr.com.serap.movie.request;

import lombok.Data;

@Data
public class MovieSaveRequest {
	
	private String imdbID;
	
	private String title;
	
	private String year;
	
	private String type;
	
	private String poster;
}