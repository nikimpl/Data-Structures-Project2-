import java.io.*;
import java.util.*;


public class Top_k_withPQ extends PQ{
	
	private static int k;
	private static File f = null;
	private static BufferedReader reader = null;
	private static SongComparator cmp = new SongComparator();
	
	//constructor for class Top_k_withPQ
	Top_k_withPQ(int capacity, SongComparator cmp){
		super(capacity, cmp);
	}
	
	public static void topsongs(int k){
		PQ songs = new PQ(2*k,cmp);
		try{
			//Reads line
			String line = reader.readLine();
			//Scans line
			Scanner scan = new Scanner(line);
			
			//Checks if line is not null
			while(line != null){
				//First token is id(this is taken for granted)		
				int id = scan.nextInt();
				//Second token is the title
				String title = scan.next();
				//If the title has more than one words in it then add the next tokens that aren't integers
				while(!scan.hasNextInt()){
					title = title + " " + scan.next();
				}
				//After the title , the next token, and the final one, is the number of likes
				int likes = scan.nextInt();
				//Creates new Song object with the characteristics from this line
				Song s = new Song(id,title,likes);
				//If this is the first song, we just insert it to the priority queue
				if (songs.isEmpty()) {
					songs.insert(s);
				}
				else{
					//We find the song at the end of the priority queue and name it "l" so that we can reference it later
					Song l = songs.find(songs.size());
					//If we have k songs in the queue, we remove the song with the smallest priority
					if(k==(songs.size()-1)) songs.remove(l.getID());
					//Then we insert the song s
					songs.insert(s);
				}
				//Reads next line
				line = reader.readLine();
				
				if (line !=null){
					//If there is a next line, then scan it
					scan = new Scanner(line);
				}
			}
			//Close scanner
			scan.close();
			
			//Prints the top k songs
			System.out.println("The top " + k + " songs are:");
			for(int i=1; i<=k; i++){
				System.out.println(songs.getmax().getTitle());
			}
		}
		catch (IOException e) {
            System.out.println("Error reading line " );
		}
	}
	
	public static void main(String[] args) {
		
		//reads k from user
		int k = Integer.parseInt(args[0]);

		//find file
        try{
            f = new File(args[1]);
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

		//prints k top songs
        topsongs(k);
        
		//close file
        try {
            reader.close();
        }
        catch (IOException e){
            System.err.println("Error closing file.");
        }
    }
}