package chatroomjavafx;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

        private DataInputStream streamIn;
        private Socket clientSocket;
        
        public void connectToServer() {
            try {
                int portNumber = 7;

                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                ChatClient chatClient = new ChatClient("localhost", 7);
                chatClient.start();
                chatClient.stop();
                open();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                boolean done = false;
                while (in.ready()) {
                    String line = in.readLine();
                    System.out.println(line);
                    done = line.equals(".bye");
                }
                close();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        public void open() throws IOException {
            streamIn = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        }

        public void close() throws IOException {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (streamIn != null) {
                streamIn.close();
            }
        }
    }