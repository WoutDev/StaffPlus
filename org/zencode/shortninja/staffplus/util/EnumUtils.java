package org.zencode.shortninja.staffplus.util;

public class EnumUtils
{
    public static boolean isValidEnum(Class enumClass, String enumName)
    {
        if(enumName == null)
            return false;
        try
        {
            Enum.valueOf(enumClass, enumName);
            return true;
        }
        catch(IllegalArgumentException ex)
        {
            return false;
        }
    }
}
