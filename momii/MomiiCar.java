package jp.ac.kanazawait.ep.momii;

import jp.ac.kanazawait.ep.majorlabB.car.AbstCar;
import jp.ac.kanazawait.ep.majorlabB.car.TimeKeeper;
import jp.ac.kanazawait.ep.majorlabB.checker.ColorCheckerThread;
import jp.ac.kanazawait.ep.majorlabB.navigator.Navigator;  // Navigatorインターフェースのインポート
import lejos.hardware.Button;
import lejos.robotics.Color;

//public class MomiiCar extends AbstCar {
//
//	public static void main(String[] args) {
//		TimeKeeper car = new MomiiCar();
//		car.start();
//	}
//
//	public MomiiCar() {
//		colorChecker = ColorCheckerThread.getInstance();
//		driver = new MomiiNaiveDriver("B", "C");
//		navigator = new MomiiNaiveRightEdgeTracer();
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void run() {
//		while (!Button.ESCAPE.isDown() && colorChecker.getColorId() != Color.RED) {
//			navigator.decision(colorChecker, driver);
//		}
//	}
//
//}

public class MomiiCar extends AbstCar {

    private Navigator currentNavigator;
    private boolean isUsingRightEdgeTracer = true;  // 初期状態は右エッジ追従

    public static void main(String[] args) {
        TimeKeeper car = new MomiiCar();
        car.start();
    }

    public MomiiCar() {
        colorChecker = ColorCheckerThread.getInstance();
        driver = new MomiiNaiveDriver("B", "C");
        currentNavigator = new MomiiNaiveRightEdgeTracer();  // 初期ナビゲーターは右エッジ追従
    }

    /**
     * 走行メソッドをオーバーライドして、ナビゲーションの切り替え機能を追加
     */
//    @Override
//    public void run() {
//        while (!Button.ESCAPE.isDown() && colorChecker.getColorId() != Color.RED) {
//            if (Button.ENTER.isDown()) {
//                switchNavigator();  // ナビゲーター切り替え
//                while(Button.ENTER.isDown()) { // ENTERボタンがリリースされるまで待つ
//                    // 何もしない、ただ待つ
//                }
//            }
//            currentNavigator.decision(colorChecker, driver);
//        }
//    }
    @Override
    public void run() {
        while (!Button.ESCAPE.isDown() && colorChecker.getColorId() != Color.RED) {
            if (colorChecker.getColorId() == Color.CYAN) { // シアン色を検知した場合
                switchNavigator();  // ナビゲーター切り替え
                while (colorChecker.getColorId() == Color.CYAN) {
                    // シアン色が続いている間は何もしない（チャタリング防止）
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            currentNavigator.decision(colorChecker, driver);
        }
    }

    /**
     * 現在のナビゲーターを切り替える
     */
    private void switchNavigator() {
        isUsingRightEdgeTracer = !isUsingRightEdgeTracer;  // フラグを反転
        if (isUsingRightEdgeTracer) {
            currentNavigator = new MomiiNaiveRightEdgeTracer();
            System.out.println("Switched to Right Edge Tracing");
        } else {
            currentNavigator = new MomiiNaiveLeftEdgeTracer();
            System.out.println("Switched to Left Edge Tracing");
        }
    }
}
