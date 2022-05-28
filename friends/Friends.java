package friends;
import java.util.ArrayList;
import structures.Queue;

/**
 * A Friends class
 * 
 * @author Alex Hu
 */
public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * 
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {		
		/** COMPLETE THIS METHOD **/
		p1=p1.toLowerCase();
		p2=p2.toLowerCase();
		makeLowerCase(g);
		boolean[] used = new boolean[g.members.length];
		ArrayList<String> result = new ArrayList<String>();
		Queue<Integer> q = new Queue<Integer>();
		int[] parent = new int[g.members.length];
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
		Friend p;
		int startIdx = g.map.get(p1);
		int parentIdx = g.map.get(p1);
		int goalIdx = g.map.get(p2);
		q.enqueue(parentIdx);
		while(!q.isEmpty()) {
			parentIdx=q.dequeue();
			used[parentIdx] = true;
			for(p = g.members[parentIdx].first; p != null; p=p.next) {
				if(!used[p.fnum]) {
					used[p.fnum] = true;
					if(parent[p.fnum] == -1) {
						parent[p.fnum] = parentIdx;
					}
					q.enqueue(p.fnum);
				}
			}
		}
		if(!used[goalIdx]) {
			return null;
		}
		
		while(goalIdx != startIdx) {
			result.add(0,g.members[goalIdx].name);
			goalIdx=parent[goalIdx];
		}
		result.add(0,p1);
		return result; //QUIETS THE COMPILER
	}
	
	//private helper for shortestChain
	private static void makeLowerCase(Graph g) {
		if(g==null)
			return;
		for(int i = 0; i < g.members.length; i++) {
			if(g.members[i].name!=null) {
				g.members[i].name=g.members[i].name.toLowerCase();
			}
			if(g.members[i].school!=null) {
				g.members[i].school=g.members[i].school.toLowerCase();
			}

		}
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an ArrayList of ArrayLists - each constituent ArrayList contains
	 * the names of all students in a clique.
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		/** COMPLETE THIS METHOD **/
		makeLowerCase(g);
		school = school.toLowerCase();
		boolean[] used = new boolean[g.members.length];
		ArrayList<ArrayList<String>> finalRes = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < g.members.length; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			
			if(!used[i] && g.members[i].school != null && g.members[i].school.equals(school)) {
				used[i] = true;
				dfs(g, g.members[i], school, temp, used);
			}
			if(!temp.isEmpty())
				finalRes.add(temp);
		}
		return finalRes; 
	}
	
	//private helper for cliques
	private static void dfs(Graph g, Person f, String school, ArrayList<String> result, boolean[] used) {
		result.add(f.name);
		for(Friend p = f.first; p != null; p=p.next) {
			if(g.members[p.fnum].school == null)
				continue;
			if(g.members[p.fnum].school.equals(school)) {
				if(!used[p.fnum]) {
					used[p.fnum] = true;
					dfs(g, g.members[p.fnum], school, result, used);
				}
			}
		}
	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 */
	public static ArrayList<String> connectors(Graph g) {
		/** COMPLETE THIS METHOD **/
		int[] back = new int[g.members.length];
		int[] dfsnum = new int[back.length];
		boolean[] used = new boolean[dfsnum.length];
		ArrayList<String> result = new ArrayList<String>();
		Friend ctFriend = new Friend(0, null);
		for(int i = 0; i < used.length; i++) {
			//System.out.println("running dfs-- ");
			if(!used[i])
				dfs(ctFriend, i, -1, back, dfsnum, used, g, result);
		}
		removeDuplicates(result);
		return result; 
	}
	//dfs private helper for connectors
	private static void dfs(Friend ct, int v, int w, int[] back, int[] dfsnum, boolean[] used, Graph g, ArrayList<String> result) {
		used[v] = true;
		dfsnum[v] = ct.fnum;
		back[v] = ct.fnum++;
		int ct1 = 0;
		for(Friend p = g.members[v].first; p!=null; p=p.next) {
			if(w!=p.fnum) {
				if(used[p.fnum]) {
					back[v] = Math.min(back[v], dfsnum[p.fnum]);
				}else {
					dfs(ct, p.fnum, v, back, dfsnum, used, g, result);
					back[v] = Math.min(back[v], back[p.fnum]);
					if(back[p.fnum] >= dfsnum[v] && w != -1)
						result.add(g.members[v].name.toLowerCase());
					ct1++;
				}
			}
		}
		if(w == -1 && ct1 > 1)
			result.add(g.members[v].name.toLowerCase());
	}
	//removeDuplicates private helper for connectors
	private static void removeDuplicates(ArrayList<String> arr){
		for(int i = 0; i < arr.size(); i++) {
			for(int j = 0; j < arr.size(); j++) {
				if(arr.get(i).equals(arr.get(j)) && i!=j) {
					arr.remove(j);
					j--;
				}
			}
		}
	}
}

