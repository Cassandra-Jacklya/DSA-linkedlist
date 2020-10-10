/**
** Author: Cassandra Jacklya
** Purpose:  Modified the test harness to fit practical 4 where linked list is 
			implemented
** Last modified on: 28th August 2020
**/
import java.util.*;
import java.io.*;

public class TestStacks {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		DSAStack s = new DSAStack();
		int ops;
		Object value;
		boolean stop = false;
		String word = "";
		try {
			do {	//loop breaks once stop operation is chosen
				//prints the menu to allow user to enter values into the stack
				System.out.println("\n" + "Choose an operation");
				System.out.println("1. Push");
				System.out.println("2. Pop");
				System.out.println("3. Peek at top value");
				System.out.println("4. Stop");
				ops = sc.nextInt();
				
				switch(ops) {
					case 1:
						System.out.print("\n" + "Enter a value: ");
						sc.nextLine();
						value = sc.nextLine();
						s.push(value);
						break;
					case 2:
						s.pop();
						break;
					case 3:
						System.out.println("The top value is: " + s.topVal());
						break;
					case 4:
						stop = true;
						break;
					default:
						throw new IllegalArgumentException("Invalid option");
				}
			} while (stop == false);
		
			System.out.println("\n" + "Stack Count: " + s.getCount());	//counts the number of elements in the stack
			
			char choice;
			String filename = "";
			System.out.println("Enter a filename for saving and loading later on: ");
			sc.nextLine();
			filename = sc.nextLine();
			do {
				stop = false;
				
				//menu display for options to serialize or deserialize an object
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
					save(s, filename+".txt");
					break;
				
				case 'd': case 'D':
					word = displayList(filename+".txt");
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
			System.out.println("\n" + "You chose the wrong file!");
		}
	}
	
	//serialization methods
	private static void save(DSAStack objSave, String filename) {
		FileOutputStream fileStream;
		ObjectOutputStream objStream;
		try {		//all file methods must be accompanied with try..catch blocks
			fileStream = new FileOutputStream(filename);
			objStream = new ObjectOutputStream(fileStream);
			objStream.writeObject(objSave);
			objStream.close();
			fileStream.close();	//closes both object stream and file stream to avoid 
								//...possible runtime errors
			System.out.println("Object has been serialized");	//tells the user that operation is successful
		}
		catch (IOException e2) {
			System.out.println("Error in saving object to file");	//outputs an error message
		}
	}
	
	private static DSAStack load(String filename) {
		FileInputStream fileStream;
		ObjectInputStream objStream;
		DSAStack inObj = null;
		
		try {
			fileStream = new FileInputStream(filename);
			objStream = new ObjectInputStream(fileStream);
			inObj = (DSAStack)objStream.readObject();
			objStream.close();
			fileStream.close();
			System.out.println("Object has been deserialized");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Class does not exist");	//outputs error if class does not exist
		}
		catch (IOException e2) {
			System.out.println("Error in loading object from file");	//if file does not exist output an error message
		}
		return inObj;
	}
	
	//method to output the deserialized object to the main
	private static String displayList(String filename) {
		String word = "";
		try {
			DSAStack show = new DSAStack();
			show = load(filename);
			word = show.toString();
		}
		catch (NullPointerException e) {
			System.out.println("Non-existent data in file");
		}
		return word;
	}
	
}
		
		
					
			
			