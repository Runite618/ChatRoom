/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroomjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 *
 * @author matth
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private Label label;

    @FXML
    private Button send;

    @FXML
    private TableColumn<?, ?> users;

    @FXML
    private TextField chatMessage;

    @FXML
    private TextField chatRoom;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
