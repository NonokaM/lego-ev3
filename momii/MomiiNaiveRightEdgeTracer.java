package jp.ac.kanazawait.ep.momii;

import jp.ac.kanazawait.ep.majorlabB.checker.ColorChecker;
import jp.ac.kanazawait.ep.majorlabB.driver.MotorDriver;
import jp.ac.kanazawait.ep.majorlabB.navigator.Navigator;
import lejos.robotics.Color;

public class MomiiNaiveRightEdgeTracer implements Navigator {

    /**
     * {@inheritDoc}
     */
    @Override
    public void decision(ColorChecker colorChecker, MotorDriver driver) {
        switch (colorChecker.getColorId()) {
        case Color.WHITE:
            driver.turnLeft();   // 白色を検出した場合、左に曲がる
            driver.forward();    // 前進する
            break;
        case Color.BLACK:
            driver.turnRight();  // 黒色を検出した場合、右に曲がる
            driver.forward();    // 前進する
            break;
        default:
            driver.goStraight(); // それ以外の色の場合、直進する
            driver.forward();
        }
    }

}
