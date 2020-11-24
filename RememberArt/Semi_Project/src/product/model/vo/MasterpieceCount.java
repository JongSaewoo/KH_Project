package product.model.vo;

public class MasterpieceCount {
	private int count; //좋아요 갯수

	
	public MasterpieceCount() {
	}

	public MasterpieceCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "MasterpieceCount [count=" + count + "]";
	}
	
	

}
