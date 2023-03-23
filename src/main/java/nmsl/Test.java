package nmsl;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Test extends Library {

    public Test instance= (Test) Native.loadLibrary("C:/Users/32302/Desktop/rat.dll",Test.class);
    int Get();
    int Rat();
    int Set(int c);
}
