package org.example;

import org.example.Ostatne.Konstanty;
import org.example.Simulacia.Stanok.SimulaciaStanok;

public class Main
{
    public static void main(String[] args)
    {
        SimulaciaStanok simulacia = new SimulaciaStanok(1000000,
        Konstanty.KONIEC_SEKUND - Konstanty.ZACIATOK_SEKUND, 69, false);
        simulacia.simuluj();
    }
}