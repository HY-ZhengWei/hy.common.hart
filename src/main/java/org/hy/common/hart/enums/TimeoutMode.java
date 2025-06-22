package org.hy.common.hart.enums;

import com.fazecast.jSerialComm.SerialPort;





/**
 * 超时阻塞的枚举
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-06-22
 * @version     v1.0
 */
public enum TimeoutMode
{
    
    // port.setComPortTimeouts(TIMEOUT_NONBLOCKING, 0, 0);
    NonBlocking     (SerialPort.TIMEOUT_NONBLOCKING        ,"非阻塞模式（立即返回）"),
    
    // 平衡实时性和效率，适合不确定数据到达时间的场景
    // port.setComPortTimeouts(TIMEOUT_READ_SEMI_BLOCKING, 最长读时间（毫秒）, 0);
    ReadSemiBlocking(SerialPort.TIMEOUT_READ_SEMI_BLOCKING ,"半阻塞读取模式（混合行为）"),
    
    // 读取操作会一直阻塞，直到 请求的字节数全部接收 或 超时
    ReadBlocking    (SerialPort.TIMEOUT_READ_BLOCKING      ,"完全阻塞读取模式"),
               
    // 写入操作会阻塞，直到 所有数据发送完成 或 超时
    // port.setComPortTimeouts(TIMEOUT_WRITE_BLOCKING, 0, 最长写时间（毫秒）);
    WriteBlocking   (SerialPort.TIMEOUT_WRITE_BLOCKING     ,"阻塞写入模式"),
               
    // 针对特定设备（如条码扫描仪）优化
    TIMEOUT_SCANNER (SerialPort.TIMEOUT_SCANNER            ,"扫描仪模式（特殊用途）"),
    
    ;
    
    
    
    /** 值 */
    private Integer value;
    
    /** 描述 */
    private String  comment;
    
    
    
    /**
     * 数值转为常量
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-07-29
     * @version     v1.0
     *
     * @param i_Value
     * @return
     */
    public static TimeoutMode get(Integer i_Value)
    {
        if ( i_Value == null )
        {
            return null;
        }
        
        for (TimeoutMode v_Enum : TimeoutMode.values())
        {
            if ( v_Enum.value.equals(i_Value) )
            {
                return v_Enum;
            }
        }
        
        return null;
    }
    
    
    
    TimeoutMode(Integer i_Value ,String i_Comment)
    {
        this.value   = i_Value;
        this.comment = i_Comment;
    }

    
    
    public Integer getValue()
    {
        return this.value;
    }
    
    
    
    public String getComment()
    {
        return this.comment;
    }
    
    

    public String toString()
    {
        return this.value + "";
    }
    
}
