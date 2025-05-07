package org.hy.common.hart.crc;





/**
 * CRC算法校验的类型的枚举
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-05-07
 * @version     v1.0
 */
public enum CRCType
{
    
    CRC8 ("CRC8"  ,"8位校验码"),
    
    CRC16("CRC16" ,"16位校验码"),
    
    CRC32("CRC32" ,"32位校验码"),
    
    ;
    
    
    
    /** 值 */
    private String  value;
    
    /** 描述 */
    private String  comment;
    
    
    
    /**
     * 数值转为常量
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-02-11
     * @version     v1.0
     *
     * @param i_Value
     * @return
     */
    public static CRCType get(String i_Value)
    {
        if ( i_Value == null )
        {
            return null;
        }
        
        String v_Value = i_Value.trim();
        for (CRCType v_Enum : CRCType.values())
        {
            if ( v_Enum.value.equalsIgnoreCase(v_Value) )
            {
                return v_Enum;
            }
        }
        
        return null;
    }
    
    
    
    CRCType(String i_Value ,String i_Comment)
    {
        this.value   = i_Value;
        this.comment = i_Comment;
    }

    
    
    public String getValue()
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
