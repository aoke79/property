package com.sinoframe.common.util;

import java.text.MessageFormat;
import java.util.*;

public class Messages {
	public Messages()
    {
    }

    public static String getMessage(Locale locale, String key)
    {
        if(locale == null)
            locale = defaultLocale;
        String msg = null;
        String formatKey = messageKey(locale, key);
        synchronized(formats)
        {
            msg = (String)formats.get(formatKey);
            if(msg == null)
            {
                ResourceBundle myResources = ResourceBundle.getBundle("AppMessage", locale);
                msg = myResources.getString(key);
                formats.put("formatKey", msg);
            }
        }
        return msg;
    }

    public static String getMessage(Locale locale, String key, Object args[])
    {
        MessageFormat format = new MessageFormat(getMessage(locale, key));
        return format.format(((Object) (args)));
    }

    public static String getMessage(Locale locale, String key, Object arg0)
    {
        Object args[] = new Object[1];
        args[0] = arg0;
        return getMessage(locale, key, args);
    }

    public static String getMessage(Locale locale, String key, Object arg0, Object arg1)
    {
        Object args[] = new Object[2];
        args[0] = arg0;
        args[1] = arg1;
        return getMessage(locale, key, args);
    }

    public static String getMessage(Locale locale, String key, Object arg0, Object arg1, Object arg2)
    {
        Object args[] = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        return getMessage(locale, key, args);
    }

    public static String getMessage(Locale locale, String key, Object arg0, Object arg1, Object arg2, Object arg3)
    {
        Object args[] = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        return getMessage(locale, key, args);
    }

    protected static String localeKey(Locale locale)
    {
        if(locale == null)
            return "";
        else
            return locale.toString();
    }

    protected static String messageKey(Locale locale, String key)
    {
        return localeKey(locale) + "." + key;
    }

    static Locale defaultLocale = Locale.getDefault();
    static HashMap formats = new HashMap();

}
