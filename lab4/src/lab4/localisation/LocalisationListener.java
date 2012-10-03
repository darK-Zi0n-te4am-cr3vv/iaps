package lab4.localisation;

public interface LocalisationListener <T extends LocalisationBase>
{
    void addChildListeners(T localisation);
    void removeChildListeners(T localisation);
    
    
    void applyLocalisation(T localisation);
}
