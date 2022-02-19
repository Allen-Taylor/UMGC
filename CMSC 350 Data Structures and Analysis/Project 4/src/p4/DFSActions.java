/**
 * DFSActions Interface (Generic)
 **/
package p4;

public interface DFSActions<V> {
    void processVertex(V v);

    void descend();

    void ascend();

    void cycleDetected();
}