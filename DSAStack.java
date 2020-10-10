/**
** Author: Cassandra Jacklya
** Purpose: to implement a stack using linkedlist instead of arrays
** Last modified: 28th August 2020
**/
import java.util.*;
import java.io.Serializable;

public class DSAStack implements Iterable, Serializable {
	private DSALinkedList list;
	int count;
	private static final long serialversionUID = 1000L;
	
	//constructor (default)
	public DSAStack() {
		list = new DSALinkedList();
		count = 0;
	}

	//getter method
	public int getCount() {
		return this.count;
	}
	
	public Iterator iterator() {
		return list.iterator();
	}
	
	//setter methods
	public void push(Object value) {
		list.insertFirst(value);
		count++;
	}
	
	public Object pop() {
		Object top = null;
		try {
			if (isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("Operation aborted: The stack is empty");
			}
			else {
				top = list.peekFirst();
				list.removeFirst();
				count--;
			}
		}
		catch (ArrayIndexOutOfBoundsException e2) {
			System.out.println(e2.getMessage());
		}
		return top;
	}
	
	public Object topVal() {
		Object peek = null;
		try {
			if (isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("No elements found. Stack is empty");
			}
			else {
				peek = list.peekFirst();
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		return peek;
	}
	
	//this method prints out the stack to the main
	public String toString() {
		Object s;
		String word = "";
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			s = (Object)iter.next();
			word = word + "\n" + s.toString();
		}
		return word;
	}
	
	//checks whether the stack is empty
	public boolean isEmpty() {
		return (list.isEmpty());
	}
}


	
	
	
	