package org.fasttrackit.OrientatPeConexiune2;

import java.io.Serializable;

public class ConfirmReceivingDataBaliseOutputStation implements Serializable {

    private String confirmReceivingDataBaliseOutputStation;
    ConfirmReceivingDataBaliseOutputStation(String confirmReceiving)
    {
        this.confirmReceivingDataBaliseOutputStation=confirmReceivingDataBaliseOutputStation;
    }

    public String toString()
    {
        return confirmReceivingDataBaliseOutputStation;
    }
}

