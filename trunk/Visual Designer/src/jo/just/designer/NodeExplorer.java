/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MyEditor.java
 *
 * Created on Oct 17, 2012, 4:56:42 PM
 */
package jo.just.designer;

import java.awt.BorderLayout;
import javax.swing.ActionMap;
import javax.swing.text.DefaultEditorKit;
import jo.just.api.CloudComputingAPI;
import jo.just.node.widget.CloudComputingNode;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

@ConvertAsProperties(dtd = "-//jo.just.designer//NodeExplorer//EN",
autostore = false)
@TopComponent.Description(preferredID = "NodeExplorerTopComponent",
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@TopComponent.OpenActionRegistration(displayName = "#CTL_Explorer", preferredID = "NodeExplorerTopComponent")
@ActionID(category = "Window", id = "jo.just.designer.NodeExplorerTopComponent")
@ActionReference(path = "Menu/Window")
public class NodeExplorer extends TopComponent implements ExplorerManager.Provider, Lookup.Provider{
    
    private static ExplorerManager mgr;
    private static CloudComputingNode cloudComputingNode;

    /** Creates new form MyEditor */
    public NodeExplorer() {
        initComponents();
        setName(NbBundle.getMessage(NodeExplorer.class, "CTL_Explorer"));
        setToolTipText(NbBundle.getMessage(NodeExplorer.class, "CTL_Explorer"));
        add (new BeanTreeView(), BorderLayout.CENTER);
        
        mgr = new ExplorerManager();
        ActionMap map = this.getActionMap ();
        map.put(DefaultEditorKit.copyAction, ExplorerUtils.actionCopy(mgr));
        map.put(DefaultEditorKit.cutAction, ExplorerUtils.actionCut(mgr));
        map.put(DefaultEditorKit.pasteAction, ExplorerUtils.actionPaste(mgr));
        map.put("delete", ExplorerUtils.actionDelete(mgr, true));
        associateLookup (ExplorerUtils.createLookup(mgr, map));
        
        cloudComputingNode = new CloudComputingNode(new CloudComputingAPI());
        mgr.setRootContext(cloudComputingNode);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
//    private final InstanceContent content = new InstanceContent();
    

    @Override
    public ExplorerManager getExplorerManager() {
        return mgr;
    }
    
    // It is good idea to switch all listeners on and off when the
    // component is shown or hidden. In the case of TopComponent use:
    @Override
    protected void componentActivated() {
        ExplorerUtils.activateActions(mgr, true);
    }
    @Override
    protected void componentDeactivated() {
        ExplorerUtils.activateActions(mgr, false);
    }
    
    
     void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    
    public static CloudComputingNode getDefultRootNood(){
        return cloudComputingNode;
    }
    
    public static Node getRootNood(){
        return mgr.getRootContext();
    }
    
    public static ExplorerManager getNodeExplorerManager(){
        return mgr;
    }
}
