import java.util.ArrayList;

public class test {
	public static ArrayList<Integer> RMHC(int iter, String file, int n){
		double[][] x;
		x = TSP.ReadArrayFile(file, " ");
		ArrayList<Integer> u=new ArrayList<Integer>();
		TSPSolution.RandPerm(x);
		u=TSPSolution.getCities();
		
		ArrayList<Integer> temp=new ArrayList<Integer>();
		u=TSPSolution.getCities();
		//double [][] mst ;
		//mst = MST.PrimsMST(x);
		//double mstL = mst.length;
		
		//double sol = TSPSolution.TSPFitness(t.size(), t, x);
		//double bestSol = TSPSolution.TSPFitness(t.size(), t, x);
		for(int i =0;i<iter;++i)
		{
			//the initial t is taken as oldsolution, and is copied to temp
			//inorder to reverse change if newfitness is worse
			double oldfitness = TSPSolution.TSPFitness(u.size(), u, x);
			System.out.println("old fitness:"+oldfitness);
			temp.clear();
			temp=TSPSolution.Clone(u);
			
			//small change applied to t and this fitness is taken as newfitness
			//Swap(u);
			double newfitness = TSPSolution.TSPFitness(u.size(), u, x);
			//System.out.println("new fitness:"+newfitness);
			
			//compares the two fitness and if the old fitness is better,
			//copies the temp file back to t;
			if(newfitness>oldfitness)
				{			
					u.clear();
					u=TSPSolution.Clone(temp);
				
				}
			
		
		}
		
		//double newSol=TSPSolution.TSPFitness(t.size(), t, x);
		//System.out.println(newSol);
		return u;
	}
	//RMHC function takes in number of cities to visit(N), the 2D array(D)
		//the tour(t), and number of iterations and returns optimal tour route
		//as an integer array list
	public static ArrayList<Integer> RMHC3(double[][] D,int iter, int n)
		{
			TSPSolution.RandPerm(n);
			int N = D.length;
			ArrayList<Integer> t = new ArrayList<Integer>();
			t= TSPSolution.getCities();
			int size=t.size();
			System.out.println(size);
			ArrayList<Integer> temp=new ArrayList<Integer>(size);
			
			for(int i =0;i<iter-1;++i)
			{
				//the initial t is taken as oldsolution, and is copied to temp
				//inorder to reverse change if newfitness is worse
				double oldfitness = TSPSolution.TSPFitness(N, t, D);
				//System.out.println("old fitness:"+oldfitness);
				//temp.clear();
				//temp=logistics.Copy(t);
				ArrayList<Integer> clone1 = (ArrayList<Integer>)t.clone();
				temp=clone1;
				
				//small change applied to t and this fitness is taken as newfitness
				TSPSolution.Swap();

				double newfitness = TSPSolution.TSPFitness(N, t, D);
				//System.out.println("new fitness:"+newfitness);
				
				//compares the two fitness and if the old fitness is better,
				//copies the temp file back to t;
				if(newfitness>oldfitness)
					{			
						//t.clear();
						//t=logistics.Copy(temp);
						ArrayList<Integer> clone = (ArrayList<Integer>)temp.clone();
						t=clone;
					}
			
			}	
			return t;	
		}
	public static ArrayList<Integer> RMHC1(int iter, String file,int n){
		double[][] x;
		x = TSP.ReadArrayFile(file, " ");
		//TSPSolution.RandPerm(x);
		TSPSolution.RandPerm(n);
		ArrayList<Integer> t = new ArrayList<Integer>();
		
		t=TSPSolution.getCities();
		ArrayList<Integer> tBest = new ArrayList<Integer>(t);
		//tBest=t;
		double sol = TSPSolution.TSPFitness(t.size(), t, x);
		double bestSol = TSPSolution.TSPFitness(t.size(), t, x);
		for(int i=0;i<iter;i++)
	       {
			/*
			for(int j=0;j<t.size();++j){
				System.out.print(t.get(j)+" ");
			}
			System.out.println();
			*/
			TSPSolution.Swap();
	        tBest=TSPSolution.getCities();
	        bestSol=TSPSolution.TSPFitness(tBest.size(), tBest, x);
	        if (bestSol<sol){
	        	sol=bestSol;
	        	//t.clear();
	        	ArrayList<Integer> clone = (ArrayList<Integer>)tBest.clone();
				t=clone;
	        	
	        }
	            System.out.println(sol);
	       }
		
		//System.out.println(newSol);
		return t;
	}
	public static ArrayList<Integer> SHC(int iter, String file){
		double[][] x;
		x = TSP.ReadArrayFile(file, " ");
		TSPSolution.RandPerm(x);
		ArrayList<Integer> t = new ArrayList<Integer>();
		t=TSPSolution.getCities();
		
		double sol = TSPSolution.TSPFitness(t.size(), t, x);
		double bestSol = TSPSolution.TSPFitness(t.size(), t, x);
		for(int i=0;i<iter;i++)
	       {
			/*
			for(int j=0;j<t.size();++j){
				System.out.print(t.get(j)+" ");
			}
			System.out.println();
			*/
			
			TSPSolution.Swap();
	        t=TSPSolution.getCities();
	        bestSol=TSPSolution.TSPFitness(t.size(), t, x);
	        double T=4000;
	        double prAccept = 1/(1+Math.exp((bestSol-sol)/T));
	        //System.out.println(prAccept);
	        if (bestSol<sol){
	        	sol=bestSol;
	        }else if (Math.random()<prAccept){
	        	sol=bestSol;
	        }
	            System.out.println(sol);
	       }
		t=TSPSolution.getCities();
		//System.out.println(sol);
		return t;
	}
	public static ArrayList<Integer> SA(int iter, String file,double ti, double lamda, int n){
		double[][] x;
		x = TSP.ReadArrayFile(file, " ");
		//TSPSolution.RandPerm(x);
		TSPSolution.RandPerm(n);
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<Integer> t1 = new ArrayList<Integer>();
		t=TSPSolution.getCities();
		
		for(int i=0;i<iter;i++)
	       {
			/*
			for(int j=0;j<t.size();++j){
				System.out.print(t.get(j)+" ");
			}
			System.out.println();
			*/
			double sol=TSPSolution.TSPFitness(t.size(), t, x);
			TSPSolution.Swap();
	        t1=TSPSolution.getCities();
	        double bestSol=TSPSolution.TSPFitness(t1.size(), t1, x);
	        if (bestSol>sol){
	        	double p = Pr(sol,bestSol,ti);
	        	if(p<CS2004.UR(0,1)){
	        		
	        	}
	        	else{
	        		sol=bestSol;
	        		t=(ArrayList<Integer>) t1.clone();
	        	}
	        }
	        else{
	        	t=(ArrayList<Integer>) t1.clone();
	        }
	        ti=lamda*ti;
	            //System.out.println(sol);
	        System.out.println("ti is "+ti);
	        System.out.println("lamda is "+lamda);
	       }
		//t=TSPSolution.getCities();
		double newSol=TSPSolution.TSPFitness(t.size(), t, x);
		//System.out.println(newSol);
		return t;
	}
}
