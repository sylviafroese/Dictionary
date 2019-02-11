import java.util.*;

//Input:
//Two integer parameters between 1 and 999, inclusive

//Output:
//Dictionary<int, int> whose keys are all the integers between the two input parameters, inclusive, and whose values are the number of occurrences of the letter ‘e’ contained in the English spelling of the corresponding key.

//Example:
//If we execute your method, `YourMethod(6, 10)`, we expect to get a dictionary with the following (key, value) pairs:
//(6, 0)      (“six” has no ‘e’s)
//(7, 2)      (“seven” has two ‘e’s)
//(8, 1)      (“eight” has one ‘e’)
//(9, 1)      (“nine” has one ‘e’)
//(10, 1)   (“ten” has one ‘e’)

public class dictionary
{
	public static void main(String[] args)
	{
		int[] strippedNum1 = new int[3];
		String convertedWord = "";
		String outputString = "";
		int numberOfEs;

		System.out.println("Enter two integer numbers");
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter number one: ");

		int num1 = kb.nextInt();
		System.out.print("Enter number two: ");
		int num2 = kb.nextInt();

		if ( num1 > num2 )
			System.out.println("Number One cannot be greater than number two");
		else if ( (num1 >= 1 && num1 <= 999) && (num2 >= 1 && num2 <= 999) )
			{
			for (int i=num1; i<=num2; i++)
				{
				convertedWord = convertToWords(stripDigits(i));
				numberOfEs = countE(convertedWord);
				outputString = "(" + i + ", " + numberOfEs + ")   (" + convertedWord + " has " + numberOfEs;
				outputString += (numberOfEs != 1) ? " e\'s" : " e";
				System.out.println(outputString + " )");
				}
			} // end if
	} // end main


public static int countE(String word)
{
	int countE = 0;

	for (int i=0; i<word.length(); i++)
	{
		if (word.charAt(i) == 'E')
			countE++;
	}

	return countE;
}



public static String numberToWord(int digit)
	{
	String ones[] = {"", " ONE ", " TWO ", " THREE ", " FOUR ", " FIVE ", " SIX "," SEVEN ", " EIGHT ", " NINE "};

	int i = 0;

	String word = "";

	boolean done = false;
	while ((i<= 9))
		{
			if (i == digit)
			{
				done = true;
				word = ones[i];
			}
		i++;
		}

	return word;
	}


public static int[] stripDigits(int num)
	{
	int keep;
	int count = 1;

	int[] completeNumber = new int[3];


	while (num > 0)
		{
		keep = num % 10;
		num	= num / 10;

		switch (count)
			{
			case 1:
				completeNumber[2] = keep; // ones digit
				break;

			case 2:
				completeNumber[1] = keep; // tens digit
				break;

			case 3:
				completeNumber[0] = keep; // hundreds digit
				break;
			} // end switch

			count++;
		} // end while

	return completeNumber;

	} // end method


public static String convertToWords(int[] strippedNum1)
{
	String concatenatedNumber = "";

	String tens[] = {"", "", " TWENTY ", " THIRTY ", " FORTY ", " FIFTY ", " SIXTY ", " SEVENTY ", " EIGHTY ", " NINETY "};
	String tenToNineteen[] = {" TEN", " ELEVEN", " TWELVE", " THIRTEEN", " FOURTEEN", " FIFTEEN", " SIXTEEN", " SEVENTEEN", " EIGHTEEN", " NINETEEN"};


	for (int i=0; i<3; i++)
		{
			if ((i == 0) && (strippedNum1[i] !=0))  // hundreds
			{
				concatenatedNumber += numberToWord(strippedNum1[i]);

				concatenatedNumber += " HUNDRED ";
			}
			else if (i == 1)  // tens
			{
				if (strippedNum1[i] == 0)

					concatenatedNumber += "";

				else if (strippedNum1[i] == 1) // number between 10 and 19

					concatenatedNumber += tenToNineteen[strippedNum1[i+1]] + "";

				else if (strippedNum1[i] >=2) // number 20 and 90

					concatenatedNumber += tens[strippedNum1[i]];
				// end inner if

			} // end else if
			else if (i == 2) // ones
			{
				if (strippedNum1[i-1] != 1) // number is less than 10 and greater than 19
					{
					concatenatedNumber += numberToWord(strippedNum1[i]);
					}
			}

		}  // end for
		return concatenatedNumber;
}



} // end class