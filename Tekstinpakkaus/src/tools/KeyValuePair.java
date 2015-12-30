package tools;

/**
 * Luokka sanakirjaan laitettaville avain-arvo-pareille
 */
public class KeyValuePair {
    
    private int key;
    private Nodes nodes;
    
    public KeyValuePair (int key, Nodes nodes) {
        this.key = key;
        this.nodes = nodes;
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * @return the nodes
     */
    public Nodes getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(Nodes nodes) {
        this.nodes = nodes;
    }
    
}
