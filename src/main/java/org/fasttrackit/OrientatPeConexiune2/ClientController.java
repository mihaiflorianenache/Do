package org.fasttrackit.OrientatPeConexiune2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientController {

    private InetAddress IP = null;
    private Socket socketBaliseOutputStation = null;

    private Socket socketBaliseInputStation=null;

    private void sendDataToBaliseOutputStation()
    {
        try {
            //set IP addres
            IP = InetAddress.getByName("localhost");

            //set 1903 port from IP address
            socketBaliseOutputStation = new Socket(IP, 1903);

            //socket take data from stream (in socket data are written)
            ObjectOutputStream oosBaliseOutputStation = new ObjectOutputStream(socketBaliseOutputStation.getOutputStream());

            CheckLineFree checkLineFree=new CheckLineFree();
            //EVC send coordinated to balise
            System.out.println("Client controller send informations regard of line's state to balise");

            oosBaliseOutputStation.writeObject(checkLineFree);

            //data are forced to be sent at balise (flush cleans the stream)
            oosBaliseOutputStation.flush();

            //oosBalise.close();
        } catch (Exception exception) {
            System.out.println("Client controller can not send data to balise output station because of the error " + exception.getMessage());
        }
    }

    private void receiveDataFromBaliseOutputStation()
    {
        try {
            //EVC receives data from balise
            ObjectInputStream oinBalise = new ObjectInputStream(socketBaliseOutputStation.getInputStream());

            //EVC reads data from balise
            System.out.println("Client controller receive from balise " + oinBalise.readObject());
        } catch (Exception exception) {
            System.out.println("Client controller can not read data from balise because of the error " + exception.getMessage());
        }
    }

    private void sendDataToInterlocking()
    {

    }

    private void sendDataToBaliseInputStation()
    {
        try {
            //set IP addres
            IP = InetAddress.getByName("localhost");

            //set 1905 port regard of sending information to balise input station
            socketBaliseInputStation=new Socket(IP,1905);
            ObjectOutputStream oosBaliseInputStation = new ObjectOutputStream(socketBaliseInputStation.getOutputStream());
            CheckLineFree checkLineFree=new CheckLineFree();
            oosBaliseInputStation.writeObject(checkLineFree);
            oosBaliseInputStation.flush();
        }catch(Exception exception){
            System.out.println("Client controller can not send data to balise output station because of the error " + exception.getMessage());
        }
    }

    public static void main(String[] args)
    {
        ClientController clientController=new ClientController();

        //communication between client controller and balise input station
        clientController.sendDataToBaliseInputStation();

        //communication between client controller and balise output station
        clientController.sendDataToBaliseOutputStation();
        clientController.receiveDataFromBaliseOutputStation();

        //communication between client controller and interlocking
        clientController.sendDataToInterlocking();
    }
}

