package org.example.Simulacia.Stanok.Udalosti;

import org.example.Simulacia.Udalost;

import java.util.Comparator;

public class UdalostKomparator implements Comparator<Udalost>
{
    @Override
    public int compare(Udalost udalost1, Udalost udalost2)
    {
        return Double.compare(udalost1.getCasVykonania(), udalost2.getCasVykonania());
    }
}
