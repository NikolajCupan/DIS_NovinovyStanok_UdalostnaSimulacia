package org.example.Simulacia.Stanok.Udalosti;

import org.example.Simulacia.Jadro.SimulacneJadro;
import org.example.Simulacia.Stanok.Agent;
import org.example.Simulacia.Stanok.SimulaciaStanok;
import org.example.Simulacia.Jadro.Udalost;

public class UdalostZaciatokObsluhy extends Udalost
{
    public UdalostZaciatokObsluhy(SimulacneJadro simulacneJadro, double casVykonania, Agent agent)
    {
        super(simulacneJadro, casVykonania, agent);
    }

    private void vypis()
    {
        System.out.print("[UDALOST " + this.getAgent().getID() + "]   ");
        System.out.format("%-30s", "Zaciatok obsluhy zakaznika");
        System.out.println(this.getCasVykonania());
    }

    @Override
    public void vykonajUdalost()
    {
        this.vypis();
        SimulaciaStanok simulacia = (SimulaciaStanok)this.getSimulacneJadro();
        simulacia.setObsluhaPrebieha(true);

        // Nastavenie atributov agenta, ktory udalost vykonava
        Agent agent = this.getAgent();
        agent.setCasZaciatkuObsluhy(this.getCasVykonania());

        // Zaznamenanie statistik
        simulacia.getStatistikaCasFront().pridajHodnotu(agent.getCasZaciatkuObsluhy() - agent.getCasPrichodu());

        double dlzkaObsluhy = simulacia.getSpojityTrojuholnikovyGenerator().sample();
        double casVykonania = this.getCasVykonania() + dlzkaObsluhy;

        UdalostKoniecObsluhy koniecObsluhy = new UdalostKoniecObsluhy(simulacia, casVykonania, agent);
        simulacia.naplanujUdalost(koniecObsluhy);
    }
}
