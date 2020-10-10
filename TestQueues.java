import java.util.*;
import java.io.*;
/**
** Author: Cassandra Jacklya
** Purpose: to test the DSAQueue class and ensure all methods works correctly
** Last modified: 28th August 2020
**/

public class TestQueues {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//delcaring variables
		boolean stop = false;
		char choice;
		Object value;
		
		DSAQueue q = new DSAQueue();
		
		//modified and added more code to test the serialization and 
		//...deserialization methods 
		try {
			do {
				System.out.println("\n" + "Choose an operation");
				System.out.println("(E)nqueue");
				System.out.println("(D)equeue");
				System.out.println("(P)eek first");
				System.out.println("(S)top");
				choice = sc.next().charAt(0);
			
				switch(choice) {
				
					case 'E': case 'e':
						System.out.print("\n" + "Enter any value: ");
						sc.nextLine();
						value = sc.nextLine();
						q.enqueue(value);
						break;
					
					case 'D': case 'd':
						q.dequeue();
						break;
					
					case 'p': case 'P':
						System.out.println("\n" + "Top: " + q.peek());
						break;
						
					case 's': case 'S':
						stop = true;
						break;
					default:
						throw new IllegalArgumentException("Invalid option");
				}
			} while (stop == false);

			System.out.println("\n" + "Queue count: " + q.getCount());
			
			String filename = "";
			System.out.println("Enter a filename for saving and loading later on: ");
			sc.nextLine();
			filename = sc.nextLine();
			do {
				stop = false;
				System.out.println("\n" + "What do you want to do next?");
				System.out.println("(R)ead a serialized file");
				System.out.println("(W)rite into a serialized file");
				System.out.println("(D)isplay the list");
				choice = sc.next().charAt(0);
				
				switch(choice) {
				case 'r': case 'R':
					load(filename+".txt");
					break;
				
				case 'W': case 'w':
					save(q, filename+".txt");
					break;
				
				case 'd': case 'D':
					String word = displayList(filename+".txt");
					System.out.println("\n" + "Data deserialized:");
					System.out.println(word);
					stop = true;
					break;
					
				default:
					throw new IllegalArgumentException("Invalid option");
				}
			} while (stop == false);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e2) {
			System.out.println("\n" + "You chose the wrong file");
		}
	}
	
	private static void save(DSAQueue objSave, String filename) {
		FileOutputStream fileStream;
		ObjectOutputStream objStream;
		try {
			fileStream = new FileOutputStream(filename);
			objStream = new ObjectOutputStream(fileStream);
			objStream.writeObject(objSave);
			objStream.close();
			fileStream.close();
			System.out.println("Object has been serialized");
		}
		catch (IOException e2) {
			System.out.println("Error in saving object to file");
		}
	}
	
	private static DSAQueue load(String filename) {
		FileInputStream fileStream;
		ObjectInputStream objStream;
		DSAQueue inObj = null;
		
		try {
			fileStream = new FileInputStream(filename);
			objStream = new ObjectInputStream(fileStream);
			inObj = (DSAQueue)objStream.readObject();
			objStream.close();
			fileStream.close();
			System.out.println("Object has been deserialized");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Class does not exist");
		}
		catch (IOException e2) {
			System.out.println("Error in loading object from file");
		}
		return inObj;
	}
	
	private static String displayList(String filename) {
		String word = "";
		try {
			DSAQueue show = new DSAQueue();
			show = load(filename);
			word = show.toString();
		}
		catch (NullPointerException e) {
			System.out.println("Non-existent data in file");
		}
		return word;
	}
}