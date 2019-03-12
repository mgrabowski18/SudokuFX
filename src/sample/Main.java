package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class Main extends Application {

    private static TextField[][] tx = new TextField[9][9];
    private static int[][] gridValues;
    private static String[][] gridId;


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sudoku");
        Sudoku2 sudo = new Sudoku2();
        gridValues = sudo.getGrid();


        StackPane layout = new StackPane();
        layout.setMinSize(600, 600);
        layout.setMaxSize(600, 600);


        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setPrefSize(600, 600);
        flow.setMinSize(600, 600);
        flow.setMaxSize(600, 600);
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(600);


        GridPane[][] topPane = new GridPane[3][3];
        int x_pane;
        int y_pane;


        for (int i = 0; i <= 8; i++) {
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
                    tx[i][index].setId(Integer.toString(i) + index);
                    tx[i][index].setAlignment(Pos.CENTER);
                    if (gridValues[i][index] != 0) {
                        tx[i][index].setText(Integer.toString(gridValues[i][index]));
                        tx[i][index].setDisable(true);
                    }
                    tx[i][index].setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
                        String newText = change.getControlNewText();
                        if (newText.length() > 1 || newText.matches("[^1-9]")) {
                            return null;
                        } else {
                            return change;
                        }
                    }));

                    tx[i][index].setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            StringBuilder build = new StringBuilder().append(event.getSource());
                            int index = build.lastIndexOf("id=");
                            build = build.delete(0, index + 3);
                            index = build.indexOf(",");
                            build = build.delete(index, build.length());


                            String id = build.toString();
                            String value = event.getText();
                            int segment = Integer.parseInt(String.valueOf(id.charAt(0)));
                            int pos = Integer.parseInt(String.valueOf(id.charAt(1)));

                            Map<String, String> toReturn = new HashMap<>();

                            if (value.matches("[1-9]") && tx[segment][pos].getText().equals("")) {
                                if (!tx[segment][pos].isDisable()) {
                                    sudo.add(id, value);
                                    if (!sudo.isGridValid(segment, pos, Integer.parseInt(event.getText()))) {
                                        tx[segment][pos].setStyle("-fx-background-color: #AB4642;");
                                        //tx[segment][pos].setBackground();
                                    }
                                    else {
                                        gridValues = sudo.getGrid();
                                        tx[segment][pos].setStyle(null);
                                        System.out.println("");
                                        for (int i = 0; i < gridValues.length; i++) {
                                            for (int j = 0; j < gridValues[i].length; j++)
                                                System.out.print(gridValues[i][j] + " ");
                                            System.out.println("");
                                        }
                                    }
                                    //System.out.println(tx[segment][pos].getText());
                                }
                            }
                            else
                                tx[segment][pos].setStyle(null);

                        }
                    });


                    topPane[x_pane][y_pane].add(tx[i][index], k + i % 3 * 3, j + i / 3 * 3);


                }

            }
            flow.getChildren().add(topPane[x_pane][y_pane]);
        }


        flow.setAlignment(Pos.CENTER);
        layout.getChildren().add(flow);

        Scene scene = new Scene(layout, 600, 600);
        scene.getStylesheets().add("sample/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
