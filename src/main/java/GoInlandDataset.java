import com.sun.jna.Library;
import com.sun.jna.Native;


public class GoInlandDataset {
    static String dllPath =  "C:\\Programs\\Go-Inland\\go-inland.dll";
    public static final goDataset csq = (goDataset) Native.load(dllPath, goDataset.class);

    public interface goDataset extends Library{
         int GoInland(String filepath, String terrainpath,String nsiPath, String output, int espg);
    }

    public static int Compute(String filepath, String terrainpath,String nsiPath, String output, int espg){
        return csq.GoInland(filepath, terrainpath,nsiPath,output,espg);
    }
}

