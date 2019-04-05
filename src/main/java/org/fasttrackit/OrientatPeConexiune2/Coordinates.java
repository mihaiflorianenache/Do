package org.fasttrackit.OrientatPeConexiune2;
import java.io.Serializable;

public class Coordinates implements Serializable {

    private double travelledDistance;
    private int numberLine;

    Coordinates(double travelledDistance,int numberLine)
    {
        this.travelledDistance=travelledDistance;
        this.numberLine=numberLine;
    }

    public String toString()
    {
        return "travelled distance = "+travelledDistance+" and number line is = "+numberLine;
    }
}
