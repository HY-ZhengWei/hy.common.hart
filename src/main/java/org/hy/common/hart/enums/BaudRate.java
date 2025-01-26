package org.hy.common.hart.enums;

import java.util.Arrays;
import java.util.List;





/**
 * 波特率
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-01-24
 * @version     v1.0
 */
public class BaudRate
{
    
    /** 波特率 */
    public static final List<Integer> $BaudRates = Arrays.asList(
                                                     110
                                                    ,300
                                                    ,600
                                                    ,1200
                                                    ,2400
                                                    ,4800
                                                    ,9600
                                                    ,14400
                                                    ,19200
                                                    ,38400
                                                    ,56000
                                                    ,57600
                                                    ,115200
                                                    ,128000
                                                    ,256000);
    
    
    
    private BaudRate()
    {
        // Nothing.
    }
    
}
