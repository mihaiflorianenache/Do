package org.fasttrackit.OrientatPeConexiune2;

import java.io.Serializable;

public class LineFree implements Serializable {
    private boolean stateLine;

    LineFree(boolean stateLine)
    {
        this.stateLine=stateLine;
    }

    public String toString()
    {
        if(stateLine==true) return "Line is free";
        else return "Line is not free";
    }
}
