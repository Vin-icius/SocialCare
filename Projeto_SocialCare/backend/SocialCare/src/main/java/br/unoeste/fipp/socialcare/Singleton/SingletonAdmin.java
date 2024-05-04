package br.unoeste.fipp.socialcare.Singleton;

public class SingletonAdmin {

    private static SingletonAdmin instance;

    private SingletonAdmin() {}

    public static SingletonAdmin getInstance()
    {
        if (instance==null)
            instance = new SingletonAdmin();
        return (instance);
    }

}
