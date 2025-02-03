package counting_sort;

import java.util.ArrayList;
import java.util.Collections;

/*====================================================================================================================*/

public class Sort
{
  /*------------------------------------------------------------------------------------------------------------------*/

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
      min = Math.min(min, num);
      max = Math.max(max, num);
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
    for (int i = len - 1; i > -1; --i) {
      int curr = data.get(i);
      // save the last number to the result vector at index determined by the sum in the count arr
      res.set(count[curr - min] - 1, curr);

      // subtract the number from the arr so next number lands before it
      count[data.get(i) - min]--;
    }

    return res;
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  public static void swap (ArrayList<Integer> data, int i)
  {
    int tmp = data.get(i);
    data.set(i, data.get(i + 1));
    data.set(i + 1, tmp);
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  public static ArrayList<Integer> bubbleSort (ArrayList<Integer> data)
  {
    int len = data.size();
    boolean isSorted = false;
    while (!isSorted) {
      isSorted = true;
      for (int i = 0; i < len - 1; ++i) {
        if (data.get(i) > data.get(i + 1)) {
          isSorted = false;
          swap(data, i);
        }
      }
    }
    return data;
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  public static ArrayList<Integer> insertSort (ArrayList<Integer> data)
  {
    int len = data.size();
    for (int i = 1; i < len; ++i) {
      for (int j = i - 1; j >= 0; --j) {
        if (data.get(j) > data.get(j + 1)) {
          swap(data, j);
          continue;
        }
        break;
      }
    }
    return data;
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  public static ArrayList<Integer> shuffle (ArrayList<Integer> data)
  {
    if (data == null)
      return null;
    Collections.shuffle(data);
    return data;
  }
}

/*====================================================================================================================*/