package org.example.Simulacia.Stanok;

import org.example.Generatory.Ostatne.GeneratorNasad;
import org.example.Generatory.SpojityExponencialnyGenerator;
import org.example.Generatory.SpojityTrojuholnikovyGenerator;
import org.example.Ostatne.Identifikator;
import org.example.Simulacia.Jadro.SimulacneJadro;
import org.example.Simulacia.Stanok.Udalosti.UdalostKomparator;
import org.example.Simulacia.Stanok.Udalosti.UdalostPrichodZakaznika;
import org.example.Simulacia.Jadro.Udalost;
import org.example.Simulacia.Statistiky.CasVSysteme;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class SimulaciaStanok extends SimulacneJadro
{
    private final GeneratorNasad generatorNasad;
    private SpojityExponencialnyGenerator spojityExponencialnyGenerator;
    private SpojityTrojuholnikovyGenerator spojityTrojuholnikovyGenerator;

    private Queue<Agent> front;
    private boolean obsluhaPrebieha;

    // Statistiky
    private CasVSysteme casVSysteme;

    public SimulaciaStanok(int pocetReplikacii, double dlzkaTrvaniaSimulacie, int nasada, boolean pouziNasadu)
    {
        super(pocetReplikacii, dlzkaTrvaniaSimulacie);

        GeneratorNasad.inicializujGeneratorNasad(nasada, pouziNasadu);
        this.generatorNasad = new GeneratorNasad();
    }

    public void pridajAgentaDoFrontu(Agent agent)
    {
        this.front.add(agent);
    }

    public Agent odoberAgentaZFrontu()
    {
        if (this.front.isEmpty())
        {
            throw new RuntimeException("Pokus o vybratie agenta z frontu, ktory je prazdny!");
        }

        return this.front.poll();
    }

    public int getPocetAgentovVoFronte()
    {
        return this.front.size();
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

    public CasVSysteme getCasVSysteme()
    {
        return this.casVSysteme;
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
        this.spojityTrojuholnikovyGenerator = new SpojityTrojuholnikovyGenerator(100.0, 400.0, 120.0,
            this.generatorNasad);
    }

    @Override
    protected void poReplikaciach()
    {
    }

    @Override
    protected void predReplikaciou()
    {
        this.front = new LinkedList<>();
        this.obsluhaPrebieha = false;

        // Vynulovanie statistik
        this.casVSysteme = new CasVSysteme();

        // Naplanovania prichodu 1. zakaznika v case 0.0
        UdalostPrichodZakaznika prichod = new UdalostPrichodZakaznika(this, 0.0, new Agent(Identifikator.getID()));
        this.naplanujUdalost(prichod);
    }

    @Override
    protected void poReplikacii()
    {
        System.out.println("Statistika priemerna doba v systeme: " + this.casVSysteme.vypocitajStatistiku());
    }
}
