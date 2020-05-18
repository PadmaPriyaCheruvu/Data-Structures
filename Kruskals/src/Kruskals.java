import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Kruskals {
		
	public static void main(String[] args) throws IOException {
		String row = "";
		BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\Padma Priya Cheruvu\\Data_Structures\\Kruskals\\src\\assn9_data.csv"));
		int lines = 0;
		
		while ((row = csvReader.readLine()) != null) {
			lines++;
		}
		
		String[] nodes = new String[lines];
		int i = 0;
		
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			nodes[i] = data[0];
			i++;
			
			int t = data.length;
			while(t!=0)
			{
				
			}
		}
		
		System.out.println(lines);
		Edge[] e = new Edge[2];
		Edge e1 = new Edge();
		Edge e2 = new Edge();
		e1.setCost(2);
		e1.setu(100);
		e1.setv(200);
		
		List<Edge> l = new ArrayList<Edge>();
		l.add(e1);
		e[0] = e1;
		e2.setCost(1);
		e2.setu(330);
		e2.setv(400);
		e[1] = e2;
		l.add(e1);
		Kruskals k = new Kruskals();
		k.kruskal(e,4);
		 
		csvReader.close();
	}
	
	public static List<Edge> kruskal( Edge[] edges, int numVertices )
	{
	DisjSets ds = new DisjSets( numVertices );
	BinaryHeap<Edge> pq = new BinaryHeap<Edge>(edges);
	List<Edge> mst = new ArrayList<>();
	while( mst.size( ) != numVertices - 1 )
	{
	Edge e = pq.deleteMin();
	System.out.println(e.getCost());//Edge e = (u, v)
	int uset = ds.find( e.getu( ) );
	int vset = ds.find( e.getv( ) );
	if( uset != vset )
	{
	// Accept the edge
	mst.add( e );
	ds.union( uset, vset );
	}
	}
	return mst;
	}
	
	

}
