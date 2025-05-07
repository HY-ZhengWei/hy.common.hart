package org.hy.common.hart.crc;





/**
 * CRC算法校验接口
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-05-07
 * @version     v1.0
 */
public interface ICRC
{
    
    /**
     * 初始化多项式及初始值（可选配置）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-05-07
     * @version     v1.0
     *
     * @param i_Polynomial  多项式的值
     * @param i_InitValue   初始化的值
     */
    public void initPolynomial(int i_Polynomial ,int i_InitValue);
    
    
    
    /**
     * 计算CRC算法的校验位
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-05-07
     * @version     v1.0
     *
     * @param i_Packet      数据报文
     * @param i_BeginIndex  数据报文的开始位置，包含开始位置，下标从0开始。
     * @param i_BeginIndex  数据报文的结束位置，小于结束位置。
     * @return              返回校验位
     */
    public byte[] calcCRC(byte[] i_Packet ,int i_BeginIndex ,int i_EndIndex);
    
    
    
    /**
     * 获取：多项式
     */
    public int getPolynomial();
    

    
    /**
     * 获取：初始值
     */
    public int getInitValue();
    
}
