package org.fasttrackit.OrientatPeConexiune2;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class EVCComputer {

    private InetAddress IP = null;
    private Socket socketBaliseOutputStation = null;

    private Socket socketBaliseInputStation=null;

    private void sendDataToBaliseOutputStation() {
        try {
            //set IP addres
            IP = InetAddress.getByName("localhost");

            //set 1900 port from IP address
            socketBaliseOutputStation = new Socket(IP, 1900);

            //socket take data from stream (in socket data are written)
            ObjectOutputStream oosBaliseOutputStation = new ObjectOutputStream(socketBaliseOutputStation.getOutputStream());

            Coordinates coordinates = new Coordinates(1, 1);

            //EVC send coordinated to balise
            System.out.println("EVC computer send informations regard of coordinates to baliseOutputStation");

            oosBaliseOutputStation.writeObject(coordinates);

            //data are forced to be sent at balise (flush cleans the stream)
            oosBaliseOutputStation.flush();

            //socketBalise.close();
        } catch (Exception exception) {
            System.out.println("EVC can not send data to balise because of the error " + exception.getMessage());
        }
    }

    private void receiveDataFromBaliseOutputStation() {
        try {
            //EVC receives data from balise
            ObjectInputStream oinBaliseOutputStation = new ObjectInputStream(socketBaliseOutputStation.getInputStream());

            //EVC reads data from balise
            System.out.println("EVC received from balise " + oinBaliseOutputStation.readObject());
        } catch (Exception exception) {
            System.out.println("EVC can not read data from balise because of the error " + exception.getMessage());
        }
    }

    private void confirmReceivingDataFromBaliseOutputStation()
    {
        try {
            ObjectOutputStream oosConfirmDataBaliseOutputStation = new ObjectOutputStream(socketBaliseOutputStation.getOutputStream());

            //EVC confirm receiving data from balise
            ConfirmReceivingDataBaliseOutputStation confirmReceivingDataBaliseOutputStation=new ConfirmReceivingDataBaliseOutputStation("EVC received data from balise");
            System.out.println("EVC sends information regard of receiving data to balise");
            oosConfirmDataBaliseOutputStation.writeObject(confirmReceivingDataBaliseOutputStation);
            oosConfirmDataBaliseOutputStation.flush();
            // oosConfirmDataBaliseOutputStation.close();
        }catch(Exception exception)
        {
            System.out.println("EVC can not confirm receiving data from balise because of the error "+exception.getMessage());
        }
    }

    private void sendDataToBaliseInputStation(){
        try {
            //set IP addres
            IP = InetAddress.getByName("localhost");

            //set 1904 port from IP address
            socketBaliseInputStation = new Socket(IP, 1904);

            Coordinates coordinates=new Coordinates(1,1);

            ObjectOutputStream oosBaliseInputStation=new ObjectOutputStream(socketBaliseInputStation.getOutputStream());
            oosBaliseInputStation.writeObject(coordinates);
            System.out.println("Coordinates are sending to balise input station");

        }catch(Exception exception){
            System.out.println("Data can not be sent because of the error "+exception.getMessage());
        }
    }

    private void receiveDataFromBaliseInputStation(){
        try {
            //EVC receives data from balise input station
            ObjectInputStream oinBaliseInputStation = new ObjectInputStream(socketBaliseInputStation.getInputStream());

            //EVC reads data from balise input station
            System.out.println("EVC received from balise input station " + oinBaliseInputStation.readObject());
        } catch (Exception exception) {
            System.out.println("EVC can not read data from balise input station because of the error " + exception.getMessage());
        }
    }

    private void confirmReceivingDataFromBaliseInputStation(){
        try {
            ObjectOutputStream oosConfirmDataBaliseInputStation = new ObjectOutputStream(socketBaliseInputStation.getOutputStream());

            //EVC confirm receiving data from balise
            ConfirmReceivingDataBaliseInputStation confirmReceivingDataBaliseInputStation=new ConfirmReceivingDataBaliseInputStation("EVC received data from balise");
            System.out.println("EVC sends information regard of receiving data to balise input station");
            oosConfirmDataBaliseInputStation.writeObject(confirmReceivingDataBaliseInputStation);
            oosConfirmDataBaliseInputStation.flush();
            // oosConfirmDataBaliseOutputStation.close();
        }catch(Exception exception)
        {
            System.out.println("EVC can not confirm receiving data from balise input station because of the error "+exception.getMessage());
        }
    }

    public static void main(String[] args) {
        EVCComputer evcComputer = new EVCComputer();

        evcComputer.sendDataToBaliseInputStation();
        evcComputer.receiveDataFromBaliseInputStation();
        evcComputer.confirmReceivingDataFromBaliseInputStation();

       /* evcComputer.sendDataToBaliseOutputStation();
        evcComputer.receiveDataFromBaliseOutputStation();
        evcComputer.confirmReceivingDataFromBaliseOutputStation();*/
    }
}
