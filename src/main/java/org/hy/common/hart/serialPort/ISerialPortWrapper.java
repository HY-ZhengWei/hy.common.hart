package org.hy.common.hart.serialPort;

import java.io.InputStream;
import java.io.OutputStream;





/**
 * 串口的包装类
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-01-24
 * @version     v1.0
 */
public interface ISerialPortWrapper
{
    
    /**
     * 用配置初始化串口
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-26
     * @version     v1.0
     *
     * @param i_Config
     */
    public void init(SerialPortConfig i_Config);
    
    
    
    /**
     * 反向获取串口配置
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-26
     * @version     v1.0
     *
     * @return
     */
    public SerialPortConfig getConfig();
    
    
    
    /**
     * 获取：波特率
     */
    public int getBaudRate();
    
    
    
    /**
     * 获取：数据位
     */
    public int getDataBits();
    
    
    
    /**
     * 获取：停止位
     */
    public int getStopBits();
    
    
    
    /**
     * 获取：奇偶校验
     */
    public int getParity();
    
    
    
    /**
     * 获取：软硬流控制
     */
    public int getFlowControls();
    
    
    
    /**
     * 获取：超时阻塞中的读超时（单位：毫秒）
     */
    public int getReadTimeout();
    
    
    
    /**
     * 获取：超时阻塞中的写超时（单位：毫秒）
     */
    public int getWriteTimeout();
    
    
    
    /**
     * 关闭串口
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-24
     * @version     v1.0
     *
     * @throws Exception
     */
    public void close() throws Exception;

    
    
    /**
     * 打开串口
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-24
     * @version     v1.0
     *
     * @throws Exception
     */
    public void open() throws Exception;
    
    
    
    /**
     * 获取：是否打开连接
     */
    public boolean isOpen();

    
    
    /**
     * 读取字节数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-24
     * @version     v1.0
     *
     * @param io_Buffer     读取到的数据存放的缓存（输入输出类型）
     * @param i_BufferSize  缓存大小 
     * @return              返回读取到的数据实际大小，小于0时表示异常
     */
    public int readBytes(byte[] io_Buffer ,int i_BufferSize);
    
    
    
    /**
     * 写入字节数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-24
     * @version     v1.0
     *
     * @param i_Buffer      写入的数据
     * @param i_BufferSize  数据大小
     * @return              实际写入数据的大小，小于0时表示异常
     */
    public int writeBytes(byte[] i_Buffer ,int i_BufferSize);
    
    
    
    /**
     * 获取输入流
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-24
     * @version     v1.0
     *
     * @return
     */
    public InputStream getInputStream();

    
    
    /**
     * 获取输出流
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-24
     * @version     v1.0
     *
     * @return
     */
    public OutputStream getOutputStream();

}
