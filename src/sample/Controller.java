package sample;

import javafx.scene.control.TextField;
import java.awt.event.ActionEvent;

public class Controller {


    private TextField tx1;

    public void handleTextFieldAction()
    {

        System.out.println(tx1.getText());
    }


}
