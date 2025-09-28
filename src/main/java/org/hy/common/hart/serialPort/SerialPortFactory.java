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
 *              v2.0  2025-09-28  添加：支持新版本的串口名称，格式为：串口名称<硬件路径>，如：Physical Port S2</dev/ttyS2>
 *                                添加：支持串口名称、串口硬件路径、串口名称<硬件路径>三种格式
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
     *              v2.0  2025-09-27  添加：支持新版本的串口名称，格式为：串口名称<硬件路径>
     * 
     * @param i_CommPortName  串口名称
     * @return
     */
    public static SerialPort getCommPortByName(String i_CommPortName)
    {
        SerialPort [] v_Ports = SerialPort.getCommPorts();
        
        // 支持新版本的串口名称，格式为：串口名称<硬件路径>，如：Physical Port S2</dev/ttyS2>
        String v_CommPortName = i_CommPortName;
        int    v_SIndex       = v_CommPortName.indexOf("<");
        int    v_EIndex       = v_CommPortName.indexOf(">");
        if ( v_SIndex > 0 && v_EIndex == v_CommPortName.length() -1 )
        {
            v_CommPortName = v_CommPortName.substring(0 ,v_SIndex);
        }
        
        for (SerialPort v_Port : v_Ports)
        {
            if ( i_CommPortName.equals(v_Port.getDescriptivePortName()) 
              || v_CommPortName.equals(v_Port.getDescriptivePortName())
              || i_CommPortName.equals(v_Port.getSystemPortPath()) )
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
     *              v2.0  2025-09-27  添加：支持新版本的串口名称，格式为：串口名称<硬件路径>
     *
     * @return
     */
    public static List<String> getCommPortNames()
    {
        List<String>  v_Ret   = new ArrayList<String>();
        SerialPort [] v_Ports = SerialPort.getCommPorts();
        
        for (SerialPort v_Port : v_Ports)
        {
            v_Ret.add(v_Port.getDescriptivePortName() + "<" + v_Port.getSystemPortPath() + ">");
        }
        
        return v_Ret;
    }
    
    
    
    /**
     * 验证串口名称
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-30
     * @version     v1.0
     *              v2.0  2025-09-27  添加：支持新版本的串口名称，格式为：串口名称<硬件路径>
     * 
     * @param i_CommPortName  串口名称
     * @return
     */
    public static boolean checkCommPortName(String i_CommPortName)
    {
        List<String> v_CommPortNames = getCommPortNames();
        boolean      v_Ret           = false;
        
        if ( Help.isNull(v_CommPortNames) )
        {
            return false;
        }
        
        for (String v_Name : v_CommPortNames)
        {
            // 支持新版本的串口名称，格式为：串口名称<硬件路径>，如：Physical Port S2</dev/ttyS2>
            int    v_SIndex = v_Name.indexOf("<");
            int    v_EIndex = v_Name.indexOf(">");
            String v_Name1  = v_Name.substring(0 ,v_SIndex);
            String v_Name2  = v_Name.substring(v_SIndex + 1 ,v_EIndex);
            
            if ( v_Name .equals(i_CommPortName) 
              || v_Name1.equals(i_CommPortName)
              || v_Name2.equals(i_CommPortName) )
            {
                v_Ret = true;
                break;
            }
        }
        
        v_CommPortNames.clear();
        v_CommPortNames = null;
        return v_Ret;
    }
    
    
    
    private SerialPortFactory()
    {
        // Nothing.
    }
    
}
