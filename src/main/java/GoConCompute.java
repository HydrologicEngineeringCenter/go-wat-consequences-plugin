import com.sun.jna.Native;

public class GoConCompute {
    public static void main(String[] args){
        String terrainPath = "C:\\Projects\\TrinityTerrain\\Terrain\\Terrain_With_Bathymetry.vrt";
        String nsipath = "C:\\Projects\\TrinitySI\\TrinitySIReduced.gpkg";
        String resultsfilepath="C:\\temp\\61Grapevine_TX00005.p15.hdf";
        String output = "C:\\Temp\\goinland_W61.gpkg";
        int espg = 4326;
        GoInlandDataset.Compute(resultsfilepath,terrainPath,nsipath,output,espg);
    }
}
