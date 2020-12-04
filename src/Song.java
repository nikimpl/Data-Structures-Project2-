public class Song implements Comparable<Song>{

	private int id;
	private String title;
	private int likes;

	//empty constructor
	public Song(){
		this(0,"",0);
	}
	
	//constructor
	public Song(int id, String title, int likes){
		this.id = id;
		this.title = title;
		this.likes = likes;
	}
	
	//compareTo method
	@Override
	public int compareTo(Song other) {
		if (this.likes == other.likes)
			return this.getTitle().compareTo(other.getTitle());
			
		else
			return Integer.compare(this.getLikes(),other.getLikes());
	}
	
	//getters
	public int getID(){ return this.id; }
	
	public String getTitle(){ return this.title; }
	
	public int getLikes(){ return this.likes; }
	
	//setters
	public void setID(int id){ this.id = id; }
	
	public void setTitle(String title){ this.title = title; }
	
	public void setLikes (int likes){ this.likes = likes; }
} 