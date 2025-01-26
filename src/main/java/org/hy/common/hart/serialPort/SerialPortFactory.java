package org.hy.common.hart.serialPort;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hy.common.Help;
import org.hy.common.xml.log.Logger;

import com.fazecast.jSerialComm.SerialPort;





/**
 * 串口的工厂类
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-01-26
 * @version     v1.0
 */
public class SerialPortFactory
{
    
    private static final Logger $Logger = new Logger(SerialPortWrapperDefault.class);
    
    /** 串口缓存 */
    private static final Map<String ,ISerialPortWrapper> $SerialPorts = new HashMap<String ,ISerialPortWrapper>();
    
    
    
    /**
     * 获取串口的包装类
     * 
     *   不创建，仅获取已创建过的。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-26
     * @version     v1.0
     *
     * @param i_CommPortName  串口名称
     * @return          当发现串口设备不存时，将删除串口缓存，并且返回NULL
     */
    public static synchronized ISerialPortWrapper get(String i_CommPortName)
    {
        if ( Help.isNull(i_CommPortName) )
        {
            return null;
        }
        
        if ( !checkCommPortName(i_CommPortName) )
        {
            $Logger.warn("串口设备已不存：" + i_CommPortName);
            $SerialPorts.remove(i_CommPortName);
            return null;
        }
        
        ISerialPortWrapper v_SerialPortWrapper = $SerialPorts.get(i_CommPortName);
        if ( v_SerialPortWrapper == null )
        {
            $Logger.warn("请先连接串口设备：" + i_CommPortName);
            return null;
        }
        
        return v_SerialPortWrapper;
    }
    
    
    
    /**
     * 获取串口的包装类
     * 
     *   不创建，仅获取已创建过的。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-26
     * @version     v1.0
     *
     * @param i_CommPortName  串口名称
     * @return          当发现串口设备不存时，将删除串口缓存，并且返回NULL
     */
    @SuppressWarnings("unchecked")
    public static synchronized <E extends ISerialPortWrapper> E get(String i_CommPortName ,Class<E> i_Wrapper)
    {
        if ( Help.isNull(i_CommPortName) )
        {
            return null;
        }
        
        if ( !checkCommPortName(i_CommPortName) )
        {
            $Logger.warn("串口设备已不存：" + i_CommPortName);
            $SerialPorts.remove(i_CommPortName);
            return null;
        }
        
        E v_Wrapper = (E) $SerialPorts.get(i_CommPortName);
        if ( v_Wrapper == null )
        {
            $Logger.warn("请先连接串口设备：" + i_CommPortName);
            return null;
        }
        
        return v_Wrapper;
    }
    
    
    
    /**
     * 获取串口的包装类
     * 
     *   单例模式：即已创建过的，并且串口存在的，不重复创建
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-26
     * @version     v1.0
     *
     * @param i_Config  连接信息
     * @return          当发现串口设备不存时，将删除串口缓存，并且返回NULL
     */
    public static synchronized ISerialPortWrapper get(SerialPortConfig i_Config)
    {
        if ( i_Config == null || Help.isNull(i_Config.getCommPortName()) )
        {
            return null;
        }
        
        if ( !checkCommPortName(i_Config.getCommPortName()) )
        {
            $Logger.warn("串口设备已不存：" + i_Config.getCommPortName());
            $SerialPorts.remove(i_Config.getCommPortName());
            return null;
        }
        
        ISerialPortWrapper v_SerialPortWrapper = $SerialPorts.get(i_Config.getCommPortName());
        if ( v_SerialPortWrapper == null )
        {
            v_SerialPortWrapper = new SerialPortWrapperDefault(i_Config);
            $SerialPorts.put(i_Config.getCommPortName() ,v_SerialPortWrapper);
        }
        
        return v_SerialPortWrapper;
    }
    
    
    
    /**
     * 获取串口的包装类
     * 
     *   单例模式：即已创建过的，并且串口存在的，不重复创建
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-26
     * @version     v1.0
     *
     * @param i_Config   连接信息
     * @param i_Wrapper  指定串口的包装类
     * @return           当发现串口设备不存时，将删除串口缓存，并且返回NULL
     */
    @SuppressWarnings("unchecked")
    public static synchronized <E extends ISerialPortWrapper> E get(SerialPortConfig i_Config ,Class<E> i_Wrapper)
    {
        if ( i_Config == null || Help.isNull(i_Config.getCommPortName()) )
        {
            return null;
        }
        
        if ( !checkCommPortName(i_Config.getCommPortName()) )
        {
            $Logger.warn("串口设备已不存：" + i_Config.getCommPortName());
            $SerialPorts.remove(i_Config.getCommPortName());
            return null;
        }
        
        E v_SerialPortWrapper = (E) $SerialPorts.get(i_Config.getCommPortName());
        if ( v_SerialPortWrapper == null )
        {
            try
            {
                v_SerialPortWrapper = i_Wrapper.getDeclaredConstructor().newInstance();
                v_SerialPortWrapper.init(i_Config);
                $SerialPorts.put(i_Config.getCommPortName() ,v_SerialPortWrapper);
            }
            catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException exce)
            {
                $Logger.error(exce);
                return null;
            }
        }
        
        return v_SerialPortWrapper;
    }
    
    
    
    /**
     * 按串口名称匹配到串口对象
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-30
     * @version     v1.0
     * 
     * @param i_CommPortName  串口名称
     * @return
     */
    public static SerialPort getCommPortByName(String i_CommPortName)
    {
        SerialPort [] v_Ports = SerialPort.getCommPorts();
        
        for (SerialPort v_Port : v_Ports)
        {
            if ( i_CommPortName.equals(v_Port.getDescriptivePortName()) )
            {
                return v_Port;
            }
        }
        
        return null;
    }
    
    

    /**
     * 获取所有串口名称
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-30
     * @version     v1.0
     *
     * @return
     */
    public static List<String> getCommPortNames()
    {
        List<String>  v_Ret   = new ArrayList<String>();
        SerialPort [] v_Ports = SerialPort.getCommPorts();
        
        for (SerialPort v_Port : v_Ports)
        {
            v_Ret.add(v_Port.getDescriptivePortName());
        }
        
        return v_Ret;
    }
    
    
    
    /**
     * 验证串口名称
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-30
     * @version     v1.0
     * 
     * @param i_CommPortName  串口名称
     * @return
     */
    public static boolean checkCommPortName(String i_CommPortName)
    {
        List<String> v_CommPortNames = getCommPortNames();
        
        if ( Help.isNull(v_CommPortNames) )
        {
            return false;
        }
        
        for (String v_Name : v_CommPortNames)
        {
            if ( v_Name.equals(i_CommPortName) )
            {
                v_CommPortNames.clear();
                v_CommPortNames = null;
                return true;
            }
        }
        
        v_CommPortNames.clear();
        v_CommPortNames = null;
        return false;
    }
    
    
    
    private SerialPortFactory()
    {
        // Nothing.
    }
    
}
