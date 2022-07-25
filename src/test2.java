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
public class test2 {
    //boolean pump is used to validation
    public static Boolean[] pump1 = new Boolean[6];
    public static Boolean[] pump2 = new Boolean[6];
    public static Boolean[] pump3 = new Boolean[6];
    public static Boolean[] pump4 = new Boolean[6];
    public static Boolean[] pump5 = new Boolean[6];
    public static String[][] FirstName = new String[5][6];
    public static String[][] SecondName = new String[5][6];
    public static String[][] VehicleNo = new String[5][6];
    public static Double[][] NumLiters = new Double[5][6];

    public  static ArrayList<String> QueueFirstName= new ArrayList<String>();
    public  static ArrayList<String> QueueSecondName= new ArrayList<String>();
    public  static ArrayList<String> QueueVehicleNo= new ArrayList<String>();
    public  static ArrayList<Double> QueueNumLiters= new ArrayList<Double>();
    public static int low[] = new int[5];

    public static Scanner scanner = new Scanner(System.in);

    public static int stock = 6600;

    public static int count1 = 0;
    public static int count2 = 0;
    public static int count3 = 0;
    public static int count4 = 0;
    public static int count5 = 0;

    public static boolean loop = true;

    public static void main(String[] args){
        menu();
        LOOP();
    }

