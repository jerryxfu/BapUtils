package net.jerrydev.baputils.utils;

import net.jerrydev.baputils.Constants;

import java.text.Normalizer;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.repeat;

public final class ChatUtils {
    public static String cc(CCodes color, String message) {
        return color.colorCode + message + CCodes.RESET.colorCode;
    }

    public static String cc(List<CCodes> colorsL, String message) {
        final StringBuilder colors = new StringBuilder();

        for (final CCodes color : colorsL) {
            colors.append(color.colorCode);
        }

        return colors + message + CCodes.RESET.colorCode;
    }

    public static String cc(CCodes color, String message, boolean addReset) {
        return color.colorCode + message + (addReset ? CCodes.RESET.colorCode : "");
    }

    public static String autoTF(String str) {
        return str.replaceAll("true", cc(CCodes.GREEN, "true"))
            .replaceAll("false", cc(CCodes.RED, "false"))
            .replaceAll("null", cc(CCodes.GRAY, "null"));
    }

    /**
     * RGBfy a string with 12 colors
     *
     * @param message Input string
     * @return RGBfied message
     */
    public static String RGBfy(String message) {
        final CCodes[] rgbCCodes = {CCodes.DARK_RED, CCodes.RED, CCodes.GOLD, CCodes.YELLOW, CCodes.GREEN, CCodes.DARK_GREEN,
            CCodes.AQUA, CCodes.DARK_AQUA, CCodes.BLUE, CCodes.DARK_BLUE, CCodes.LIGHT_PURPLE, CCodes.DARK_PURPLE};

        final StringBuilder rgbMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            final char character = message.charAt(i);
            final CCodes color = rgbCCodes[i % rgbCCodes.length];
            rgbMessage.append(color.colorCode).append(character);
        }

        return rgbMessage + CCodes.RESET.colorCode;
    }

    /**
     * Rainbowify a string with 7 colors
     *
     * @param message Input string
     * @return rainbowified message
     */
    public static String rainbowify(String message) {
        final CCodes[] rainbowCCodes = {CCodes.RED, CCodes.GOLD, CCodes.YELLOW, CCodes.GREEN, CCodes.BLUE, CCodes.DARK_BLUE, CCodes.DARK_PURPLE};

        final StringBuilder rainbowMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            final char character = message.charAt(i);
            final CCodes color = rainbowCCodes[i % rainbowCCodes.length];
            rainbowMessage.append(color.colorCode).append(character);
        }

        return rainbowMessage + CCodes.RESET.colorCode;
    }

    /**
     * Removes Minecraft color control codes, removes Hypixel rank prefixes, and applies character normalization.
     *
     * @param message Message to clean
     * @return Cleaned string
     */
    public static String cleanString(String message) {
        String cleanMessage = stripColorCodes(message);
        cleanMessage = removeHypixelRanks(cleanMessage);
        Normalizer.normalize(cleanMessage, Normalizer.Form.NFKD);
        return cleanMessage;
    }

    /**
     * Removes Minecraft color control codes from a string.
     *
     * @param message Message to clean
     * @return Cleaned string
     */
    public static String stripColorCodes(String message) {
        return message.replaceAll(Constants.kColorCodeP, "");
    }

    /**
     * Removes Hypixel's rank prefixes from a string.
     *
     * @param message Message to clean
     * @return Cleaned string
     */
    public static String removeHypixelRanks(String message) {
        return message.replaceAll(Constants.kHypixelRankP + "(" + Constants.kMcUserP + ")", "$1");
    }

    public static String center(String str, int totalWidth) {
        final String cleanStr = stripColorCodes(str);

        // Calculate the pad length
        final int padLength = (totalWidth - cleanStr.length()) / 2;

        // Use leftPad to center the string
        return leftPad(str, cleanStr.length() + padLength);
    }

    /**
     * @author org.apache.commons.lang3.StringUtils
     */
    public static String leftPad(String str, int size) {
        return leftPad(str, size, " ");
    }

    /**
     * @author org.apache.commons.lang3.StringUtils
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        } else {
            final int pads = size - stripColorCodes(str).length();
            if (pads <= 0) {
                return str;
            } else {
                return (pads > 8192) ? leftPad(str, size, String.valueOf(padChar)) : repeat(padChar, pads).concat(str);
            }
        }
    }

    /**
     * @author org.apache.commons.lang3.StringUtils
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        } else {
            if (isEmpty(padStr)) {
                padStr = " ";
            }

            final int padLen = padStr.length();
            final int strLen = stripColorCodes(str).length();
            final int pads = size - strLen;
            if (pads <= 0) {
                return str;
            } else if ((padLen == 1) && (pads <= 8192)) {
                return leftPad(str, size, padStr.charAt(0));
            } else if (pads == padLen) {
                return padStr.concat(str);
            } else if (pads < padLen) {
                return padStr.substring(0, pads).concat(str);
            } else {
                final char[] padding = new char[pads];
                final char[] padChars = padStr.toCharArray();

                for (int i = 0; i < pads; ++i) {
                    padding[i] = padChars[i % padLen];
                }

                return new String(padding).concat(str);
            }
        }
    }

    public enum CCodes {
        BLACK("§0"),
        DARK_BLUE("§1"),
        DARK_GREEN("§2"),
        DARK_AQUA("§3"),
        DARK_RED("§4"),
        DARK_PURPLE("§5"),
        GOLD("§6"),
        GRAY("§7"),
        DARK_GRAY("§8"),
        BLUE("§9"),
        GREEN("§a"),
        AQUA("§b"),
        RED("§c"),
        LIGHT_PURPLE("§d"),
        YELLOW("§e"),
        WHITE("§f"),
        OBFUSCATED("§k"),
        BOLD("§l"),
        STRIKETHROUGH("§m"),
        UNDERLINE("§n"),
        ITALIC("§o"),
        RESET("§r");

        public final String colorCode;

        CCodes(String _codeCode) {
            this.colorCode = _codeCode;
        }
    }
}
