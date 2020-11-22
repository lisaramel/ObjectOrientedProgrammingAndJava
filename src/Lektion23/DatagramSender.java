package Lektion23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by Lisa Ramel
 * Date: 2020-11-02
 * Time: 10:39
 * Project: Objektorienterad programmering och Java
 * Copywrite: MIT
 */
public class DatagramSender {
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException, InterruptedException {
        BufferedReader in = new BufferedReader(new
                InputStreamReader(System.in));

        String quote1 = "What a good day to be proud of all the progress I've made";
        String quote2 = "I was in the mood for a new mood";
        String quote3 = "It's O-K";

        List<String> allQuotes = new ArrayList<>();
        allQuotes.add(quote1);
        allQuotes.add(quote2);
        allQuotes.add(quote3);
        int listCounter = 0;

        InetAddress adress = InetAddress.getLocalHost();
        int port = 55555;
        DatagramSocket socket = new DatagramSocket();

         while(true) {
                byte[] data = allQuotes.get(listCounter).getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, adress, port);
                socket.send(packet);
                listCounter = (listCounter + 1) % 3;
                Thread.sleep(5000);
            }
        }

        /*
        String quote;
        System.out.println("Citat: ");

        while((quote = in.readLine()) != null){
            if(quote.equals("bye")) System.exit(0);
            byte[] data = quote.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, adress, port);
            socket.send(packet);
            System.out.println("Citat: ");
        }System.exit(0);

         */


}
