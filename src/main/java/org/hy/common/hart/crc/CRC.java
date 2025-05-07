package org.hy.common.hart.crc;





/**
 * CRC算法校验的工厂方法类
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-05-07
 * @version     v1.0
 */
public class CRC
{
    
    /** 全局默认参数的、统一的、单例的CRC算法对象 */
    private static ICRC $CRC8  = null;
    
    /** 全局默认参数的、统一的、单例的CRC算法对象 */
    private static ICRC $CRC16 = null;
    
    /** 全局默认参数的、统一的、单例的CRC算法对象 */
    private static ICRC $CRC32 = null;
    
    
    
    /**
     * 获取全局默认参数的、统一的、单例的CRC算法对象
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-05-07
     * @version     v1.0
     *
     * @param i_Type
     * @return
     */
    public static synchronized ICRC getCRC(CRCType i_Type)
    {
        if ( i_Type == null )
        {
            return null;
        }
        else if ( i_Type.equals(CRCType.CRC8) )
        {
            if ( $CRC8 == null )
            {
                $CRC8 = new CRC8();
            }
            return $CRC8;
        }
        else if ( i_Type.equals(CRCType.CRC16) )
        {
            if ( $CRC16 == null )
            {
                $CRC16 = new CRC16();
            }
            return $CRC16;
        }
        else if ( i_Type.equals(CRCType.CRC32) )
        {
            if ( $CRC32 == null )
            {
                $CRC32 = new CRC32();
            }
            return $CRC32;
        }
        else
        {
            return null;
        }
    }
    
    
    
    /**
     * 构建一个新的CRC算法校验
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-05-07
     * @version     v1.0
     *
     * @param i_Type  CRC算法类型
     * @return
     */
    public static ICRC newCRC(CRCType i_Type)
    {
        if ( i_Type == null )
        {
            return null;
        }
        else if ( i_Type.equals(CRCType.CRC8) )
        {
            return new CRC8();
        }
        else if ( i_Type.equals(CRCType.CRC16) )
        {
            return new CRC16();
        }
        else if ( i_Type.equals(CRCType.CRC32) )
        {
            return new CRC32();
        }
        else
        {
            return null;
        }
    }
    
    
    
    private CRC()
    {
        // Nothing.
    }
    
}
