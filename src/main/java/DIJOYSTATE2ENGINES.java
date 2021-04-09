

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;


//DIJOYSTATE2用基本类型重新定义，以便与UDK DLLBind一起工作
public class DIJOYSTATE2ENGINES extends Structure {
    public  int lX;                     /* x-axis position---x轴位置              */
    public  int lY;                     /* y-axis position---y轴位置              */
    public  int lZ;                     /* z-axis position---z轴位置              */
    public  int lRx;                    /* x-axis rotation---x轴旋转              */
    public  int lRy;                    /* y-axis rotation---y轴旋转              */
    public  int lRz;                    /* z-axis rotation---z轴旋转              */
//    public  int[] rglSlider = new int[2];                /* extra axes positions---额外轴位置         */
//    public  int[] rgdwPOV = new int[4];             /* POV directions---POV方向               */
//    public  char[] rgbButtons = new char[128];        /* 128 buttons---128个按钮                  */
//    public  int lVX;                    /* x-axis velocity---x轴速度              */
//    public  int lVY;                    /* y-axis velocity---y轴速度              */
//    public  int lVZ;                    /* z-axis velocity---z轴速度              */
//    public  int lVRx;                   /* x-axis angular velocity---x轴角速度      */
//    public  int lVRy;                   /* y-axis angular velocity---y轴角速度      */
//    public  int lVRz;                   /* z-axis angular velocity---z轴角速度      */
//        public  int[] rglVSlider = new int[2];             /* extra axes velocities---额外轴速度        */
//    public  int lAX;                    /* x-axis acceleration---x轴加速度          */
//    public  int lAY;                    /* y-axis acceleration---y轴加速度          */
//    public  int lAZ;                    /* z-axis acceleration---z轴加速度          */
//    public  int lARx;                   /* x-axis angular acceleration---x轴角加速度  */
//    public  int lARy;                   /* y-axis angular acceleration---y轴角加速度  */
//    public  int lARz;                   /* z-axis angular acceleration---z轴角加速度  */
//        public  int[] rglASlider = new int[2];            /* extra axes accelerations---额外轴加速度     */
//    public  int lFX;                    /* x-axis force---x轴力   离合              */
//    public  int lFY;                    /* y-axis force---y轴力                 */
//    public  int lFZ;                    /* z-axis force---z轴力                 */
//    public  int lFRx;                   /* x-axis torque---x轴扭矩                */
//    public  int lFRy;                   /* y-axis torque---y轴扭矩                */
//    public  int lFRz;                   /* z-axis torque---z轴扭矩                */
//   public  int[] rglFSlider = new int[2];            /* extra axes forces----额外轴扭矩            */

    public static class ByReference extends DIJOYSTATE2ENGINES implements Structure.ByReference {};

    public static class ByValue extends DIJOYSTATE2ENGINES implements Structure.ByValue {};

    public DIJOYSTATE2ENGINES(){}
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("lX", "lY", "lZ", "lRx", "lRy", "lRz");
    }
}
