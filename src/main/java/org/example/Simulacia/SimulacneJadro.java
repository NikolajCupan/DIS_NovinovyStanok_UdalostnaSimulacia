package org.example.Simulacia;

import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class SimulacneJadro
{
    private final int pocetReplikacii;
    private double dlzkaTrvaniaSimulacie;

    private double aktualnySimulacnyCas;

    private PriorityQueue<Udalost> kalendarUdalosti;
    private Comparator komparatorUdalosti;

    protected SimulacneJadro(int pocetReplikacii, double dlzkaTrvaniaSimulacie)
    {
        this.validujVstupy(pocetReplikacii, dlzkaTrvaniaSimulacie);

        this.pocetReplikacii = pocetReplikacii;
        this.dlzkaTrvaniaSimulacie = dlzkaTrvaniaSimulacie;
    }

    private void validujVstupy(int pocetReplikacii, double dlzkaTrvaniaSimulacie)
    {
        if (pocetReplikacii < 1)
        {
            throw new RuntimeException("Pocet replikacii nemoze byt mensi ako 1!");
        }

        if (dlzkaTrvaniaSimulacie <= 0.0)
        {
            throw new RuntimeException("Dlzka trvania simulacie musi byt vacsia ako 0!");
        }
    }

    public void simuluj()
    {
        int aktualnaReplikacia = 1;

        this.predReplikaciami();

        while (aktualnaReplikacia <= this.pocetReplikacii)
        {
            this.predReplikaciouJadro();
            this.predReplikaciou();

            while (!this.kalendarUdalosti.isEmpty()
                   && this.aktualnySimulacnyCas <= this.dlzkaTrvaniaSimulacie)
            {
                Udalost aktualnaUdalost = this.kalendarUdalosti.poll();
                double casVykonaniaUdalosti = aktualnaUdalost.getCasVykonania();

                if (casVykonaniaUdalosti <= this.dlzkaTrvaniaSimulacie)
                {
                    // Udalost mozno vykonat, nebol presiahnuty cas simulacie
                    this.aktualnySimulacnyCas = casVykonaniaUdalosti;
                    aktualnaUdalost.vykonajUdalost();
                }
                else
                {
                    // Presiahnuty cas trvania simulacie
                    break;
                }
            }

            this.poReplikacii();
            aktualnaReplikacia++;
        }

        this.poReplikaciach();
    }

    private void predReplikaciouJadro()
    {
        if (this.komparatorUdalosti == null)
        {
            throw new RuntimeException("Komparator udalosti nebol nastaveny!");
        }
        this.kalendarUdalosti = new PriorityQueue<>(this.komparatorUdalosti);

        this.aktualnySimulacnyCas = 0.0;
    }

    public void pridajUdalost(Udalost udalost)
    {
        this.kalendarUdalosti.add(udalost);
    }

    public double getAktualnySimulacnyCas()
    {
        return this.aktualnySimulacnyCas;
    }

    public void nastavKomparator(Comparator komparator)
    {
        this.komparatorUdalosti = komparator;
    }

    protected abstract void predReplikaciami();
    protected abstract void poReplikaciach();
    protected abstract void predReplikaciou();
    protected abstract void poReplikacii();
}
