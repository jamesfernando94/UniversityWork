import java.util.List;

public class NimmGameNode {
	public NimmGameNode parent;
	public int pileCount;
	public List<NimmGameNode> children;

	public NimmGameNode(int pileCount) {
		this(pileCount, null);
	}

	public NimmGameNode(int pileCount, NimmGameNode parent){
		this.pileCount = pileCount;
		this.parent = parent;

	}
}
