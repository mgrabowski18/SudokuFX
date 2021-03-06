package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application {

    private static TextField[][] tx = new TextField[9][9];
    private static int[][] gridValues;
    private static String id = "";
    private static int segment;
    private static int pos;



    @Override
    public void start(Stage primaryStage) {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sudoku");
        Sudoku2 sudo = new Sudoku2();
        gridValues = sudo.getGrid();


        BorderPane layout = new BorderPane();
        layout.setMinSize(600, 700);
        layout.setMaxSize(600, 700);


        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setPrefSize(600, 600);
        flow.setMinSize(600, 600);
        flow.setMaxSize(600, 600);
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(600);

        HBox hButton = new HBox();
        hButton.setPadding(new Insets(15, 12, 15, 12));
        hButton.setSpacing(45);
        hButton.setPrefSize(600, 100);
        hButton.setMinSize(600, 100);
        hButton.setMaxSize(600, 100);
        hButton.setStyle("-fx-background-color: #555555;");


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


                            id = changeId(event.getSource().toString());
                            String value = event.getText();
                            segment = Integer.parseInt(String.valueOf(id.charAt(0)));
                            pos = Integer.parseInt(String.valueOf(id.charAt(1)));


                            if (value.matches("[1-9]") && tx[segment][pos].getText().equals("")) {
                                if (!tx[segment][pos].isDisable()) {

                                    sudo.add(id, value);
                                    if (!sudo.isGridValid(segment, pos, Integer.parseInt(event.getText()))) {
                                        tx[segment][pos].setStyle("-fx-background-color: #AB4642;");
                                        //tx[segment][pos].setBackground();
                                    } else {
                                        gridValues = sudo.getGrid();
                                        tx[segment][pos].setStyle(null);
                                    }
                                }
                            }
                            if (tx[segment][pos].getText().equals("") && !value.matches("[1-9]")) {
                                tx[segment][pos].setStyle(null);
                                sudo.add(id, "0");
                                gridValues = sudo.getGrid();
                            }
                            for (int i = 0; i < gridValues.length; i++) {
                                for (int j = 0; j < gridValues[i].length; j++) {
                                    id = changeId(tx[i][j].toString());
                                    segment = Integer.parseInt(String.valueOf(id.charAt(0)));
                                    pos = Integer.parseInt(String.valueOf(id.charAt(1)));
                                    value = tx[i][j].getText();
                                    if (!value.equals("") && !tx[i][j].isDisable()) {
                                        if (sudo.isGridValid(segment, pos, Integer.parseInt(value))) {
                                            tx[i][j].setStyle(null);
                                            sudo.add(id, value);
                                            gridValues = sudo.getGrid();
                                        } else {
                                            tx[segment][pos].setStyle("-fx-background-color: #AB4642;");
                                            sudo.add(id, "0");
                                            gridValues = sudo.getGrid();
                                        }
                                    }
                                }
                            }
                            winEvent();
                        }
                    });
                    topPane[x_pane][y_pane].add(tx[i][index], k + i % 3 * 3, j + i / 3 * 3);
                }
            }
            flow.getChildren().add(topPane[x_pane][y_pane]);
        }

        Button newButton = new Button("New Grid");
        Button solveButton = new Button("Solve Grid");
        Button resetButton = new Button("Clear Grid");

        newButton.setPrefSize(100, 20);
        solveButton.setPrefSize(100, 20);
        resetButton.setPrefSize(100, 20);

        hButton.getChildren().addAll(newButton, solveButton, resetButton);


        flow.setAlignment(Pos.CENTER);
        hButton.setAlignment(Pos.CENTER);


        layout.setCenter(flow);
        layout.setBottom(hButton);


        Scene scene = new Scene(layout, 600, 700);
        scene.getStylesheets().add("sample/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        newButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sudo.resetGrid();
                for (int i = 0; i < tx.length; i++)
                    for (int j = 0; j < tx[i].length; j++) {
                        tx[i][j].setDisable(false);
                        tx[i][j].setStyle(null);
                        tx[i][j].setText("");
                        gridValues[i][j] = 0;
                    }

                gridValues = sudo.getGrid();

                for (int i = 0; i < gridValues.length; i++) {
                    for (int j = 0; j <= 2; j++) {
                        for (int k = 0; k <= 2; k++) {
                            int index = (j + k) + 2 * j;
                            if (gridValues[i][index] != 0) {
                                tx[i][index].setText(Integer.toString(gridValues[i][index]));
                                tx[i][index].setDisable(true);
                            }
                        }
                    }
                }
            }
        });

        solveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gridValues=sudo.getSolvedGrid();
                for (int i = 0; i < gridValues.length; i++) {
                    for (int j = 0; j <= 2; j++) {
                        for (int k = 0; k <= 2; k++) {
                            int index = (j + k) + 2 * j;
                            if (!tx[i][index].isDisable()) {
                                tx[i][index].setText(String.valueOf(gridValues[i][index]));
                                tx[i][index].setStyle(null);
                                tx[i][index].setDisable(true);
                            }
                        }
                    }
                }
            }


        });

        resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gridValues=sudo.clearToSolve();
                for (int i = 0; i < gridValues.length; i++) {
                    for (int j = 0; j <= 2; j++) {
                        for (int k = 0; k <= 2; k++) {
                            int index = (j + k) + 2 * j;
                            if (!tx[i][index].isDisable()) {
                                tx[i][index].setText("");
                            }
                            tx[i][index].setStyle(null);
                        }
                    }
                }
                //gridValues=sudo.getGrid();

            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

    private boolean isCorrect(int[][] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                if (array[i][j] == 0)
                    return false;
        return true;
    }

    private String changeId(String input) {
        StringBuilder build = new StringBuilder().append(input);
        build = build.delete(0, build.lastIndexOf("id=") + 3);
        build = build.delete(build.indexOf(","), build.length());
        return build.toString();
    }

    private void winEvent() {
        if (isCorrect(gridValues)) {
            Alert winWindow = new Alert(Alert.AlertType.INFORMATION);
            winWindow.setTitle("YOU WIN!");
            winWindow.setHeaderText("You solved sudoku!");
            winWindow.show();
            for (int i = 0; i < tx.length; i++) {
                for (int j = 0; j < tx[i].length; j++) {
                    tx[i][j].setText(String.valueOf(gridValues[i][j]));
                    tx[i][j].setDisable(true);
                    tx[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #FFFFFF;");
                }
            }
        }
        return;
    }
}
