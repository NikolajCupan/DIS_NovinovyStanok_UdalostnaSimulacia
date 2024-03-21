package org.example.Simulacia.Stanok.Udalosti;

import org.example.Simulacia.SimulacneJadro;
import org.example.Simulacia.Stanok.SimulaciaStanok;
import org.example.Simulacia.Udalost;

public class UdalostPrichodZakaznika extends Udalost
{
    public UdalostPrichodZakaznika(SimulacneJadro simulacneJadro, double casVykonania)
    {
        super(simulacneJadro, casVykonania);
    }

    private void vypis()
    {
        System.out.print("[UDALOST]   ");
        System.out.format("%-30s", "Prichod zakaznika");
        System.out.println(this.getCasVykonania());
    }

    @Override
    public void vykonajUdalost()
    {
        this.vypis();
        SimulaciaStanok simulacia = (SimulaciaStanok)this.getSimulacneJadro();

        // Prichod noveho zakaznika sa planuje vzdy
        double dalsiPrichodPo = simulacia.getSpojityExponencialnyGenerator().sample();
        double casUdalosti = simulacia.getAktualnySimulacnyCas() + dalsiPrichodPo;

        UdalostPrichodZakaznika dalsiPrichod = new UdalostPrichodZakaznika(simulacia, casUdalosti);
        simulacia.naplanujUdalost(dalsiPrichod);

        if (simulacia.jeObsluhaObsadena())
        {
            // Niekto je obsluhovany, pridaj zakaznika do frontu
            simulacia.pridajZakaznikaDoFrontu();
        }
        else
        {
            // Nikto nie je obsluhovany, mozno obsluzit zakaznika
            UdalostZaciatokObsluhy zaciatokObsluhy = new UdalostZaciatokObsluhy(this.getSimulacneJadro(), this.getCasVykonania());
            simulacia.naplanujUdalost(zaciatokObsluhy);
        }
    }
}
