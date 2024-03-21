package org.example.Simulacia.Jadro;

public abstract class Udalost
{
    private final SimulacneJadro simulacneJadro;
    private final double casVykonania;

    public Udalost(SimulacneJadro simulacneJadro, double casVykonania)
    {
        this.simulacneJadro = simulacneJadro;
        this.casVykonania = casVykonania;
    }

    public SimulacneJadro getSimulacneJadro()
    {
        return this.simulacneJadro;
    }

    public double getCasVykonania()
    {
        return this.casVykonania;
    }

    abstract public void vykonajUdalost();
}