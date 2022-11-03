package com.example.eximporter.exporter.model.extended;

import com.example.eximporter.exporter.model.api.Journal;
import com.example.eximporter.exporter.model.api.Peo;

public class ExtendedPeo {

    private Journal journal;
    private Peo peo;


    public ExtendedPeo(Journal journal, Peo peo) {
        this.journal = journal;
        this.peo = peo;
    }

    public Journal getJournal() {
        return journal;
    }

    public Peo getPeo() {
        return peo;
    }
}
