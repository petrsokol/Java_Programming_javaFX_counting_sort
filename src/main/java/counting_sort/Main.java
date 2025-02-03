package counting_sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main
{
  public static void main (String[] args)
  {
    App.main(args);
  }
}

/*
    // specify data file
    String address = "C:\\java\\hlavac\\zk\\data";
    String name = "data_02";
    File file = new File(address + "\\" + name + ".txt");

    // create an array for data to be sorted
    ArrayList<Integer> dataRead = new ArrayList<>();

    try {
      // open a scanner for data reading
      Scanner scanner = new Scanner(file);

      // read all lines in the file and count them
      int len = 0;
      while (scanner.hasNextInt()) {
        int tmp = scanner.nextInt();
        dataRead.add(tmp);
        len++;
      }
      System.out.println("Successfully loaded " + len + " numbers.");

      // close the scanner
      scanner.close();

      // operations on data
      ArrayList<Integer> sorted = new ArrayList<>(len);

      sorted = Sort.countingSort(dataRead);

      // save sorted data
      try {
        // open stream
        FileWriter fileWriter = new FileWriter(address + "\\" + name + "_sorted" + ".txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // write to file
        for (int i = 0; i < len; ++i)
          bufferedWriter.write(sorted.get(i) + "\n");

        // close stream
        bufferedWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    // report error for file not found
    catch (FileNotFoundException e) {
      System.err.println("Error: file not found.");
      e.printStackTrace();
    }

    Random random = new Random(1);
    for (int i = 0; i < 50; ++i) {
      System.out.println(random.nextInt(30, 50));
    }
 */