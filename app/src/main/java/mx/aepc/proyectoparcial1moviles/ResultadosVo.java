package mx.aepc.proyectoparcial1moviles;

import android.graphics.Bitmap;

public class ResultadosVo {
    private Object nombre;
    private Object edad;
    private int foto;

    public ResultadosVo(String samantha, String edad, Bitmap imgbitmap){

    }


    public ResultadosVo(Object nombre, Object edad, int foto) {
        this.nombre = nombre;
        this.edad = edad;
        this.foto = foto;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    public Object getEdad() {
        return edad;
    }

    public void setEdad(Object edad) {
        this.edad = edad;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}