package project.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import project.controller.AbstractController;

public class SliderListener extends AbstractListener implements ChangeListener<Number> {

    public SliderListener(AbstractController c) {
        super(c);
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        controller.setSlider(newValue.intValue());
    }

}
