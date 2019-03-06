package sample;

import com.sun.javafx.binding.StringFormatter;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.event.KeyListener;
import java.util.stream.Stream;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sudoku");


        StackPane layout = new StackPane();
        layout.setMinSize(600,600);
        layout.setMaxSize(600,600);



        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5,0,5,0));
        flow.setPrefSize(600,600);
        flow.setMinSize(600,600);
        flow.setMaxSize(600,600);
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(600);


        GridPane[][] topPane = new GridPane[3][3];
        int x_pane;
        int y_pane;

        TextField[][] tx = new TextField[9][9];
        for(int i=0; i<=8; i++) {
            x_pane = i % 3;
            y_pane = i / 3;
            topPane[x_pane][y_pane] = new GridPane();
            for (int j = 0; j <= 2; j++) {
                for (int k = 0; k <= 2; k++) {
                    int index = (j + k) + 2 * j;
                    tx[i][index] = new TextField();
                    tx[i][index].setMinSize(60, 60);
                    tx[i][index].setMaxSize(60, 60);
                    tx[i][index].setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                    tx[i][index].setId(Integer.toString(i) + Integer.toString(index));
                    tx[i][index].setAlignment(Pos.CENTER);
                    tx[i][index].setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
                        String newText = change.getControlNewText();
                        if (newText.length() > 1 || newText.matches("[^1-9]")) {
                            return null ;
                        } else {
                            return change ;
                        }
                    }));
                    tx[i][index].setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            StringBuffer buff = new StringBuffer();
                            //buff.
                            System.out.println(event.getSource());
                        }
                    });


                    topPane[x_pane][y_pane].add(tx[i][index], k + i % 3 * 3, j + i / 3 * 3);


                }

            }
            flow.getChildren().add(topPane[x_pane][y_pane]);
        }

        flow.setAlignment(Pos.CENTER);
        layout.getChildren().add(flow);


        primaryStage.setScene(new Scene(layout, 600, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
