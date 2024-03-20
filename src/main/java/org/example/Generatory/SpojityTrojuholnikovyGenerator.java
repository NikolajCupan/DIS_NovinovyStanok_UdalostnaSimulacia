package org.example.Generatory;

import org.example.Generatory.Ostatne.GeneratorNasad;
import org.example.Generatory.Rozhrania.ISpojityGenerator;

import java.util.Random;

public class SpojityTrojuholnikovyGenerator implements ISpojityGenerator
{
    private final Random random;
    private final double minHodnota;
    private final double maxHodnota;
    private final double modus;

    public SpojityTrojuholnikovyGenerator(double minHodnota, double maxHodnota, double modus, GeneratorNasad generatorNasad)
    {
        this.validujVstupy(minHodnota, maxHodnota, modus);

        this.minHodnota = minHodnota;
        this.maxHodnota = maxHodnota;
        this.modus = modus;

        this.random = new Random(generatorNasad.nasada());
    }

    private void validujVstupy(double minHodnota, double maxHodnota, double modus)
    {
        if (minHodnota >= modus || modus >= maxHodnota)
        {
            throw new RuntimeException("Neplatne zadane parametre spojiteho trojuholnikoveho rozdelenia!");
        }
    }

    @Override
    public double sample()
    {
        throw new RuntimeException("Spojityr trojuholnikovy generator zatial nie je implmenetovany!");
    }
}
