package chatroomjavafx;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {

    private Socket socket;
    private DataInputStream console;
    private DataOutputStream streamOut;

    public ChatClient(String serverName, int serverPort) throws IOException {
        System.out.println("Establishing connection. Please wait ...");
        socket = new Socket(serverName, serverPort);
        System.out.println("Connected: " + socket);
        String line = "";
        while (!line.equals(".bye")) {
            line = console.readUTF();
            streamOut.writeUTF(line);
            streamOut.flush();
        }
    }

    public void start() throws IOException {
        console = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
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
}
