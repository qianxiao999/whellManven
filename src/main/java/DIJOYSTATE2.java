
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * @author
 * @create
 */
public class DIJOYSTATE2 extends Structure {
//    public  int    lX;                     /* x-axis position              */
//    public  long    lY;                     /* y-axis position              */
//    public  NativeLong    lZ;                     /* z-axis position              */
//    public  NativeLong    lRx;                    /* x-axis rotation              */
//    public  NativeLong    lRy;                    /* y-axis rotation              */
//    public  long    lRz;                    /* z-axis rotation              */
//        public  int[] rglSlider = new int[2];                /* extra axes positions---额外轴位置         */
    public  int[] rgdwPOV = new int[4];             /* POV directions---POV方向               */
//    public  char[] rgbButtons = new char[128];         /* 128 buttons                  */
//    public  NativeLong    lVX;                    /* x-axis velocity              */
//    public  NativeLong    lVY;                    /* y-axis velocity              */
//    public  NativeLong    lVZ;                    /* z-axis velocity              */
//    public  NativeLong    lVRx;                   /* x-axis angular velocity      */
//    public  NativeLong    lVRy;                   /* y-axis angular velocity      */
//    public  NativeLong    lVRz;                   /* z-axis angular velocity      */
//    public static NativeLong    rglVSlider[]= new NativeLong[2];          /* extra axes velocities        */
//    public  NativeLong    lAX;                    /* x-axis acceleration          */
//    public  NativeLong    lAY;                    /* y-axis acceleration          */
//    public  NativeLong    lAZ;                    /* z-axis acceleration          */
//    public  NativeLong    lARx;                   /* x-axis angular acceleration  */
//    public  NativeLong    lARy;                   /* y-axis angular acceleration  */
//    public  NativeLong    lARz;                   /* z-axis angular acceleration  */
//    public static NativeLong    rglASlider[] = new NativeLong[2];          /* extra axes accelerations     */
//    public  NativeLong    lFX;                    /* x-axis force                 */
//    public  NativeLong    lFY;                    /* y-axis force                 */
//    public  NativeLong    lFZ;                    /* z-axis force                 */
//    public  NativeLong    lFRx;                   /* x-axis torque                */
//    public  NativeLong    lFRy;                   /* y-axis torque                */
//    public  NativeLong    lFRz;                   /* z-axis torque                */
//
//    public static NativeLong    rglFSlider[] = new NativeLong[2];          /* extra axes forces            */

    public static   class ByReference extends DIJOYSTATE2 implements Structure.ByReference{};
    public static class ByValue extends DIJOYSTATE2 implements Structure.ByValue{};


    @Override
    protected List<String> getFieldOrder() {
//        return Arrays.asList("lX", "lY","lZ","lRx", "lRy","lRz","rglSlider", "rgdwPOV","rgbButtons","lVX", "lVY","lVZ","lVRx", "lVRy","lVRz","rglVSlider", "lAX","lAY","lAZ", "rglASlider","lFX","lFY", "lFRx","lFRy","lFRz","rglFSlider");
        return Arrays.asList("rgdwPOV");
    }
}
