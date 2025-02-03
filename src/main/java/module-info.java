module com.example.javafx_counting_sort {
  requires javafx.controls;
  requires javafx.fxml;


  opens counting_sort to javafx.fxml;
  exports counting_sort;
}