import javax.swing.*;

/*  Inlämningsuppgift 1 Miniräknare
    Jesper Lindberg
*/

public class Main {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Skriv ett matematiskt uttryck: ");
        input = input.replaceAll("\\s+", "");

        // En giltig input innehåller inga bokstäver eller ogiltiga symboler
        for (int i = 0; i < input.length(); i++) {
            // Hittar alla bokstäver
            if (Character.isLetter(input.charAt(i))) {
                JOptionPane.showMessageDialog(null, "Får ej innehålla bokstäver");
                System.exit(-1);
            }
            // Hittar alla symboler som inte är dom tillåtna
            if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '+' && input.charAt(i) != '-' &&
                    input.charAt(i) != '*' && input.charAt(i) != '/' && input.charAt(i) != '.') {
                JOptionPane.showMessageDialog(null, "Får ej innehålla symboler förutom .+-*/");
                System.exit(-1);
            }
        }

        // En giltig input innehåller minst 3 karaktärer
        if (input.length() <= 2) {
            JOptionPane.showMessageDialog(null, "Måste innehålla minst 2 tal och en operator");
            System.exit(-1);
        }

        // Söker efter första giltiga operator, ignorerar första karaktären för möjlighet till negativt tal
        int operatorIndex = -1;
        for (int i = 1; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '.') {
                operatorIndex = i;
                break;
            }
        }

        // Hitta ingen giltig operator
        if (operatorIndex == -1) {
            JOptionPane.showMessageDialog(null, "Måste ha med en giltig operator (+-*/)");
            System.exit(-1);
        }

        char operator = input.charAt(operatorIndex);
        String firstNumberString = input.substring(0, operatorIndex);
        String secondNumberString = input.substring(operatorIndex + 1);

        if (!validateString(firstNumberString) || !validateString(secondNumberString)) {
            System.exit(-1);
        }

        double firstNumber = Double.parseDouble(firstNumberString);
        double secondNumber = Double.parseDouble(secondNumberString);

        // Skyddar mot divide by zero
        if (operator == '/' && secondNumber == 0) {
            JOptionPane.showMessageDialog(null, "Kan inte dela med noll");
            System.exit(-1);
        }

        double answer = switch (operator) {
            case '+' -> firstNumber + secondNumber;
            case '-' -> firstNumber - secondNumber;
            case '*' -> firstNumber * secondNumber;
            case '/' -> firstNumber / secondNumber;
            default -> -1.0;
        };

        JOptionPane.showMessageDialog(null, answer);
    }

    public static boolean validateString(String data){
        // Kollar efter flera operatorer
        if (data.contains("/") || data.contains("*") || data.contains("+") || data.lastIndexOf("-") > 0){
            JOptionPane.showMessageDialog(null, "Får ej vara mer än en operator.");
            return false;
        }
        // Kollar så det inte är flera punkter
        if (data.indexOf(".") != data.lastIndexOf(".")){
            JOptionPane.showMessageDialog(null, "Kan inte ha flera punkter.");
            return false;
        }
        // Kan inte sluta med punkt
        if (data.endsWith(".")) {
            JOptionPane.showMessageDialog(null, "Kan inte sluta på punkt.");
            return false;
        }
        // Om den har minustecken först måste den följa upp med siffror
        if (data.contains("-") && data.length() == 1){
            JOptionPane.showMessageDialog(null, "Kan inte vara ett ensamt minustecken");
            return false;
        }
        return true;
    }
}