/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.rma.client.Browser;
import com.rma.io.RmaFile;
import com.rma.model.Project;
import hec.data.Parameter;
import hec.model.OutputVariable;
import hec2.plugin.model.ComputeOptions;
import hec2.plugin.selfcontained.SelfContainedPluginAlt;
import hec2.wat.model.tracking.OutputVariableImpl;
import org.jdom.Document;
import org.jdom.Element;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author WatPowerUser
 */
public class Go_Con_Alternative extends SelfContainedPluginAlt{
    private String _pluginVersion;
    private static final String DocumentRoot = "Go_Con_Alternative";
    private static final String OutputVariableElement = "OutputVariables";
    private static final String AlternativeNameAttribute = "Name";
    private static final String AlternativeDescriptionAttribute = "Desc";
    private ComputeOptions _computeOptions;
    private String _terrainPath ;
    private String _nsipath;
    private String _output;
    private String _resultsPath;
    int _espg = 4326;
    public Go_Con_Alternative(){
        super();
    }
    public Go_Con_Alternative(String name){
        this();
        setName(name);
    }
    @Override
    protected boolean loadDocument(Document dcmnt) {
        if(dcmnt!=null){
            Element ele = dcmnt.getRootElement();
            if(ele==null){
                System.out.println("No root element on the provided XML document.");
                return false;
            }
            if(ele.getName().equals(DocumentRoot)){
                setName(ele.getAttributeValue(AlternativeNameAttribute));
                setDescription(ele.getAttributeValue(AlternativeDescriptionAttribute));
            }else{
                System.out.println("XML document root was imporoperly named.");
                return false;
            }
            setModified(false);
            return true;
        }else{
            System.out.println("XML document was null.");
            return false;
        }
    }
    public void setComputeOptions(ComputeOptions opts){
        _computeOptions = opts;
        hec2.wat.model.ComputeOptions wco = (hec2.wat.model.ComputeOptions) opts;
        String pathToLifecycleDSS = wco.getDssFilename();
        String resultsPath = pathToLifecycleDSS.substring(0,pathToLifecycleDSS.lastIndexOf("\\"));
        _resultsPath = resultsPath + "\\event " + wco.getCurrentEventNumber() + "\\RAS\\Grapevine_TX00005.p15.hdf";
        //get the project directory
        Project proj = Browser.getBrowserFrame().getCurrentProject();
        String dir = proj.getProjectDirectory();
        if(dir == null){return;}
        _output = resultsPath + "\\event " + wco.getCurrentEventNumber() + "\\Consequences.gpkg";
        _terrainPath = dir + "\\RAS\\Terrain\\Terrain_With_Bathymetry_FFRD.vrt";
        _nsipath = dir + "\\NSI\\TrinitySIReduced.gpkg";
        _espg = 4326;
    }
    @Override
    public boolean isComputable() {
        return true;
    }
    @Override
    public boolean compute() {
        GoInlandDataset.Compute(_resultsPath,_terrainPath,_nsipath,_output,_espg);
        return true;
    }
    @Override
    public boolean cancelCompute() {
        return false;
    }
    @Override
    public String getLogFile() {
        return null;
    }
    @Override
    public int getModelCount() {
        return 1;
    }
    @Override
    public boolean saveData(RmaFile file){
        if(file!=null){
            //used to be sElement
            Element root = new Element(DocumentRoot);
            root.setAttribute(AlternativeNameAttribute,getName());
            root.setAttribute(AlternativeDescriptionAttribute,getDescription());
            Document doc = new Document(root);
            return writeXMLFile(doc,file);
        }
        return false;
    }
    public List<OutputVariable> getOutputVariables(){
        OutputVariableImpl oimpl = new OutputVariableImpl();
        oimpl.setName("RAS Compute Failures");
        oimpl.setDescription("Finds RAS Compute Failures");
        oimpl.setParamId(Parameter.PARAMID_COUNT);
        List<OutputVariable> ret = new ArrayList<>();
            ret.add(oimpl);
        return ret;
    }
    public boolean hasOutputVariables(){
        return true;
    }
    boolean computeOutputVariables(List<OutputVariable> list) { return true; }
}
