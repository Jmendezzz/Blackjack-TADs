package infraestructure.factory;

import domain.enums.ServerInteraction;
import server.service.PlayerAction;

public interface PlayerActionsFactory {
    PlayerAction createPlayerAction(ServerInteraction action);
}
