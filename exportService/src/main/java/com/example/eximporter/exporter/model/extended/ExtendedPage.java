package com.example.eximporter.exporter.model.extended;

import com.example.eximporter.exporter.model.api.Journal;
import com.example.eximporter.exporter.model.api.Project;

public class ExtendedPage {

    private Journal journal;
    private Project project;


    public ExtendedPage(Journal journal, Project project) {
        this.journal = journal;
        this.project = project;
    }

    public Journal getJournal() {
        return journal;
    }

    public Project getProject() {
        return project;
    }
}
