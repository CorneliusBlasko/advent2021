package com.alberto.advent.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Diver{

    private static final String FORWARD = "forward";
    private static final String DOWN = "down";
    private static final String UP = "up";

    public long findFinalPositionWithAim() throws IOException{
        List<Instruction> instructions = this.retrieveMovements();
        long depth = 0L;
        long position = 0L;
        long aim = 0L;

        for(Instruction instruction : instructions) {
            if(instruction.getDirection().equals(FORWARD)) {
                position += instruction.getAmount();
                depth += aim * instruction.getAmount();
            }

            if(instruction.getDirection().equals(DOWN)) {
                aim += instruction.getAmount();
            }

            if(instruction.getDirection().equals(UP)) {
                aim -= instruction.getAmount();
            }
        }

        return position * depth;
    }

    public long findFinalPosition() throws IOException{
        List<Instruction> instructions = this.retrieveMovements();
        long depth = 0L;
        long position = 0L;

        for(Instruction instruction : instructions) {
            if(instruction.getDirection().equals(FORWARD)) {
                position += instruction.getAmount();
            }

            if(instruction.getDirection().equals(DOWN)) {
                depth += instruction.getAmount();
            }

            if(instruction.getDirection().equals(UP)) {
                depth -= instruction.getAmount();
            }
        }

        return position * depth;
    }

    public List<Instruction> retrieveMovements() throws IOException{
        List<String> content = Files.readAllLines(Path.of("src/main/resources/movements.txt"));
        List<Instruction> instructions = new ArrayList<>();

        for(String line : content) {
            String[] split = line.split("\\s+");
            instructions.add(Instruction.builder()
                                        .direction(split[0])
                                        .amount(Long.parseLong(split[1]))
                                        .build());
        }

        return instructions;
    }
}
