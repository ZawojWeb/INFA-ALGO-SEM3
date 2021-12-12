package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            super.unlock(code);
            super.setState(DoorState.OPEN);
        }
        catch (CannotUnlockDoorException ex) {
            throw new IncorrectDoorCodeException(ex);
        }
        catch (CannotChangeStateOfLockedDoor ex) {
            throw new IncorrectDoorCodeException(ex);
        }        
    }

    @Override
    public void close() {
        try {
            super.setState(DoorState.CLOSED);
            super.lock();
        }
        catch (CannotChangeStateOfLockedDoor ex) {
            System.out.print(ex);
        }

    }

    @Override
    public boolean isOpen() {
        if (super.getState() == DoorState.OPEN)
            return true;
        else if (super.getState() == DoorState.CLOSED)
            return false;
        else
            return false;

    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        try {
            super.unlock(oldCode);

            if (newCode != newCodeConfirmation)
                throw new CodeMismatchException();
            else
                super.setNewLockCode(newCodeConfirmation);
        }
        catch (CannotUnlockDoorException ex) {
            throw new IncorrectDoorCodeException(ex);
        }
        catch (CannotChangeCodeForUnlockedDoor ex) {
            throw new IncorrectDoorCodeException(ex);
        }


    }

    @Override
    public boolean testCode(String code) {
        try {
            super.unlock(code);
        }
        catch (CannotUnlockDoorException ex){
            return false;
        }
        return true;
    }
    
}
