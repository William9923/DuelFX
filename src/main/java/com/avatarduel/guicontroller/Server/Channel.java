package com.avatarduel.guicontroller.Server;

import com.avatarduel.model.type.PlayerType;

public enum Channel {
    DECK,
    HAND,
    FIELD,
    PLAYER_A,
    PLAYER_B;

    public static Channel getChannelFromPlayerType(PlayerType playerType) {
        if(playerType == PlayerType.A) {
            return Channel.PLAYER_A;
        }
        else {
            return Channel.PLAYER_B;
        }
    }
}
