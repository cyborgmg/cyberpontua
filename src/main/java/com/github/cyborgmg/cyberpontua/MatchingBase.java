package com.github.cyborgmg.cyberpontua;

import java.util.ArrayList;

public abstract class MatchingBase {

    protected  String normalize(String str) {

        try {

            char[] FIRST_CHAR
                    = (" !'#$%&'()*+\\-./0123456789:;<->?@ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ E ,f'.++^%S<O Z  ''''.-"
                    + "-~Ts>o ZY !C#$Y|$'(a<--(_o+23'u .,1o>113?AAAAAAACEEEEIIIIDNOO"
                    + "OOOXOUUUUyTsaaaaaaaceeeeiiiidnooooo/ouuuuyty")
                    .toCharArray();
            char[] SECOND_CHAR
                    = ("  '         ,                                               "
                    + "\\                                   $  r'. + o  E      ''  "
                    + "  M  e     #  =  'C.<  R .-..     ..>424     E E            "
                    + "   E E     hs    e e         h     e e     h ")
                    .toCharArray();

            char[] chars = str.toCharArray();
            StringBuilder ret = new StringBuilder(chars.length * 2);
            for (int i = 0; i < chars.length; ++i) {
                char aChar = chars[i];
                if (aChar == ' ' || aChar == '\t') {
                    ret.append(' '); // convertido para espaço
                } else if (aChar > ' ' & aChar < '\u0100') {
                    if (FIRST_CHAR[aChar - ' '] != ' ') {
                        ret.append(FIRST_CHAR[aChar - ' ']); // 1 caracter
                    }
                    if (SECOND_CHAR[aChar - ' '] != ' ') {
                        ret.append(SECOND_CHAR[aChar - ' ']); // 2 caracteres
                    }
                }
            }

            return ret.toString().trim().toUpperCase();

        } catch (NullPointerException e) {
            return null;
        }
    }

    private  ArrayList<String> lstalfa() {
        ArrayList<String> letras = new ArrayList<String>();
        letras.add("A");
        letras.add("Q");
        letras.add("W");
        letras.add("E");
        letras.add("R");
        letras.add("T");
        letras.add("Y");
        letras.add("U");
        letras.add("I");
        letras.add("O");
        letras.add("P");
        letras.add("A");
        letras.add("S");
        letras.add("D");
        letras.add("F");
        letras.add("G");
        letras.add("H");
        letras.add("J");
        letras.add("K");
        letras.add("L");
        letras.add("Ç");
        letras.add("Z");
        letras.add("X");
        letras.add("C");
        letras.add("V");
        letras.add("B");
        letras.add("N");
        letras.add("M");
        letras.add(" ");
        return letras;
    }

    private  ArrayList<String> lstnum() {
        ArrayList<String> letras = new ArrayList<String>();
        letras.add("0");
        letras.add("9");
        letras.add("8");
        letras.add("7");
        letras.add("6");
        letras.add("5");
        letras.add("4");
        letras.add("3");
        letras.add("2");
        letras.add("1");
        return letras;
    }

    private  ArrayList<String> lstall() {
        ArrayList<String> letras = new ArrayList<String>();
        letras.addAll(lstalfa());
        letras.addAll(lstnum());
        return letras;
    }

    private  boolean filter(String ch, ArrayList<String> letras) {

        try {
            for (Object letra : letras) {
                if (ch.equals(letra)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private  String clearstring(String pl) {
        String aux = "";

        for (int i = 0; i < pl.length(); i++) {

            if (filter(pl.toUpperCase().substring(i, i + 1), lstall())) {
                aux += pl.substring(i, i + 1);
            }
        }

        return aux;
    }

    private  String justalfa(String pl) {
        String aux = "";

        for (int i = 0; i < pl.length(); i++) {

            if (filter(pl.toUpperCase().substring(i, i + 1), lstalfa())) {
                aux += pl.substring(i, i + 1);
            }
        }

        return aux;
    }

}
