package com.github.cyborgmg.cyberpontua;

import java.util.ArrayList;
import java.util.List;

public class PntFrase extends MatchingBase{
	
	private static double match; 
	private static double order;
	
	public static PntFrase getInstace(double match, double order) {
		
		PntFrase.match = match;
		PntFrase.order = order;
		
		return new PntFrase();
	}

    public  double getPonto(String frasePrincipal, String fraseCompara) {
        PntFrase pntfP = new PntFrase(frasePrincipal, fraseCompara);
        
        return (pntfP.ponto * match / 100) + (pntfP.pontoOrd * order / 100);
    }

    private  boolean contains(List<String> lst, List<String> sublst) {
        for (int i = 0; i < lst.size() - sublst.size() + 1; i++) {
            if (lst.subList(i, i + sublst.size()).equals(sublst)) {
                return true;
            }
        }
        return false;
    }

    private String FrasePrincipal = "";
    private String FraseCompara = "";

    private List<String> lstFrasePrincipal = new ArrayList<String>();
    private List<String> lstFraseCompara = new ArrayList<String>();
    private final List<LstPnt> lstFrasePontuada = new ArrayList<LstPnt>();

    private double ponto;
    private double pontoOrd = 0;

    private PntFrase(String frasePrincipal, String fraseCompara) {

        if (frasePrincipal.length() > fraseCompara.length()) {
            this.FrasePrincipal = normalize(frasePrincipal).toUpperCase();
            this.FraseCompara = normalize(fraseCompara).toUpperCase();
        } else {
            this.FrasePrincipal = normalize(fraseCompara).toUpperCase();
            this.FraseCompara = normalize(frasePrincipal).toUpperCase();
        }
        this.lstFrasePrincipal = loadList(this.FrasePrincipal);
        this.lstFraseCompara = loadList(this.FraseCompara);

        ordenaList();
    }

    public PntFrase() {
		super();
	}

	private List<String> loadList(String str) {
        
        String palavra = "";
        List<String> lst = new ArrayList<String>();
        str += " ";
        for (int i = 0; i < str.toCharArray().length; i++) {
            char letra = str.toCharArray()[i];
            if (letra != ' ') {
                palavra += letra;
            } else {
                lst.add(palavra);
                palavra = "";
            }
        }
        return lst;
    }

    private void ordenaList() {
        double pnt = 0;
        List<String> lPrincipal = new ArrayList<String>(this.lstFrasePrincipal);
        List<String> lCompara = new ArrayList<String>(this.lstFraseCompara);
        List<LstPnt> lstPontua = new ArrayList<LstPnt>();
        List<String> palavraCOrd = new ArrayList<String>();
        for (int i = 0; i < lPrincipal.size(); i++) {
            
            
            String palavraP = (String) lPrincipal.get(i);
            pnt = (-1);
            lstPontua.add(null);
            this.lstFrasePontuada.add(null);
            for (Object lCompara1 : lCompara) {
                
                String palavraC = (String) lCompara1; /**/
                double pntp_ponto = Math.round(palavraP.length() > palavraC.length() ? PntPalavra.getPonto(palavraP, palavraC) : PntPalavra.getPonto(palavraC, palavraP));
                if (pntp_ponto > pnt) {
                    lstPontua.set(lstPontua.size() - 1, new LstPnt(i, palavraP, palavraC, pntp_ponto));
                    pnt = pntp_ponto;
                }
            }
        }
        double cntFrasePrincipal = this.FrasePrincipal.replaceAll(" ", "").length();
        for (int i = 100; i >= 0; i--) {

            for (int j = 0; j < lstPontua.size(); j++) {
                LstPnt lstpnt = (LstPnt) lstPontua.get(j);

                if (lstpnt.pnt == i) {

                    if (lCompara.contains(lstpnt.palavraC)) {
                        this.lstFrasePontuada.set(lstpnt.ord, lstpnt);
                        lCompara.remove(lstpnt.palavraC);
                        palavraCOrd.add(lstpnt.palavraC);
                    } else {
                        this.lstFrasePontuada.set(lstpnt.ord, new LstPnt(lstpnt.ord, lstpnt.palavraP, " ", 0.0));
                    }

                    ponto += (((LstPnt) this.lstFrasePontuada.get(j)).palavraP.length() * 100 / cntFrasePrincipal) * ((LstPnt) this.lstFrasePontuada.get(j)).pnt / 100;
                }

            }
        }
        PontuaOrd(palavraCOrd, this.lstFraseCompara);
    }

    private void PontuaOrd(List<String> vPalavraP, List<String> vPalavraC) {
        List<String> lstFalse = new ArrayList<String>();
        int i = 2;

        for (int j = 0; j < vPalavraC.size() - i + 1; j++) {
            if (!contains(vPalavraP, vPalavraC.subList(j, j + i))) {
                lstFalse.addAll(vPalavraC.subList(j, j + i));
                pontoOrd++;
            }
        }

        pontoOrd = ((vPalavraC.size() - ((int) ((((pontoOrd % 2 == 0) || (pontoOrd == 1)) ? pontoOrd + 2 : pontoOrd) / 3))) * 100) / vPalavraC.size();
    }

    private class LstPnt {

        int ord;
        String palavraP;
        String palavraC;
        double pnt;
        
        LstPnt(int ord, String ppalavraP, String ppalavraC, double ppnt) {
            this.ord = ord;
            this.palavraP = ppalavraP;
            this.palavraC = ppalavraC;
            this.pnt = ppnt;
        }
    }

}
