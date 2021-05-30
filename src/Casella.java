public class Casella {

    private int mines;
    private boolean descoberta;
    private boolean marcada;
    private boolean esMina;

    public Casella(){

        this.mines      = 0;
        this.descoberta = false;
        this.marcada    = false;
        this.esMina     = false;
    }

    public boolean getEsMina(){
        return this.esMina;
    }

    public void setEsMina(){

        this.esMina = true;
    }

    public boolean getDescoberta(){
        return this.descoberta;
    }

    public void setDescoberta(){
        this.descoberta = true;
    }

    public boolean getMinaMarcada(){
        return this.marcada;
    }

    public void setMarcaMina(){
        this.marcada = !this.marcada;
    }

    public void setMines( int mines ){
        this.mines = mines;
    }

    public int getMines(){
        return this.mines;
    }

    @Override
    public String toString() {

        if( !this.descoberta && !this.marcada )  return " ";
        else if( this.descoberta && this.esMina )  return "#";
        else if( this.marcada)  return "*";
        else                    return String.valueOf(this.mines);

    }
}
