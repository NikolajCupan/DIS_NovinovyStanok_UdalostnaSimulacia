package org.example.Simulacia.Stanok.Udalosti;

import org.example.Simulacia.Jadro.SimulacneJadro;
import org.example.Simulacia.Stanok.Agent;
import org.example.Simulacia.Stanok.SimulaciaStanok;
import org.example.Simulacia.Jadro.Udalost;

public class UdalostKoniecObsluhy extends Udalost
{
    public UdalostKoniecObsluhy(SimulacneJadro simulacneJadro, double casVykonania, Agent agent)
    {
        super(simulacneJadro, casVykonania, agent);
    }

    private void vypis()
    {
        System.out.print("[UDALOST " + this.getAgent().getID() + "]   ");
        System.out.format("%-30s", "Koniec obsluhy zakaznika");
        System.out.println(this.getCasVykonania());
    }

    @Override
    public void vykonajUdalost()
    {
        this.vypis();
        SimulaciaStanok simulacia = (SimulaciaStanok)this.getSimulacneJadro();
        simulacia.setObsluhaPrebieha(false);

        // Nastavenie atributov agenta, ktory udalost vykonava
        Agent agent = this.getAgent();
        agent.setCasKoncaObsluhy(this.getCasVykonania());
        agent.vypis();

        // Zaznamenanie statistik
        simulacia.getStatistikaCasSystem().pridajHodnotu(agent.getCasKoncaObsluhy() - agent.getCasPrichodu());

        // Naplanovanie dalsej obsluhy
        if (simulacia.getPocetAgentovVoFronte() == 0)
        {
            // Front je prazdny, nemozno naplanovat dalsiu obsluhu
        }
        else
        {
            Agent odobratyAgent = simulacia.odoberAgentaZFrontu();
            UdalostZaciatokObsluhy zaciatokObsluhy = new UdalostZaciatokObsluhy(simulacia, this.getCasVykonania(), odobratyAgent);
            simulacia.naplanujUdalost(zaciatokObsluhy);

            simulacia.getStatistikaVelkostFrontu().pridajHodnotu(this.getCasVykonania(),
                simulacia.getPocetAgentovVoFronte());
        }
    }
}
