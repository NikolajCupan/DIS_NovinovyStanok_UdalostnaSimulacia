package org.example.Simulacia.Statistiky;

import java.util.ArrayList;

public class SpojitaStatistika
{
    private static class StavFrontu
    {
        protected double cas;
        protected int velkostFrontu;

        protected StavFrontu(double cas, int velkostFrontu)
        {
            this.cas = cas;
            this.velkostFrontu = velkostFrontu;
        }
    }

    private final ArrayList<StavFrontu> data;

    public SpojitaStatistika()
    {
        this.data = new ArrayList<>();

        // Prvotny stav, ked je front prazdny
        this.data.add(new StavFrontu(0.0, 0));
    }

    public void pridajHodnotu(double cas, int velkostFrontu)
    {
        this.data.add(new StavFrontu(cas, velkostFrontu));
    }

    public double vypocitajStatistiku()
    {
        this.skontrolujData();

        if (this.data.size() == 1)
        {
            // Front bol po cely cas prazdny
            return 0;
        }

        double citatel = 0.0;
        double menovatel = this.data.getLast().cas;
        for (int i = 0; i < this.data.size() - 1; i++)
        {
            StavFrontu curStav = this.data.get(i);
            StavFrontu nextStav = this.data.get(i + 1);

            citatel += (nextStav.cas - curStav.cas) * curStav.velkostFrontu;
        }

        return citatel / menovatel;
    }

    private void skontrolujData()
    {
        StavFrontu prvy = this.data.getFirst();
        if (prvy.cas != 0.0 || prvy.velkostFrontu != 0)
        {
            throw new RuntimeException("Prvy element spojitej statistiky je neplatny!");
        }

        if (this.data.size() == 1)
        {
            return;
        }

        StavFrontu predposledny = this.data.get(this.data.size() - 2);
        StavFrontu posledny = this.data.getLast();
        if (predposledny.velkostFrontu != posledny.velkostFrontu)
        {
            throw new RuntimeException("Posledny element spojitej statistiky je neplatny!");
        }
    }
}
