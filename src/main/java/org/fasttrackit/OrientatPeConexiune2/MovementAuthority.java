package org.fasttrackit.OrientatPeConexiune2;

import java.io.Serializable;

public class MovementAuthority implements Serializable {
    private double travelledDistanceSafety;
    private double minimumSpeed;
    private double maximumSpeed;

    MovementAuthority(double travelledDistanceSafety,double minimumSpeed,double maximumSpeed)
    {
        this.travelledDistanceSafety=travelledDistanceSafety;
        this.minimumSpeed=minimumSpeed;
        this.maximumSpeed=maximumSpeed;
    }

    public String toString()
    {
        return "travelled distance in safety = "+travelledDistanceSafety+", minimum speed allowed = "+minimumSpeed+" and maximum speed allowed = "+maximumSpeed;
    }
}
