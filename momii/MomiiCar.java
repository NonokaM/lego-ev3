package jp.ac.kanazawait.ep.momii;

import jp.ac.kanazawait.ep.majorlabB.car.AbstCar;
import jp.ac.kanazawait.ep.majorlabB.car.TimeKeeper;
import jp.ac.kanazawait.ep.majorlabB.checker.ColorCheckerThread;
import jp.ac.kanazawait.ep.majorlabB.navigator.Navigator;
import lejos.hardware.Button;
import lejos.robotics.Color;

public class MomiiCar extends AbstCar {

    private Navigator currentNavigator;
    private boolean isUsingRightEdgeTracer = false;
    private int lastColor = Color.NONE; // 前回の色を保存する変数

    public static void main(String[] args) {
        TimeKeeper car = new MomiiCar();
        car.start();
    }

    public MomiiCar() {
        colorChecker = ColorCheckerThread.getInstance();
        driver = new MomiiNaiveDriver("B", "C");
        currentNavigator = new MomiiNaiveLeftEdgeTracer(); // 初期は左エッジ走行
    }

    @Override
    public void run() {
    	
    	 MomiiNaiveRightEdgeTracer  navi_R = new MomiiNaiveRightEdgeTracer();
    	 MomiiNaiveLeftEdgeTracer   navi_L = new MomiiNaiveLeftEdgeTracer();
    	 
    	 
    	 lastColor = colorChecker.getColorId();
        while (!Button.ESCAPE.isDown() && colorChecker.getColorId() != Color.RED) {
        	
            int currentColor = colorChecker.getColorId(); // 現在の色を取得
            if ((lastColor == Color.BLACK || lastColor == Color.WHITE || lastColor == Color.DARK_GRAY) && currentColor == Color.CYAN) {
//            if (lastColor == Color.BLACK && currentColor == Color.CYAN) {

            	
                if (isUsingRightEdgeTracer) {
                    currentNavigator = navi_L;
                } else {
                    currentNavigator = navi_R;
                }
                
                isUsingRightEdgeTracer = !isUsingRightEdgeTracer; // フラグを反転
            	
//            	switchNavigator(); // 黒または白からシアンに変わった時のみナビゲーターを切り替え            
            }
            currentNavigator.decision(colorChecker, driver); // ナビゲーションの判断を実行

            lastColor = currentColor; // 現在の色を前回の色として保存
//
//            try {
//                Thread.sleep(10); // チャタリング防止
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
 

    private void switchNavigator() {
        isUsingRightEdgeTracer = !isUsingRightEdgeTracer; // フラグを反転
        if (isUsingRightEdgeTracer) {
            currentNavigator = new MomiiNaiveRightEdgeTracer();
 //           System.out.println("Switched to Right Edge Tracing");
        } else {
            currentNavigator = new MomiiNaiveLeftEdgeTracer();
 //           System.out.println("Switched to Left Edge Tracing");
        }
    }
}
