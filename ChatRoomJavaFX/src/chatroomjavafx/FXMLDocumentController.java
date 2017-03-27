/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroomjavafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            // TODO
            String hostName = "localhost";
            int portNumber = 80;
            
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            String userInput;
            while((userInput = stdIn.readLine()) != null)
            {
                chatRoom.setText(userInput);
                chatRoom.setText("echo: " + in.readLine());
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
