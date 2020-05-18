package stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class MyStack<AnyType> 
{
	public boolean isEmpty()
	{
		return stack.isEmpty();
	}
	
	public void push(AnyType val)
	{
		stack.add(val);
	}
	
	public void pop()
	{
		stack.remove(stack.size()-1);
	}
	
	public int size()
	{
		return stack.size();
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder( "\n" );

        for(int i = 0;i<stack.size();i++ )
            sb.append("| "+ stack.get(i) + " |\n" );
        sb.append( "-----" );

        return new String( sb );
	}
	
	public AnyType peek()
	{
		return stack.get(stack.size()-1);
	}
	
	private ArrayList<AnyType> stack = new ArrayList<AnyType>();


}


class TestStack 
{
	
	public String checkBalance(String s)
	{
		String closed = "})]>";
		MyStack<String> stck = new MyStack<String>();
		for(int i=0;i<s.length();i++)
		{
			String c = s.substring(i,i+1);
			if(c.equals("{") || c.equals("(") || c.equals("[") || c.equals("<")) 
			{
				stck.push(c);
				
			}
			else
			{
				if(closed.contains(c))
				if(((int)c.charAt(0)-(int)stck.peek().charAt(0) )== 1 || ((int)c.charAt(0)-(int)stck.peek().charAt(0)  == 2))
				{
					stck.pop();
				}
				else
				{
					return "Not Balanced";
				}
			}
			
		}
		if(stck.size()==0)
			return "Balanced";
		else
			return "Not balanced";
	}
	
public static void main(String[] args) {
		
	TestStack obj = new TestStack();

	
		System.out.println("{{()}} "+obj.checkBalance("{{()}}"));
		System.out.println("{{(}} "+obj.checkBalance("{{(}}"));
		System.out.println("{{([)}]} "+obj.checkBalance("{{([)}]}"));
		
		System.out.println("Enter input");
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		
		
		System.out.println(obj.checkBalance(str));
		
	}
}