package counting_sort;

import javafx.event.ActionEvent;

import java.util.ArrayList;

public class Sort
{
  public static ArrayList<Integer> countingSort (ArrayList<Integer> data)
  {
    // if array is empty or has one element, there is nothing to sort
    if (data == null || data.size() < 2)
      return data;

    int len = data.size();

    // determine minimum and maximum value in list
    int min = data.get(0);
    int max = data.get(0);
    for (int num : data) {
      min = min < num ? min : num;
      max = max > num ? max : num;
    }

    // prepare count array
    int countLen = max - min + 1;
    int[] count = new int[countLen];


    // prepare res array
    ArrayList<Integer> res = new ArrayList<>(len);
    for (int i = 0; i < len; ++i)
      res.add(0);

    // distribute numbers into count array
    for (int num : data) {
      count[num - min]++;
    }

    // adjust count array
    for (int i = 0; i < countLen - 1; ++i) {
      count[i + 1] += count[i];
    }

    // read backwards and sort into res
    for (int i = len - 1; i > 0 - 1; --i) {
      int curr = data.get(i);
      // save the last number to the result vector at index determined by the sum in the count arr
      res.set(count[curr - min] - 1, curr);

      // subtract the number from the arr so next number lands before it
      count[data.get(i) - min]--;
    }

    // print result:
    for (int i = 0; i < len; ++i)
      System.out.println(res.get(i));

    return res;
  }
}
