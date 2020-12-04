import java.io.*;
import java.util.*;


public class Top_k extends Song{
	
	
	private static File f = null;
	private static BufferedReader reader = null;
	//Creates an array of Songs objects called songs
	private static Song songs[] = new Song[100];
	
	//exch method
	static void exch(Song[] a, int i, int j) {
		Song t = a[i]; a[i] = a[j]; a[j] = t; 
	}
	
	//quicksort method
	static void quicksort(Song[] a, int p, int r){ 
		if (r <= p) return;
		
		int i = partition(a, p, r);
		
		quicksort(a, p, i-1);
		quicksort(a, i+1, r);
	}

	//partition method
	static int partition(Song a[], int p, int r){ 
		
		int i = p-1; 
		int j = r; 
		
		Song v = a[r];
		
		for(;;){ 	
			
			while (a[++i].compareTo(v) == -1);
			while (v.compareTo(a[--j]) == -1)
				if (j == p) break;
			
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, i, r);
		return i; 
	} 
	
	//method that sorts the array in an ascending order based on the number of likes
	//in case of the same number of likes, the sorting is based on the alphabetic order
	private static void topsongs(int k){
	
		try{
			//Reads line
			String line = reader.readLine();
			//Scans line
			Scanner scan = new Scanner(line);
			
			//Counter for songs added to the array
			int m = 0;
			//Checks if line is not null
			while(line != null){
				//First token is id(this is taken for granded)			
				String id = scan.next();
				//Second token is the title
				String title = scan.next();
				//If the title has more than one words in it then add the next tokens that aren't integers
				while(!scan.hasNextInt()){
					title = title + " " + scan.next();
				}
				//After the title , the next token, and the final one, is the number of likes
				String likes = scan.next();

				//Adds a new Song Object in the array at position m
				songs[m]= new Song(Integer.parseInt(id),title,Integer.parseInt(likes));
				//Increases the counter by one
				m++;
				//Reads next line
				line = reader.readLine();
				
				if (line !=null){
					//If there is a next line, then scan it
					scan = new Scanner(line);
				}
			}
			//Close scanner
			scan.close();
			
			//Calls quicksort method only for the part of the array that has objects in it.
			quicksort(songs,0,m-1);
		
			int x = m-k;	//this variable indicates the end of the for loop
			
			
			if (k > m ){
				System.err.println("Error. Variable k is bigger than the number of songs in the array. Terminating.");
				System.exit(0);
			}
			//Prints the songs starting from the last Object in the array since the order is ascending.
			System.out.println("The top " + k + " songs are:");
			for(int j = m-1; j >= x; j--)
				System.out.println(songs[j].getTitle());
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

		//print top k songs
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