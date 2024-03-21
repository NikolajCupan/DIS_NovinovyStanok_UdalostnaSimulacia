package org.example.Simulacia.Stanok.Udalosti;

import org.example.Simulacia.SimulacneJadro;
import org.example.Simulacia.Stanok.SimulaciaStanok;
import org.example.Simulacia.Udalost;

public class UdalostKoniecObsluhy extends Udalost
{
    public UdalostKoniecObsluhy(SimulacneJadro simulacneJadro, double casVykonania)
    {
        super(simulacneJadro, casVykonania);
    }

    private void vypis()
    {
        System.out.print("[UDALOST]   ");
        System.out.format("%-30s", "Koniec obsluhy zakaznika");
        System.out.println(this.getCasVykonania());
    }

    @Override
    public void vykonajUdalost()
    {
        this.vypis();
        SimulaciaStanok simulacia = (SimulaciaStanok)this.getSimulacneJadro();
        simulacia.zmenStavObsluhy(false);

        // Naplanovanie dalsej obsluhy
        if (simulacia.getPocetLudiVoFronte() == 0)
        {
            // Front je prazdny, nemozno naplanovat dalsiu obsluhu
        }
        else
        {
            simulacia.odoberZakaznikaZFrontu();
            UdalostZaciatokObsluhy zaciatokObsluhy = new UdalostZaciatokObsluhy(simulacia, this.getCasVykonania());
            simulacia.naplanujUdalost(zaciatokObsluhy);
        }
    }
}
