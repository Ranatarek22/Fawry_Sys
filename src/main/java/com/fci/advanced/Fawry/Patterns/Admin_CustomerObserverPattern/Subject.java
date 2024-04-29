package com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();
    public String getNewService();
}
