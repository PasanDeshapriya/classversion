import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
public class test1 {
    public  static ArrayList<String> QueueFirstName= new ArrayList<String>();
    public  static ArrayList<String> QueueSecondName= new ArrayList<String>();
    public  static ArrayList<String> QueueVehicleNo= new ArrayList<String>();
    public  static ArrayList<Integer> QueueNumLiter= new ArrayList<Integer>();
    public  static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        val2();
    }

    public static void validation(){
        try {
            System.out.println("input num");
            double num = scanner.nextDouble();
        }
        catch (InputMismatchException nfe){
            System.out.println("you entered a wrong input");
            validation();
        }
    }
    public static void val(){
        double num;
        try{
            num = scanner.nextDouble();
        }catch (InputMismatchException nfe){
            System.out.println("you entered a wrong input");
            val();
        }
    }
    public static void val2() {


        do{
            System.out.println("pls enter  number of liters that the customer wants to buy? ");
            String num = scanner.nextLine();
            try
            {
                Double.parseDouble(num);
                double numInt=Double.parseDouble(num);

                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println(num + " is not a valid input !");
            }
        }while(true);
    }



}