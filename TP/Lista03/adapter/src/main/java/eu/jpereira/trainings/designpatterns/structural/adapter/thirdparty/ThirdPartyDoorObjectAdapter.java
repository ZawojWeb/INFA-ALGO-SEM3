package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
// import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.DoorState;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.LockStatus;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {

	ThirdPartyDoor door = new ThirdPartyDoor();

	@Override
	public void open(String code) throws IncorrectDoorCodeException {
		// TODO Auto-generated method stub
		if (!isOpen()) {
			try {
				door.unlock(code);
				if (door.getLockStatus().equals(LockStatus.UNLOCKED)) {
					door.setState(DoorState.OPEN);
				}

			} catch (CannotUnlockDoorException e) {
				// TODO Auto-generated catch block
				throw new IncorrectDoorCodeException();
			} catch (CannotChangeStateOfLockedDoor e) {
				// TODO Auto-generated catch block
				throw new IncorrectDoorCodeException();
			}
		}
	}

	@Override
	public void close() {
		try {
			if (isOpen()) {
				// TODO Auto-generated method stub
				door.setState(DoorState.CLOSED);
			}
		} catch (CannotChangeStateOfLockedDoor e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		if (door.getState().equals(DoorState.OPEN)) {
			return true;
		}
		return false;
	}

	@Override
	public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException,
			CodeMismatchException {
		// TODO Auto-generated method stub
		try {
			if (door.getLockStatus().equals(LockStatus.LOCKED)) {
				door.unlock(oldCode);
			}
			if (!newCode.equals(newCodeConfirmation)) {
				door.lock();
			}

			door.setNewLockCode(newCode);
		} catch (CannotChangeCodeForUnlockedDoor e) {
			// TODO Auto-generated catch block
			throw new CodeMismatchException();
		} catch (CannotUnlockDoorException e) {
			// TODO Auto-generated catch block
			throw new IncorrectDoorCodeException();
		}

	}

	@Override
	public boolean testCode(String code) {
		// TODO Auto-generated method stub
		try {
			door.setNewLockCode(code);
			return true;
		} catch (CannotChangeCodeForUnlockedDoor e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

}

