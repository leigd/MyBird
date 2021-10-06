package com.msh.fivestarflag;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;


import javax.swing.*;

/**
 * 五星红旗
 * @author: lgd
 * @date: 2021/10/01/ 22:29
*/
public class FiveStarFlag extends JPanel {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("五星红旗");
        //测试
        jFrame.getContentPane().add(new FiveStarFlag(800));
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    /**
     * 创建一个五角星形状.
     * 该五角星的中心坐标为(sx,sy),中心到顶点的距离为radius,其中某个顶点与中心的连线的偏移角度为
     *
     * @return pentacle 一个☆
     */
    public static Shape createPentacle(double sx, double sy, double radius, double theta) {
        final double arc = Math.PI / 5;
        final double rad = Math.sin(Math.PI / 10) / Math.sin(3 * Math.PI / 10);
        GeneralPath path = new GeneralPath();
        path.moveTo(1, 0);
        for (int i = 0; i < 5; i++) {
            path.lineTo(rad * Math.cos((1 + 2 * i) * arc), rad * Math.sin((1 + 2 * i) * arc));
            path.lineTo(Math.cos(2 * (i + 1) * arc), Math.sin(2 * (i + 1) * arc));
        }
        path.closePath();
        AffineTransform atf = AffineTransform.getScaleInstance(radius, radius);
        atf.translate(sx / radius, sy / radius);
        atf.rotate(theta);
        return atf.createTransformedShape(path);
    }

    private int width, height;
    private double maxR = 0.15, minR = 0.05;
    private double maxX = 0.25, maxY = 0.25;
    private double[] minX = {0.50, 0.60, 0.60, 0.50};
    private double[] minY = {0.10, 0.20, 0.35, 0.45};

    /**
     * 创建一个宽度为width的国旗
     */
    public FiveStarFlag(int width) {
        this.width = width / 3 * 3;
        this.height = width / 3 * 2;
        setPreferredSize(new Dimension(this.width, this.height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        //画旗面
        graphics2D.setPaint(Color.RED);
        graphics2D.fillRect(0, 0, width, height);

        //画大☆
        double ox = height * maxX, oy = height * maxY;
        graphics2D.setPaint(Color.YELLOW);
        graphics2D.fill(createPentacle(ox, oy, height * maxR, -Math.PI / 2));

        //画小★
        for (int i = 0; i < 4; i++) {
            double sx = minX[i] * height, sy = minY[i] * height;
            double theta = Math.atan2(oy - sy, ox - sx);
            graphics2D.fill(createPentacle(sx, sy, height * minR, theta));
        }
    }

}