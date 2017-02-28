package project.listener;

import project.controller.AbstractController;

abstract class AbstractListener {

    protected AbstractController controller;

    AbstractListener(AbstractController c) {
        controller = c;
    }

}
