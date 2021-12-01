package com.alberto.advent.dayone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Counter{

    public Long measure(List<Long> measurements){
        long larger = 0L;

        for(int i = 1; i < measurements.size(); i++) {
            Long previous = measurements.get(i-1);
            Long next = measurements.get(i);
            boolean isLarger = next > previous;

            if(isLarger) {
                larger++;
            }
        }
        return larger;
    }

    public List<Long> createWindows(List<Long> measurements){
        List<Long> windows = new ArrayList<>();

        for(int i = 0; i < measurements.size(); i++) {
            if(i < (measurements.size() - 2)) {
                windows.add(measurements.get(i) + measurements.get(i+1) + measurements.get(i+2));
            }
        }
        return windows;
    }

    public List<Long> createMeasurements() throws IOException{
        Path fileName = Path.of("src/main/resources/001.txt");
        String content = Files.readString(fileName);
        String[] stringArray = content.split("\\r?\\n");
        List<String> stringList = Arrays.asList(stringArray);
        return stringList.stream().map(Long::valueOf).collect(Collectors.toList());
    }
}
