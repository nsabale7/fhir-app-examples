package com.google.fhir.examples.configurablecare;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.context.SimpleWorkerContext;
import org.hl7.fhir.utilities.npm.NpmPackage;

import java.io.IOException;
import java.util.ArrayList;

public class ComplexWorkerContext extends SimpleWorkerContext {
    public ComplexWorkerContext() throws IOException, FHIRException {
    }

    public void loadFromMultiplePackages(ArrayList<NpmPackage> packages, Boolean allowDuplicates) throws IOException {
        this.setAllowLoadingDuplicates(allowDuplicates);
        for (int i = 0; i < packages.size(); i++) {
            this.loadFromPackage(packages.get(i), null);
        }
    }
}
