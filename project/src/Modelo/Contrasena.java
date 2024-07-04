package Modelo;

import javax.swing.JOptionPane;

public class Contrasena {
    public String contra;

    public Contrasena (String contra){
        this.contra = contra;
    }
    public boolean largo(){
        boolean vale = false;
        if(getContra().length()>=10){
            vale = true;
        } 
        return vale;
    }
    public boolean mayus(){
        int mayusculas = 0;
        boolean vale = false;
        for(int i=0; i<getContra().length(); i++){
            char letra = contra.charAt(i);
            if (letra == Character.toUpperCase(letra) && (letra>='A' && letra<='Z')){
                mayusculas++;
            }
        }
        if (mayusculas>=1){
            vale = true;
        } 
        return vale;
    }
    public boolean minus(){
        int minusculas = 0;
        boolean vale = false;
        for(int i=0; i<getContra().length(); i++){
            char letra = contra.charAt(i);
            if (letra == Character.toLowerCase(letra) && (letra>='a' && letra<='z')){
                minusculas++;
            }
        }
        if (minusculas>=2){
            vale = true;
        } 
        return vale;
    }
    public boolean symbols(char[] caracteres){
        int simbolos = 0;
        boolean vale = false;
        for(int i=0; i<getContra().length(); i++){
            char letra = contra.charAt(i);
            for (int j=0; j<caracteres.length; j++){
                if (letra == caracteres[j]){
                    simbolos++;
                }
            }
        }
        if (simbolos>=1){
            vale = true;
        } 
        return vale;
    }
    public boolean nums(){
        int numeros = 0;
        char[] numbers = {'1','2','3','4','5','6','7','8','9','0'};
        boolean vale = false;
        for(int i=0; i<getContra().length(); i++){
            char letra = contra.charAt(i);
            for (int j=0; j<numbers.length; j++){
                if (letra == numbers[j]){
                    numeros++;
                }
            }
        }
        if (numeros>=1){
            vale = true;
        } 
        return vale;
    }
    public boolean contraSegura(){
        char[] caracteres = {'%','&','$','/','*'};
        verificar();
        return mayus() && minus() && nums() && symbols(caracteres) && largo();
    }
    public void verificar(){
        char[] caracteres = {'%','&','$','/','*'};
        if (contraSegura()){
            JOptionPane.showMessageDialog(null, "Contraseña segura");
        } else{
            if (largo()== false){
                JOptionPane.showMessageDialog(null, "La contraseña debe tener un minimo de 10 caracteres");
            }
            if (mayus() == false){
                JOptionPane.showMessageDialog(null, "La contraseña debe tener minimo 1 letra mayuscula");
            }
            if (minus() == false){
                JOptionPane.showMessageDialog(null, "La contraseña debe tener minimo de 2 letras minusculas");
            }
            if (nums() == false){
                JOptionPane.showMessageDialog(null, "La contraseña debe tener minimo 1 numero");
            }
            if (symbols(caracteres)== false){
                JOptionPane.showMessageDialog(null, "La contraseña debe tener minimo 1 caracter especial ");
                for (int i=0; i<caracteres.length; i++){
                    System.out.print(caracteres[i]+" ");
                }
            } 
        }
    }
    public void setContra(String contra) {
        this.contra = contra;
    }
    public String getContra() {
        return contra;
    }

}
