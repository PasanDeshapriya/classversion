public class test1 {
    public static void main(String[] args){
        int low[]=new int[5];
        int count1=3;
        int count2=6;
        int count3=1;
        int count4=9;
        int count5=0;

        low[0] = count1;
        low[1] = count2;
        low[2] = count3;
        low[3] = count4;
        low[4] = count5;

        int count = 0;
        int temp = 0;
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