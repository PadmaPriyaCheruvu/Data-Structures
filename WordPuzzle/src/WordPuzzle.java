import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	
	
	public static void main(String[] args) throws IOException {
		
		Random rnd = new Random();
		
		Scanner n = new Scanner(System.in);
		
		System.out.println("Enter number of rows");
		int rows = n.nextInt();
		
		System.out.println("Enter number of columns");
		int columns = n.nextInt();
		
		char[][] a = new char[rows][columns];
		
		for(int i=0;i<rows;i++)
			for(int j=0;j<columns;j++)
				a[i][j] = (char)(rnd.nextInt(26) + 'a');
		

			ArrayList<String> wordList = new ArrayList<String>();		
			ArrayList<String> dictionary = new ArrayList<String>();
			ArrayList<String> words_one = new ArrayList<String>();

			File file = new File("C:\\Users\\Padma Priya Cheruvu\\Data_Structures\\Project3\\src\\dictionary.txt"); 
	        BufferedReader br = new BufferedReader(new FileReader(file)); 
	        
	        String st; 
	        while ((st = br.readLine()) != null) 
	          {dictionary.add(st);}
	        
	        MyHashTable diction_table = new MyHashTable(dictionary.size()*2 + 10);

	        for(int i=0;i<dictionary.size();i++)
	        	diction_table.insert(dictionary.get(i), true);
	        
	        

		System.out.println("Running Part One - Hash table with only words");
		long startTime = System.currentTimeMillis( );
		
		String temp = "";
		int step = 1;
		for(int i = 0;i<rows;i++)
		{
		for(int j = 0;j<columns;j++)
		{
			step = 1;
			temp = "";
			temp = temp + a[i][j];
			wordList.add(Character.toString(a[i][j]));
			while(j+step<columns)
				{
					
					temp = temp + a[i][j+step];
					wordList.add(temp);
					step++;
				}
			step = 1;
			temp = "";
			temp = temp + Character.toString(a[i][j]);
			while(j-step>=0)
			{
				temp = temp + a[i][j-step];
				wordList.add(temp);
				step++;
			}
			
			step = 1;
			temp = "";
			temp = temp + Character.toString(a[i][j]);
			while(i-step>=0)
			{
				temp = temp + a[i-step][j];
				wordList.add(temp);
				step++;
			}
			
			step = 1;
			temp = "";
			temp = temp + Character.toString(a[i][j]);
			while(i+step<rows)
			{
				temp = temp + a[i+step][j];
				wordList.add(temp);
				step++;
			}
			
			step = 1;
			temp = "";
			temp = temp + Character.toString(a[i][j]);
			while(i-step>=0 && j-step>=0)
			{
				temp = temp + a[i-step][j-step];
				wordList.add(temp);
				step++;
			}
			
			step = 1;
			temp = "";
			temp = temp + Character.toString(a[i][j]);
			while(i+step<rows && j+step<columns)
			{
				temp = temp + a[i+step][j+step];
				wordList.add(temp);
				step++;
			}
			
			step = 1;
			temp = "";
			temp = temp + Character.toString(a[i][j]);
			while(i-step>=0 && j+step<columns)
			{
				temp = temp + a[i-step][j+step];
				wordList.add(temp);
				step++;
			}
			
			step = 1;
			temp = "";
			temp = temp + Character.toString(a[i][j]);
			while(i+step<rows && j-step>=0)
			{
				temp = temp + a[i+step][j-step];
				wordList.add(temp);
				step++;
			}
			
		}
	}
		
	        for(int i = 0;i < wordList.size();i++)
	        {
	        	if(diction_table.contains(wordList.get(i)))
	        	{
	        		words_one.add(wordList.get(i));
	        	}
	        }
	        long endTime = System.currentTimeMillis( );
	        
	        
	        
	        System.out.println("Running Part two - Hash table with prefixes and words");
	        
	        MyHashTable diction_table_2 = new MyHashTable(dictionary.size()*10);
	        MyHashTable table_two = new MyHashTable();
	        dictionary = new ArrayList<String>();
	        ArrayList<String> words_two = new ArrayList<String>();
	        
	        
	        BufferedReader b = new BufferedReader(new FileReader(file)); 
	        while ((st = b.readLine()) != null) 
	          {
	        	if(!diction_table_2.contains(st))
	        	diction_table_2.insert(st, true);
	        	
	        	for(int i=0;i<st.length();i++)
	        	{
	        		if(!diction_table_2.contains(st.substring(0,i)))
	        		diction_table_2.insert(st.substring(0,i), false);
	        	}
	        	
	           }
	        
	        long s = System.currentTimeMillis( );
	        temp = "";
			step = 1;
			boolean flag = true;
			for(int i = 0;i<rows;i++)
			{
			for(int j = 0;j<columns;j++)
			{
				step = 1;
				temp = "";
				temp = temp + a[i][j];
				
				if(diction_table_2.contains(temp) && diction_table_2.isWord(diction_table_2.findPos(temp)))
					words_two.add(temp);
				
				wordList.add(Character.toString(a[i][j]));
				while(j+step<columns)
					{
						
						temp = temp + a[i][j+step];
						if(diction_table_2.contains(temp) && diction_table_2.isWord(diction_table_2.findPos(temp)))
							words_two.add(temp);
						else if(!diction_table_2.contains(temp))
							break;
						step++;
					}
				step = 1;
				temp = "";
				temp = temp + Character.toString(a[i][j]);
				while(j-step>=0)
				{
					temp = temp + a[i][j-step];
					if(diction_table_2.contains(temp) && diction_table_2.isWord(diction_table_2.findPos(temp)))
						words_two.add(temp);
					else if(!diction_table_2.contains(temp))
						break;

					step++;
				}
				
				step = 1;
				temp = "";
				temp = temp + Character.toString(a[i][j]);
				while(i-step>=0)
				{
					temp = temp + a[i-step][j];
					if(diction_table_2.contains(temp) && diction_table_2.isWord(diction_table_2.findPos(temp)))
						words_two.add(temp);
					else if(!diction_table_2.contains(temp))
						break;

					step++;
				}
				
				step = 1;
				temp = "";
				temp = temp + Character.toString(a[i][j]);
				while(i+step<rows)
				{
					temp = temp + a[i+step][j];
					if(diction_table_2.contains(temp) && diction_table_2.isWord(diction_table_2.findPos(temp)))
						words_two.add(temp);
					else if(!diction_table_2.contains(temp))
						break;

					step++;
				}
				
				step = 1;
				temp = "";
				temp = temp + Character.toString(a[i][j]);
				while(i-step>=0 && j-step>=0)
				{
					temp = temp + a[i-step][j-step];
					if(diction_table_2.contains(temp) && diction_table_2.isWord(diction_table_2.findPos(temp)))
						words_two.add(temp);
					else if(!diction_table_2.contains(temp))
						break;

					step++;
				}
				
				step = 1;
				temp = "";
				temp = temp + Character.toString(a[i][j]);
				while(i+step<rows && j+step<columns)
				{
					temp = temp + a[i+step][j+step];
					if(diction_table_2.contains(temp) && diction_table_2.isWord(diction_table_2.findPos(temp)))
						words_two.add(temp);
					else if(!diction_table_2.contains(temp))
						break;

					step++;
				}
				
				step = 1;
				temp = "";
				temp = temp + Character.toString(a[i][j]);
				while(i-step>=0 && j+step<columns)
				{
					temp = temp + a[i-step][j+step];
					if(diction_table_2.contains(temp) && diction_table_2.isWord(diction_table_2.findPos(temp)))
						words_two.add(temp);
					else if(!diction_table_2.contains(temp))
						break;

					step++;
				}
				
				step = 1;
				temp = "";
				temp = temp + Character.toString(a[i][j]);
				while(i+step<rows && j-step>=0)
				{
					temp = temp + a[i+step][j-step];
					if(diction_table_2.contains(temp) && diction_table_2.isWord(diction_table_2.findPos(temp)))
						words_two.add(temp);
					else if(!diction_table_2.contains(temp))
						break;

					step++;
				}
			}
			}
			
			long e = System.currentTimeMillis( );
	        		
		//Printing list 1 words - Part 1 
	    System.out.println("Word list 1");
		for(int i=0;i<words_one.size();i++)
			System.out.println(words_one.get(i));
		
		//Printing list 2 words - Part 2
		System.out.println("\nWord list 2");
		for(int i=0;i<words_two.size();i++)
			System.out.println(words_two.get(i)+" ");
		
		System.out.println("Elapsed time for Part 1 - "+(endTime-startTime));
		System.out.println("Elapsed time for Part 2 - "+(e-s));
		
	}
}
