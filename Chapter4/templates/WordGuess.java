
 public class WordGuess 
 {

	public static void main(String[] args) 
	{
		final String FLAG = "!";

		//Create a File object with the name of your file is the parameter

		
		//Declare a File reader object

		//Declare a BufferedReader object

		//Create a random number object

		int numWords, wordToGuess;
		String secretWord = "";
		String wordSoFar = "", updatedWord = "";
		String letterGuess, wordGuess = "";
		int numGuesses = 0;	
		
		

		/* select secret word */
		try {
				//initialize the file reader object to name of the file object
                
                //initialize the BufferedReader objct to the name of the file reader as a parameter

				//Get the number of words in the file using readFile
                
                //update the word to guess to the random object and number of words read plus one
               
				
                //itirate through the word to guess slots
                        //update the secret work from the lines read from the file
               
                //close your BufferedReader object
                //close your FileReader object
				
    	} catch (/* ?? */) 
        {
			/* ?? */
		} catch (/* ?? */) 
        {
			/* ?? */
    	}



		/* begin the game */
		System.out.println("WordGuess game.\n");

        //iterate through the secret word, and update the word so far variable to represent using dashes 
        //the length of the secret word
        //output the word so far using dashes
	



		/* allow player to make guesses*/

		do {
			//?? prompt for a letter


			/* increment number of guesses */
			//?

			/* player correctly guessed a letter--extract string in wordSoFar up to the letter
			 * guessed and then append guessed letter to that string. Next, extract rest of
			 * wordSoFar and append after the guessed letter
			 */
			
             //???





			/* display guessed letter instead of dash */
			    //??


		} while (!letterGuess.equals(FLAG) && !wordSoFar.equals(secretWord));



        
		/* finish game and display message and number of guesses */
		
        //??
	}
}