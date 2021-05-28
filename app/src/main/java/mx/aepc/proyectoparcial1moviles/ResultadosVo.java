package mx.aepc.proyectoparcial1moviles;

public class ResultadosVo {
    private String nombre;
    private String edad;
    private int foto;

    public ResultadosVo(){

    }

    public ResultadosVo(String nombre, String edad, int foto) {
        this.nombre = nombre;
        this.edad = edad;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
