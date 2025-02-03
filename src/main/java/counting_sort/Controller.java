package counting_sort;

import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Controller implements Initializable
{
  private Stage stage;

  public Label labelAppName;
  public TextArea textAreaData;

  private ArrayList<Integer> data;

  public void chooseFile ()
  {
    File file = openFileChooser();
    data = loadFileToArray(file);
    displayData(data);
  }

  public void sortData ()
  {
    ArrayList<Integer> inputData = getData();
    ArrayList<Integer> sortedData = Sort.countingSort(inputData);
    displayData(sortedData);

    // Dynamically add a CSS class
    textAreaData.getStyleClass().add("text-area");

    // Dynamically set inline styles
    textAreaData.setStyle("-fx-background-color: #2E3440; -fx-text-fill: #D8DEE9;");
  }

  public void saveChanges ()
  {
    ArrayList<Integer> inputData = getData();
    File file = openSaveAsDialog();
    if (file != null) {
      saveDataToFile(file, inputData);
    }
  }

  /**
   * Opens a "Save As" dialog and returns the selected file.
   */
  private File openSaveAsDialog() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save As");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    return fileChooser.showSaveDialog(stage);
  }

  /**
   * Saves the ArrayList data to the specified file.
   */
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

  public void discardChanges ()
  {

  }

  private File openFileChooser ()
  {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open File");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    return fileChooser.showOpenDialog(stage);
  }

  /**
   * Reads the file and loads its content into a List.
   */
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

  /**
   * Displays the loaded data in the TextArea.
   */
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

  private ArrayList<Integer> getData ()
  {
    String rawData = textAreaData.getText();
    String[] lines = rawData.split("\n");
    int len = lines.length;
    ArrayList<Integer> res = new ArrayList<>(len);

    for (int i = 0; i < len; ++i) {
      res.add(Integer.parseInt(lines[i].trim()));
    }
    return res;
  }

  public void terminateApp ()
  {
    Platform.exit();
  }

  public void setStage (Stage stage)
  {
    this.stage = stage;
  }

  @Override
  public void initialize (URL url, ResourceBundle resourceBundle)
  {
    return;
  }
}



