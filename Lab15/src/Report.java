
public class Report {
private String file;
private String type;
private double fitness;
private double mst;
private double quality;

public Report(String file, String type, double fitness,double mst, double quality){
	this.file = file;
	this.type = type;
	this.fitness = fitness;
	this.mst = mst;
	this.quality = quality;
}
}
