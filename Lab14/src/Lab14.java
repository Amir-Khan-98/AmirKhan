import java.util.ArrayList;

public class Lab14 {
	public static void main(String args[]){
		String filename = "C:\\Users\\Amir Khan\\Documents\\ClusterLab.txt";
		String sep = ",";
		 ArrayList<Integer> arrangement= new ArrayList<Integer>();
		 for(int i=0;i<25;++i){
		 arrangement.add(1);
		 }
		 for(int i=0;i<50;++i){
			 arrangement.add(2);
			 }
		 for(int i=0;i<25;++i){
			 arrangement.add(3);
			 }
		 //System.out.println(arrangement.size());
		double cluster[][];
		cluster = KMeans.ReadArrayFile(filename,sep);
		int rows=100;
		int columns=3;
		KMeans s = new KMeans(cluster,columns,rows);
		int iter = 10;
		int noClu = 3;
		ArrayList<Integer> sol= new ArrayList<Integer>();
	    sol = s.RunIter(noClu,iter,arrangement,false);
	    double f = KMeans.GroupingWK(arrangement,sol);
	    System.out.println(f);
	}
}
