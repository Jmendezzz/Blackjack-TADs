package infraestructure.factory.impl;

import domain.enums.ServerInteraction;
import infraestructure.factory.PlayerActionsFactory;
import server.service.PlayerAction;
import server.service.PlayerActionImpl;

import java.io.ObjectOutputStream;

public class PlayerFactoryImpl implements PlayerActionsFactory {
    private final ObjectOutputStream objectOut;

    public PlayerFactoryImpl(ObjectOutputStream objectOut) {
        this.objectOut = objectOut;
    }

    @Override
    public PlayerAction createPlayerAction(ServerInteraction action) {
        switch (action) {
            case TURN:
                return new PlayerActionImpl(objectOut);
            default:
                return null;
        }
    }
}
