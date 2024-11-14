package com.github.vanily.lobby.constants;

import com.github.vanily.core.color.ColorUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageConstants {

    public String PLAYER_NOT_FOUND = String.format("%sJogador não encontrado!", ColorUtil.RED_COLOR);

    public String BUILDER_ON = String.format("%sModo construtor foi ativado.", ColorUtil.GREEN_COLOR);
    public String BUILDER_OFF = String.format("%sModo construtor foi desativado.", ColorUtil.GREEN_COLOR);
    public String BUILDER_ACTIONBAR = String.format("%sAtive o modo construtor digitando /build", "<#f7d38b>");

    public String WELCOME_TITLE = String.format("%s%sVANILY", "<#1ce8bf>", "<bold>");
    public String WELCOME_SUBTITLE = String.format("%sBoas-vindas!", "<#c4f5eb>");

    public String PLAYERS_DISABLED = String.format("%sVocê agora não está mais vendo os jogadores.", ColorUtil.RED_COLOR);
    public String PLAYERS_ACTIVED = String.format("%sVocê agora está vendo os jogadores.", ColorUtil.GREEN_COLOR);

}
