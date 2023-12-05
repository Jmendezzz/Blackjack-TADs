package server.service;

import domain.enums.PlayerInteraction;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PlayerActionImpl implements PlayerAction {
    private final ObjectOutputStream objectOut;

    public PlayerActionImpl(ObjectOutputStream objectOut) {
        this.objectOut = objectOut;
    }

    @Override
    public void execute() {
        PlayerInteraction[] playerInteractions = PlayerInteraction.values();
        int opt = JOptionPane.showOptionDialog(null, "What do you want to do?",
                "Your turn", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, playerInteractions, playerInteractions[0]);
        if (opt == 0) {
            try {
                String playerInteraction = PlayerInteraction.HIT.toString();
                objectOut.writeObject(playerInteraction);
                objectOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            try {
                String playerInteraction = PlayerInteraction.STAND.toString();
                objectOut.writeObject(playerInteraction);
                objectOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
