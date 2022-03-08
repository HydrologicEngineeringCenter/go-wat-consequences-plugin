import com.sun.jna.Native;

public class GoConCompute {
    public static void main(String[] args){
        String terrainPath = "C:\\Projects\\TrinityTerrain\\Terrain\\Terrain_With_Bathymetry.vrt";
        String nsipath = "C:\\Projects\\TrinitySI\\nsiv2_48.gpkg";
        String resultsfilepath="C:\\DCWorkingDir\\Trinity WAT Model\\runs\\Existing_Conditions\\Trinity\\realization 1\\lifecycle 1\\event 1\\RAS\\Grapevine_TX00005.p15.hdf";
        String output = "C:\\Temp\\goinland_W.gpkg";
        int espg = 4326;

        GoInlandDataset.Compute(resultsfilepath,terrainPath,nsipath,output,espg);

    }
}
