package com.miko.assemblyLanguage;

public class AssemblyInterpreter {
    private int[] registers = new int[16]; // 16 general-purpose registers

    public void execute(String program) {
        String[] lines = program.split("\n");
        for (String line : lines) {
            String[] tokens = line.split(" ");
            if (tokens[0].equals("MV")) {
                int reg = Integer.parseInt(tokens[1].substring(3));
                int value = Integer.parseInt(tokens[2].substring(1));
                registers[reg] = value;
            } else if (tokens[0].equals("ADD")) {
                int reg1 = Integer.parseInt(tokens[1].substring(3));
                int value;
                if (tokens[2].charAt(0) == '#') {
                    value = Integer.parseInt(tokens[2].substring(1));
                } else {
                    int reg2 = Integer.parseInt(tokens[2].substring(3));
                    value = registers[reg2];
                }
                registers[reg1] += value;
            } else if (tokens[0].equals("SHOW")) {
                int reg = Integer.parseInt(tokens[1].substring(3));
                System.out.println("Value in REG" + reg + ": " + registers[reg]);
            } else {
                System.out.println("Invalid instruction: " + line);
            }
        }
    }

    public int getResult() {
        return registers[1]; // Assuming you want to return the result from REG1
    }
}
