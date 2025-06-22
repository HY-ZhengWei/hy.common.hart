package org.hy.common.hart.enums;

import com.fazecast.jSerialComm.SerialPort;





/**
 * 软件、硬件流控制的枚举
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-06-22
 * @version     v1.0
 */
public enum FlowControl
{
    
    Disabled   (SerialPort.FLOW_CONTROL_DISABLED            ,"禁用流控制，默认配置，适合大多数情况"),
               
    // 通常 RTS 和 CTS 需同时启用（FLOW_CONTROL_RTS_ENABLED | FLOW_CONTROL_CTS_ENABLED）
    CTS        (SerialPort.FLOW_CONTROL_CTS_ENABLED         ,"硬件流控制（需设备支持）：Clear to Send"),
    
    RTS        (SerialPort.FLOW_CONTROL_RTS_ENABLED         ,"硬件流控制（需设备支持）：Request to Send"),
               
    DSR        (SerialPort.FLOW_CONTROL_DSR_ENABLED         ,"硬件流控制（需设备支持）：Data Set Ready"),
               
    DTR        (SerialPort.FLOW_CONTROL_DTR_ENABLED         ,"硬件流控制（需设备支持）：Data Terminal Ready"),
    
    XONXOFF_In (SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED  ,"软件流控制（无需硬件，但仅适用于文本协议）：启用输入方向的软件流控制"),
    
    XONXOFF_Out(SerialPort.FLOW_CONTROL_XONXOFF_OUT_ENABLED ,"软件流控制（无需硬件，但仅适用于文本协议）：启用输出方向的软件流控制"),
    
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
    public static FlowControl get(Integer i_Value)
    {
        if ( i_Value == null )
        {
            return null;
        }
        
        for (FlowControl v_Enum : FlowControl.values())
        {
            if ( v_Enum.value.equals(i_Value) )
            {
                return v_Enum;
            }
        }
        
        return null;
    }
    
    
    
    FlowControl(Integer i_Value ,String i_Comment)
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
