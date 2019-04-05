package org.fasttrackit.OrientatPeConexiune2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BaliseInputStation {
    private ServerSocket serverSocketEVC = null;
    private Socket socketEVC = null;

    private ServerSocket serverSocketClientController = null;
    private Socket socketClientController = null;

    private void connectSocket(){
        try {
            serverSocketEVC = new ServerSocket(1904);

            //socket know that server accepted the connection to 1904 port
            socketEVC = serverSocketEVC.accept();
        }catch(Exception exception){
            System.out.println("The connection can not be achieved because of the error "+exception.getMessage());
        }
    }

    private void receiveDataFromEVC(){

        try {
            connectSocket();

            ObjectInputStream oinEVC = new ObjectInputStream(socketEVC.getInputStream());

            //balise receive data from EVC
            System.out.println("BaliseInputStation received: " + oinEVC.readObject());
        }catch(Exception exception)
        {
            System.out.println("BaliseInputStation can not receive data from EVC because of the error "+exception.getMessage());
        }
    }

    private void sendDataToEVC(){
        try {
            MovementAuthority movementAuthority = new MovementAuthority(20, 40, 60);
            ObjectOutputStream oosEVC = new ObjectOutputStream(socketEVC.getOutputStream());

            //movement authority is sending to EVC
            System.out.println("BaliseInputStation sent informations regard of movement authority to EVC");

            oosEVC.writeObject(movementAuthority);

            //data are forced to be sent at EVC (flush cleans the stream)
            oosEVC.flush();
        }catch(Exception exception){
            System.out.println("Balise input station can not sent information to EVC because of the error "+exception.getMessage());
        }
    }

    private void receiveConfirmationDataFromEVC(){
        try {
            connectSocket();
            ObjectInputStream oinReceiveConfirmation = new ObjectInputStream(socketEVC.getInputStream());
            System.out.println("BaliseInputStation received "+oinReceiveConfirmation.readObject());
        }catch(Exception exception)
        {
            System.out.println("BaliseInputStation can not receive confirmation from EVC because of the error "+exception.getMessage());
        }
    }

    public static void main(String[] args){
        BaliseInputStation baliseInputStation=new BaliseInputStation();

        baliseInputStation.receiveDataFromEVC();
        baliseInputStation.sendDataToEVC();
        baliseInputStation.receiveConfirmationDataFromEVC();
    }
}

