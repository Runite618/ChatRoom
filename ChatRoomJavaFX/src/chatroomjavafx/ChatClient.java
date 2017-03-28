package chatroomjavafx;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {

    private Socket socket;
    private BufferedReader console;
    private DataOutputStream streamOut;
    
    public ChatClient(String serverName, int serverPort) throws IOException {
        System.out.println("Establishing connection. Please wait ...");
        socket = new Socket(serverName, serverPort);
        System.out.println("Connected: " + socket);
        String line = "";
        start();
        while (!line.equals(".bye")) {
            line = console.readLine();
            streamOut.writeUTF(line);
            streamOut.flush();
        }
        stop();
    }

    public void start() throws IOException {
        streamOut = new DataOutputStream(socket.getOutputStream());
        console   = new BufferedReader(new InputStreamReader(System.in));
    }

    public void stop() throws IOException {
        if (console != null) {
            console.close();
        }
        if (streamOut != null) {
            console.close();
        }
        if (socket != null) {
            console.close();
        }
    }
    
    public static void main(String[] args) throws IOException
    {
       ChatClient chatClient = new ChatClient("localhost", 1030);
    }
}
