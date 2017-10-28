package com.example.android.personasmaterialdiplomado;

import java.util.ArrayList;

/**
 * Created by android on 07/10/2017.
 */

public class Datos {


    public static ArrayList<Persona> personas = new ArrayList<>();


    public static void guardarPersona(Persona p){
        personas.add(p);
    }
    public static ArrayList<Persona> obtenerPersonas(){
        return personas;
    }

    public static boolean  eliminarPersonas(Persona p){

        for (int i = 0; i <personas.size() ; i++) {
            if(p.getCedula().equals(personas.get(i).getCedula())){
                personas.remove(i);
                return true;
            }
        }

             return false;

     }
    public static boolean  ModificarPersonas(Persona p,String cc){

        for (int i = 0; i <personas.size() ; i++) {
            if(cc.equals(personas.get(i).getCedula())){
                p.setFoto(personas.get(i).getFoto());
                personas.set(i,p);
                return true;
            }
        }

        return false;

    }


}
