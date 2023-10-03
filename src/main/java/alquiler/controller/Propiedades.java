package alquiler.controller;

import alquiler.servicios.Traducible;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class Propiedades {

    private static final String RUTA= "/propiedades";
    private static Propiedades instance;
    private ResourceBundle bundle;
    private List<Traducible> listaTraducibles;

    public static Propiedades getInstance(){
        if(instance==null){
            instance=new Propiedades();
        }
        return instance;
    }

    private Propiedades(){
        bundle= ResourceBundle.getBundle(RUTA);
        listaTraducibles= new ArrayList<>();
    }
    public void setLanguage(Locale locale) {
        bundle = ResourceBundle.getBundle(RUTA, locale);
        for (Traducible traducible : listaTraducibles) {
            traducible.actualizarIdioma(bundle);
        }
    }
    public void agregarTraducible(Traducible traducible) {
        traducible.actualizarIdioma(bundle);
        listaTraducibles.add(traducible);
    }




}
