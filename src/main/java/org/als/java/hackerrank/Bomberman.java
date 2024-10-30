package org.als.java.hackerrank;

import org.apache.commons.collections4.list.TreeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Bomberman {

    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

    public static List<String> bomberMan(int n, List<String> grid) {
        // Write your code here
        int rows = grid.size();
        int columns = grid.get(0).length();

        List<String> newGrid = new ArrayList<>();
        int bombermanDoesNothing = 1;
        int bombermanPlantsBombsInCellsWithoutBombs = 2;

        if( isValidInputData(n, grid) ) {
            Map<String,GridStatus> gridStatus = initializeGrid(grid);
            for( int i = 1 ; i <= n ; i++ ) {
                if( i == bombermanDoesNothing ) {
                    continue;
                }

                if( i%bombermanPlantsBombsInCellsWithoutBombs==0 ) {
                    plantBombs(gridStatus, i);
                }

                detonateBoombs(gridStatus, i, rows, columns);
            }

            newGrid = getListRepresentation(gridStatus, rows, columns);
        }

        return newGrid;
    }

    public static void detonateBoombs( Map<String,GridStatus> grid, int currentSecond, int rows, int columns ) {
        int explodeAfter = 3;
        Map<String, GridStatus> bombsToExplode = new TreeMap<>();

        for( Map.Entry<String,GridStatus> entry : grid.entrySet()) {
            int i = Integer.valueOf(entry.getKey().split(",")[0]);
            int j = Integer.valueOf(entry.getKey().split(",")[1]);
            GridStatus gridStatus = entry.getValue();

            if ( gridStatus.hasBomb() ) {
                if( currentSecond - gridStatus.getTime() == explodeAfter ) {
                    bombsToExplode.put(entry.getKey(),gridStatus);

                    String rightKey =  String.format( "%s,%s", i, ((j+1<columns)?(j+1):j) );
                    String leftKey = String.format( "%s,%s", i, ((j-1>=0)?(j-1):j) );
                    String bottomKey = String.format( "%s,%s", (i+1<rows?(i+1):i), j);
                    String topKey =  String.format( "%s,%s", (i-1>=0?(i-1):i), j);

                    GridStatus right = grid.get( rightKey );
                    GridStatus left = grid.get( leftKey );
                    GridStatus top = grid.get( topKey );
                    GridStatus bottom = grid.get( bottomKey );

                    bombsToExplode.put( rightKey, right );
                    bombsToExplode.put( leftKey, left );
                    bombsToExplode.put( topKey, top );
                    bombsToExplode.put( bottomKey, bottom );
                }
            }
        }

        for( Map.Entry<String,GridStatus> gridStatus : bombsToExplode.entrySet() ) {
            if( gridStatus != null ) {
                gridStatus.getValue().explodeBomb(currentSecond);
            } else {

            }
        }
    }

    public static void plantBombs( Map<String,GridStatus> grid, int currentSecond) {
        for( Map.Entry<String, GridStatus> entry : grid.entrySet() ) {
            GridStatus gridStatus = entry.getValue();

            if( !gridStatus.hasBomb() ) {
                gridStatus.plantBomb(currentSecond);
            }
        }
    }
    public static List<String> getListRepresentation( Map<String,GridStatus> gridStatus, int r, int c ) {
        List<String> listResult = new ArrayList<>();

        for( int i = 0 ; i < r ; i++ ) {
            StringBuilder builder = new StringBuilder();
            for( int j = 0 ; j < c ; j++ ) {
                builder.append(gridStatus.get(String.format("%s,%s", i, j)));
            }
            listResult.add(builder.toString());
        }

        return listResult;
    }

    public static boolean isValidInputData( int n, List<String> grid ) {
        int maxTime = Double.valueOf(Math.pow(10, 9)).intValue();
        int r = grid.size();
        int c = grid.get(0).length();

        if( maxTime < n || n < 1 ) {
            return false;
        }
        if( 200 < r || r < 1 || 200 < c || c < 1 ) {
            return false;
        }

        return true;
    }

    public static Map<String,GridStatus> initializeGrid( List<String> grid ) {
        Map<String,GridStatus> gridStatusMap = new TreeMap<>();
        for( int i = 0 ; i < grid.size() ; i++ ) {
            for( int j = 0 ; j < grid.get(0).length() ; j++ ) {
                String cellStr = grid.get(i).substring(j, j+1);
                Bomberman.GridStatus gridStatus = new GridStatus(false, 0);

                if( cellStr.equals("O")) {
                    gridStatus.plantBomb(0);
                }

                gridStatusMap.put(String.format("%s,%s",i,j), gridStatus);
            }
        }

        return gridStatusMap;
    }

    static class GridStatus {
        boolean hasBomb;
        int time;

        public GridStatus( boolean hasBomb, int time ){
            this.hasBomb = hasBomb;
            this.time = time;
        }

        public boolean hasBomb(){
            return this.hasBomb;
        }
        public int getTime() {
            return this.time;
        }

        public void plantBomb(int time){
            this.hasBomb = true;
            this.time = time;
        }
        public void explodeBomb(int time){
            this.hasBomb = false;
            this.time = time;
        }
        public String toString(){
            return hasBomb?"O":".";
        }
    }

    public static void main(String[] args) throws IOException {
        Logger LOGGER = LoggerFactory.getLogger(Bomberman.class);
        int r = 6;
        int c = 7;
        int n = 3;

        List<String> grid = Arrays.asList(".......", "...O...", "....O..", ".......", "OO.....", "OO.....");
        List<String> result = Bomberman.bomberMan(n, grid);

        LOGGER.info( String.format("Grid: %s",grid));
        LOGGER.info( String.format("Results: %s",result));
    }
}

