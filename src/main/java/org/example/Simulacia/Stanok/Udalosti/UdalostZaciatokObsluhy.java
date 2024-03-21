package org.example.Simulacia.Stanok.Udalosti;

import org.example.Simulacia.SimulacneJadro;
import org.example.Simulacia.Stanok.SimulaciaStanok;
import org.example.Simulacia.Udalost;

public class UdalostZaciatokObsluhy extends Udalost
{
    public UdalostZaciatokObsluhy(SimulacneJadro simulacneJadro, double casVykonania)
    {
        super(simulacneJadro, casVykonania);
    }

    private void vypis()
    {
        System.out.print("[UDALOST]   ");
        System.out.format("%-30s", "Zaciatok obsluhy zakaznika");
        System.out.println(this.getCasVykonania());
    }

    @Override
    public void vykonajUdalost()
    {
        this.vypis();
        SimulaciaStanok simulacia = (SimulaciaStanok)this.getSimulacneJadro();
        simulacia.zmenStavObsluhy(true);

        double dlzkaObsluhy = simulacia.getSpojityRovnomernyGenerator().sample();
        double casVykonania = this.getCasVykonania() + dlzkaObsluhy;

        UdalostKoniecObsluhy koniecObsluhy = new UdalostKoniecObsluhy(this.getSimulacneJadro(), casVykonania);
        simulacia.naplanujUdalost(koniecObsluhy);
    }
}
