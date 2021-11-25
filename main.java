import java.util.Scanner;

class Main {
    //method executes principal method needed
    public static void main (String[] args) {
        execute();
        System.exit(0);
    }

    public static void execute () {

        //asks user for string
        Scanner scanner = new Scanner(System.in);
        System.out.println("String: ");
        String str = scanner.nextLine();

        //string it split up by blank spaces
        String[] ch = str.split("");

        //position is the end of the array
        int position = ch.length - 1;

        //function does the math on the string
        ch = recursiveParser(position, ch);

        //checking for errors in result
        boolean result = false;
        result = recursiveArrCheck(position, ch, result);
        if (result)
        {
            System.out.println("Too many numbers - incorrect format");
        }

        //if first element is equal to an error statement, a corresponding statement is returned
        switch (ch[0])
        {
            case "error1":
                System.out.println("Error, letter is not A, B or C");
                break;
            case "error2":
                System.out.println("Error with multiplication format");
                break;
            case "error3":
                System.out.println("Error, with T function format");
                break;
            default:
                System.out.println("Your answer is " + ch[0]);
        }
        return;
    }//END of execute

    //recursive function
    public static String[] recursiveParser(int position, String[] array)
    {
        //recusion happens for all of these statements
        if (position < 0) 
        {
            return array;
        } else {
            //checks if the letter its on making sure its not T
            if (Character.isLetter(array[position].charAt(0)) && array[position].charAt(0) != 'T')
            {
                String letter = array[position].toUpperCase();
                int num = letter.charAt(0);
                num -= 55;
                
                //makes sure letter is not too far in the alphabet
                if (num > 12)
                {
                    array[0] = "error1";
                    position = 0;
                }
                else
                {
                    array[position] = Integer.toString(num); 
                }
                
            }
            //checks for * sybmol
            if (array[position].equals("*"))
            {
                try {
                    int first = Integer.parseInt(String.valueOf(array[position+1]));
                    array[position+1] = null;
                    int second = Integer.parseInt(String.valueOf(array[position+2]));
                    array[position+2] = null;
                    int combine = first*second;
                    array[position] = Integer.toString(combine);
                }
                catch(Exception e) {
                    array[0] = "error2";
                    position = 0;
                }

            }
            //checks for T
            if (array[position].equals("T"))
            {
                try {
                    int num = Integer.parseInt(String.valueOf(array[position+1]));
                    int total = (num * 3) + 3;
                    array[position] = Integer.toString(total);
                    array[position+1] = null;
                }
                catch(Exception e) {
                    array[0] = "error3";
                    position = 0;
                }

            }
            recursiveParser(position - 1, array);

        }
        return array;

    } //END of recursiveParser
    
    //checks that all the values are null apart from the first element
    public static boolean recursiveArrCheck (int pos, String[] arr, boolean result)
    {
        if (pos < 1)
        {
            return result;
        }
        else
        {
            if (arr[pos] != null)
            {
                result = true;
            }
            recursiveArrCheck(pos-1, arr, result);
        }
        return result;
    } //END of recursiveArrCheck

}
