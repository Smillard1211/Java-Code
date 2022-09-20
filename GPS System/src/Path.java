


public  class Path implements Comparable<Path>{

	public String vertex;
	public String path;
	public int cost;
	
	public Path(String start, int cost, String start2) {
		this.vertex = start;
		this.path = start2;
		this.cost = cost;
		
	}
	public int compareTo(Path o) {
		
		return cost - o.cost;
	}
	public String toString(String V) {

		String ret =("Start: " + V + "\ncost: " + cost + " \nPath: ");
		if(path.equals("No Path"))
			ret = ret + "No Path";
		else {
			for(int i = 0; i < path.length(); i++) {
				ret = ret +"\n" + path.substring(i, i+1) ;
			}
		}
		
		return ret;
	}
		
	
	

}
