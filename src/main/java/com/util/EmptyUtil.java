package com.util;

import java.util.Collection;
import java.util.Map;

public class EmptyUtil {

	public static boolean isEmpty(Object o)
    {
        if (o == null)
        {
            return true;
        }
        else if (o instanceof String && ((String) o).trim().length() == 0)
        {
            return true;
        }
        else if (o instanceof String && ((String) o).equalsIgnoreCase("NULL"))
        {
            return true;
        }
        else if (o instanceof Collection && ((Collection<?>) o).isEmpty())
        {
            return true;
        }
        else if (o instanceof Map && ((Map<?, ?>) o).isEmpty())
        {
            return true;
        }
        else if (o instanceof Object[] && ((Object[]) o).length == 0)
        {
            return true;
        }
        else if (o instanceof Character && (Character) o == ' ')
        {
            return true;
        }
        return false;
    }
}
