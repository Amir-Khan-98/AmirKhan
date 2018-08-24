import java.util.ArrayList;

public class TSPSolution {
	public static ArrayList<Integer> t = new ArrayList<Integer>();
	public static ArrayList<Integer> getCities(){
		ArrayList<Integer> x = (ArrayList<Integer>) t.clone();
		return x;
	}


	public static void RandPerm(int n){
		
		//System.out.println(n);
		ArrayList<Integer> p = new ArrayList<Integer>();
		for(int i=0;i<n;++i){
			p.add(i);
		}
		//System.out.println(p.size());
		while(p.size()>0){
			int j = CS2004.UI(0,(p.size()-1));
			t.add(p.get(j));
			//System.out.println(p.get(j));
			//System.out.println(p.size());
			p.remove(j);
		}
		
	}
	public static void RandPerm(double[][] x){
		int n = x.length;
		//System.out.println(n);
		ArrayList<Integer> p = new ArrayList<Integer>();
		for(int i=0;i<n;++i){
			p.add(i);
		}
		//System.out.println(p.size());
		while(p.size()>0){
			int j = CS2004.UI(0,(p.size()-1));
			t.add(p.get(j));
			//System.out.println(p.get(j));
			//System.out.println(p.size());
			p.remove(j);
		}
		
	}
	public static ArrayList<Integer> RandPerm1(double[][] x){
		int n = x.length;
		//System.out.println(n);
		ArrayList<Integer> start = new ArrayList<Integer>();
		ArrayList<Integer> p = new ArrayList<Integer>();
		for(int i=0;i<n;++i){
			p.add(i);
		}
		//System.out.println(p.size());
		while(p.size()>0){
			int j = CS2004.UI(0,(p.size()-1));
			start.add(p.get(j));
			//System.out.println(p.get(j));
			//System.out.println(p.size());
			p.remove(j);
		}
		return start;
	}
	public static void ClearAr(){
		t.clear();
	}
	public static ArrayList<Integer> Clone(ArrayList<Integer> t){
		ArrayList<Integer> p = new ArrayList<Integer>();
		int l = t.size();
		for (int i=0;i<l;++i){
			int get=t.get(i);
			p.add(get);
		}
		return p;
	}
	public static  void Swap(){
		int i=0,j=0;
		while (i==j){
			i = CS2004.UI(0,(t.size()-1));
			j = CS2004.UI(0,(t.size()-1));
		}
		int temp = t.get(i);
		t.set(i, t.get(j));
		t.set(j, temp);
	}
	
	public static double TSPFitness(int n, ArrayList<Integer> t, double[][] d){
		double s = 0;
		//System.out.println(n);
		for(int i=0;i<n-1;++i){
			int a = t.get(i);
			int b = t.get(i+1);
			//System.out.println(i);
			s=s+d[a][b];
		}
		int end_city = t.get(n-1);
		int start_city = t.get(0);
		s=s + d[end_city][start_city];
		return s;
	}
}
