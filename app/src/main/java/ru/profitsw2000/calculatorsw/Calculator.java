package ru.profitsw2000.calculatorsw;

import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

class Calculator implements Serializable {
    private StringBuilder argument1, argument2 ;
    private Double var1, var2, var;

    public String getOutputText() {
        return outputText;
    }

    private String outputText   ;
    private Operation operation ;

    Calculator () {
        this.argument1 = new StringBuilder() ;
        this.argument2 = new StringBuilder() ;
        this.var1 = 0.0   ;
        this.var2 = 0.0   ;
        this.var = 0.0  ;
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

    private StringBuilder convertDoubleToStringBuilder(Double dbl) {
        StringBuilder stringBuilder = new StringBuilder()    ;
        String string = new String()    ;

        string = Double.toString(dbl)   ;
        stringBuilder.append(string)    ;

        if (dbl%1 != 0) {
            stringBuilder.replace(stringBuilder.indexOf("."), (stringBuilder.indexOf(".") + 1), ",")    ;
        } else {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        return stringBuilder    ;
    }


    private Double convertStringBuilderToDouble(StringBuilder stringBuilder) {

        if (stringBuilder.indexOf(",") != -1) {
            stringBuilder.replace(stringBuilder.indexOf(","), (stringBuilder.indexOf(",") + 1), ".")    ;
        }

        return Double.parseDouble(stringBuilder.toString())    ;
    }

    private void equalPressed() {
        switch (operation) {
            case MINUS:
                var = var1 - var2   ;
                break;
            case PLUS:
                var = var1 + var2   ;
                break;
            case DIVIDE:
                var = var1 / var2 ;
                break;
            case MULTIPLE:
                var = var1 * var2   ;
                break;
            default:
                break;
        }
        outputText = Double.toString(var)   ;
        argument1.setLength(0);
        argument2.setLength(0);
        var1 = var  ;
        this.operation = Operation.NONE ;
    }

    public void setText(TextView textView) {
        textView.setText(outputText);
    }

    public void getButton(String buttonText){

        if(isInteger(buttonText)) {
            if (this.operation == Operation.NONE) {
                argument1.append(buttonText) ;
                var1 = convertStringBuilderToDouble(argument1)    ;
                outputText = argument1.toString()   ;
            }
            else {
                argument2.append(buttonText)    ;
                var2 = convertStringBuilderToDouble(argument2) ;
                outputText = argument2.toString()   ;
            }
        }
        else {
            switch (buttonText) {
                case ",":
                    if (this.operation == Operation.NONE) {
                        if (argument1.indexOf(",") == -1) {
                            argument1.append(",");
                            outputText = argument1.toString()   ;
                        }
                    } else {
                        if (argument2.indexOf(",") == -1) {
                            argument2.append(",");
                            outputText = argument2.toString()   ;
                        }
                    }
                    break;
                case "C":
                    argument1.setLength(0);
                    argument2.setLength(0);
                    this.var1 = 0.0   ;
                    this.var2 = 0.0   ;
                    this.var = 0.0  ;
                    this.operation = Operation.NONE ;
                    outputText = "0"   ;
                    break;
                case "<--":
                    if (this.operation == Operation.NONE){
                        if (argument1.length() > 1) {
                            argument1.deleteCharAt(argument1.length() - 1)    ;
                            var1 = Double.parseDouble(argument1.toString())    ;
                            outputText = argument1.toString();
                        }
                        else if (argument1.length() > 0 && argument1.charAt(0) != '0'){
                            argument1.setLength(0)   ;
                            var1 = 0.0    ;
                            outputText = "0"    ;
                        }
                    } else {
                        if (argument2.length() > 1) {
                            argument2.deleteCharAt(argument2.length() - 1)    ;
                            var2 = Double.parseDouble(argument2.toString())    ;
                            outputText = argument2.toString();
                        }
                        else if (argument2.length() > 0 && argument2.charAt(0) != '0'){
                            argument2.setLength(0)   ;
                            var2 = 0.0  ;
                            outputText = "0"    ;
                        }
                    }

                    break;
                case "√":
                    if (this.operation == Operation.NONE) {
                        var = Math.sqrt(var1)   ;
                        outputText = convertDoubleToStringBuilder(var).toString()  ;
                    }
                    else {
                        var2 = Math.sqrt(var2)  ;
                        outputText = convertDoubleToStringBuilder(var2).toString()  ;
                    }
                    break;
                case "-":
                    if (this.operation != Operation.MINUS){
                        argument2.setLength(0);
                        var2 = var1 ;
                        this.operation = Operation.MINUS    ;
                    }
                    break;
                case "+":
                    if (this.operation != Operation.PLUS){
                        argument2.setLength(0);
                        var2 = var1 ;
                        this.operation = Operation.PLUS    ;
                    }
                    break;
                case "/":
                    if (this.operation != Operation.DIVIDE){
                        argument2.setLength(0);
                        var2 = var1 ;
                        this.operation = Operation.DIVIDE    ;
                    }
                    break;
                case "x":
                    if (this.operation != Operation.MULTIPLE){
                        argument2.setLength(0);
                        var2 = var1 ;
                        this.operation = Operation.MULTIPLE    ;
                    }
                    break;
                case "=":
                    equalPressed();
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
