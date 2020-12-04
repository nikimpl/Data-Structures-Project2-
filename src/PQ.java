import java.util.Comparator;

public class PQ extends Song{
	
	//heap
	private Song[] heap;
    //heap size
    private static int size;
    //comparator
    protected SongComparator cmp;
	
    //constructor
	public PQ(int capacity,SongComparator cmp) {
        if (capacity < 1) throw new IllegalArgumentException();
        
        this.heap = new Song[capacity+1];
        this.size = 0;
        this.cmp = cmp;
    }
	
	//isEmpty method
	public boolean isEmpty(){
        return size == 0;
    }
	
	//size method
	public static int size(){
		return size;
	}
	//insert method
	public void insert(Song object) {
        if (object == null) throw new IllegalArgumentException();
        if (size == heap.length - 1) throw new IllegalStateException();
		if (size == 0.75*heap.length) resize();
        heap[++size] = object;
        swim(size);		
    }
	
	//resize method
	public void resize(){
		Song[] newHeap;
		newHeap = new Song[heap.length*2];
		for(int i=0; i<size; i++) {
			newHeap[i] = getmax();
		}
		heap = newHeap;
	}
	
	//max method
	public Song max(){
		if (size == 0) throw new IllegalStateException();
		Song object = heap[1];
		return object;
	}
	
	//getmax method
    public Song getmax() {
        if (size == 0) throw new IllegalStateException();
        Song object = heap[1];
        if (size > 1) heap[1] = heap[size];
        heap[size--] = null;
        sink(1);
        return object;
    }
	
	//find method
	public Song find(int i){
		if (size == 0) throw new IllegalStateException();
		return heap[i];
	}
	
	//remove method
	public Song remove(int id){
		for(int i=1; i<size+1; i++) {
			if(heap[i].getID() == id) {
				Song object = new Song(heap[i].getID(),heap[i].getTitle(),heap[i].getLikes());
				swap(i,size);
				heap[size--]=null;
				swim(size);
				return object;
			}
		}
		return new Song();
	}
	
	//swim method
	private void swim(int i) {
        while (i > 1) {
            int p = i/2;
            int result = cmp.compare(heap[i], heap[p]);
            if (result <= 0) return;
            swap(i, p);
            i = p;
        }
    }

	//sink method
    private void sink(int i){
        int left = 2*i, right = left+1, max = left;
        while (left <= size) {
            if (right <= size) {
                max = cmp.compare(heap[left], heap[right]) < 0 ? right : left;
            }
            if (cmp.compare(heap[i], heap[max]) >= 0) return;
            swap(i, max);
            i = max; left = 2*i; right = left+1; max = left;
        }
    }
    
	//swap method
    private void swap(int i, int j) {
        Song tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}