package Sort_for_seil;
/*
За подробностями алгоритма можно посетить "https://en.wikipedia.org/wiki/American_flag_sort"
*/
import java.util.Arrays;

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

   
        public static void main(String []args){
         Integer[] nums = new Integer[] { 3, 2, 5, 0, 6, 4, 8, 7, 1, 6 };
           sort(nums);
           System.out.println(Arrays.toString(nums));
        }
   }