package sample;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sudoku {
    private int[][] sudokuArray;
    private int[][] toSolve;
    private Map<String, String> arrayAddresses = new HashMap<>();
    private Set<Integer> set = new HashSet<>();
    private int count[] = new int[81];
    private int row;
    private int col;
    private int counter;


    public Sudoku() {
        this.sudokuArray = new int[9][9];
        this.toSolve = new int[9][9];
        arrayAddresses.put("00", "00");
        arrayAddresses.put("01", "01");
        arrayAddresses.put("02", "02");
        arrayAddresses.put("03", "10");
        arrayAddresses.put("04", "11");
        arrayAddresses.put("05", "12");
        arrayAddresses.put("06", "20");
        arrayAddresses.put("07", "21");
        arrayAddresses.put("08", "22");
        arrayAddresses.put("10", "03");
        arrayAddresses.put("11", "04");
        arrayAddresses.put("12", "05");
        arrayAddresses.put("13", "13");
        arrayAddresses.put("14", "14");
        arrayAddresses.put("15", "15");
        arrayAddresses.put("16", "23");
        arrayAddresses.put("17", "24");
        arrayAddresses.put("18", "25");
        arrayAddresses.put("20", "06");
        arrayAddresses.put("21", "07");
        arrayAddresses.put("22", "08");
        arrayAddresses.put("23", "16");
        arrayAddresses.put("24", "17");
        arrayAddresses.put("25", "18");
        arrayAddresses.put("26", "26");
        arrayAddresses.put("27", "27");
        arrayAddresses.put("28", "28");
        arrayAddresses.put("30", "30");
        arrayAddresses.put("31", "31");
        arrayAddresses.put("32", "32");
        arrayAddresses.put("33", "40");
        arrayAddresses.put("34", "41");
        arrayAddresses.put("35", "42");
        arrayAddresses.put("36", "50");
        arrayAddresses.put("37", "51");
        arrayAddresses.put("38", "52");
        arrayAddresses.put("40", "33");
        arrayAddresses.put("41", "34");
        arrayAddresses.put("42", "35");
        arrayAddresses.put("43", "43");
        arrayAddresses.put("44", "44");
        arrayAddresses.put("45", "45");
        arrayAddresses.put("46", "53");
        arrayAddresses.put("47", "54");
        arrayAddresses.put("48", "55");
        arrayAddresses.put("50", "36");
        arrayAddresses.put("51", "37");
        arrayAddresses.put("52", "38");
        arrayAddresses.put("53", "46");
        arrayAddresses.put("54", "47");
        arrayAddresses.put("55", "48");
        arrayAddresses.put("56", "56");
        arrayAddresses.put("57", "57");
        arrayAddresses.put("58", "58");
        arrayAddresses.put("60", "60");
        arrayAddresses.put("61", "61");
        arrayAddresses.put("62", "62");
        arrayAddresses.put("63", "70");
        arrayAddresses.put("64", "71");
        arrayAddresses.put("65", "72");
        arrayAddresses.put("66", "80");
        arrayAddresses.put("67", "81");
        arrayAddresses.put("68", "82");
        arrayAddresses.put("70", "63");
        arrayAddresses.put("71", "64");
        arrayAddresses.put("72", "65");
        arrayAddresses.put("73", "73");
        arrayAddresses.put("74", "74");
        arrayAddresses.put("75", "75");
        arrayAddresses.put("76", "83");
        arrayAddresses.put("77", "84");
        arrayAddresses.put("78", "85");
        arrayAddresses.put("80", "66");
        arrayAddresses.put("81", "67");
        arrayAddresses.put("82", "68");
        arrayAddresses.put("83", "76");
        arrayAddresses.put("84", "77");
        arrayAddresses.put("85", "78");
        arrayAddresses.put("86", "86");
        arrayAddresses.put("87", "87");
        arrayAddresses.put("88", "88");

        initGrid();
        generate();
        System.out.println("----Generated----");
        for (int i = 0; i < sudokuArray.length; i++) {
            for (int j = 0; j < sudokuArray[i].length; j++)
                System.out.print(sudokuArray[i][j] + " ");
            System.out.println("");
        }
        System.out.println(isValid(sudokuArray));
    }

    public void add(String position, String value) {
        int row = Integer.parseInt(String.valueOf(arrayAddresses.get(position).charAt(0)));
        int col = Integer.parseInt(String.valueOf(arrayAddresses.get(position).charAt(1)));
        int valueToPut = Integer.parseInt(value);
        if (sudokuArray[row][col] != valueToPut) {
            sudokuArray[row][col] = valueToPut;
            System.out.print(row + "" + col + ": " + value);
        }

    }


    private void initGrid() {
        for (int i = 0; i < this.sudokuArray.length; i++) {
            for (int j = 0; j < this.sudokuArray.length; j++) {
                this.sudokuArray[i][j] = 0;

            }
        }
    }

    private void resetGrid() {
        if (sudokuArray != null) {
            for (int i = 0; i < this.sudokuArray.length; i++) {
                for (int j = 0; j < this.sudokuArray.length; j++) {
                    this.sudokuArray[i][j] = 0;
                }

            }
        }
    }

    private boolean notInColumn(int k, int j) {
        for (int i = 0; i < this.sudokuArray.length; i++) {
            if (this.sudokuArray[i][j] == k)
                return false;
        }
        return true;
    }

    private boolean notInColumn(int[][] array, int row, int col) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][col] == array[row][col] && i != row)
                return false;
        }
        return true;
    }

    private boolean notInRow(int k, int i) {
        for (int j = 0; j < this.sudokuArray.length; j++) {
            if (this.sudokuArray[i][j] == k)
                return false;
        }
        return true;
    }

    private boolean notInRow(int[][] array, int row, int col) {
        for (int j = 0; j < array.length; j++) {
            if (array[row][j] == array[row][col] && j != col)
                return false;
        }
        return true;
    }

    private boolean notInCell(int k, int i, int j) {

        int i2 = i - (i % 3);
        int j2 = j - (j % 3);
        for (i = i2; i < i2 + 3; i++)
            for (j = j2; j < j2 + 3; j++)
                if (this.sudokuArray[i][j] == k)
                    return false;
        return true;
    }

    private boolean notInCell(int[][] array, int row, int col) {

        int i2 = row - (row % 3);
        int j2 = col - (col % 3);
        for (int i = i2; i < i2 + 3; i++)
            for (int j = j2; j < j2 + 3; j++)
                if (array[i][j] == array[row][col] && i != row && j != col)
                    return false;
        return true;
    }

    private boolean isValid(int value, int row, int col) {

        if (notInColumn(value, col) && notInRow(value, row) && notInCell(value, row, col)) {
            return true;
        } else
            return false;
    }

    private boolean isValid(int[][] array) {

        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                if (!notInCell(array, row, col) || !notInRow(array, row, col) || !notInColumn(array, row, col))
                    return false;

            }
        }
        return true;
    }


    private void generate() {
        for (this.row = 0; row < sudokuArray.length; row++)
            for (this.col = 0; col < sudokuArray[row].length; col++) {
                if (sudokuArray[row][col] == 0) {
                    int value = 0;
                    if (sudokuArray[row][col] == value)
                        generate(row, col, value);
                }
            }
    }


    private void generate(int row, int col, int value) {
        ++counter;
        if (counter > 1500 || count[0]>10) {
            emergencyReset();
            return;
        }


        int index = row * 9 + col;
        count[index]++;

        if (value == 0)
            value = (int) (Math.random() * 9) + 1;
        else
            value = valueGenerator(value);

        if (isValid(value, row, col)) {
            sudokuArray[row][col] = value;
            resetCount(index);
        } else if (count[index] > 8) {
            if (col > 0) {
                col--;
               // row = this.row;
            } else if (col == 0 && row > 0) {
                col = 8;
                row--;
            } else
                return;

            resetCount(index);
            this.row = row;
            this.col = col;
            value = sudokuArray[row][col];
            generate(row, col, value);

        } else {
            generate(row, col, value);
        }


    }

    private int valueGenerator(int value) {
        value = value + 1;
        if (value > 9)
            value = 1;

        return value;
    }

    private void resetCount() {
        for (int e=0; e<this.count.length; e++) {
            count[e]=0;
        }

    }

    private void resetCount(int i) {
        for (int index = i + 1; index < this.count.length; index++)
            this.count[index] = 0;
    }

    private void emergencyReset() {
        resetGrid();
        resetCount();
        this.col = 0;
        this.row = 0;
        this.counter = 0;
        sudokuArray[row][col] = (int) (Math.random() * 9) + 1;
    }


}
