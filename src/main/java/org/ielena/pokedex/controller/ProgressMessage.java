package org.ielena.pokedex.controller;

import javafx.application.Preloader;

import java.util.Objects;

public class ProgressMessage implements Preloader.PreloaderNotification {

    private double progress;
    private String message;
    private boolean done;
    private boolean failed;

    public ProgressMessage(double progress, String message){
        this.progress = progress;
        this.message  = message;
        this.done     = false;
        this.failed   = false;
    }

    public double getProgress(){return progress;}
    public String getMessage(){return message;}

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ProgressMessage message1 = (ProgressMessage) o;
        return Double.compare(message1.progress, progress) == 0 &&
                done == message1.done && failed == message1.failed &&
                Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(progress, message, done, failed);
    }

    @Override
    public String toString() {
        return "ProgressMessage{" + "progress=" + progress + ", message='" +
                message + '\'' + ", done=" + done + ", failed=" + failed + '}';
    }
}
