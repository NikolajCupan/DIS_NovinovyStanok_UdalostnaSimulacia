package org.example.Simulacia.Stanok.Udalosti;

import org.example.Simulacia.Jadro.SimulacneJadro;
import org.example.Simulacia.Stanok.SimulaciaStanok;
import org.example.Simulacia.Jadro.Udalost;

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
        simulacia.setObsluhaPrebieha(true);

        double dlzkaObsluhy = simulacia.getSpojityTrojuholnikovyGenerator().sample();
        double casVykonania = this.getCasVykonania() + dlzkaObsluhy;

        UdalostKoniecObsluhy koniecObsluhy = new UdalostKoniecObsluhy(simulacia, casVykonania);
        simulacia.naplanujUdalost(koniecObsluhy);
    }
}
