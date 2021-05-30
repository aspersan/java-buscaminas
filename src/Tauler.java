import java.util.Random;

public class Tauler {

    private int n;
    private int mines;
    private Casella[][] tauler;

    public Tauler(int n, int mines){

        this.n = n;
        this.mines = mines;
        this.tauler = new Casella[n][n];

        for(int f=0; f<n; f++){
            for( int c=0; c<n; c++){
                this.tauler[f][c] = new Casella();
            }
        }

    }

    public void posaMines( int mines ){

        int xR;
        int yR;
        int minasOk = 0;
        Random random = new Random();

        while( minasOk < mines ){

            xR = random.nextInt(this.n);
            yR = random.nextInt(this.n);

            if( !this.tauler[xR][yR].getEsMina() ){

                this.tauler[xR][yR].setEsMina();
                minasOk++;
            }
        }
    }

    public void comptaMines(){

        int mines = 0;
        for(int f=0; f<this.n; f++){
            for(int c=0; c<this.n; c++){

                // Contar minas alrededor
                if( f-1>=0 && c-1>=0 && this.tauler[f-1][c-1].getEsMina() )             mines++;
                if( f-1>=0 && this.tauler[f-1][c].getEsMina() )                         mines++;
                if( f-1>=0 && c+1<this.n && tauler[f-1][c+1].getEsMina() )              mines++;
                if( c-1>=0 && this.tauler[f][c-1].getEsMina() )                         mines++;
                if( c+1<this.n && this.tauler[f][c+1].getEsMina() )                     mines++;
                if( f+1<this.n && c-1>=0 && this.tauler[f+1][c-1].getEsMina() )         mines++;
                if( f+1<this.n && this.tauler[f+1][c].getEsMina() )                     mines++;
                if( f+1<this.n && c+1 <this.n && this.tauler[f+1][c+1].getEsMina() )    mines++;

                this.tauler[f][c].setMines( mines );
                mines = 0;
            }
        }
    }

    public boolean hiHaMina(int x, int y){

        return this.tauler[y][x].getEsMina();
    }

    public boolean descobert(){

        for(int f=0; f<this.n; f++){
            for(int c=0; c<this.n; c++){
                if( !this.tauler[f][c].getDescoberta() &&
                        !this.tauler[f][c].getEsMina() ) return false;
            }
        }
        return true;
    }

    public void descobreixCasella(int x, int y){

        this.tauler[y][x].setDescoberta();
    }

    public void descobreixCasellaREC( int x, int y){

        if( coordenadesOK(x,y) &&
            !this.minaMarcada(x, y) &&
            !this.descoberta(x, y)) {

            this.tauler[y][x].setDescoberta();
            //descobreixCasella(x, y);

            if (tauler[y][x].getMines() == 0) {


                for(int f=-1; f<=1; f++){
                    for(int c=-1; c<=1; c++){

                        if( f!=0 || c!=0 ) {
                            descobreixCasellaREC(x+c, y+f);
                        }
                    }
                }
/*
                descobreixCasellaREC(x - 1, y - 1);
                descobreixCasellaREC(x, y - 1);
                descobreixCasellaREC(x + 1, y - 1);
                descobreixCasellaREC(x - 1, y);
                descobreixCasellaREC(x + 1, y);
                descobreixCasellaREC(x - 1, y + 1);
                descobreixCasellaREC(x, y + 1);
                descobreixCasellaREC(x + 1, y + 1);

 */
            }
            else {
                //Caso base: hay minas cerca, paramos recursividad
            }
        }
    }

    public boolean coordenadesOK(int x, int y){

        if( x>=0 && x<n && y>=0 && y<n ) return true;
        else return false;

    }

    public void descobreixTauler(){
        for(int f=0; f<this.n; f++){
            for(int c=0; c<this.n; c++){
                this.tauler[f][c].setDescoberta();
            }
        }
    }

    public boolean descoberta(int x, int y){

        return this.tauler[y][x].getDescoberta();
    }

    public void marcaMina(int x, int y){
        this.tauler[y][x].setMarcaMina();
    }

    public boolean minaMarcada(int x, int y){
        return this.tauler[y][x].getMinaMarcada();
    }

    public String toString(){

        String taulerStr="";

        for(int f=0; f<this.n; f++){

            if( f == 0 ){

                for(int c=0; c<this.n; c++) {
                    if(c==0)taulerStr +="   ";

                    taulerStr +=" "+(char) (97+c)+" ";
                }
                taulerStr+="\n";
            }

            for(int c=0; c<this.n; c++){
                if(c==0){
                    taulerStr+=" "+(char) (97+f)+" ";
                }
                taulerStr+="["+this.tauler[f][c].toString()+"]";
            }
            taulerStr+="\n";
        }
        return taulerStr;
    }

}
