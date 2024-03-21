package org.example.Simulacia.Statistiky;

import java.util.ArrayList;

public class DiskretnaStatistika
{
    private final ArrayList<Double> data;

    public DiskretnaStatistika()
    {
        this.data = new ArrayList<>();
    }

    public void pridajHodnotu(double cas)
    {
        this.data.add(cas);
    }

    public double vypocitajStatistiku()
    {
        if (this.data.isEmpty())
        {
            return -1;
        }

        final int pocetZaznamov = this.data.size();
        double sucetCasov = 0.0;

        for (double cas : this.data)
        {
            sucetCasov += cas;
        }

        return sucetCasov / pocetZaznamov;
    }
}
