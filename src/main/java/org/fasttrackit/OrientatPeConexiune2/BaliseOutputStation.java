package org.fasttrackit.OrientatPeConexiune2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BaliseOutputStation extends Thread{

    private ServerSocket serverSocketEVC = null;
    private Socket socketEVC = null;

    private ServerSocket serverSocketClientController = null;
    private Socket socketClientController = null;

    private void receiveDataFromEVC() {
        //server is connecting to 1900 port
        try {
            serverSocketEVC = new ServerSocket(1900);

            //socket know that server accepted the connection to 1900 port
            socketEVC = serverSocketEVC.accept();

            //from socket data are read
            ObjectInputStream oinEVC = new ObjectInputStream(socketEVC.getInputStream());

            //balise receive data from EVC
            System.out.println("BaliseOutputStation received: " + oinEVC.readObject());
        }catch(Exception exception)
        {
            System.out.println("BaliseOutputStation can not receive data from EVC because of the error "+exception.getMessage());
        }
    }

    private void sendDataToEVC() {

        try {
            MovementAuthority movementAuthority = new  MovementAuthority(20, 40, 60);
            ObjectOutputStream oosEVC = new ObjectOutputStream(socketEVC.getOutputStream());

            //movement authority is sending to EVC
            System.out.println("BaliseOutputStation sent informations regard of movement authority to EVC");

            oosEVC.writeObject(movementAuthority);

            //data are forced to be sent at EVC (flush cleans the stream)
            oosEVC.flush();

        }catch(Exception exception)
        {
            System.out.println("BaliseOutputStation can not send data to EVC because of the error "+exception.getMessage());
        }
    }

    private void receiveConfirmationDataFromEVC()
    {
        try {
            ObjectInputStream oinReceiveConfirmation = new ObjectInputStream(socketEVC.getInputStream());
            System.out.println("BaliseOutputStation received "+oinReceiveConfirmation.readObject());
        }catch(Exception exception)
        {
            System.out.println("BaliseOutputStation can not receive confirmation from EVC because of the error "+exception.getMessage());
        }
    }

    private void receiveDataFromClientController() {

        //balise receive information from client controller
        try {
            serverSocketClientController = new ServerSocket(1903);

            socketClientController = serverSocketClientController.accept();

            ObjectInputStream oinClientController = new ObjectInputStream(socketClientController.getInputStream());

            //balise receive data from client controller
            CheckLineFree checkLineFree = (CheckLineFree) oinClientController.readObject();
            System.out.println("BaliseOutputStation received from controller " + checkLineFree);
        }catch(Exception exception)
        {
            System.out.println("BaliseOutputStation can not receive data from client controller because of the error "+exception.getMessage());
        }
    }

    private void sendDataToClientController(LineFree lineFree)
    {
        try {
            ObjectOutputStream oosClientController = new ObjectOutputStream(socketClientController.getOutputStream());

            System.out.println("BaliseOutputStation sent information regard of line's state");

            oosClientController.writeObject(lineFree);

            oosClientController.flush();
        }catch(Exception exception)
        {
            System.out.println("BaliseOutputStation can not send data to client controller because of the error "+exception.getMessage());
        }
    }

    private void communicationBaliseOutputStationEVC()
    {
        receiveDataFromEVC();
        sendDataToEVC();
        receiveConfirmationDataFromEVC();
    }

    private void communicationBaliseClientController(LineFree lineFree)
    {
        sendDataToClientController(lineFree);
    }

    public void run()
    {
        System.out.println("BaliseOutputStation<->EVC");
        //communication between balise and EVC
        communicationBaliseOutputStationEVC();

        System.out.println("BaliseOutputStation send data to client controller");
        //balise send to client controller that line is free, because the train had passed this

        LineFree lineFree = new LineFree(true);
        communicationBaliseClientController(lineFree);
    }

    public static void main(String[] args) {
        BaliseOutputStation baliseOutputStation = new BaliseOutputStation();

        System.out.println("\nBaliseOutputStation receive data from Client controller\n");
        //communication between balise and client controller
        baliseOutputStation.receiveDataFromClientController();
        baliseOutputStation.start();
    }
}


