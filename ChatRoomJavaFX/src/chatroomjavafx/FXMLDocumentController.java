/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroomjavafx;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    public TextField chatMessage;

    @FXML
    public TextField chatRoom;
    
    public LoginController.UserName UserName;
    
    public void setUser(LoginController.UserName userName) 
    {
        this.UserName = userName;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ChatClient chatClient = new ChatClient("localhost", 1030);
            send.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        InputStream console = new ByteArrayInputStream(chatMessage.getText().getBytes(StandardCharsets.UTF_8));
                        String line = "";
                        while (!line.equals(".bye")) {
                            line = chatClient.send(console);
                            chatMessage.setText(line);
                            chatRoom.appendText(line + "\n");
                        }
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
