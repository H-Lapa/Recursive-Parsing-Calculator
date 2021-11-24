import java.util.Scanner;

class Main {
    public static void main (String[] args) {
        execute();
        System.exit(0);
    }

    public static void execute () {
        Scanner scanner = new Scanner(System.in);

        System.out.println("String: ");
        String str = scanner.nextLine();
        
        String[] ch = str.split("");
        int position = ch.length - 1;

        ch = recursiveParser(position, ch);
        boolean result = false;
        result = recursiveArrCheck(position, ch, result);
        if (result)
        {
            System.out.println("Too many numbers - incorrect format");
        }
        
        //currently for testing -  to be removed
        for (int i=0; i<=position; i++) {
            System.out.println(ch[i]);
        }
        
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
    }

    //recursive function
    public static String[] recursiveParser(int position, String[] array)
    {
        if (position < 0) 
        {
            return array;
        } else {
            if (Character.isLetter(array[position].charAt(0)) && array[position].charAt(0) != 'T')
            {
                String letter = array[position].toUpperCase();
                int num = letter.charAt(0);
                num -= 55;
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

    }
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
    }

}
