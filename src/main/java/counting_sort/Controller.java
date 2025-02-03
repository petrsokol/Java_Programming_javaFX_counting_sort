package counting_sort;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable
{
  /*------------------------------------------------------------------------------------------------------------------*/

  private Stage stage;
  public TextArea textAreaData;

  /*------------------------------------------------------------------------------------------------------------------*/

  public void chooseFile ()
  {
    File file = loadWithFileChooser();
    ArrayList<Integer> data = loadFileToArray(file);
    displayData(data);
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  private File loadWithFileChooser ()
  {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open File");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    return fileChooser.showOpenDialog(stage);
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  private ArrayList<Integer> loadFileToArray (File file)
  {
    ArrayList<Integer> data = new ArrayList<>();
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        data.add(scanner.nextInt());
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    }
    return data;
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  public void sortData ()
  {
    ArrayList<Integer> inputData = getData();
    ArrayList<Integer> sortedData = Sort.countingSort(inputData);
    displayData(sortedData);
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  public void saveChanges ()
  {
    ArrayList<Integer> inputData = getData();
    File file = openSaveAsDialog();
    if (file != null) {
      saveDataToFile(file, inputData);
    }
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  private File openSaveAsDialog() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save As");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    return fileChooser.showSaveDialog(stage);
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  private void saveDataToFile(File file, ArrayList<Integer> data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      for (Integer number : data) {
        writer.write(number.toString());
        writer.newLine();
      }
      System.out.println("File saved successfully: " + file.getAbsolutePath());
    } catch (IOException e) {
      System.err.println("Error saving file: " + e.getMessage());
    }
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  private void displayData (List<Integer> data)
  {
    StringBuilder content = new StringBuilder();
    if (data == null)
      return;
    for (int line : data) {
      content.append(line).append("\n");
    }
    textAreaData.setText(content.toString());
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  private ArrayList<Integer> getData ()
  {
    String rawData = textAreaData.getText();
    String[] lines = rawData.split("\n");
    int len = lines.length;
    ArrayList<Integer> res = new ArrayList<>(len);

    for (String line : lines) {
      res.add(Integer.parseInt(line.trim()));
    }
    return res;
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  public void terminateApp ()
  {
    Platform.exit();
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  public void setStage (Stage stage)
  {
    this.stage = stage;
  }

  /*------------------------------------------------------------------------------------------------------------------*/

  @Override
  public void initialize (URL url, ResourceBundle resourceBundle)
  {

  }

  /*------------------------------------------------------------------------------------------------------------------*/
}



