package org.fpm.di.example;

import javax.inject.Inject;

public class People {
    private final ServingTheBall Serving;
    private final ReceptionOfBall Reception;

    @Inject
    public People(ServingTheBall s, ReceptionOfBall r) {
        this.Serving = s;
        this.Reception = r;
    }
    public ServingTheBall getServingTheBall() {
        return Serving;
    }
    public ReceptionOfBall getReceptionOfBall() {
        return Reception;
    }
}
