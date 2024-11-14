package com.github.vanily.authentication.login.constants;

import com.github.vanily.core.color.ColorUtil;

public class MessageConstants {

    public final static String REGISTER_SYNTAX_ERROR = String.format("%sComando incorreto! Digite '/registrar <senha> <senha>'", ColorUtil.RED_COLOR);
    public final static String PASSWORD_ARE_NOT_EQUALS = String.format("%sAs senhas não coincidem.", ColorUtil.RED_COLOR);
    public final static String REGISTERED_SUCCESSFULLY = String.format("%sVocê registrou-se com sucesso", ColorUtil.GREEN_COLOR);
    public final static String ALREADY_REGISTERED = String.format("%sVocê já está registrado.", ColorUtil.RED_COLOR);
    public final static String ALREADY_LOGGED = String.format("%sVocê já está logado.", ColorUtil.RED_COLOR);
    public final static String LOGIN_SYNTAX_ERROR = String.format("%sComando incorreto! Digite '/logar <senha>'", ColorUtil.RED_COLOR);
    public final static String LOGIN_WRONG_PASSWORD = String.format("%sA senha que inseriu é incorreta.", ColorUtil.RED_COLOR);
    public final static String LOGGED_SUCCESSFULLY = String.format("%sVocê logou com sucesso.", ColorUtil.GREEN_COLOR);
    public final static String PLAYER_JOIN_TITLE = String.format("%s%sVANILY", ColorUtil.BLUE_COLOR, "<bold>");
    public final static String PLAYER_LOGIN_SUBTITLE = String.format("%sUtilize: /logar <senha>", ColorUtil.WHITE_COLOR);
    public final static String PLAYER_REGISTER_SUBTITLE = String.format("%sUtilize: /registrar <senha> <senha>", ColorUtil.WHITE_COLOR);
    public final static String REDIRECT_TO_LOBBY = String.format("%sVocê será redirecionado em instantes para o lobby.", ColorUtil.BLUE_COLOR);
    public final static String LOGGED_SUCCESSFULLY_TITLE = String.format("%s%sVANILY", ColorUtil.BLUE_COLOR, "<bold>");
    public final static String LOGGED_SUCCESSFULLY_SUBTITLE = String.format("%sLogado com sucesso.", ColorUtil.WHITE_COLOR);
    public final static String REGISTERED_SUCCESSFULLY_TITLE = String.format("%s%sVANILY", ColorUtil.BLUE_COLOR, "<bold>");
    public final static String REGISTERED_SUCCESSFULLY_SUBTITLE = String.format("%sRegistrado com sucesso.", ColorUtil.WHITE_COLOR);

}
