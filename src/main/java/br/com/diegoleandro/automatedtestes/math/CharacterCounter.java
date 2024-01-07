package br.com.diegoleandro.automatedtestes.math;


// implemente um método countCharacters que conta o número de caracteres em uma string.
public class CharacterCounter {

    public int countCharacters(String str1) {
        return str1.replaceAll("\\s", "").length();
    }

}
