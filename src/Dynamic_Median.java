import java.io.*;
import java.util.*;


public class Dynamic_Median {
	
	private static File f = null;
	private static BufferedReader reader = null;
	private static Song median = new Song();
	
	
	public static void median(){
		
		//comparator for the min-heap
		SongComparator min=new SongComparator() {
            @Override
            public int compare(Song s1, Song s2) {
                if (s1.getLikes() == s2.getLikes()){
					return (s2.getTitle().compareTo(s1.getTitle()))*(-1);
					} 
				else
					return (Integer.compare(s1.getLikes(),s2.getLikes()))*(-1);
            }
        };

		//comparator for the max-heap
        SongComparator max=new SongComparator() {
            @Override
            public int compare(Song s1, Song s2) {
				if (s1.getLikes() == s2.getLikes()){
					return s2.getTitle().compareTo(s1.getTitle());
					} 
				else
					return Integer.compare(s1.getLikes(),s2.getLikes());
            }
        };
		
		PQ minheap = new PQ(2,min); //min-heap
		PQ maxheap = new PQ(2,max); //max-heap
		int i = 0;	//line counter
		
		try{
			String line = reader.readLine();
			Scanner scan = new Scanner(line);
			while(line != null){
				int id = scan.nextInt();
				String title = scan.next();
				while(!scan.hasNextInt()){
					title = title + " " + scan.next();
				}
				int likes = scan.nextInt();
				Song s = new Song(id,title,likes);
				
				median = s;
				
				
				if (max.compare(s,median)<=0)
                        minheap.insert(s);
                    else
                        maxheap.insert(s);

				if(minheap.size()-maxheap.size()>1 || minheap.size()-maxheap.size()<-1){
					if(minheap.size()>maxheap.size()){
						maxheap.insert(median);
						median=minheap.getmax();
					}
					else{
						minheap.insert(median);
						median=maxheap.getmax();
					}
				}
				
				//Increases line counter
				i++;
				//Every 5 lines, print the median
				if(i%5==0)
					System.out.println("Median = " + median.getLikes() + " likes, achieved by Song: " + median.getTitle());
				//Reads next line
				line = reader.readLine();
				
				if (line !=null){
					//If there is a next line, then scan it
					scan = new Scanner(line);
				}
			}
			//Close scanner
			scan.close();
		}
		catch (IOException e) {
            System.out.println("Error reading line " );
		}
	}
	
	public static void main(String[] args) {
		
		
		//find file
        try{
            f = new File(args[0]);
        }
        catch (NullPointerException e){
            System.err.println ("File not found.");
        }
	
		//open file
        try{
            reader = new BufferedReader(new FileReader(f));
        }
        catch (FileNotFoundException e ){
            System.err.println("Error opening file. Terminating.");
            System.exit(1); 
        }

		//Calculates and print median
        median();
        
		//close file
        try {
            reader.close();
        }
        catch (IOException e){
            System.err.println("Error closing file.");
        }
    }
}