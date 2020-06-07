package com.github.cyborgmg.cyberpontua;

public final class PntPalavra {

    public static double getPonto(String PalavraP, String PalavraC) {
        PntPalavra pntP = new PntPalavra(PalavraP, PalavraC);

        return pntP.ponto;
    }



    private double pontoLetra = 0;
    private double pontoOrd = 0;
    private double ponto = 0;

    private PntPalavra(String PalavraP, String PalavraC) {
        
        pontoLetra = PontuaLetra(PalavraP, PalavraC);
        pontoOrd = PontuaOrd(PalavraP, PalavraC);
        ponto = (pontoLetra + pontoOrd) / 2;

    }

    private double PontuaLetra(String vPalavraP, String vPalavraC) {

        StringBuilder PalavraP = new StringBuilder(vPalavraP);
        StringBuilder PalavraC = new StringBuilder(vPalavraC);
        String aux = "";
        for (int i = 0; i < PalavraP.length(); i++) {

            for (int j = 0; j < PalavraC.length(); j++) {

                if (PalavraP.charAt(i) == PalavraC.charAt(j)) {
                    aux += PalavraP.charAt(i);
                    PalavraC.deleteCharAt(j);
                    break;
                }

            }

        }
        return aux.length() > 0 ? aux.length() * 100 / vPalavraP.length() : 0.0;

    }

    private double PontuaOrd(String vPalavraP, String vPalavraC) {
        String aux = "";
        double pnt = 0;
        for (int i = 1; i <= vPalavraC.length(); i++) {

            aux = vPalavraC.substring(0, i);
            if ((vPalavraP.contains(aux)) && (aux.length() > pnt)) {
                pnt = aux.length();
            }

        }
        for (int i = vPalavraC.length() - 1; i >= 0; i--) {
            aux = vPalavraC.substring(i, vPalavraC.length());
            if ((vPalavraP.contains(aux)) && (aux.length() > pnt)) {
                pnt = aux.length();
            }
        }
        return pnt > 0 ? pnt * 100 / vPalavraP.length() : 0.0;
    }


}
