package org.example.Simulacia.Statistiky;

import java.util.ArrayList;

public class CasVSysteme
{
    private ArrayList<Double> casyVSysteme;

    public CasVSysteme()
    {
        this.casyVSysteme = new ArrayList<>();
    }

    public void pridajHodnotu(double casVSysteme)
    {
        this.casyVSysteme.add(casVSysteme);
    }

    public double vypocitajStatistiku()
    {
        if (this.casyVSysteme.isEmpty())
        {
            return -1;
        }

        final int pocetZaznamov = this.casyVSysteme.size();
        double sucetCasov = 0.0;

        for (double cas : this.casyVSysteme)
        {
            sucetCasov += cas;
        }

        return sucetCasov / pocetZaznamov;
    }
}
