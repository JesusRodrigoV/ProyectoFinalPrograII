package com.banco.modelo;

public class Contrasena {
    private final String contra;

    public Contrasena(String contra) {
        this.contra = contra;
    }

    public boolean largo() {
        return contra != null && contra.length() >= 10;
    }

    public boolean mayus() {
        if (contra == null) return false;
        int mayusculas = 0;
        for (int i = 0; i < contra.length(); i++) {
            char letra = contra.charAt(i);
            if (letra >= 'A' && letra <= 'Z') {
                mayusculas++;
            }
        }
        return mayusculas >= 1;
    }

    public boolean minus() {
        if (contra == null) return false;
        int minusculas = 0;
        for (int i = 0; i < contra.length(); i++) {
            char letra = contra.charAt(i);
            if (letra >= 'a' && letra <= 'z') {
                minusculas++;
            }
        }
        return minusculas >= 2;
    }

    public boolean nums() {
        if (contra == null) return false;
        int numeros = 0;
        for (int i = 0; i < contra.length(); i++) {
            char letra = contra.charAt(i);
            if (letra >= '0' && letra <= '9') {
                numeros++;
            }
        }
        return numeros >= 1;
    }

    public boolean symbols() {
        if (contra == null) return false;
        char[] caracteres = {'%', '&', '$', '/', '*'};
        for (int i = 0; i < contra.length(); i++) {
            char letra = contra.charAt(i);
            for (char c : caracteres) {
                if (letra == c) return true;
            }
        }
        return false;
    }

    public boolean contraSegura() {
        return mayus() && minus() && nums() && symbols() && largo();
    }

    public String errores() {
        var sb = new StringBuilder();
        if (!largo()) sb.append("- Debe tener mínimo 10 caracteres\n");
        if (!mayus()) sb.append("- Debe tener mínimo 1 mayúscula\n");
        if (!minus()) sb.append("- Debe tener mínimo 2 minúsculas\n");
        if (!nums()) sb.append("- Debe tener mínimo 1 número\n");
        if (!symbols()) sb.append("- Debe tener mínimo 1 símbolo especial (% & $ / *)\n");
        return sb.toString();
    }

    public String getContra() {
        return contra;
    }
}
