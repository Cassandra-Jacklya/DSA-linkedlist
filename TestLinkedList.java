import java.util.*;
import java.io.*;

/** 
** Author: Cassandra Jacklya
** Purpose: To ensure the DSALinkedList is working as it should be
** Last modified on: 28th August 2020
**/

public class TestLinkedList {
	public static void main(String[] args) {
		
	Scanner sc = new Scanner(System.in);
	DSALinkedList list= new DSALinkedList();
	
	try {
		//Adding elements to the Linked list
		list.insertFirst("Steve");
		list.insertFirst("Carl");
		list.insertLast("Cass");
		list.insertFirst("John");
		list.insertFirst("Hello");

		//Adding an element to the first position
		list.insertFirst("Negan");

		//Adding an element to the last position
		list.insertLast("Rick");
		list.removeLast();
		list.removeFirst();
		list.removeLast();

		/********************************
		** LIST SHOULD SHOW AS FOLLOWS: *
		** Hello						*
		** John							*
		** Carl							*
		** Steve						*
		********************************/
		
		/**************************************************************************/
		char choice;
		boolean stop;
		String filename = "";
		System.out.println("Enter a filename for saving and loading later on: ");
		filename = sc.nextLine();
		do {
			stop = false;
			
			//displays the serialization menu options to the user
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
					save(list, filename+".txt");
					break;
				
				case 'd': case 'D':
					String word = displayList(filename+".txt");
					System.out.println("\n" + "Data deserialized:");
					System.out.println(word);
					stop = true;
					break;
					
				//throws exception is user enters an unsupported character
				default:
					throw new IllegalArgumentException("Invalid option");
				}
		} while (stop == false);	//loops stops when user chooses to display the items in the list
	}
	//catches possible exceptions from the try block
	catch (IllegalArgumentException e2) {
		System.out.println(e2.getMessage());
	}
	catch (Exception e) {
		System.out.println("\n" + "You chose the wrong file!");
	}
}

	//serializes the object into the file
	private static void save(DSALinkedList objSave, String filename) {
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
	
	//loads the serialized object from the file to the program
	private static DSALinkedList load(String filename) {
		FileInputStream fileStream;
		ObjectInputStream objStream;
		DSALinkedList inObj = null;
		
		try {
			fileStream = new FileInputStream(filename);
			objStream = new ObjectInputStream(fileStream);
			inObj = (DSALinkedList)objStream.readObject();
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
	
	public static String displayList(String filename) {
		String word = "";
		try {
			DSALinkedList show = new DSALinkedList();
			show = load(filename);
			word = show.toString();
		}
		catch (NullPointerException e) {
			System.out.println("Non-existent data in file");
		}
		return word;
	}
} 