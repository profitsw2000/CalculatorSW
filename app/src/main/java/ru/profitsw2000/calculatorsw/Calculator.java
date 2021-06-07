package ru.profitsw2000.calculatorsw;

import android.view.View;
import android.widget.TextView;

class Calculator {
    private StringBuilder argument ;
    private Double var1, var2;
    private boolean secondArgument  ;
    private String outputText   ;
    private Operation operation ;

    Calculator () {
        this.argument = new StringBuilder(16) ;
        this.argument.append("0")    ;
        this.var1 = 0.0   ;
        this.var2 = 0.0   ;
        this.secondArgument = false ;
        this.operation = Operation.NONE ;
    }

    private boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    private void equalPressed(Operation operation, Double var1, Double var2) {
        switch (operation) {
            case MINUS:
                argument.append(Double.toString(var1 - var2))   ;
                break;
            case PLUS:
                argument.append(Double.toString(var1 + var2))   ;
                break;
            case DIVIDE:
                argument.append(Double.toString(var1/var2))   ;
                break;
            case MULTIPLE:
                argument.append(Double.toString(var1*var2))   ;
                break;
            default:
                break;
        }
    }

    public void setText(TextView textView) {
        outputText = argument.toString()    ;
        textView.setText(outputText);
    }

    public void getButton(String buttonText){

        if(isInteger(buttonText) && argument.length() < 16) {
            if (argument.charAt(0) == '0' && argument.length() < 2) argument.deleteCharAt(0);
            argument.append(buttonText) ;
        }
        else {
            switch (buttonText) {
                case ",":
                    if (argument.indexOf(",") == -1) {
                        argument.append(".") ;
                    }
                    break;
                case "C":
                    argument.setLength(0)   ;
                    argument.append("0")    ;
                    break;
                case "<--":
                    if (argument.length() > 1) {
                        argument.deleteCharAt(argument.length() - 1)    ;
                    }
                    else if (argument.charAt(0) != '0'){
                        argument.setLength(0)   ;
                        argument.append("0")    ;
                    }
                    break;
                case "√":
                    var1 = Double.parseDouble(argument.toString())  ;
                    argument.setLength(0)   ;
                    argument.append(Double.toString(Math.sqrt(var1)))    ;
                    break;
                case "-":
                    var1 = Double.parseDouble(argument.toString())  ;
                    operation = Operation.MINUS ;
                    break;
                case "+":
                    operation = Operation.PLUS  ;
                    break;
                case "/":
                    operation = Operation.DIVIDE    ;
                    break;
                case "x":
                    operation = Operation.MULTIPLE  ;
                    break;
                case "=":
                    var2 = Double.parseDouble(argument.toString())  ;
                    equalPressed(operation, var1, var2);
                    break;
                default:
                    break;
            }
        }
    }
}

enum Operation {
    MINUS,
    PLUS,
    DIVIDE,
    MULTIPLE,
    NONE
}
