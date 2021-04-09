import com.alibaba.fastjson.JSONObject;
import com.sun.jna.Library;
import com.sun.jna.Native;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class testWindow extends JFrame {
    //角度
    private JLabel angle;
    //刹车
    private JLabel brake;
    //油门
    private JLabel throttle;
    //挡位
    private JLabel gear;
    //方向盘的图片
    private JLabel wheel;

    public testWindow(){
        //打包之后获取背景图片
        URL imageURL = getClass().getClassLoader().getResource("static/backGround1.png");
        ImageIcon icon1=new ImageIcon(imageURL);
        //本地获取
//        ImageIcon icon1=new ImageIcon("C:\\exportFile\\backGround.png");
        //添加JLabel 放置图片
        JLabel label1=new JLabel(icon1);
        //设置label的位置、大小，label大小为图片的大小
        label1.setBounds(0,0,icon1.getIconWidth(),icon1.getIconHeight());
        label1.setBackground(Color.BLUE);
        // 设置窗口的 宽高
        setSize(600, 628);
        //窗口不允许缩放
        setResizable(false);
        //在frame的底层容器添加label
        getLayeredPane().add(label1,new Integer(Integer.MIN_VALUE));
        //设置窗口标题
        setTitle("罗技G29方向盘正在控制中......");
        //panel
        JPanel panel =new JPanel();
        //panelTop，顶层容器
        JPanel panelTop=new JPanel();
        panelTop=(JPanel)this.getContentPane();
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        panel.setOpaque(false);
        angle = new JLabel();
        angle.setSize(70,70);
        //dialog - 字体 1-粗体；0-正常   15-字号
        angle.setFont(new java.awt.Font("Dialog", 1, 15));
        angle.setLocation(60,520);
        angle.setForeground(Color.WHITE);
        add(angle);
        brake = new JLabel();
        brake.setSize(70,70);
        //dialog - 字体 1-粗体；0-正常   15-字号
        brake.setFont(new java.awt.Font("Dialog", 1, 15));
        brake.setLocation(207,520);
        brake.setForeground(Color.WHITE);
        add(brake);
        throttle = new JLabel();
        throttle.setSize(70,70);
        //dialog - 字体 1-粗体；0-正常   15-字号
        throttle.setFont(new java.awt.Font("Dialog", 1, 15));
        throttle.setLocation(350,520);
        throttle.setForeground(Color.WHITE);
        add(throttle);
        gear = new JLabel();
        gear.setSize(70,70);
        //dialog - 字体 1-粗体；0-正常   15-字号
        gear.setFont(new java.awt.Font("Dialog", 1, 15));
        gear.setLocation(496,520);
        gear.setForeground(Color.WHITE);
        add(gear);
        //设置方向盘的图片
        URL wheelURL = getClass().getClassLoader().getResource("static/wheel.png");
        ImageIcon wheelIcon1 = new ImageIcon(wheelURL);
        wheel = new JLabel();
        wheelIcon1.setImage(wheelIcon1.getImage().getScaledInstance(wheelIcon1.getIconWidth(),wheelIcon1.getIconHeight(),0));
        wheel.setIcon(wheelIcon1);
        wheel.setBounds(146,50,wheelIcon1.getIconWidth(),wheelIcon1.getIconHeight());
        this.getContentPane().add(wheel);
        //添加panel，设置大小，可视
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * 加载显示提示信息
     * @param angle
     */
    public void setHintInfoAll(String angle,String brake,String throttle,String gear) {
        this.angle.setText(angle);
        this.brake.setText(brake);
        this.throttle.setText(throttle);
        this.gear.setText(gear);
    }

    private static testWindow dialogHintInfo = new testWindow();

    /**
     * 更新提示信息
     */
    public static void updateHintInfoAll(String angle,String brake,String throttle,String gear) {
        dialogHintInfo.setHintInfoAll(angle,brake,throttle,gear);
    }
    //云平台数据接收地址
    public static final String url = "http://10.0.3.32:9015/api/DesktopProgramDate/doAdd";

    public interface WhellIn extends Library {
        //获取G29的dll文件
        WhellIn whellin = (WhellIn) Native.load("LogitechSteeringWheelEnginesWrapper", WhellIn.class);
        //内部方法
        boolean LogiSteeringInitialize(boolean ignoreXInputControllers);
        boolean LogiUpdate();
        //index代表第几个设备
        boolean LogiIsConnected(int index);
        DIJOYSTATE2ENGINES LogiGetStateENGINES(int index);
        boolean LogiButtonTriggered(int start,int end);
        boolean LogiButtonIsPressed(int start,int end);
        boolean LogiButtonReleased(int start,int end);
    }

    public static void main(String[] args) {
        String carId = "";
        if (args.length == 0 || "".equals(args[0])) {
            try {
                Registry.registerUrlHandler();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("-----Register Url Handler success!-----");
        } else {
            // 通过注册表执行了程序, 这里打印出参数. 可以添加其他自定义逻辑
            System.out.println(Arrays.toString(args));
            String info = Arrays.toString(args);
            carId = info.substring(info.lastIndexOf("="),info.lastIndexOf("/"));
            System.out.println("-----called by Url!-----");
        }
        Map<String, String> params = new HashMap<>();
        params.put("requestId", "A659B364C3CDD96BBC03A115029041E3");
        params.put("sourceSystem","ADC");
        params.put("serviceName","S0100002C");
        JSONObject jsonObject = new JSONObject();
        while (WhellIn.whellin.LogiSteeringInitialize(false)){
            if (WhellIn.whellin.LogiUpdate() && WhellIn.whellin.LogiIsConnected(0)){
                DIJOYSTATE2ENGINES dijoystate2ENGINES = WhellIn.whellin.LogiGetStateENGINES(0);
                String gear = null;
                //获取相应挡位信息
                if (WhellIn.whellin.LogiButtonTriggered(0,12)){
                    gear = "1挡";
                } else if (WhellIn.whellin.LogiButtonIsPressed(0,12)){
                    gear = "1挡";
                } else if(WhellIn.whellin.LogiButtonReleased(0,12)){
                    gear = "1挡";
                }
                if (WhellIn.whellin.LogiButtonTriggered(0,13)){
                    gear = "2挡";
                } else if (WhellIn.whellin.LogiButtonIsPressed(0,13)){
                    gear = "2挡";
                } else if(WhellIn.whellin.LogiButtonReleased(0,13)){
                    gear = "2挡";
                }
                if (WhellIn.whellin.LogiButtonTriggered(0,14)){
                    gear = "3挡";
                } else if (WhellIn.whellin.LogiButtonIsPressed(0,14)){
                    gear = "3挡";
                } else if(WhellIn.whellin.LogiButtonReleased(0,14)){
                    gear = "3挡";
                }
                if (WhellIn.whellin.LogiButtonTriggered(0,15)){
                    gear = "4挡";
                } else if (WhellIn.whellin.LogiButtonIsPressed(0,15)){
                    gear = "4挡";
                } else if(WhellIn.whellin.LogiButtonReleased(0,15)){
                    gear = "4挡";
                }
                if (WhellIn.whellin.LogiButtonTriggered(0,16)){
                    gear = "5挡";
                } else if (WhellIn.whellin.LogiButtonIsPressed(0,16)){
                    gear = "5挡";
                } else if(WhellIn.whellin.LogiButtonReleased(0,16)){
                    gear = "5挡";
                }
                if (WhellIn.whellin.LogiButtonTriggered(0,17)){
                    gear = "6挡";
                } else if (WhellIn.whellin.LogiButtonIsPressed(0,17)){
                    gear = "6挡";
                } else if(WhellIn.whellin.LogiButtonReleased(0,17)){
                    gear = "6挡";
                }
                if (WhellIn.whellin.LogiButtonTriggered(0,18)){
                    gear = "R挡";
                } else if (WhellIn.whellin.LogiButtonIsPressed(0,18)){
                    gear = "R挡";
                } else if(WhellIn.whellin.LogiButtonReleased(0,18)){
                    gear = "R挡";
                }
                if (gear == null){
                    gear = "空挡";
                }
                //更新各个信息
                updateHintInfoAll("" + dijoystate2ENGINES.lX, "" + dijoystate2ENGINES.lY,"" + dijoystate2ENGINES.lRz,gear);
                jsonObject.put("angle" , dijoystate2ENGINES.lX);
                jsonObject.put("throttle" , dijoystate2ENGINES.lY);
                jsonObject.put("brake" , dijoystate2ENGINES.lRz);
                jsonObject.put("gear" , gear);
                jsonObject.put("carId",carId);
                try{
                    //不要实时推送，否则客户端会崩溃
                    Thread.sleep(10);
                    //推送信息
                    String results = HTTPUtil.HttpPostWithJson(url, jsonObject.toJSONString(), params);
                    JSONObject parse = (JSONObject)JSONObject.parse(results);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
