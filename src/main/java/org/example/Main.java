package org.example;

import org.example.Ostatne.Konstanty;
import org.example.Simulacia.Stanok.SimulaciaStanok;

public class Main
{
    public static void main(String[] args)
    {
        SimulaciaStanok simulacia = new SimulaciaStanok(1,
        Konstanty.KONIEC_SEKUND - Konstanty.ZACIATOK_SEKUND, 420, false);
        simulacia.simuluj();
    }
}