import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner( System.in );

        //Pedir mida n (1-26)
        int n;
        do {
            System.out.print("Tauler (1 - 26): ");
            n = sc.nextInt();
        }while( n<1 || n>26 );

        //Pedir minas (1-nxn) - Validar minas <= nxn

        int minas;
        do {
            System.out.print("Minas (1 - "+n*n+"): ");
            minas = sc.nextInt();
        }while( minas<1 || minas>n*n );

        //Mostrar tablero y pedir casilla a descubrir o marcar/desmarcar
        //Hasta descubrir todas las casillas no-minas o encontrar mina

        Tauler tauler = new Tauler(n, minas);
        tauler.posaMines( minas );
        tauler.comptaMines();

        System.out.println( tauler.toString() );

        String cela;
        int c, f;
        boolean bomba = false;

        while(!tauler.descobert()){
            System.out.print("Cel·la: ");
            cela = sc.next();

            if( cela.length() >=2 && cela.length() <= 3){

                c = (int)cela.charAt(0)-97;
                f = (int)cela.charAt(1)-97;

                if( c >= 0 && c < n && f >= 0 && f < n) {

                    if( cela.length() == 3 && cela.charAt(2) == 'm'){

                        if( tauler.descoberta(c,f) ){
                            System.out.println("Descubierta. No se puede marcar");
                        }else{
                            tauler.marcaMina(c,f);
                        }
                    }else{

                        if( tauler.minaMarcada(c,f) ){
                            System.out.println("Mina Marcada. No se puede descubrir");
                        }else{

                            if(tauler.hiHaMina(c,f)){
                                bomba = true;
                                tauler.descobreixTauler();
                            }else{
                                //Recursividad
                                tauler.descobreixCasellaREC(c, f);
                            }
                        }

                    }
                }
            }
            System.out.println( tauler.toString() );
        }
        if( bomba ) System.out.println("¡Bomba!");
        else System.out.println("¡Molt bé!");
    }
}
