package org.example.Simulacia.Stanok;

import org.example.Generatory.Ostatne.GeneratorNasad;
import org.example.Generatory.SpojityExponencialnyGenerator;
import org.example.Simulacia.SimulacneJadro;
import org.example.Simulacia.Stanok.Udalosti.UdalostKomparator;
import org.example.Simulacia.Stanok.Udalosti.UdalostPrichodZakaznika;
import org.example.Simulacia.Udalost;

import java.util.Comparator;

public class SimulaciaStanok extends SimulacneJadro
{
    private final GeneratorNasad generatorNasad;
    private SpojityExponencialnyGenerator spojityExponencialnyGenerator;

    private int pocetLudiVoFronte;
    private boolean obsluhaPrebieha;

    public SimulaciaStanok(int pocetReplikacii, double dlzkaTrvaniaSimulacie, int nasada, boolean pouziNasadu)
    {
        super(pocetReplikacii, dlzkaTrvaniaSimulacie);

        GeneratorNasad.inicializujGeneratorNasad(nasada, pouziNasadu);
        this.generatorNasad = new GeneratorNasad();
    }

    public void zmenStavObsluhy(boolean obsluhaPrebieha)
    {
        this.obsluhaPrebieha = obsluhaPrebieha;
    }

    public boolean jeObsluhaObsadena()
    {
        return this.obsluhaPrebieha;
    }

    public void pridajZakaznikaDoFrontu()
    {
        this.pocetLudiVoFronte++;
    }

    public void odoberZakaznikaZFrontu()
    {
        if (this.pocetLudiVoFronte == 0)
        {
            throw new RuntimeException("Pokus o vybratie zakaznika z frontu, ktory je prazdny!");
        }

        this.pocetLudiVoFronte--;
    }

    @Override
    protected void predReplikaciami()
    {
        Comparator<Udalost> komparator = new UdalostKomparator();
        this.nastavKomparator(komparator);

        this.spojityExponencialnyGenerator = new SpojityExponencialnyGenerator(1.0 / 240.0, this.generatorNasad);
    }

    @Override
    protected void poReplikaciach()
    {
    }

    @Override
    protected void predReplikaciou()
    {
        this.pocetLudiVoFronte = 0;
        this.obsluhaPrebieha = false;

        // Naplanovania prichodu 1. zakaznika v case 0.0
        UdalostPrichodZakaznika prichod = new UdalostPrichodZakaznika(this, 0.0,
            this.spojityExponencialnyGenerator);
        this.pridajUdalost(prichod);
    }

    @Override
    protected void poReplikacii()
    {
    }
}
