package Sort_for_seil;
/*
За подробностями алгоритма можно посетить "https://en.wikipedia.org/wiki/American_flag_sort"
*/

import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;



public class AmericanFlagSort {

   private static final int BUCKETS_NUMBER = 16; 

   private AmericanFlagSort() { }

   public static Integer[] sort(Integer[] unsorted) {
       int numberOfDigits = getMaxNumberOfDigits(unsorted); // Максимальное количество цифр
       int max = 1;
       for (int i = 0; i < numberOfDigits - 1; i++)
           max *= 10;        
       sort(unsorted, 0, unsorted.length, max);
       return unsorted;
   }

   public static void sort(Integer[] unsorted, int start, int length, int divisor) {   
       // Первый проход - найти счет
       int[] count = new int[BUCKETS_NUMBER];
       int[] offset = new int[BUCKETS_NUMBER];
       int digit = 0;
       for (int i = start; i < length; i++) {
           int d = unsorted[i];
           digit = getDigit(d, divisor);
           count[digit]++;
       }
       offset[0] = start;
       for (int i = 1; i < BUCKETS_NUMBER; i++) {
           offset[i] = count[i - 1] + offset[i - 1];
       }
       // Второй проход - занять позицию
       for (int b = 0; b < BUCKETS_NUMBER; b++) {
           while (count[b] > 0) {
               int origin = offset[b];
               int from = origin;
               int num = unsorted[from];
               unsorted[from] = -1;
               do {
                   digit = getDigit(num, divisor);
                   int to = offset[digit]++;
                   count[digit]--;
                   int temp = unsorted[to];
                   unsorted[to] = num;
                   num = temp;
                   from = to;
               } while (from != origin);
           }
       }
       if (divisor > 1) {
           // Sort the buckets
           for (int i = 0; i < BUCKETS_NUMBER; i++) {
               int begin = (i > 0) ? offset[i - 1] : start;
               int end = offset[i];
               if (end - begin > 1)
                   sort(unsorted, begin, end, divisor / 10);
           }
       }
   }

   private static int getMaxNumberOfDigits(Integer[] unsorted) {
       int max = Integer.MIN_VALUE;
       int temp = 0;
       for (int i : unsorted) {
           temp = (int) Math.log10(i) + 1;
           if (temp > max)
               max = temp;
       }
       return max;
   }

   private static int getDigit(int integer, int divisor) {
       return (integer / divisor) % 10;
   }
    
    private static void Savemas(Integer[] nums){

    try(FileWriter writer = new FileWriter("masives.txt", false))
    {
       // запись всей строки
        String text = Arrays.toString(nums);
        writer.write(text);
        // запись по символам
        writer.append('\n');       
         
        writer.flush();
    }
    catch(IOException ex){
         
        System.out.println(ex.getMessage());
    } 

}





private static void creator(){
    Integer[] nums;
    String Choice;
    System.out.println("Ты хочешь (1)отсортировать готовый пример или (2)написать свой пример");
    Scanner in = new Scanner(System.in);
    Choice = in.nextLine();
    int parsic = Integer.parseInt(Choice);
    switch (parsic) {
        case 1: 
        long before = System.currentTimeMillis();
        nums = new Integer[] { 3, 2, 5, 0, 6, 4, 8, 7, 1, 6 };
           sort(nums);
           Savemas(nums);
           long after = System.currentTimeMillis();
           System.out.println("Время исполнения = " + (after - before) + " мс.");
           System.out.println(Arrays.toString(nums));
           

           menu();
                 break;
        case 2: 
        Scanner input = new Scanner(System.in); 
        System.out.println("Enter array length: ");
        int size = input.nextInt(); 
        nums = new Integer[size]; 
        System.out.println("Insert array elements:");        
        for (int i = 0; i < size; i++) {
            nums[i] = input.nextInt(); 
        }    
        long before2 = System.currentTimeMillis();
           sort(nums);
           long after2 = System.currentTimeMillis();
           System.out.println("Время исполнения = " + (after2 - before2) + " мс.");
           Savemas(nums);
           System.out.println(Arrays.toString(nums));
           menu();
                 break;
        default: creator();
             break;
            }

}

private static void menu(){    
    String Choice;
    System.out.println("Ты что хочешь отсортировать по американскому флагу(yes/no)");
    Scanner in = new Scanner(System.in);
    Choice = in.nextLine();
    switch (Choice) {
        case "yes": creator(); 
                 break;
        case "no": System.exit(0); 
                 break;
        default: menu();
             break;
            }

}   
        public static void main(String []args){
           menu();      
           
        }
   }