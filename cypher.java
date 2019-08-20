/**
 *  Author: MazzyK
 *  Description: A java implementation of the Vigenere Cypher
 */
import java.util.Scanner;


/**
 * create a class to define an object to hold 3 values that describe the object
 * this will be used for the message, cypher and key input by the user
 * the string, its length and an (empty) integer array will be used throughout the programme
 */

class CypherData {

	String dataStr; 		//to store the string of the instance
	int dataArrLen; 		//to store the length of the string
	int [] dataPos; 		// to store the mapped allocation of the string item to the tabula recta

	CypherData (String a, int b, int [] c)
	{
		dataStr = a;
		dataArrLen = b;
		dataPos = c;
	}

}

public class cypher {
	
	/** a global char array to hold the alphabet */
	
	public static char [] abc = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	/**
	 * return an object of class CypherData that holds 3 values:
	 *  the string, its length and an empty integer array (this will
	 *  be used later on to map string letters to numbers)
	 */
	static CypherData getCypherData (char [] val1) {

		String a = String.copyValueOf(val1);
		int b = a.length();
		int [] c = new int [b];

		return new CypherData (a,b,c);
	}
	
	/** a method to get key for encoding or decoding */
	
	public static char [] getKey() {
		
		System.out.println("Please enter key:");
		System.out.println("(DO NOT use whitespaces, special symbols or numbers. Use ONLY letters of the alphabet: \n)");
		
		return getUsrInfo();
	}
	
	/** a method to get message to encode */
	
	public static char [] getMessage() {
		
		System.out.println("Please enter message:\n");
		System.out.println("(DO NOT use whitespaces, special symbols or numbers. Use ONLY letters of the alphabet: \n)");
		
		return getUsrInfo();
		 
	}
	
	/** a method to prompt user for the cypher */
	
	public static char [] getCypher() {
		
		System.out.println("Please enter cypher:");
		
		return getUsrInfo();
	}
	
	/** a method to read user input from keyboard */
	
	@SuppressWarnings("resource") 
	public static char [] getUsrInfo () {
		 Scanner usrInput =  new Scanner(System.in);
		
		String inpt;
		
		inpt = usrInput.nextLine().toUpperCase();
		int len = inpt.length();
			
		char [] dat =  new char [len];
				
		for(int i=0; i<len;i++)
		{
		   dat[i] = inpt.charAt(i);		//store each letter input by the user into the dat array
		}
				
		return dat;
	}
	
	public static char [] key ; 		// a global variable to store the key input by the user
	public static char [] message ;		// a global variable to store the message input by the user
	public static char [] cypher; 		// a global variable to store the cypher 
	
	
	/** a method to print array content */
	
	public static void printArray(int length, char[][] array) {
		
	for (int i=0;i<length;i++) {
			
			for (int j=0; j<length;j++) {
				
				System.out.print(array[i][j] + "  ");
						
			}
			System.out.println();
		}
		
	}
	
	/** a method to populate the tabula recta matrix */
	
	public static char[][] populateArray(){
		char[][] tabulaRecta = new char [26][26];
		int k=0;
		
				
		for (int i=0;i<26;i++) {
			
			k=i;
			
			for (int j=0; j<26;j++) {
					
				tabulaRecta[i][j]= abc[k];
								
				if(k==25) {
					k=0;
				}else {
					k++;
				}	
				
			}
		}
		
		return tabulaRecta;
	}
	
	/**
	 * A method to get and map each letter of the key to the corresponding number
	 * in the alphabet e.g. A -> 1
	 */
	
	public static CypherData mapKey() {
		
		int j=0;
		
		CypherData cip = getCypherData(key);
		
		for (int i=0;i<cip.dataArrLen;i++) {
			
			for (int k=0;k<26;k++) {
					if(cip.dataStr.charAt(i) == abc[k]) { 		// identify location of key letter in alphabet
						cip.dataPos[j]=k; 						//map key letter to alphabet number
						j++;
					}
				}
		}
			
		return cip;
	}
	
	/**
	 * get message and return 3 values: the string, the length and an integer array
	 * map each letter of the message to its relevant number position in the alphabet
	 * and store in the integer array 
	**/
	
	public static CypherData mapMsg() {
		
		
		CypherData msg = getCypherData(message); 
		int j=0;
		
		for (int i=0; i<msg.dataArrLen; i++) { 
			for (int k=0;k<26;k++) {
				if(msg.dataStr.charAt(i) == abc[k]) {
					msg.dataPos[j]=k;
					j++;
				}
			}
			
		}
		
		
		return msg;
	}
	
	/** 
	 * method to get and map each letter of the cypher to the corresponding number 
	 * in the alphabet e.g. A -> 1
	 */
	
