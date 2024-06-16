package jp.ac.kanazawait.ep.momii;

import jp.ac.kanazawait.ep.majorlabB.driver.AbstDriver;

/**
 * 堅実な走行を行うドライバークラス
 * @author mmotoki
 *
 */
public class MomiiNaiveDriver extends AbstDriver {

	final int speedNormal = 300;
	final int speedSyan = 140;
	final int speedLow = 80;

	/**
	 * 左モーターを "B" に，右モーターを "C" に接続した状態のコンストラクタ
	 */
	public MomiiNaiveDriver() {
		this("B", "C");
	}

	/**
	 *
	 * @param portLeft	左モーターを接続したポート（"A"～"D"の４つのいずれか）
	 * @param portRight	右モーターを接続したポート（"A"～"D"の４つのいずれか）
	 */
	public MomiiNaiveDriver(String portLeft, String portRight) {
		setMotor(portLeft, portRight);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void goStraight() {
		setSpeed(speedNormal);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnLeft() {
		setSpeed(speedLow, speedNormal);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnRight() {
		setSpeed(speedNormal, speedLow);
	}
	
    // シアン色検出時の速度設定
    public void adjustSpeedForCyanDetection() {
        setSpeed(speedSyan); // シアン色を検出した際の速度を低速に設定
    }

    // 通常速度へのリセット
    public void resetSpeedToNormal() {
        setSpeed(speedNormal); // シアン色以外が検出された際に通常速度に戻す
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnLeftSharply() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnRightSharply() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnLeftGently() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnRightGently() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void increaseSpeed() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void decreaseSpeed() {
		// 実装する場合は，次の throw を消す
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + "ではこのメソッドを実装していません");
	}

}
