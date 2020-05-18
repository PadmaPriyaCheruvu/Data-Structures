
public class Edge implements Comparable<Edge>
	{
		private int u;
		int v;
		int cost;
		
		public int getv()
		{
			return v;
		}
		
		public int getu()
		{
			return u;
		}
		
		public int getCost()
		{
			return cost;
		}
		
		public void setv(int v)
		{
			this.v = v;
		}
		
		public void setu(int u)
		{
			this.u = u;
		}
		
		public void setCost(int cost)
		{
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge otherEdge){
			if(this.cost > otherEdge.cost)
				return 1;
			else if (this.cost == otherEdge.cost)
				return 0;
			else if (this.cost < otherEdge.cost)
				return -1;
			return 0;
		}
	}