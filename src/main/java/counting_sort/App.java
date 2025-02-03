package counting_sort;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application
{
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    try {
      // formalities
      FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("app.fxml")));
      Parent root = loader.load();
      Controller controller = loader.getController();

      // switch to default screen
      Scene scene = new Scene(root);

      controller.setStage(stage);

      // keyboard controls
      scene.setOnKeyPressed(event -> {
        if (event.isControlDown()) {
          // multi key shortcuts using CTRL
          switch(event.getCode()) {
            // open file
            case O -> controller.chooseFile();

            // save changes
            case S -> controller.saveChanges();

            // exit app
            case W -> controller.terminateApp();

          }
        } else {
          switch(event.getCode()) {

            // fullscreen
            case F11, F -> stage.setFullScreen(!stage.isFullScreen());

            // sort - does not work - edits the data in dataPane
            case S -> controller.countingSort();
          }
        }
      });

      // add CSS
      String css = Objects.requireNonNull(this.getClass().getResource("CSS.css")).toExternalForm();
      scene.getStylesheets().add(css);

      // add image icon and title
      // stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("appIcon.png"))));
      stage.setScene(scene);
      stage.setTitle("Sorting App");

      // set fullscreen mode
      stage.setFullScreen(false);
      stage.setFullScreenExitHint("");
      stage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.K));

      // set minimum window size
      stage.setMinHeight(950);
      stage.setMinWidth(700);

      // show the display
      stage.show();

    } catch(Exception e) {
      e.printStackTrace();
    }


  }
}
