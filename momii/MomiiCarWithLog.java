package jp.ac.kanazawait.ep.momii;

import jp.ac.kanazawait.ep.majorlabB.car.AbstCar;
import jp.ac.kanazawait.ep.majorlabB.car.TimeKeeper;
import jp.ac.kanazawait.ep.majorlabB.checker.ColorCheckerThread;
import jp.ac.kanazawait.ep.majorlabB.logger.LoggerThread;
import lejos.hardware.Button;
import lejos.robotics.Color;

public class MomiiCarWithLog extends AbstCar {

	public static void main(String[] args) {
		TimeKeeper car = new MomiiCarWithLog();
		car.start();
	}

	public MomiiCarWithLog() {
		colorChecker = ColorCheckerThread.getInstance();
		driver = new MomiiNaiveDriver("B", "C");
		navigator = new MomiiNaiveLeftEdgeTracer();
		// ログ設定
		logger = LoggerThread.getInstance();
		logger.setCar(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		while (!Button.ESCAPE.isDown() && colorChecker.getColorId() != Color.RED) {
			navigator.decision(colorChecker, driver);
		}
	}

}