	public static CypherData mapCypher() {
		
		
		CypherData cyp = getCypherData(cypher);
		int j=0;
		
		for (int i=0;i<cyp.dataArrLen;i++) {
			
			for (int k=0;k<26;k++) {
					if(cyp.dataStr.charAt(i) == abc[k]) {		// identify location of key letter in alphabet
						cyp.dataPos[j]=k; 						//map key letter to alphabet number
						j++;
					}
				}
		}
					
		return cyp;
	}

	/** a method to encode a message input by user*/
	
	public static char [] encodeMsg (CypherData val1, CypherData val2) {
		
		int msgLen =  val1.dataArrLen;
		int keyLen = val2.dataArrLen;
		char [] encMsg = new char [msgLen];
		
		int [] pos = mapKey().dataPos;
		int [] keyPos = new int [msgLen];
		
		
		
		for (int i=0; i<msgLen; i++) { 
			
		    keyPos[i]=pos[i%keyLen];	//repeat key to length of message and store in keyPos array	    
		    
		}
				
		System.out.println("You entered the message: \n");
		
		for (int i=0;i<val1.dataArrLen;i++) { 	//print the key to screen
				System.out.print(val1.dataStr.charAt(i));
		}
		
		System.out.println("\n");
		
		System.out.println("The encoded message is: \n");
		for (int i=0;i<val1.dataArrLen;i++) {
			encMsg[i]= populateArray().clone()[keyPos[i]][val1.dataPos[i]];		 //copy letter from tabula recta found at intersection of the two values
			System.out.print(encMsg[i]);
		}
		
		
		return encMsg;
	}
	
	
	/** a method to decode the cypher input by user */
	
	public static char [] decodeCypher (CypherData val1, CypherData val2) {
		
		int cypherLen =  val1.dataArrLen; 
		int keyLen = val2.dataArrLen;
		String cyphr = val1.dataStr;
		int j=0;
		
		char [] decMsg = new char [cypherLen]; 		//this will hold the decoded message
		char [] tempArray = new char [26]; 			// this will hold the relevant row of the tabula recta
		
		int [] tempArrX = new int[cypherLen]; 		//this will hold the x co-ordinate of each letter of the encoded message
		int [] pos = mapKey().dataPos; 				//an array of the mapped key
		int [] keyPos =  new int [cypherLen]; 		//will contain the key mapped to ABC in numbers repeated to length of cypher
		int [] cypArray = mapCypher().dataPos; 		//will contain the cypher mapped to numbers of the ABC
		
		
		 
		for (int i=0; i<cypherLen; i++) {
			keyPos[i] = pos[i%keyLen]; 				//repeat key to length of cypher
		}
	

		
		/** map the letters of the cypher to
		 * their position in the alphabet
		*/
		
		for (int i=0; i<cypherLen; i++) { 
			for (int k=0;k<26;k++) {
				if(cyphr.charAt(i) == abc[k]) {
					cypArray[j]=k;
					j++;
				}
			}
			
		}
		
		/** find the letters of the message by locating the intersection
		 * of the cypher and key values in the tabula recta
		 */
		
		j=0;
		for (int y=0;y<cypherLen;y++) {
			for (int i=0;i<26;i++) {
				tempArray[i] = populateArray().clone()[keyPos[y]][i];
				if(cyphr.charAt(y)==tempArray[i]) {
					tempArrX[j]=i;
					j++;
				}
				
			}
		}
		
		
		/** map integers to Alphabet characters */
		
		for (int i=0;i<cypherLen;i++) {
			decMsg[i] = abc[tempArrX[i]];
			
		}
		
		System.out.println("The decoded message is: \n");
		for(int i=0; i<cypherLen; i++) {
			System.out.print(decMsg[i]);
		}
		
		
		return decMsg;
	}
	
	/** the main menu */
	
	public static void getMenu () {
		
		boolean option=true;
		String selection;
		
		while (option) {
			System.out.println("\n");
			System.out.println("--------------------- \n");
			System.out.println("MENU - SELECT OPTION \n");
			System.out.println("1. Encode message \n");
			System.out.println("2. Decode message \n");
			System.out.println("3. Exit \n");
			System.out.println("--------------------- \n");
			System.out.println("\n");
			
			selection = String.copyValueOf(getUsrInfo()); //convert user input from char to string
			
			
		
			
			switch(selection) {
			  case "1":
				  key = getKey();
				  message = getMessage();
				  encodeMsg (mapMsg(),mapKey());
			    break;
			  case "2":
				  cypher = getCypher();
				  key = getKey();
				  decodeCypher(mapCypher(),mapKey());
			    break;
			  case "3":
				    option = false;
				    System.out.println("Goodbye!");
				    break;
			  default:
			    option = false;
			}
		}
	}
	public static void main(String[] args) {
		
		getMenu();
		
	}

}
