package com.github.vanily.core.messages;

import com.github.vanily.core.color.ColorUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GeneralMessageConstants {

    public String INTERNAL_ERROR = String.format("%sOcorreu um erro inesperado.", ColorUtil.RED_COLOR);

}
