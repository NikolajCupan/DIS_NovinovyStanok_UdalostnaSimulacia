package org.example.Simulacia.Stanok;

import org.example.Generatory.Ostatne.GeneratorNasad;
import org.example.Generatory.SpojityExponencialnyGenerator;
import org.example.Generatory.SpojityTrojuholnikovyGenerator;
import org.example.Simulacia.Jadro.SimulacneJadro;
import org.example.Simulacia.Stanok.Udalosti.UdalostKomparator;
import org.example.Simulacia.Stanok.Udalosti.UdalostPrichodZakaznika;
import org.example.Simulacia.Jadro.Udalost;

import java.util.Comparator;

public class SimulaciaStanok extends SimulacneJadro
{
    private final GeneratorNasad generatorNasad;
    private SpojityExponencialnyGenerator spojityExponencialnyGenerator;
    private SpojityTrojuholnikovyGenerator spojityTrojuholnikovyGenerator;

    private int pocetLudiVoFronte;
    private boolean obsluhaPrebieha;

    public SimulaciaStanok(int pocetReplikacii, double dlzkaTrvaniaSimulacie, int nasada, boolean pouziNasadu)
    {
        super(pocetReplikacii, dlzkaTrvaniaSimulacie);

        GeneratorNasad.inicializujGeneratorNasad(nasada, pouziNasadu);
        this.generatorNasad = new GeneratorNasad();
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

    public int getPocetLudiVoFronte()
    {
        return this.pocetLudiVoFronte;
    }

    public boolean getObsluhaPrebieha()
    {
        return this.obsluhaPrebieha;
    }

    public SpojityExponencialnyGenerator getSpojityExponencialnyGenerator()
    {
        return this.spojityExponencialnyGenerator;
    }

    public SpojityTrojuholnikovyGenerator getSpojityTrojuholnikovyGenerator()
    {
        return this.spojityTrojuholnikovyGenerator;
    }

    public void setObsluhaPrebieha(boolean obsluhaPrebieha)
    {
        this.obsluhaPrebieha = obsluhaPrebieha;
    }

    @Override
    protected void predReplikaciami()
    {
        Comparator<Udalost> komparator = new UdalostKomparator();
        this.nastavKomparator(komparator);

        this.spojityExponencialnyGenerator = new SpojityExponencialnyGenerator(1.0 / 240.0, this.generatorNasad);
        this.spojityTrojuholnikovyGenerator = new SpojityTrojuholnikovyGenerator(100.0, 120.0, 400.0,
            this.generatorNasad);
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
        UdalostPrichodZakaznika prichod = new UdalostPrichodZakaznika(this, 0.0);
        this.naplanujUdalost(prichod);
    }

    @Override
    protected void poReplikacii()
    {
    }
}
