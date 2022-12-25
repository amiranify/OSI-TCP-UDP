import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static ru.netology.ServerConfig.PORT;

public class Server {
    public static void main(String... args) {


        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            System.out.println("Сервер запущен");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    System.out.println("New connection accepted");
                    final String name = in.readLine();
                    writer.println(String.format("Hi %s, your port is %d%n", name, clientSocket.getPort()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
