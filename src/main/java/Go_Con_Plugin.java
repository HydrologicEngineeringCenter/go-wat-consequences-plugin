/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.rma.factories.NewObjectFactory;
import hec.model.OutputVariable;
import hec2.map.GraphicElement;
import hec2.model.DataLocation;
import hec2.model.ProgramOrderItem;
import hec2.plugin.action.EditAction;
import hec2.plugin.action.OutputElement;
import hec2.plugin.lang.ModelLinkingException;
import hec2.plugin.lang.OutputException;
import hec2.plugin.model.ModelAlternative;
import hec2.wat.model.tracking.OutputPlugin;
import hec2.wat.plugin.AbstractSelfContainedWatPlugin;
import hec2.wat.plugin.CreatableWatPlugin;
import hec2.wat.plugin.WatPluginManager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WatPowerUser
 */
public class Go_Con_Plugin extends AbstractSelfContainedWatPlugin<Go_Con_Alternative> implements CreatableWatPlugin, OutputPlugin  {
    public static final String PluginName = "GoConPlugin";
    private static final String _pluginVersion = "0.0.1";
    private static final String _pluginSubDirectory = "go-wat-con";
    private static final String _pluginExtension = ".gowatcon";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Go_Con_Plugin p = new Go_Con_Plugin();
    }
    public Go_Con_Plugin(){
        super();
        setName(PluginName);
        setProgramOrderItem(new ProgramOrderItem(PluginName,
                "A tool to compute consequences for HEC-WAT",
                false,1,"shortname","Images/fda/wsp.png"));
        WatPluginManager.register(this);
    }
    @Override
    protected String getAltFileExtension() {
        return _pluginExtension;
    }
    @Override
    public String getPluginDirectory() {
        return _pluginSubDirectory;
    }
    @Override
    public String getVersion() {
        return _pluginVersion;
    }
    @Override
    public boolean saveProject() {
        boolean success = true;
        for(Go_Con_Alternative alt: _altList){
            if(!alt.saveData()){
                success = false;
                System.out.println("Alternative " + alt.getName() + " could not save");
            }
        }
        return success;
    }

    @Override
    protected Go_Con_Alternative newAlternative(String string) {
        return new Go_Con_Alternative(string);
    }
    @Override
    protected NewObjectFactory getAltObjectFactory() {
        return new Go_Con_AlternativeFactory(this);
    }
    @Override
    public List<DataLocation> getDataLocations(ModelAlternative ma, int i) {
        return null;
    }
    @Override
    public boolean setDataLocations(ModelAlternative ma, List<DataLocation> list) throws ModelLinkingException {
        return true;
    }
    @Override
    public boolean compute(ModelAlternative ma) {
        Go_Con_Alternative alt = getAlt(ma);
        if (alt != null) {
            alt.setComputeOptions(ma.getComputeOptions());
            return alt.compute();
        }
        return false;
    }
    @Override
    public void editAlternative(Go_Con_Alternative e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public boolean displayApplication() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public List<GraphicElement> getGraphicElements(ModelAlternative ma) {
        return new ArrayList<>();
    }
    @Override
    public List<OutputElement> getOutputReports(ModelAlternative ma) {
        return new ArrayList<>();
    }
    @Override
    public boolean displayEditor(GraphicElement ge) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean displayOutput(OutputElement oe, List<ModelAlternative> list) throws OutputException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public List<EditAction> getEditActions(ModelAlternative ma) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void editAction(String string, ModelAlternative ma) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<OutputVariable> getAvailOutputVariables(ModelAlternative ma) {
        List<OutputVariable> ret = new ArrayList<>();
        Go_Con_Alternative alt = getAlt(ma);
        return alt.getOutputVariables();
    }
    @Override
    public boolean computeOutputVariables(List<OutputVariable> list, ModelAlternative ma) {
        Go_Con_Alternative alt = getAlt(ma);
        return alt.computeOutputVariables(list);
    }

    @Override
    public boolean hasOutputVariables(ModelAlternative ma) {
        Go_Con_Alternative alt = getAlt(ma);
                Boolean hazvars = alt.hasOutputVariables();
                return hazvars;
    }

}
