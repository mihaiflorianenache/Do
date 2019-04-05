package org.fasttrackit.OrientatPeConexiune2;

import java.io.Serializable;

public class ConfirmReceivingDataBaliseInputStation implements Serializable {
    private String confirmReceivingDataBaliseInputStation;
    ConfirmReceivingDataBaliseInputStation(String confirmReceiving)
    {
        this.confirmReceivingDataBaliseInputStation=confirmReceivingDataBaliseInputStation;
    }

    public String toString()
    {
        return confirmReceivingDataBaliseInputStation;
    }
}

