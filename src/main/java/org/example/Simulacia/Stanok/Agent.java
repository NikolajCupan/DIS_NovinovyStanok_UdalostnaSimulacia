package org.example.Simulacia.Stanok;

public class Agent
{
    private long ID;

    private double casPrichodu;
    private double casZaciatkuObsluhy;
    private double casKoncaObsluhy;

    public Agent(long ID)
    {
        this.ID = ID;

        this.casPrichodu = -1;
        this.casZaciatkuObsluhy = -1;
        this.casKoncaObsluhy = -1;
    }

    public void vypis()
    {
        System.out.format("%-45s%n", "     [AGENT " + this.ID + "] Prichod: " + this.casPrichodu);
        System.out.format("%-45s%n", "     [AGENT " + this.ID + "] Zaciatok obsluhy: " + this.casZaciatkuObsluhy);
        System.out.format("%-45s%n", "     [AGENT " + this.ID + "] Koniec obsluhy: " + this.casKoncaObsluhy);
    }

    public long getID()
    {
        return this.ID;
    }

    public double getCasPrichodu()
    {
        return this.casPrichodu;
    }

    public double getCasZaciatkuObsluhy()
    {
        return this.casZaciatkuObsluhy;
    }

    public double getCasKoncaObsluhy()
    {
        return this.casKoncaObsluhy;
    }

    public void setID(long ID)
    {
        this.ID = ID;
    }

    public void setCasPrichodu(double casPrichodu)
    {
        this.casPrichodu = casPrichodu;
    }

    public void setCasZaciatkuObsluhy(double casZaciatkuObsluhy)
    {
        this.casZaciatkuObsluhy = casZaciatkuObsluhy;
    }

    public void setCasKoncaObsluhy(double casKoncaObsluhy)
    {
        this.casKoncaObsluhy = casKoncaObsluhy;
    }
}