    public  static void LOOP() {


        System.out.println("\n pls choose an option ");
        String choice = scanner.nextLine();
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
            case 100 -> System.out.println("\n you have selected a wrong option !");

        }
        if(loop){
            LOOP();
        }else{
            System.out.println("\n Thank you for using this program ");
        }
    }

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

    public static void ACQ() {

        int in = 0;
        int out = 0;
        SortLow();
        System.out.println("Add customer to a Queue");
        if(count1==6 && count2==6 && count3==6 && count4==6 && count5==6) {
            System.out.println("All the pumps are full");
            System.out.println("You are been added to the waiting list.");
            System.out.println("pls enter customer First name");
            String name = scanner.nextLine();
            QueueFirstName.add(name);
            System.out.println("pls enter customer Second name");
            name = scanner.nextLine();
            QueueSecondName.add(name);
            System.out.println("pls enter customer Vehicle number ");
            String Vnum = scanner.nextLine();
            QueueVehicleNo.add(Vnum);

            do{
                System.out.println("pls enter  number of liters that the customer wants to buy? ");
                String num = scanner.nextLine();
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
                }
            }while(true);

            int size=QueueFirstName.size()-1;
            System.out.println("a customer "+QueueFirstName.get(size)+" "+QueueFirstName.get(size)+ " is added to waiting list in "+size+" position.");








        }else if((count1==low[0] && count2==low[0] && count3==low[0] && count4==low[0] && count5==low[0])){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[0][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name = scanner.nextLine();
                    FirstName[0][in]=name;
                    System.out.println("pls enter customer Second name");
                    name = scanner.nextLine();
                    SecondName[0][in]=name;
                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum = scanner.nextLine();
                    VehicleNo[0][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.nextLine();
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
                        }
                    }while(true);

                    count1+=1;
                    pump1[in]=true;
                    System.out.println("a customer "+FirstName[0][in]+" "+SecondName[0][in]+ " is added to pump  0, row " + in+ ".");
                    break;
                }
            }


        }else if(count1==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[0][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name = scanner.nextLine();
                    FirstName[0][in]=name;
                    System.out.println("pls enter customer Second name");
                    name = scanner.nextLine();
                    SecondName[0][in]=name;
                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum = scanner.nextLine();
                    VehicleNo[0][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.nextLine();
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
                        }
                    }while(true);

                    count1+=1;
                    pump1[in]=true;
                    System.out.println("a customer "+FirstName[0][in]+" "+SecondName[0][in]+ " is added to pump  0, row " + in+ ".");
                    break;
                }
            }
        }else if(count2==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[1][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name = scanner.nextLine();
                    FirstName[1][in]=name;
                    System.out.println("pls enter customer Second name");
                    name = scanner.nextLine();
                    SecondName[1][in]=name;
                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum = scanner.nextLine();
                    VehicleNo[1][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.nextLine();
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
                        }
                    }while(true);

                    count2+=1;
                    pump2[in]=true;
                    System.out.println("a customer "+FirstName[1][in]+" "+SecondName[1][in]+ " is added to pump  1, row " + in+ ".");
                    break;
                }
            }
        }else if(count3==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[2][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name = scanner.nextLine();
                    FirstName[2][in]=name;
                    System.out.println("pls enter customer Second name");
                    name = scanner.nextLine();
                    SecondName[2][in]=name;
                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum = scanner.nextLine();
                    VehicleNo[2][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.nextLine();
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
                        }
                    }while(true);

                    count3+=1;
                    pump3[in]=true;
                    System.out.println("a customer "+FirstName[2][in]+" "+SecondName[2][in]+ " is added to pump  2, row " + in+ ".");
                    break;
                }
            }
        }else if(count4==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[3][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name = scanner.nextLine();
                    FirstName[3][in]=name;
                    System.out.println("pls enter customer Second name");
                    name = scanner.nextLine();
                    SecondName[3][in]=name;
                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum = scanner.nextLine();
                    VehicleNo[3][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.nextLine();
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
                        }
                    }while(true);

                    count4+=1;
                    pump4[in]=true;
                    System.out.println("a customer "+FirstName[3][in]+" "+SecondName[3][in]+ " is added to pump  3, row " + in+ ".");
                    break;
                }
            }
        }else if(count5==low[0]){
            for (in = 0; in < 6; in++) {
                if (Objects.equals(FirstName[4][in], null)) {
                    System.out.println("pls enter customer First name");
                    String name = scanner.nextLine();
                    FirstName[4][in]=name;
                    System.out.println("pls enter customer Second name");
                    name = scanner.nextLine();
                    SecondName[4][in]=name;
                    System.out.println("pls enter customer Vehicle number ");
                    String Vnum = scanner.nextLine();
                    VehicleNo[4][in]=Vnum;

                    do{
                        System.out.println("pls enter  number of liters that the customer wants to buy? ");
                        String num = scanner.nextLine();
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
                        }
                    }while(true);

                    count5+=1;
                    pump5[in]=true;
                    System.out.println("a customer "+FirstName[4][in]+" "+SecondName[4][in]+ " is added to pump  4, row " + in+ ".");
                    break;
                }
            }
        }
    }

    public static void RCQ() {
        System.out.println("Remove a customer from a Queue. (From a specific location)");
        int out = 0;
        int in = 0;
        System.out.println("pls enter from which row you want to remove(0,1,2,3,4)");
        out = scanner.nextInt();
        System.out.println("pls enter from which column you want to remove(0,1,2,3,4,5)");
        in = scanner.nextInt();
        if (Objects.equals(FirstName[out][in], null)) {
            System.out.println("there is no customer at the given location");
        } else {
            FirstName[out][in] = null;
            System.out.println("pump " + out + ",row " + in + " customer has been removed.");
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

                    double num = NumLiters[out][inLoop - 1];
                    NumLiters[out][inLoop - 1] = NumLiters[out][inLoop];
                    NumLiters[out][inLoop] = num;

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




        }

    }

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

                        double num = NumLiters[out][inLoop - 1];
                        NumLiters[out][inLoop - 1] = NumLiters[out][inLoop];
                        NumLiters[out][inLoop] = num;

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


                } else {
                    System.out.println("pump " + out + " has no customers.");
                }
                break;
            }
            if(stock<=500){
                System.out.println("Only "+stock+" liters stocks remaining");
            }
        }
    }

    public static void VCS() {
        int out = 0;
        int in = 0;
        int count=0;
        //0
        boolean fo=true;
        while(fo){
            for (in= 0;in <FirstName.length; in++){
                if(FirstName[0][in+1]!=null ){
                    if(FirstName[0][in].compareTo(FirstName[0][in+1])>0){

                        String temp = FirstName[0][in];
                        FirstName[0][in] =FirstName[0][in+1];
                        FirstName[0][in+1]=temp;
                    }
                }
                count++;
            }
            if(FirstName.length*FirstName.length==count){
                fo=false;
            }
        }
        //1
        out = 0;
        in = 0;
        count=0;
        fo=true;
        while(fo){
            for (in= 0;in <FirstName.length; in++){
                if(FirstName[1][in+1]!=null ){
                    if(FirstName[1][in].compareTo(FirstName[1][in+1])>0){

                        String temp = FirstName[1][in];
                        FirstName[1][in] =FirstName[1][in+1];
                        FirstName[1][in+1]=temp;
                    }
                }
                count++;
            }
            if(FirstName.length*FirstName.length==count){
                fo=false;
            }
        }
        //2
        out= 0;
        in= 0;
        count=0;
        fo=true;
        while(fo){
            for (in= 0;in <FirstName.length; in++){
                if(FirstName[2][in+1]!=null ){
                    if(FirstName[2][in].compareTo(FirstName[2][in+1])>0){

                        String temp = FirstName[2][in];
                        FirstName[2][in] =FirstName[2][in+1];
                        FirstName[2][in+1]=temp;
                    }
                }
                count++;
            }
            if(FirstName.length*FirstName.length==count){
                fo=false;
            }
        }
        //3
        out= 0;
        in= 0;
        count=0;
        fo=true;
        while(fo){
            for (in= 0;in <FirstName.length; in++){
                if(FirstName[3][in+1]!=null ){
                    if(FirstName[3][in].compareTo(FirstName[3][in+1])>0){

                        String temp = FirstName[3][in];
                        FirstName[3][in] =FirstName[3][in+1];
                        FirstName[3][in+1]=temp;
                    }
                }
                count++;
            }
            if(FirstName.length*FirstName.length==count){
                fo=false;
            }
        }
        //4
        out= 0;
        in= 0;
        count=0;
        fo=true;
        while(fo){
            for (in= 0;in <FirstName.length; in++){
                if(FirstName[4][in+1]!=null ){
                    if(FirstName[4][in].compareTo(FirstName[4][in+1])>0){

                        String temp = FirstName[4][in];
                        FirstName[4][in] =FirstName[4][in+1];
                        FirstName[4][in+1]=temp;
                    }
                }
                count++;
            }
            if(FirstName.length*FirstName.length==count){
                fo=false;
            }
        }




    }

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




    public static void EXT() {
        System.out.println("Exit the Program.");
        loop = false;
    }



    public static void SortLow() {
        int count = 0;
        int temp = 0;
        low[0] = count1;
        low[1] = count2;
        low[2] = count3;
        low[3] = count4;
        low[4] = count5;
        boolean fo = true;
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
        System.out.println(low[0]);
        System.out.println(low[1]);
        System.out.println(low[2]);
        System.out.println(low[3]);
        System.out.println(low[4]);

    }







}

