package org.example.Simulacia.Stanok.Udalosti;

import org.example.Generatory.SpojityExponencialnyGenerator;
import org.example.Simulacia.SimulacneJadro;
import org.example.Simulacia.Stanok.SimulaciaStanok;
import org.example.Simulacia.Udalost;

public class UdalostPrichodZakaznika extends Udalost
{
    private final SpojityExponencialnyGenerator spojityExponencialnyGenerator;

    public UdalostPrichodZakaznika(SimulacneJadro simulacneJadro, double casVykonania,
        SpojityExponencialnyGenerator spojityExponencialnyGenerator)
    {
        super(simulacneJadro, casVykonania);
        this.spojityExponencialnyGenerator = spojityExponencialnyGenerator;
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
        double dalsiPrichodPo = this.spojityExponencialnyGenerator.sample();
        double casUdalosti = simulacia.getAktualnySimulacnyCas() + dalsiPrichodPo;

        UdalostPrichodZakaznika dalsiPrichod = new UdalostPrichodZakaznika(simulacia, casUdalosti,
                this.spojityExponencialnyGenerator);
        simulacia.pridajUdalost(dalsiPrichod);

        if (simulacia.jeObsluhaObsadena())
        {
            // Niekto je obsluhovany, pridaj zakaznika do frontu
            simulacia.pridajZakaznikaDoFrontu();
        }
        else
        {
            // Nikto nie je obsluhovany, mozno obsluzit zakaznika
            UdalostZaciatokObsluhy uZaciatokObsluhy = new UdalostZaciatokObsluhy(this.getSimulacneJadro(), this.getCasVykonania());
            simulacia.pridajUdalost(uZaciatokObsluhy);
        }
    }
}
