package ru.profitsw2000.calculatorsw;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;


public class Calculator implements Parcelable {

    private final String CLEAR = "C"    ;
    private final String BACKSPACE = "<--"    ;
    private final String POINT = ","    ;
    private final String SQRT = "√"    ;
    private final String MINUS = "-"    ;
    private final String PLUS = "+"    ;
    private final String DIVIDE = "/"    ;
    private final String MULTIPLE = "*"    ;
    private final String EQUAL = "="    ;

    private StringBuilder argument1, argument2 ;
    private Double var1, var2, var;
    private String outputText   ;
    private Operation operation ;

    Calculator () {
        this.argument1 = new StringBuilder() ;
        argument1.append("0")   ;
        this.argument2 = new StringBuilder() ;
        this.var1 = 0.0   ;
        this.var2 = 0.0   ;
        this.var = 0.0  ;
        this.operation = Operation.NONE ;
    }

    protected Calculator(Parcel in) {
        if (in.readByte() == 0) {
            var1 = null;
        } else {
            var1 = in.readDouble();
        }
        if (in.readByte() == 0) {
            var2 = null;
        } else {
            var2 = in.readDouble();
        }
        if (in.readByte() == 0) {
            var = null;
        } else {
            var = in.readDouble();
        }
        outputText = in.readString();
        argument1.append(in.readString()) ;
        argument2.append(in.readString()) ;
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    private boolean isInteger(String input) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    public StringBuilder getArgument1() {
        return argument1;
    }

    public StringBuilder getArgument2() {
        return argument2;
    }

    public Double getVar1() {
        return var1;
    }

    public Double getVar2() {
        return var2;
    }

    public Double getVar() {
        return var;
    }

    public String getOutputText() {
        return outputText;
    }

    private String convertString (String string) {
        StringBuilder stringBuilder = new StringBuilder()   ;
        int index ;

        stringBuilder.append(string)    ;
        if (stringBuilder.indexOf(".") != -1) {
            index = stringBuilder.indexOf(".0") ;
            if (index == stringBuilder.length() - 2) {
                stringBuilder.setLength(stringBuilder.length() - 2);
            }
            else {
                stringBuilder.replace(stringBuilder.indexOf("."), (stringBuilder.indexOf(".") + 1), ",")    ;
            }
        }
        else {
            if (stringBuilder.length() > 1 && stringBuilder.charAt(0) == '0') stringBuilder.replace(0, 1, "")    ;
        }

        return stringBuilder.toString()   ;
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
        outputText = convertString(Double.toString(var))   ;
        argument1.setLength(0);
        argument1.append("0")   ;
        argument2.setLength(0);
        var1 = var  ;
        this.operation = Operation.NONE ;
    }

    private Double parseFromArgument (StringBuilder argument, String buttonText) {
        if (argument.toString().equals("0")){
            argument.setLength(0);
            argument.append(buttonText) ;
        }
        else {
            argument.append(buttonText) ;
        }
        Double dbl = Double.parseDouble(argument.toString())    ;
        return dbl  ;
    }

    private void pointPressed(){
        if (this.operation == Operation.NONE) {
            if (argument1.indexOf(",") == -1) {
                argument1.append(".");
                outputText = convertString(argument1.toString());
            }
        } else {
            if (argument2.length() > 0){
                if (argument2.indexOf(",") == -1) {
                    argument2.append(".");
                    //add second line
                    outputText = convertString(argument1.toString()) + convertString(argument2.toString())   ;
                }
            }
        }
    }

    private void clearPressed() {
        argument1.setLength(0);
        argument1.append("0")   ;
        argument2.setLength(0);
        this.var1 = 0.0   ;
        this.var2 = 0.0   ;
        this.var = 0.0  ;
        this.operation = Operation.NONE ;
        outputText = "0"   ;
    }

    private void backspacePressed() {
        if (this.operation == Operation.NONE){
            if (argument1.length() > 1) {
                argument1.deleteCharAt(argument1.length() - 1)    ;
                var1 = Double.parseDouble(argument1.toString())    ;
                outputText = convertString(argument1.toString());
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
                //add second line
                outputText = convertString(argument1.toString()) + convertString(argument2.toString());
            }
            else if (argument2.length() > 0 && argument2.charAt(0) != '0'){
                argument2.setLength(0)   ;
                var2 = 0.0  ;
                //add second line
                outputText = convertString(argument1.toString()) + "0"    ;
            }
        }
    }

    private void sqrtPressed() {
        if (this.operation == Operation.NONE) {
            var = Math.sqrt(var1)   ;
            outputText = convertString(Double.toString(var))  ;
        }
        else {
            var2 = Math.sqrt(var2)  ;
            //add second line
            outputText = convertString(argument1.toString()) + convertString(Double.toString(var2))  ;
        }
    }

    private void minusPressed (String buttonText) {
        if (this.operation != Operation.MINUS){
            this.operation = Operation.MINUS    ;
            operationButton(buttonText);
        }
    }

    private void plusPressed (String buttonText) {
        if (this.operation != Operation.PLUS){
            this.operation = Operation.PLUS    ;
            operationButton(buttonText);
        }
    }

    private void multiplePressed (String buttonText) {
        if (this.operation != Operation.MULTIPLE){
            this.operation = Operation.MULTIPLE    ;
            operationButton(buttonText);
        }
    }

    private void dividePressed (String buttonText) {
        if (this.operation != Operation.DIVIDE){
            this.operation = Operation.DIVIDE    ;
            operationButton(buttonText);
        }
    }

    public void getButton(String buttonText){

        if(isInteger(buttonText)) {
            if (this.operation == Operation.NONE) {
                var1 = parseFromArgument(argument1, buttonText)    ;
                outputText = convertString(argument1.toString())   ;
            }
            else {
                var2 = parseFromArgument(argument2, buttonText)    ;
                //add second line
                outputText = convertString(argument1.toString()) + convertString(argument2.toString())   ;
            }
        }
        else {
            switch (buttonText) {
                case POINT:
                    pointPressed();
                    break;
                case CLEAR:
                    clearPressed();
                    break;
                case BACKSPACE:
                    backspacePressed();
                    break;
                case SQRT:
                    sqrtPressed();
                    break;
                case MINUS:
                    minusPressed(buttonText);
                    break;
                case PLUS:
                    plusPressed(buttonText);
                    break;
                case DIVIDE:
                    dividePressed(buttonText);
                    break;
                case MULTIPLE:
                    multiplePressed(buttonText);
                    break;
                case EQUAL:
                    equalPressed();
                    break;
                default:
                    break;
            }
        }
    }

    private void operationButton(String buttonText) {
        argument2.setLength(0);
        var2 = var1 ;
        //add second line
        if (argument1.indexOf("\n") == -1) argument1.append(buttonText + "\n")    ;
        else {
            argument1.deleteCharAt(argument1.indexOf("\n") - 1) ;
            argument1.deleteCharAt(argument1.indexOf("\n")) ;
            argument1.append(buttonText + "\n")    ;
        }
        outputText = convertString(argument1.toString()) + convertString(Double.toString(var2))  ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (var1 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(var1);
        }
        if (var2 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(var2);
        }
        if (var == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(var);
        }
        dest.writeString(outputText);
        dest.writeString(argument1.toString());
        dest.writeString(argument2.toString());
    }
}

enum Operation {
    MINUS,
    PLUS,
    DIVIDE,
    MULTIPLE,
    NONE
}
