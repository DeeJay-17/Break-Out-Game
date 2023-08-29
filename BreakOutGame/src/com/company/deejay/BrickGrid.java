package com.company.deejay;

import java.awt.*;

public class BrickGrid {
    public int grid[][];
    public int brickwidth;
    public int brickheight;
    public BrickGrid(int row, int col) {
        grid = new int[row][col];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = 1;
            }
        }
        brickwidth = 540/col;
        brickheight = 150/row;
    }
    public void draw(Graphics2D g) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] > 0) {
                    g.setColor(Color.white);
                    g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
                    g.setStroke(new BasicStroke(6));
                    g.setColor(Color.black);
                    g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
                }
            }
        }
    }
    public void setBrickValue(int value, int row, int col) {
        grid[row][col] = value;

    }
}
