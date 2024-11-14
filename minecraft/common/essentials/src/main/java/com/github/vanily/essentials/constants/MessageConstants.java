package com.github.vanily.essentials.constants;

import com.github.vanily.core.color.ColorUtil;
import lombok.experimental.UtilityClass;

import java.awt.*;

@UtilityClass
public class MessageConstants {

    public String PLAYER_NOT_FOUND = String.format("%sJogador não encontrado!", ColorUtil.RED_COLOR);
    public String CLEARCHAT_NOTIFY = String.format("%sO chat foi limpo por {player}.", ColorUtil.GREEN_COLOR);

    public String INVSEE_COMMAND_SYNTAX_ERROR = String.format("%sComando incorreto! Digite '/invsee <player>'", ColorUtil.RED_COLOR);
    public String CANNOT_OPEN_OWN_INVENTORY = String.format("%sVocê não pode abrir o seu próprio inventário!", ColorUtil.RED_COLOR);
    public String OPENED_TARGET_INVENTORY = String.format("%sVocê abriu o inventário de {player}.", ColorUtil.GREEN_COLOR);

    public String GAMEMODE_COMMAND_SYNTAX_ERROR = String.format("%sComando incorreto! Digite '/gamemode <modo> <player>'", ColorUtil.RED_COLOR);
    public String GAMEMODE_NON_EXISTENT = String.format("%sModo inexistente! Os modos válidos são 0, 1, 2 e 3.", ColorUtil.RED_COLOR);
    public String GAMEMODE_PLAYER_CHANGED = String.format("%sO modo de jogo do {player} foi alterado para {mode}.", ColorUtil.GREEN_COLOR);
    public String GAMEMODE_CHANGED = String.format("%sO modo de jogo foi alterado para {mode}.", ColorUtil.GREEN_COLOR);

    public String LIGHT_ON = String.format("%sVocê ativou a sua lanterna com sucesso.", ColorUtil.GREEN_COLOR);
    public String LIGHT_OFF = String.format("%sVocê desativou a sua lanterna com sucesso.", ColorUtil.GREEN_COLOR);

    public String SET_SPAWN_SET = String.format("%sVocê setou a localização do spawn com sucesso.", ColorUtil.GREEN_COLOR);
    public String SET_PLOTS_SET = String.format("%sVocê setou a localização dos terrenos com sucesso.", ColorUtil.GREEN_COLOR);

    public String FLY = String.format("%sVocê {state} o fly do jogador {player}.", ColorUtil.GREEN_COLOR);

    public String SPAWN_TELEPORT = String.format("%sSeja bem-vindo ao spawn do servidor",ColorUtil.GREEN_COLOR);
    public String PLOTS_TELEPORT = String.format("%sSeja bem-vindo aos terrenos do servidor",ColorUtil.GREEN_COLOR);

    public String NEED_ITEM = String.format("%sVocê tem que ter o item na mão.", ColorUtil.RED_COLOR);
    public String NAMED_ITEM = String.format("%sVocê alterou o nome do item com sucesso.", ColorUtil.GREEN_COLOR);
    public String LORE_ITEM = String.format("%sVocê adicionou uma linha de lore ao item com sucesso.", ColorUtil.GREEN_COLOR);
    public String LORE_CLEAR = String.format("%sVocê limpou a lore do item com sucesso.", ColorUtil.GREEN_COLOR);
    public String INVALID_LINE = String.format("%sEsta linha é invalida.", ColorUtil.RED_COLOR);
    public String SKULL = String.format("%sVocê recebeu a skull com sucesso.", ColorUtil.GREEN_COLOR);
    public String SET_LORE = String.format("%sVocê alterou com sucesso a lore deste item.", ColorUtil.GREEN_COLOR);
}
