package chatroomjavafx;

import chatroomjavafx.LoginController.UserName;
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
   private ServerSocket serverSocket;

   public ChatServer(int portNumber) {
      try {
         System.out.println("Binding to port " + portNumber + ", please wait  ...");
         serverSocket = new ServerSocket(portNumber);
         System.out.println("Server started: " + serverSocket);
         System.out.println("Waiting for a client ...");
         clientSocket = serverSocket.accept();
         System.out.println("Client accepted: " + clientSocket);
         open();
         boolean done = false;
         String userName = UserName.getUserName();
         while (!done) {
            String line = streamIn.readUTF();
            System.out.println(userName + ": " + line);
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
   
   public static void main(String[] args)
   {
      ChatServer server = new ChatServer(1030);
   }
}
