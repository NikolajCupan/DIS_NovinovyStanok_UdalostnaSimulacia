package org.example.Simulacia.Stanok.Udalosti;

import org.example.Ostatne.Identifikator;
import org.example.Simulacia.Jadro.SimulacneJadro;
import org.example.Simulacia.Stanok.SimulaciaStanok;
import org.example.Simulacia.Jadro.Udalost;
import org.example.Simulacia.Stanok.Agent;

public class UdalostPrichodZakaznika extends Udalost
{
    public UdalostPrichodZakaznika(SimulacneJadro simulacneJadro, double casVykonania, Agent agent)
    {
        super(simulacneJadro, casVykonania, agent);
    }

    private void vypis()
    {
        System.out.print("[UDALOST " + this.getAgent().getID() + "]   ");
        System.out.format("%-30s", "Prichod zakaznika");
        System.out.println(this.getCasVykonania());
    }

    @Override
    public void vykonajUdalost()
    {
        this.vypis();
        SimulaciaStanok simulacia = (SimulaciaStanok)this.getSimulacneJadro();

        // Nastavenie atributov agenta, ktory udalost vykonava
        Agent agent = this.getAgent();
        agent.setCasPrichodu(this.getCasVykonania());

        // Prichod noveho zakaznika sa planuje vzdy
        double dalsiPrichodPo = simulacia.getSpojityExponencialnyGenerator().sample();
        double casUdalosti = simulacia.getAktualnySimulacnyCas() + dalsiPrichodPo;

        UdalostPrichodZakaznika dalsiPrichod = new UdalostPrichodZakaznika(simulacia, casUdalosti, new Agent(Identifikator.getID()));
        simulacia.naplanujUdalost(dalsiPrichod);

        if (simulacia.getObsluhaPrebieha())
        {
            // Niekto je obsluhovany, pridaj agenta do frontu
            simulacia.pridajAgentaDoFrontu(agent);
            simulacia.getStatistikaVelkostFrontu().pridajHodnotu(this.getCasVykonania(),
                simulacia.getPocetAgentovVoFronte());
        }
        else
        {
            // Nikto nie je obsluhovany, mozno obsluzit zakaznika
            UdalostZaciatokObsluhy zaciatokObsluhy = new UdalostZaciatokObsluhy(simulacia, this.getCasVykonania(), agent);
            simulacia.naplanujUdalost(zaciatokObsluhy);
        }
    }
}
