import java.util.Comparator;

public class SongComparator extends Song implements Comparator<Song>{
	
	//@Override
	public int compare(Song s1,Song s2) {
		
		//If two songs have the same likes, compare the title instead
		if (s1.getLikes() == s2.getLikes()){
			return s2.getTitle().compareTo(s1.getTitle());
			}
		else
			return Integer.compare(s1.getLikes(),s2.getLikes());
	}
}