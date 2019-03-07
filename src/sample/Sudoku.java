package sample;


import java.util.HashMap;
import java.util.Map;

public class Sudoku {
    private int[][] SudokuArray;
    private Map<String, String> ArrayAddresses = new HashMap<>();

    public Sudoku()
    {
        this.SudokuArray = new int[9][9];
        ArrayAddresses.put("00","00");
        ArrayAddresses.put("01","01");
        ArrayAddresses.put("02","02");
        ArrayAddresses.put("03","10");
        ArrayAddresses.put("04","11");
        ArrayAddresses.put("05","12");
        ArrayAddresses.put("06","20");
        ArrayAddresses.put("07","21");
        ArrayAddresses.put("08","22");
        ArrayAddresses.put("10","03");
        ArrayAddresses.put("11","04");
        ArrayAddresses.put("12","05");
        ArrayAddresses.put("13","13");
        ArrayAddresses.put("14","14");
        ArrayAddresses.put("15","15");
        ArrayAddresses.put("16","23");
        ArrayAddresses.put("17","24");
        ArrayAddresses.put("18","25");
        ArrayAddresses.put("20","06");
        ArrayAddresses.put("21","07");
        ArrayAddresses.put("22","08");
        ArrayAddresses.put("23","16");
        ArrayAddresses.put("24","17");
        ArrayAddresses.put("25","18");
        ArrayAddresses.put("26","26");
        ArrayAddresses.put("27","27");
        ArrayAddresses.put("28","28");
        ArrayAddresses.put("30","30");
        ArrayAddresses.put("31","31");
        ArrayAddresses.put("32","32");
        ArrayAddresses.put("33","40");
        ArrayAddresses.put("34","41");
        ArrayAddresses.put("35","42");
        ArrayAddresses.put("36","50");
        ArrayAddresses.put("37","51");
        ArrayAddresses.put("38","52");
        ArrayAddresses.put("40","33");
        ArrayAddresses.put("41","34");
        ArrayAddresses.put("42","35");
        ArrayAddresses.put("43","43");
        ArrayAddresses.put("44","44");
        ArrayAddresses.put("45","45");
        ArrayAddresses.put("46","53");
        ArrayAddresses.put("47","54");
        ArrayAddresses.put("48","55");
        ArrayAddresses.put("50","36");
        ArrayAddresses.put("51","37");
        ArrayAddresses.put("52","38");
        ArrayAddresses.put("53","46");
        ArrayAddresses.put("54","47");
        ArrayAddresses.put("55","48");
        ArrayAddresses.put("56","56");
        ArrayAddresses.put("57","57");
        ArrayAddresses.put("58","58");
        ArrayAddresses.put("60","60");
        ArrayAddresses.put("61","61");
        ArrayAddresses.put("62","62");
        ArrayAddresses.put("63","70");
        ArrayAddresses.put("64","71");
        ArrayAddresses.put("65","72");
        ArrayAddresses.put("66","80");
        ArrayAddresses.put("67","81");
        ArrayAddresses.put("68","82");
        ArrayAddresses.put("70","63");
        ArrayAddresses.put("71","64");
        ArrayAddresses.put("72","65");
        ArrayAddresses.put("73","73");
        ArrayAddresses.put("74","74");
        ArrayAddresses.put("75","75");
        ArrayAddresses.put("76","83");
        ArrayAddresses.put("77","84");
        ArrayAddresses.put("78","85");
        ArrayAddresses.put("80","66");
        ArrayAddresses.put("81","67");
        ArrayAddresses.put("82","68");
        ArrayAddresses.put("83","76");
        ArrayAddresses.put("84","77");
        ArrayAddresses.put("85","78");
        ArrayAddresses.put("86","86");
        ArrayAddresses.put("87","87");
        ArrayAddresses.put("88","88");
    }

    public void add(String position, String value)
    {
        int row=Integer.parseInt(String.valueOf(ArrayAddresses.get(position).charAt(0)));
        int col=Integer.parseInt(String.valueOf(ArrayAddresses.get(position).charAt(1)));
        int valueToPut = Integer.parseInt(value);
            if(SudokuArray[row][col]!=valueToPut) {
                SudokuArray[row][col] = valueToPut;
                System.out.print(row + "" + col + ": " + value);
            }

    }
}
