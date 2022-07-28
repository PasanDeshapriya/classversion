import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class test4 {
    //boolean pump is used to validation
    public static Boolean[] pump1 = new Boolean[6];
    public static Boolean[] pump2 = new Boolean[6];
    public static Boolean[] pump3 = new Boolean[6];
    public static Boolean[] pump4 = new Boolean[6];
    public static Boolean[] pump5 = new Boolean[6];

    //array used to store first name and others
    public static String[][] FirstName = new String[5][6];
    public static String[][] SecondName = new String[5][6];
    public static String[][] VehicleNo = new String[5][6];
    public static Double[][] NumLiters = new Double[5][6];

    //This is created because queue don't have a limit, so it must have infinite amount of inputs so arraylist is used
    public  static ArrayList<String> QueueFirstName= new ArrayList<String>();
    public  static ArrayList<String> QueueSecondName= new ArrayList<String>();
    public  static ArrayList<String> QueueVehicleNo= new ArrayList<String>();
    public  static ArrayList<Double> QueueNumLiters= new ArrayList<Double>();

    //low array is set to find the array with the lowest number of customers
    public static int low[] = new int[5];

    public static Scanner scanner = new Scanner(System.in);

    public static int stock = 6600;

    //count is used to store the length of the array, so it can be later used.
    public static int count1 = 0;
    public static int count2 = 0;
    public static int count3 = 0;
    public static int count4 = 0;
    public static int count5 = 0;

    public static boolean loop = true;


    public static void main(String[] args){

        LOOP();
    }

    public  static void LOOP() {
        menu();
        System.out.println("\n pls choose an option ");
        String choice = scanner.next();
//each options relative  function  is called via a case loop
        switch (valid(choice)) {
            case 0 -> VFQ();
            case 1 -> VEQ();
            case 2 -> ACQ();
            case 3 -> RCQ();
            case 4 -> PCQ();
            case 5 -> VCS();
            case 6 -> SPD();
            case 7 -> LPD();
            case 8 -> STK();
            case 9 -> AFS();
            case 10-> IFQ();
            case 11 -> EXT();
//if the user input something other than the given options a message will pop up informing about it
            case 100 -> System.out.println("\n you have selected a wrong option !");
        }
//this is used to exit the loop
        if(loop){
            //infinite times of repetition unit loop is false (it means when 999 or EXT is entered)
            LOOP();
        }else{
            System.out.println("\n Thank you for using this program ");
        }
        scanner.next();
    }

    //menu
    public  static void menu() {
        System.out.println("---------------MENU--------------\n" +
                "   100 or VFQ: View all Fuel Queues.\n" +
                "   101 or VEQ: View all Empty Queues.\n" +
                "   102 or ACQ: Add customer to a Queue.\n" +
                "   103 or RCQ: Remove a customer from a Queue. (From a specific location)\n" +
                "   104 or PCQ: Remove a served customer.\n" +
                "   105 or VCS: View Customers Sorted in alphabetical order\n" +
                "   106 or SPD: Store Program Data into file. 107 or LPD: Load Program Data from file.\n" +
                "   107 or LPD: Load Program Data from file\n" +
                "   108 or STK: View Remaining Fuel Stock.\n" +
                "   109 or AFS: Add Fuel Stock.\n" +
                "   110 or IFQ: View the income per line in each queue.\n" +
                "   999 or EXT: Exit the Program.\n" +
                "-------------------------------------------");

    }

//this array choice1 is stored with each input that the user can input ,so we can validate with the input and the elements in choice1 array
//repeats until the length of the array and if the choice is not found it outputs 100
    public static Integer valid(String choice) {
        String[][] choice1 = {{"100", "101", "102", "103", "104", "105", "106", "107", "108", "109","110", "999"}, {"VFQ", "VEQ", "ACQ", "RCQ", "PCQ", "VCS", "SPD", "LPD", "STK", "AFS","IFQ", "EXT"}};
        int num = 100;
        for (int i = 0; i < 12; i++) {
            if ((Objects.equals(choice1[0][i], choice)) || (Objects.equals(choice1[1][i], choice))) {
                //value of i is saved in num, so we can use it later
                System.out.println("you choose " + choice1[0][i] + ", " + choice1[1][i]);
                num = i;
                break;
            }
        }
        return num;
    }

//View all fuel queues , it will output empty if elements is null and if there is data in the element it will output that
    public static void VFQ() {

        int out = 0;
        int in = 0;
        System.out.println("View all Fuel Queues");
        for (out = 0; out < 5; out++) {
            for (in = 0; in < 6; in++) {
                if (FirstName[out][in] == null) {
                    System.out.println("pump " + out + ",row " + in + " : empty");

                } else  {
                    System.out.println("pump " + out + ",row " + in + " : "+FirstName[out][in]+" "+SecondName[out][in]);
                }
            }
        }
    }

//it will output empty if elements is null
    public static void VEQ() {
        int out = 0;
        int in = 0;
        System.out.println("View all Empty Queues.");
        for (out = 0; out < 5; out++) {
            for (in = 0; in < 6; in++) {
                if (FirstName[out][in] == null) {
                    System.out.println("pump " + out + ",row " + in + " is empty");

                }
            }
        }
    }

//adding a customer to the queue.
    public static void ACQ() {

        int in = 0;
        int out = 0;
//sort low will find the pump queue with the lowest no of customers
        SortLow();
        System.out.println("Add customer to a Queue");
//if the queue is full the customer is added to the waiting list (if the queue is full the length of the array must be 6 )
        if(count1==6 && count2==6 && count3==6 && count4==6 && count5==6) {
            System.out.println("All the pumps are full");
            System.out.println("You are been added to the waiting list.");
            System.out.println("pls enter customer First name");
            String name;
//this is validation if the user didn't input  it won't let the  user from going into the next input until the user enters an input
            do{
                if(scanner.hasNext()){
                    name = scanner.next();
                    break;
                }else{
                    scanner.next();
                }
            }while(true);
            QueueFirstName.add(name);
//this is validation if the user didn't input  it won't let the  user from going into the next input until the user enters an input
            System.out.println("pls enter customer Second name");
            do{
                if(scanner.hasNext()){
                    name = scanner.next();
                    break;

                }else{
                    scanner.next();

                }
            }while(true);
            QueueSecondName.add(name);
//this is validation if the user didn't input  it won't let the  user from going into the next input until the user enters an input
            System.out.println("pls enter customer Vehicle number ");
            String Vnum;
            do{
                if(scanner.hasNext()){
                    Vnum = scanner.next();
                    break;

                }else{
                    scanner.next();

                }
            }while(true);
            QueueVehicleNo.add(Vnum);

//This to validate whether the user has input a double number
            do{
                System.out.println("pls enter  number of liters that the customer wants to buy? ");
                String num = scanner.next();
                try
                {
                    Double.parseDouble(num);
                    double numD=Double.parseDouble(num);
                    QueueNumLiters.add(numD);
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println(num + " is not a valid input !");
                    scanner.next();
                }
            }while(true);
//the size is minus one coz the size  starts from  1, but the array starts from 0
            int size=QueueFirstName.size()-1;
            System.out.println("a customer "+QueueFirstName.get(size)+" "+QueueFirstName.get(size)+ " is added to waiting list in "+size+" position.");



        }else if((count1==low[0] && count2==low[0] && count3==low[0] && count4==low[0] && count5==low[0])){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[0][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name;
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    FirstName[0][in]=name;

                    System.out.println("pls enter customer Second name");
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    SecondName[0][in]=name;

                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum;
                    do{
                        if(scanner.hasNext()){
                            Vnum = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    VehicleNo[0][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.next();
                        try
                        {
                            Double.parseDouble(num);
                            double numD=Double.parseDouble(num);
                            NumLiters[0][in]=numD;
                            break;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println(num + " is not a valid input !");
                            scanner.next();
                        }
                    }while(true);
//count is add one to increase by one because a customer is added
                    count1+=1;
                    pump1[in]=true;
                    System.out.println("a customer "+FirstName[0][in]+" "+SecondName[0][in]+ " is Successfully added to pump  0, row " + in+ ".");
                    break;
                }
            }


        }else if(count1==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[0][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name;
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    FirstName[0][in]=name;

                    System.out.println("pls enter customer Second name");
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    SecondName[0][in]=name;

                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum;
                    do{
                        if(scanner.hasNext()){
                            Vnum = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    VehicleNo[0][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.next();
                        try
                        {
                            Double.parseDouble(num);
                            double numD=Double.parseDouble(num);
                            NumLiters[0][in]=numD;
                            break;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println(num + " is not a valid input !");
                            scanner.next();
                        }
                    }while(true);

                    count1+=1;
                    pump1[in]=true;
                    System.out.println("a customer "+FirstName[0][in]+" "+SecondName[0][in]+ " is Successfully added to pump  0, row " + in+ ".");
                    break;
                }
            }

        }else if(count2==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[1][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name;
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    FirstName[1][in]=name;

                    System.out.println("pls enter customer Second name");
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    SecondName[1][in]=name;

                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum;
                    do{
                        if(scanner.hasNext()){
                            Vnum = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    VehicleNo[1][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.next();
                        try
                        {
                            Double.parseDouble(num);
                            double numD=Double.parseDouble(num);
                            NumLiters[1][in]=numD;
                            break;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println(num + " is not a valid input !");
                            scanner.next();
                        }
                    }while(true);

                    count2+=1;
                    pump2[in]=true;
                    System.out.println("a customer "+FirstName[1][in]+" "+SecondName[1][in]+ " is Successfully added to pump  1, row " + in+ ".");
                    break;
                }
            }
        }else if(count3==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[2][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name;
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    FirstName[2][in]=name;

                    System.out.println("pls enter customer Second name");
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    SecondName[2][in]=name;

                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum;
                    do{
                        if(scanner.hasNext()){
                            Vnum = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    VehicleNo[2][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.next();
                        try
                        {
                            Double.parseDouble(num);
                            double numD=Double.parseDouble(num);
                            NumLiters[2][in]=numD;
                            break;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println(num + " is not a valid input !");
                            scanner.next();
                        }
                    }while(true);

                    count3+=1;
                    pump3[in]=true;
                    System.out.println("a customer "+FirstName[2][in]+" "+SecondName[2][in]+ " is Successfully added to pump  2, row " + in+ ".");
                    break;
                }
            }
        }else if(count4==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[3][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name;
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    FirstName[3][in]=name;

                    System.out.println("pls enter customer Second name");
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    SecondName[3][in]=name;

                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum;
                    do{
                        if(scanner.hasNext()){
                            Vnum = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    VehicleNo[3][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.next();
                        try
                        {
                            Double.parseDouble(num);
                            double numD=Double.parseDouble(num);
                            NumLiters[3][in]=numD;
                            break;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println(num + " is not a valid input !");
                            scanner.next();
                        }
                    }while(true);

                    count4+=1;
                    pump4[in]=true;
                    System.out.println("a customer "+FirstName[3][in]+" "+SecondName[3][in]+ " is Successfully added to pump  3, row " + in+ ".");
                    break;
                }
            }
        }else if(count5==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[4][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name;
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    FirstName[4][in]=name;

                    System.out.println("pls enter customer Second name");
                    do{
                        if(scanner.hasNext()){
                            name = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    SecondName[4][in]=name;

                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum;
                    do{
                        if(scanner.hasNext()){
                            Vnum = scanner.next();
                            break;

                        }else{
                            scanner.next();

                        }
                    }while(true);
                    VehicleNo[4][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.next();
                        try
                        {
                            Double.parseDouble(num);
                            double numD=Double.parseDouble(num);
                            NumLiters[4][in]=numD;
                            break;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println(num + " is not a valid input !");
                            scanner.next();
                        }
                    }while(true);

                    count5+=1;
                    pump5[in]=true;
                    System.out.println("a customer "+FirstName[4][in]+" "+SecondName[4][in]+ " is Successfully added to pump  4, row " + in+ ".");
                    break;
                }
            }
        }
    }
//the adding customer is finished
//function to remove customer from a specific location

    public static void RCQ() {
        System.out.println("Remove a customer from a Queue. (From a specific location)");
        int out = 0;
        int in = 0;
//the location is asked from the user if there is no one in that location a message is sent to inform that no one is there
//if there is the location is turned null

        System.out.println("pls enter from which row you want to remove(0,1,2,3,4)");
        out = scanner.nextInt();
        System.out.println("pls enter from which column you want to remove(0,1,2,3,4,5)");
        in = scanner.nextInt();
        if (Objects.equals(FirstName[out][in], null)) {
            System.out.println("there is no customer at the given location");
        } else {
            FirstName[out][in] = null;
            System.out.println("pump " + out + ",row " + in + " customer has been removed.");
//bubble sort is done here so that the removed slot is moved to the end of the queue and the customers are sent front
            for (int inLoop = in + 1; inLoop < 6; inLoop++) {
                if (FirstName[out][inLoop] != null) {
                    String temp = FirstName[out][inLoop - 1];
                    FirstName[out][inLoop - 1] = FirstName[out][inLoop];
                    FirstName[out][inLoop] = temp;

                    temp = SecondName[out][inLoop - 1];
                    SecondName[out][inLoop - 1] = SecondName[out][inLoop];
                    SecondName[out][inLoop] = temp;

                    temp = VehicleNo[out][inLoop - 1];
                    VehicleNo[out][inLoop - 1] = VehicleNo[out][inLoop];
                    VehicleNo[out][inLoop] = temp;

//null is not valid in double so if the num is null it is assign 0.0 and then the bubble sort is done
//this is because the num variable is double (like temp)
                    if(NumLiters[out][inLoop - 1]==null){
                        NumLiters[out][inLoop - 1]=0.0;
                    }
                    double num = NumLiters[out][inLoop - 1];
                    NumLiters[out][inLoop - 1] = NumLiters[out][inLoop];
                    NumLiters[out][inLoop] = num;
                    if(NumLiters[out][inLoop]==0.0){
                        NumLiters[out][inLoop]=null;
                    }
//the pump arrays are done the same thing(bubble sort)
                    if(out==0){
                        Boolean cho = pump1[inLoop - 1];
                        pump1[inLoop - 1] = pump1[inLoop];
                        pump1[inLoop] = cho;
                    }else if(out==1){
                        Boolean cho = pump2[inLoop - 1];
                        pump2[inLoop - 1] = pump2[inLoop];
                        pump2[inLoop] = cho;
                    }else if(out==2) {
                        Boolean cho = pump3[inLoop - 1];
                        pump3[inLoop - 1] = pump3[inLoop];
                        pump3[inLoop] = cho;
                    }else if(out==3) {
                        Boolean cho = pump4[inLoop - 1];
                        pump4[inLoop - 1] = pump4[inLoop];
                        pump4[inLoop] = cho;
                    }else{
                        Boolean cho = pump5[inLoop - 1];
                        pump5[inLoop - 1] = pump5[inLoop];
                        pump5[inLoop] = cho;
                    }

                } else{
                    break;

                }

            }
//count is removed
            if(out==0){
                count1-=1;
            }else if(out==1){
                count2-=1;
            }else if(out==2){
                count3-=1;
            }else if(out==3){
                count4-=1;
            }else if(out==4){
                count5-=1;
            }




        }

    }
//same thing as RCQ except the first customer in the queue is removed
    public static void PCQ() {
        System.out.println("Remove a served customer.");
        int out = 0;

        if(stock==0){
            System.out.println("NO stocks remaining");
        }else {
            System.out.println("pls enter from which row you want to remove(0,1,2,3,4)");
            out = scanner.nextInt();
            for (int in = 0; in < 6; in++) {
                if (!Objects.equals(FirstName[out][in], null)) {
                    System.out.println("pump " + out + ",row " + in + " severed customer has been removed.");
                    FirstName[out][in] = null;
                    SecondName[out][in] = null;
                    VehicleNo[out][in] = null;
                    stock -= NumLiters[out][in];
                    NumLiters[out][in] = null;



                    for (int inLoop = in+ 1; inLoop < 6; inLoop++) {

                        String temp = FirstName[out][inLoop - 1];
                        FirstName[out][inLoop - 1] = FirstName[out][inLoop];
                        FirstName[out][inLoop] = temp;

                        temp = SecondName[out][inLoop - 1];
                        SecondName[out][inLoop - 1] = SecondName[out][inLoop];
                        SecondName[out][inLoop] = temp;

                        temp = VehicleNo[out][inLoop - 1];
                        VehicleNo[out][inLoop - 1] = VehicleNo[out][inLoop];
                        VehicleNo[out][inLoop] = temp;


                        if(NumLiters[out][inLoop - 1]==null){
                            NumLiters[out][inLoop - 1]=0.0;
                        }
                        double num = NumLiters[out][inLoop - 1];
                        NumLiters[out][inLoop - 1] = NumLiters[out][inLoop];
                        NumLiters[out][inLoop] = num;
                        if(NumLiters[out][inLoop]==0.0){
                            NumLiters[out][inLoop]=null;
                        }

                        if(out==0){
                            Boolean cho = pump1[inLoop - 1];
                            pump1[inLoop - 1] = pump1[inLoop];
                            pump1[inLoop] = cho;
                        }else if(out==1){
                            Boolean cho = pump2[inLoop - 1];
                            pump2[inLoop - 1] = pump2[inLoop];
                            pump2[inLoop] = cho;
                        }else if(out==2) {
                            Boolean cho = pump3[inLoop - 1];
                            pump3[inLoop - 1] = pump3[inLoop];
                            pump3[inLoop] = cho;
                        }else if(out==3) {
                            Boolean cho = pump4[inLoop - 1];
                            pump4[inLoop - 1] = pump4[inLoop];
                            pump4[inLoop] = cho;
                        }else{
                            Boolean cho = pump5[inLoop - 1];
                            pump5[inLoop - 1] = pump5[inLoop];
                            pump5[inLoop] = cho;
                        }

                    }
//this is done if there is customers waiting in the waiting list
//when the first customer in the waiting list is added to the queue the position of the customer in the waiting list is removed
//in arraylist when the first customer is removed (0) the second customer in the waiting list become (0)
                    for (int inLoop =0; inLoop < 6; inLoop++) {
                        if (FirstName[out][inLoop] == null) {
                            try {
                                if (QueueFirstName.get(0) != null) {
                                    FirstName[out][inLoop] = QueueFirstName.get(0);
                                    QueueFirstName.remove(0);

                                    SecondName[out][inLoop] = QueueSecondName.get(0);
                                    QueueSecondName.remove(0);

                                    VehicleNo[out][inLoop] = QueueVehicleNo.get(0);
                                    QueueVehicleNo.remove(0);

                                    NumLiters[out][inLoop] = QueueNumLiters.get(0);
                                    QueueVehicleNo.remove(0);
                                    break;
                                }
                            }catch (IndexOutOfBoundsException nfe){
                                break;
                            }

                        }
                    }
                    if(out==0){
                        count1-=1;
                    }else if(out==1){
                        count2-=1;
                    }else if(out==2){
                        count3-=1;
                    }else if(out==3){
                        count4-=1;
                    }else if(out==4){
                        count5-=1;
                    }

                    break;



                } else if(in==5) {
                    System.out.println("pump " + out + " has no customers.");

                }

            }
            if(stock<=500){
                System.out.println("Only "+stock+" liters stocks remaining");
            }

        }

    }
//this function shows the queue in alphabetical order
    public static void VCS() {
        int out = 0;
        int in = 0;
        int count=0;
//2 temporally arrays has been introduced

        String[][] Alphabet = new String[5][6];
        String[][] Alphabet2 = new String[5][6];


//they are assign the first name and second name
        for (int outLoop = 0; outLoop < 5; outLoop++) {
            for (int inLoop = 0; inLoop < 6; inLoop++) {
                Alphabet[outLoop][inLoop]=FirstName[outLoop][inLoop];
                Alphabet2[outLoop][inLoop]=SecondName[outLoop][inLoop];
            }
        }
//here the compare function is used instead of sort
//if the .compare to is greater than 0 it is moved forward
//like bubble sort
        //0
        boolean fo=true;
        while(fo){
            for (in= 0;in <Alphabet.length; in++){
                if(Alphabet[0][in+1]!=null ){
                    if(Alphabet[0][in].compareTo(Alphabet[0][in+1])>0){

                        String temp = Alphabet[0][in];
                        Alphabet[0][in] =Alphabet[0][in+1];
                        Alphabet[0][in+1]=temp;

                        temp = Alphabet2[0][in];
                        Alphabet2[0][in] =Alphabet2[0][in+1];
                        Alphabet2[0][in+1]=temp;
                    }
                }
                count++;
            }
            if(Alphabet.length*Alphabet.length==count){
                fo=false;
            }
        }
        //1
        out = 0;
        in = 0;
        count=0;
        fo=true;
        while(fo){
            for (in= 0;in <Alphabet.length; in++){
                if(Alphabet[1][in+1]!=null ){
                    if(Alphabet[1][in].compareTo(Alphabet[1][in+1])>0){

                        String temp = Alphabet[1][in];
                        Alphabet[1][in] =Alphabet[1][in+1];
                        Alphabet[1][in+1]=temp;

                        temp = Alphabet2[1][in];
                        Alphabet2[1][in] =Alphabet2[1][in+1];
                        Alphabet2[1][in+1]=temp;
                    }
                }
                count++;
            }
            if(Alphabet.length*Alphabet.length==count){
                fo=false;
            }
        }
        //2
        out= 0;
        in= 0;
        count=0;
        fo=true;
        while(fo){
            for (in= 0;in <Alphabet.length; in++){
                if(Alphabet[2][in+1]!=null ){
                    if(Alphabet[2][in].compareTo(Alphabet[2][in+1])>0){

                        String temp = Alphabet[2][in];
                        Alphabet[2][in] =Alphabet[2][in+1];
                        Alphabet[2][in+1]=temp;

                        temp = Alphabet2[2][in];
                        Alphabet2[2][in] =Alphabet2[2][in+1];
                        Alphabet2[2][in+1]=temp;
                    }
                }
                count++;
            }
            if(Alphabet.length*Alphabet.length==count){
                fo=false;
            }
        }
        //3
        out= 0;
        in= 0;
        count=0;
        fo=true;
        while(fo){
            for (in= 0;in <Alphabet.length; in++){
                if(Alphabet[3][in+1]!=null ){
                    if(Alphabet[3][in].compareTo(Alphabet[3][in+1])>0){

                        String temp = Alphabet[3][in];
                        Alphabet[3][in] =Alphabet[3][in+1];
                        Alphabet[3][in+1]=temp;

                        temp = Alphabet2[3][in];
                        Alphabet2[3][in] =Alphabet2[3][in+1];
                        Alphabet2[3][in+1]=temp;
                    }
                }
                count++;
            }
            if(Alphabet.length*Alphabet.length==count){
                fo=false;
            }
        }
        //4
        out= 0;
        in= 0;
        count=0;
        fo=true;
        while(fo){
            for (in= 0;in <Alphabet.length; in++){
                if(Alphabet[4][in+1]!=null ){
                    if(Alphabet[4][in].compareTo(Alphabet[4][in+1])>0){

                        String temp = Alphabet[4][in];
                        Alphabet[4][in] =Alphabet[4][in+1];
                        Alphabet[4][in+1]=temp;

                        temp = Alphabet2[4][in];
                        Alphabet2[4][in] =Alphabet2[4][in+1];
                        Alphabet2[4][in+1]=temp;
                    }
                }
                count++;
            }
            if(Alphabet.length*Alphabet.length==count){
                fo=false;
            }
        }
//the newly sorted array is displayed
        for (int outLoop = 0; outLoop < 5; outLoop++) {
            for (int inLoop = 0; inLoop < 6; inLoop++) {
                if (Alphabet[outLoop][inLoop] == null) {
                    System.out.println("pump " + outLoop + ",row " + inLoop + " : empty");

                } else  {
                    System.out.println("pump " + outLoop + ",row " + inLoop + " : "+Alphabet[outLoop][inLoop]+" "+Alphabet2[outLoop][inLoop]);
                }
            }
        }
    }

 //store data into a file
 //a txt file is created and then the data is written into it
    public static void SPD() {
        System.out.println("Store Program Data into file.");
        int out=0;
        int in=0;
        System.out.println("please enter the file name ");
        String fileName=scanner.nextLine();

        try {
            File txt = new File(fileName+".txt");
            boolean x = (txt.createNewFile());
            if (!x) {
                System.out.println("You have already created txt file using this name!");
            } else {
                PrintWriter writeFile = new PrintWriter(txt);
                for (out = 0; out < 5; out++) {
                    for (in = 0; in < 6; in++) {
                        if (FirstName[out][in] == null) {
                            writeFile.println("pump " + out + ",row " + in + " is empty");
                        }else{
                            writeFile.println("pump " + out + ",row " + in + " : "+FirstName[out][in]);
                        }
                    }
                }
                writeFile.close();
                System.out.println("\ndata saved into file");
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
        }

    }

//load data from a file
//searches for a file name if the name is not available a message is given
//if the filename is available the content of the filename is shown
    public static void LPD() {
        System.out.println("Load Program Data from file.");
        System.out.println("please enter the file name ");
        String fileName=scanner.nextLine();
        try{
            FileReader load = new FileReader(fileName+".txt");
            int line = load.read();
            while(line != -1){
                System.out.print((char)line);
                line=load.read();
            }
            load.close();
        }catch(Exception e){
            System.out.println("couldn't find a file named "+fileName);
        }

    }

//shows the remaining stock
    public static void STK() {
        System.out.println("View Remaining Fuel Stock.");
        System.out.println(stock);
    }

    public static void AFS() {
        System.out.println("Add Fuel Stock.");
        System.out.println("Enter the amount of stock to add.");
        int addStock= scanner.nextInt();
        stock+=addStock;

    }

//shows the income per queue
    public static void IFQ() {

        for( int out=0;out<5;out++){
            double tot=0;
            for (int in = 0; in < 6; in++) {
                if (FirstName[out][in] != null) {
                    tot+=NumLiters[out][in] * 430;
                }
            }
            System.out.println("the total income in queue "+out+" is "+tot);
        }
    }



//exits the program
//the loop variable is turned false

    public static void EXT() {
        System.out.println("Exit the Program.");
        loop = false;
    }


//this function is used to sort the lengths of each queue from lowest to the largest
//each count with a number (eg: count1)  contains a length of a queue (eg: count1=queue0)
//each count is assign a position in the low array

    public static void SortLow() {
        int count = 0;
        int temp = 0;
        low[0] = count1;
        low[1] = count2;
        low[2] = count3;
        low[3] = count4;
        low[4] = count5;
        boolean fo = true;
//then bubble sort is done to  arrange the array from the lowest count value to the largest
        while (fo) {
            if(low[0]>low[1]){
                temp = low[0];
                low[0] = low[1];
                low[1] = temp;
            }
            if(low[1]>low[2]){
                temp = low[2];
                low[2] = low[1];
                low[1] = temp;
            }
            if(low[2]>low[3]){
                temp = low[2];
                low[2] = low[3];
                low[3] = temp;
            }if(low[3]>low[4]){
                temp = low[3];
                low[3] = low[4];
                low[4] = temp;
            }
            count++;
            if (25== count) {
                fo = false;
            }

        }
    }

}


